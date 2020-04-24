package com.quinn.util.base.util;

import com.quinn.util.base.api.LargeStringFilePartHandler;
import com.quinn.util.base.exception.file.FileOperationException;
import com.quinn.util.base.model.BaseResult;
import com.quinn.util.constant.CharConstant;
import com.quinn.util.constant.enums.ExceptionEnum;
import com.quinn.util.constant.enums.FileOperationTypeEnum;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.security.Key;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 文件工具类
 *
 * @author Qunhua.Liao
 * @since 2020-04-17
 */
public final class FileUtil {

    private FileUtil() {
    }

    /**
     * 是否为文件分隔符
     *
     * @param cha 字符
     * @return 是否为文件分隔符
     */
    public static boolean isFileSeparator(char cha) {
        return CharConstant.SLASH == cha || CharConstant.BACKS_LASH == cha;
    }

    /**
     * 拼接路径
     *
     * @param basePath    源路径
     * @param appendPaths 拼接路径
     * @return 最终路径
     */
    public static String appendFilePath(String basePath, String... appendPaths) {
        StringBuilder query = new StringBuilder();
        if (appendPaths == null || appendPaths.length == 0) {
            return basePath;
        }

        if (StringUtil.isNotEmpty(basePath)) {
            query.append(basePath);
        }

        for (String appendPath : appendPaths) {
            if (StringUtil.isNotEmpty(appendPath)) {
                if (!isFileSeparator(query.charAt(query.length() - 1))) {
                    query.append(File.separator);
                }

                if (isFileSeparator(appendPath.charAt(0))) {
                    query.append(appendPath.substring(1));
                } else {
                    query.append(appendPath);
                }
            }
        }

        return query.toString();
    }

    /**
     * 加载文件
     *
     * @param fileName 文件名
     * @return 文件文本内容
     */
    public static BaseResult<String> loadFile(String fileName, boolean lineFlag, boolean trimFlag) {
        File file = new File(fileName);
        InputStream inputStream;
        try {
            if (file.exists()) {
                inputStream = new FileInputStream(file);
            } else {
                inputStream = FileUtil.class.getClassLoader().getResourceAsStream(fileName);
            }
        } catch (Exception e) {
            return BaseResult.fail(e.getMessage());
        }

        if (inputStream == null) {
            return BaseResult.fail("");
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder sb = new StringBuilder();
        String line;

        try {
            while ((line = br.readLine()) != null) {
                if (trimFlag) {
                    line = line.trim();
                }

                sb.append(line);

                if (lineFlag) {
                    sb.append("\n");
                }
            }
            return BaseResult.success(sb.toString());
        } catch (Exception e) {
            return BaseResult.fail(e.getMessage());
        } finally {
            StreamUtil.closeQuietly(inputStream);
        }
    }

    /**
     * 写文件
     *
     * @param content  文件内容
     * @param fileName 文件名
     * @return
     */
    public static BaseResult storeFile(String content, String fileName) {
        File file = new File(fileName);
        FileWriter fileWriter = null;
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            fileWriter = new FileWriter(file, true);
            fileWriter.write(content);
            return new BaseResult();
        } catch (Exception e) {
            return BaseResult.fail(e.getMessage());
        } finally {
            StreamUtil.closeQuietly(fileWriter);
        }
    }

