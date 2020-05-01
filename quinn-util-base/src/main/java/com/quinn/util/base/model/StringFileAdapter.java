package com.quinn.util.base.model;

import com.quinn.util.base.api.FileAdapter;
import com.quinn.util.base.util.StringUtil;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * 字符串文件适配器
 *
 * @author Qunhua.Liao
 * @since 2020-05-01
 */
public class StringFileAdapter implements FileAdapter<String> {

    /**
     * 文件内容
     */
    private String content;

    /**
     * 文件类型
     */
    private String fileType;

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 文件保存路径（如果是本地保存，此属性很有必要）
     */
    private String filePath;

    /**
     * 文件内容、类型、名称是保存一个文件的必要属性：加入带参构造器
     *
     * @param content  内容
     * @param fileType 类型
     * @param fileName 名称
     */
    public StringFileAdapter(String content, String fileType, String fileName) {
        this.content = content;
        this.fileType = fileType;
        this.fileName = fileName;
    }

    /**
     * 对于本地文件系统来说，以下4个参数都有必要：不同的业务文件存在不同的路径下面，所以文件路径在某些场景下也是必要的
     *
     * @param content  内容
     * @param fileType 类型
     * @param fileName 名称
     * @param filePath 路径
     */
    public StringFileAdapter(String content, String fileType, String fileName, String filePath) {
        this(content, fileType, fileName);
        this.filePath = filePath;
    }

    @Override
    public byte[] getBytes() {
        return StringUtil.getBytes(this.content);
    }

    @Override
    public String getFilename() {
        return fileName;
    }

    @Override
    public Long getSize() {
        byte[] bytes = this.getBytes();
        return bytes == null ? 0L : (long) bytes.length * 1L;
    }

    @Override
    public String getFileType() {
        return fileType;
    }

    @Override
    public InputStream getInputStream() {
        try {
            return new ByteArrayInputStream(this.getBytes());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String getFilePath() {
        return this.filePath;
    }

    @Override
    public String realObject() {
        return this.content;
    }

}
