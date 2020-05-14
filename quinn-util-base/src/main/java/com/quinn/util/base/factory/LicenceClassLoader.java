package com.quinn.util.base.factory;

import com.alibaba.fastjson.JSONObject;
import com.quinn.util.base.model.BaseResult;
import com.quinn.util.base.model.LicenceInfo;
import com.quinn.util.base.FileUtil;
import com.quinn.util.base.StringUtil;
import com.quinn.util.constant.NumberConstant;
import com.quinn.util.constant.enums.ExceptionEnum;
import com.quinn.util.constant.enums.LicenceExceptionType;
import com.quinn.util.constant.enums.SystemExitTypeEnum;
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
import java.util.Map;

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

    /**
     * 临时目录
     */
    private static final String TEMP_DIRECTORY_4_LICENCE_FILE = "tmp/quinn/licences/";

    /**
     * 初始化标记
     */
    private static boolean initFlag;

    /**
     * 许可证信息
     */
    private static LicenceInfo licenceInfo;

    /**
     * 自定义类路径
     */
    private static String licencePath;

    /**
     * 单例对象
     */
    private static LicenceClassLoader instance;

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
    public synchronized static BaseResult<LicenceInfo> init(URL url, ClassLoader realParent) {
        if (!LicenceClassLoader.initFlag) {
            LicenceClassLoader.licencePath = url.getPath();

            instance = new LicenceClassLoader(new URL[]{url}, realParent.getParent());
            instance.realParent = realParent;
            instance.realGrandParent = realParent.getParent();
            instance.addThisToParentClassLoader(realParent);

            File tempFile = new File(TEMP_DIRECTORY_4_LICENCE_FILE);
            if (tempFile.exists()) {
                FileUtil.deleteDir(tempFile);
            }

            try {
                tempFile.mkdirs();
                String tempPath = tempFile.getAbsolutePath() + File.separatorChar;
                String tempLic = tempPath + 0;
                FileUtil.copy(licencePath, tempLic);

                BaseResult<Map<Integer, String>> mapBaseResult =
                        FileUtil.splitMergeFile(licencePath, true, tempPath, 1);

                if (!mapBaseResult.isSuccess()) {
                    Integer errCode = LicenceExceptionType.FILE_DESTROYED.code + SystemExitTypeEnum.LICENCE_ERROR.code;
                    System.err.println(ExceptionEnum.LICENCE_EXCEPTION.name() + "[" + errCode + "]");
                    System.exit(errCode);
                }

                BaseResult<Key> keyRes = FileUtil.loadObject(tempPath + 1);
                if (!keyRes.isSuccess()) {
                    Integer errCode = LicenceExceptionType.FILE_DESTROYED.code + SystemExitTypeEnum.LICENCE_ERROR.code;
                    System.err.println(ExceptionEnum.LICENCE_EXCEPTION.name() + "[" + errCode + "]");
                    System.exit(errCode);
                }

                BaseResult<String> licenceStrRes = FileUtil.loadObjectWithSalt(tempPath + 2,
                        tempPath + 1, ALGORITHM);

                if (!licenceStrRes.isSuccess()) {
                    Integer errCode = LicenceExceptionType.FILE_DESTROYED.code + SystemExitTypeEnum.LICENCE_ERROR.code;
                    System.err.println(ExceptionEnum.LICENCE_EXCEPTION.name() + "[" + errCode + "]");
                    System.exit(errCode);
                }

                licenceInfo = JSONObject.parseObject(licenceStrRes.getData(), LicenceInfo.class);
                BaseResult validateRes = licenceInfo.validate();
                if (!validateRes.isSuccess()) {
                    Integer errCode = LicenceExceptionType.FILE_DESTROYED.code + SystemExitTypeEnum.LICENCE_ERROR.code;
                    System.err.println(ExceptionEnum.LICENCE_EXCEPTION.name() + "[" + errCode + "]");
                    System.exit(errCode);
                }

                String securityKey = licenceInfo.getSecurityKey();
                String md5OfKey = DigestUtils.md5Hex(keyRes.getData().getEncoded());
                licenceInfo.setSecurityKey(md5OfKey);
                String md5OfLic = DigestUtils.md5Hex(StringUtil.getBytes(JSONObject.toJSONString(licenceInfo)));
                if (StringUtil.isEmpty(securityKey) || !securityKey.equals(md5OfLic)) {
                    Integer errCode = LicenceExceptionType.FILE_DESTROYED.code + SystemExitTypeEnum.LICENCE_ERROR.code;
                    System.err.println(ExceptionEnum.LICENCE_EXCEPTION.name() + "[" + errCode + "]");
                    System.exit(errCode);
                }

                String[] classNames = licenceInfo.getClassNames();
                if (mapBaseResult.getData().size() != classNames.length + NumberConstant.INT_TWO) {
                    Integer errCode = LicenceExceptionType.FILE_DESTROYED.code + SystemExitTypeEnum.LICENCE_ERROR.code;
                    System.err.println(ExceptionEnum.LICENCE_EXCEPTION.name() + "[" + errCode + "]");
                    System.exit(errCode);
                }

                for (int i = 0; i < classNames.length; i++) {
                    try {
                        byte[] data = instance.loadByte(tempPath, i);
                        Class aClass = instance.defineClass(classNames[i], data, 0, data.length);
                        loadedClasses.put(classNames[i], aClass);
                    } catch (Exception e) {
                        Integer errCode = LicenceExceptionType.FILE_DESTROYED.code + SystemExitTypeEnum.LICENCE_ERROR.code;
                        System.err.println(ExceptionEnum.LICENCE_EXCEPTION.name() + "[" + errCode + "]");
                        System.exit(errCode);
                    }
                }

                LicenceClassLoader.initFlag = true;
            } finally {
                if (tempFile.exists()) {
                    FileUtil.deleteDir(tempFile);
                }
            }
        }

        return BaseResult.success(licenceInfo);
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
    private byte[] loadByte(String tempPath, int index) {
        FileInputStream fis = new FileInputStream(tempPath + (index + 3));
        int len = fis.available();
        byte[] data = new byte[len];
        fis.read(data);
        fis.close();
        return data;
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        if (loadedClasses.containsKey(name)) {
            return loadedClasses.get(name);
        } else {
            return realParent.loadClass(name);
        }
    }

}