    /**
     * 读取大文件
     *
     * @param filePath 文件路径
     * @param handler  大文件处理器
     */
    public static BaseResult readLargeFile(String filePath, LargeStringFilePartHandler handler) {
        FileInputStream inputStream = null;
        Scanner sc = null;
        try {
            inputStream = new FileInputStream(filePath);
            sc = new Scanner(inputStream, "UTF-8");
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                handler.handle(line);
            }
            return new BaseResult();
        } catch (Exception e) {
            return BaseResult.fail(e.getMessage());
        } finally {
            StreamUtil.closeQuietly(inputStream);
            StreamUtil.closeQuietly(sc);
        }
    }

    /**
     * 读取大文件2
     *
     * @param filePath
     */
    public static BaseResult readLargeFile2(String filePath, LargeStringFilePartHandler handler) {
        File file = new File(filePath);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file), 5 * 1024 * 1024);
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                handler.handle(tempString);
            }
            return new BaseResult();
        } catch (IOException e) {
            return BaseResult.fail(e.getMessage());
        } finally {
            StreamUtil.closeQuietly(reader);
        }
    }

    /**
     * 写大文件
     *
     * @param content  文件内容
     * @param fileName 文件名
     * @return
     */
    public static BaseResult storeLargeFile(String content, String fileName) {
        File file = new File(fileName);
        BufferedWriter bw = null;
        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            bw = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));
            bw.write(content);

            return BaseResult.success(fileName);
        } catch (IOException e) {
            return BaseResult.fail(e.getMessage());
        } finally {
            StreamUtil.closeQuietly(bw);
        }
    }

    /**
     * 将合并后的文件重新拆分
     *
     * @param mergeName 合并文件名
     * @return 拆分文件名 (序号->文件名)
     */
    public static BaseResult<Map<Integer, String>> splitMergeFile(String mergeName, boolean useNio, String basePath, int unitLength) {
        if (StringUtil.isEmpty(mergeName)) {
            return BaseResult.fail("参数不完整");
        }

        File file = new File(mergeName);
        if (!file.exists()) {
            return BaseResult.fail("文件不存在");
        }

        File baseFile = new File(basePath);
        if (!baseFile.exists()) {
            baseFile.mkdirs();
        }

        Map<Integer, String> res = new HashMap<>();
        if (useNio) {
            FileChannel resultFileChannel = null;
            FileChannel blk = null;

            try {
                blk = new FileInputStream(mergeName).getChannel();
                ByteBuffer bbf = ByteBuffer.allocate(Long.BYTES);
                int readCount;
                int fileNum = 0;

                while ((blk.read(bbf)) > 0) {
                    fileNum++;

                    bbf.flip();
                    long nameLen = bbf.getLong();

                    byte[] nameByte = new byte[(int) nameLen];
                    ByteBuffer nameWrap = ByteBuffer.wrap(nameByte);
                    readCount = blk.read(nameWrap);
                    if (readCount <= 0) {
                        return BaseResult.fail("文件格式不匹配");
                    }
                    String name = new String(nameByte, "utf-8");

                    bbf.clear();
                    readCount = blk.read(bbf);
                    if (readCount <= 0) {
                        return BaseResult.fail("文件格式不匹配");
                    }

                    bbf.flip();
                    long contentLen = bbf.getLong();
                    if (contentLen <= 0) {
                        continue;
                    }

                    File filePart = new File(baseFile, fileNum + "");
                    if (filePart.exists()) {
                        filePart.delete();
                    }

                    res.put(fileNum, name);
                    FileOutputStream outputStream = new FileOutputStream(filePart, false);
                    resultFileChannel = outputStream.getChannel();
                    bbf.clear();

                    resultFileChannel.transferFrom(blk, resultFileChannel.size(), contentLen);

                    resultFileChannel.close();
                }
                return BaseResult.success(res);
            } catch (Exception e) {
                return BaseResult.fail(e.getMessage());
            } finally {
                StreamUtil.closeQuietly(blk, resultFileChannel);
            }
        } else {
            BufferedInputStream inputStream = null;
            BufferedOutputStream outputStream = null;
            try {
                inputStream = new BufferedInputStream(new FileInputStream(mergeName));
                int fileNum = 0;
                byte[] lengthByte = new byte[Long.BYTES];
                byte[] contentByte = new byte[unitLength];
                int readCount = 0;

                while (inputStream.read(lengthByte) > 0) {
                    long nameLen = NumberUtil.bytesToLong(lengthByte);

                    byte[] nameByte = new byte[(int) nameLen];
                    if (inputStream.read(nameByte) <= 0) {
                        return BaseResult.fail("文件格式不匹配");
                    }
                    String name = new String(nameByte, "utf-8");

                    if (inputStream.read(lengthByte) <= 0) {
                        return BaseResult.fail("文件格式不匹配");
                    }

                    long contentLen = NumberUtil.bytesToLong(lengthByte);
                    if (contentLen <= 0) {
                        continue;
                    }

                    fileNum++;
                    File filePart = new File(baseFile, fileNum + "");
                    res.put(fileNum, name);
                    outputStream = new BufferedOutputStream(new FileOutputStream(filePart));

                    int times = (int) (contentLen / unitLength);
                    int left = (int) (contentLen % unitLength);
                    for (int i = 0; i < times; i++) {
                        readCount = inputStream.read(contentByte);
                        outputStream.write(contentByte, 0, readCount);
                    }

                    if (left > 0) {
                        readCount = inputStream.read(contentByte, 0, left);
                        outputStream.write(contentByte, 0, readCount);
                    }

                    outputStream.flush();
                    outputStream.close();
                }
                return BaseResult.success(res);
            } catch (Exception e) {
                return BaseResult.fail(e.getMessage());
            } finally {
                StreamUtil.closeQuietly(inputStream, outputStream);
            }
        }
    }

    /**
     * 读取对象
     *
     * @param fileName 保存文件名
     * @param keyPath  密钥编码
     */
    public static <T> BaseResult<T> loadObjectWithSalt(String fileName, String keyPath, String algorithm) {
        BaseResult<Key> keyRes = loadObject(keyPath);
        if (!keyRes.isSuccess()) {
            return BaseResult.fail(keyRes.getMessage());
        }

        ObjectInputStream in = null;
        try {
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.DECRYPT_MODE, keyRes.getData());
            in = new ObjectInputStream(new CipherInputStream(
                    new BufferedInputStream(new FileInputStream(fileName)), cipher));

            T t = (T) in.readObject();
            return BaseResult.success(t);
        } catch (Exception e) {
            return BaseResult.fail(e.getMessage());
        } finally {
            StreamUtil.closeQuietly(in);
        }
    }

    /**
     * 保存对象
     *
     * @param fileName 保存文件名
     */
    public static <T> BaseResult<T> loadObject(String fileName) {
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fileName)));
            T t = (T) in.readObject();
            BaseResult result = BaseResult.success(t);

            result.setMessage(fileName);
            return result;
        } catch (Exception e) {
            return BaseResult.fail(e.getMessage());
        } finally {
            StreamUtil.closeQuietly(in);
        }
    }

    /**
     * 保存对象
     *
     * @param object   待保存对象
     * @param fileName 保存文件名
     */
    public static BaseResult storeObject(Object object, String fileName) {
        ObjectOutputStream os = null;
        try {
            os = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)));
            os.writeObject(object);
            return new BaseResult();
        } catch (Exception e) {
            return BaseResult.fail(e.getMessage());
        } finally {
            StreamUtil.closeQuietly(os);
        }
    }

    /**
     * 文件拷贝
     *
     * @param source 源路径
     * @param dest   目标路径
     */
    public static void copyNio(String source, String dest) {
        FileChannel input = null;
        FileChannel output = null;

        try {
            input = new FileInputStream(new File(source)).getChannel();
            output = new FileOutputStream(new File(dest)).getChannel();
            output.transferFrom(input, 0, input.size());
        } catch (Exception e) {
            throw new FileOperationException()
                    .addParam(ExceptionEnum.FILE_STREAM_OPERATION_FAIL.paramNames[0], source + "->" + dest)
                    .addParam(ExceptionEnum.FILE_STREAM_OPERATION_FAIL.paramNames[1], FileOperationTypeEnum.COPY.name())
                    .exception();
        } finally {
            StreamUtil.closeQuietly(input, output);
        }
    }

    /**
     * 删除文件级文件夹
     *
     * @param dir 文件路径
     * @return 是否成功
     */
    public static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }

}
