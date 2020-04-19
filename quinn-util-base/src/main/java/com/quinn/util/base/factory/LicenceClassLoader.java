package com.quinn.util.base.factory;

import com.alibaba.fastjson.JSONObject;
import com.quinn.util.base.exception.LicenceUnauthorizedException;
import com.quinn.util.base.model.BaseResult;
import com.quinn.util.base.model.LicenceInfo;
import com.quinn.util.base.util.FileUtil;
import com.quinn.util.base.util.StringUtil;
import com.quinn.util.constant.NumberConstant;
import com.quinn.util.constant.enums.ExceptionEnums;
import com.quinn.util.constant.enums.LicenceExceptionType;
import lombok.SneakyThrows;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandlerFactory;
import java.security.Key;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 个性化类加载器
 *
 * @author Qunhua.Liao
 * @since 2020-04-18
 */
public class LicenceClassLoader extends URLClassLoader {

    /**
     * 加密算法
     */
    private static final String ALGORITHM = "DES";

    private static final String TEMP_DIRECTORY_4_LICENCE_FILE = "tmp/quinn/licences/";

    /**
     * 初始化标记
     */
    private static boolean initFlag;

    /**
     * 自定义类路径
     */
    private static String classPath;

    /**
     * 单例对象
     */
    private static LicenceClassLoader instance;

    /**
     * 管控类
     */
    private static Set<String> licenceClasses = new HashSet<>();

    /**
     * 已经加载的类
     */
    private static Map<String, Class<?>> loadedClasses = new HashMap<>();

    public LicenceClassLoader(URL[] urls) {
        super(urls);
    }

    public LicenceClassLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
        this.realParent = parent;
    }

    public LicenceClassLoader(URL[] urls, ClassLoader parent, URLStreamHandlerFactory factory) {
        super(urls, parent, factory);
        this.realParent = parent;
    }

    public static LicenceClassLoader getInstance() {
        return instance;
    }

    /**
     * 真实的父加载器
     */
    private ClassLoader realParent;

    /**
     * 真实的爷加载器
     */
    private ClassLoader realGrandParent;

    /**
     * 初始化
     *
     * @param url 初始化条件
     */
    public synchronized static void init(URL url, ClassLoader realParent) {
        if (!LicenceClassLoader.initFlag) {
            LicenceClassLoader.classPath = url.getPath();

            instance = new LicenceClassLoader(new URL[]{url}, realParent.getParent());
            instance.realParent = realParent;
            instance.realGrandParent = realParent.getParent();
            instance.addThisToParentClassLoader(realParent);

            File tempFile = new File(TEMP_DIRECTORY_4_LICENCE_FILE);
            if (tempFile.exists()) {
                tempFile.delete();
            }
            try {
                tempFile.mkdirs();

                String srcTemp = TEMP_DIRECTORY_4_LICENCE_FILE + 0;
                FileUtil.copyNio(classPath, srcTemp);
                BaseResult<Map<Integer, String>> mapBaseResult =
                        FileUtil.splitMergeFile(srcTemp, true, TEMP_DIRECTORY_4_LICENCE_FILE, 1);

                if (!mapBaseResult.isSuccess()) {
                    throw new LicenceUnauthorizedException()
                            .addParam(ExceptionEnums.LICENCE_EXCEPTION.paramNames[0], LicenceExceptionType.FILE_DESTROYED.code)
                            .exception();
                }

                BaseResult<Key> keyRes = FileUtil.loadObject(TEMP_DIRECTORY_4_LICENCE_FILE + 1);
                if (!keyRes.isSuccess()) {
                    throw new LicenceUnauthorizedException()
                            .addParam(ExceptionEnums.LICENCE_EXCEPTION.paramNames[0], LicenceExceptionType.FILE_DESTROYED.code)
                            .exception();
                }

                BaseResult<String> licenceStrRes = FileUtil.loadObjectWithSalt(TEMP_DIRECTORY_4_LICENCE_FILE + 2,
                        TEMP_DIRECTORY_4_LICENCE_FILE + 1, ALGORITHM);

                if (!licenceStrRes.isSuccess()) {
                    throw new LicenceUnauthorizedException()
                            .addParam(ExceptionEnums.LICENCE_EXCEPTION.paramNames[0], LicenceExceptionType.FILE_DESTROYED.code)
                            .exception();
                }

                LicenceInfo licenceInfo = JSONObject.parseObject(licenceStrRes.getData(), LicenceInfo.class);
                BaseResult validateRes = licenceInfo.validate();
                if (!validateRes.isSuccess()) {
                    throw new LicenceUnauthorizedException()
                            .getMessageProp().ofPrevProp(validateRes.getMessageProp())
                            .exception();
                }

                String securityKey = licenceInfo.getSecurityKey();
                String md5OfKey = DigestUtils.md5Hex(keyRes.getData().getEncoded());
                licenceInfo.setSecurityKey(md5OfKey);
                String md5OfLic = DigestUtils.md5Hex(StringUtil.getBytes(JSONObject.toJSONString(licenceInfo)));
                if (StringUtil.isEmpty(securityKey) || !securityKey.equals(md5OfLic)) {
                    throw new LicenceUnauthorizedException()
                            .addParam(ExceptionEnums.LICENCE_EXCEPTION.paramNames[0], LicenceExceptionType.FILE_DESTROYED.code)
                            .exception();
                }

                String[] classNames = licenceInfo.getClassNames();
                if (mapBaseResult.getData().size() != classNames.length + NumberConstant.INT_TWO) {
                    throw new LicenceUnauthorizedException()
                            .addParam(ExceptionEnums.LICENCE_EXCEPTION.paramNames[0], LicenceExceptionType.FILE_DESTROYED.code)
                            .exception();
                }

                for (int i = 0; i < classNames.length; i++) {
                    try {
                        byte[] data = instance.loadByte(i);
                        Class aClass = instance.defineClass(classNames[i], data, 0, data.length);
                        loadedClasses.put(classNames[i], aClass);
                    } catch (Exception e) {
                        throw new LicenceUnauthorizedException()
                                .addParam(ExceptionEnums.LICENCE_EXCEPTION.paramNames[0], LicenceExceptionType.FILE_DESTROYED.code)
                                .exception();
                    }
                }

                LicenceClassLoader.initFlag = true;
            } finally {
                if (tempFile.exists()) {
                    tempFile.delete();
                }
            }
        }
    }

    /**
     * 将this替换为指定classLoader的parent ClassLoader
     *
     * @param classLoader 原来的父类加载器
     */
    @SneakyThrows
    private void addThisToParentClassLoader(ClassLoader classLoader) {
        Field field = ClassLoader.class.getDeclaredField("parent");
        field.setAccessible(true);
        field.set(classLoader, this);
    }

    @SneakyThrows
    private byte[] loadByte(int index) {
        FileInputStream fis = new FileInputStream(TEMP_DIRECTORY_4_LICENCE_FILE + (index + 3));
        int len = fis.available();
        byte[] data = new byte[len];
        fis.read(data);
        fis.close();
        return data;
    }

    @Override
    protected Class<?> findClass(String name) {
        Class<?> aClass = loadedClasses.get(name);
        if (aClass != null) {
            return aClass;
        }
        return null;
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        if (licenceClasses.contains(name)) {
            return super.loadClass(name);
        } else {
            return realParent.loadClass(name);
        }
    }

}
