package com.quinn.util.constant;

/**
 * 系统错误常量
 *
 * @author Qunhua.Liao
 * @since 2020-03-28
 */
public interface MessageTempConstants {

    /**
     * 分页参数没有提供
     */
    String DESC_LICENCE_EXCEPTION = "开发许可异常${【" + ParamName.EXCEPTION_TYPE + "}】，请联系管理员";

    /**
     * 分页参数没有提供
     */
    String DESC_PARAM_PAGE_NOT_PROVIDED = "分页参数${" + ParamName.PARAM_PARAM_NAME + "}没有提供";

    /**
     * 方法未实现
     */
    String DESC_PARAM_SHOULD_NOT_NULL = "必填参数【${" + ParamName.PARAM_PARAM_NAME + "}】不可为空";

    /**
     * 数据样式不匹配
     */
    String DESC_DATA_STYLE_NOT_MATCHED = "数据样式不匹配，期望类型：${" + ParamName.PARAM_EXPECT_CLASS
            + "}，实际值：${" + ParamName.PARAM_ACTUAL_VALUE + "}";

    /**
     * 数据未找到
     */
    String DESC_RESULT_NOT_FOUND = "未找到符合条件的${\" + ParamName.PARAM_DATA_TYPE + \"}数据";

    /**
     * 数据未找到
     */
    String DESC_RESULT_NOT_UNIQUE = "数据${" + ParamName.PARAM_DATA_TYPE + "}业务主键【${"
            + ParamName.PARAM_DATA_KEY + "}】不唯一（${" + ParamName.PARAM_RESULT_SIZE + "}）";

    /**
     * 操作数据未命中
     */
    String DESC_DATA_OPERATION_MISS_HINT = "${" + ParamName.PARAM_OPERATE_TYPE + "}数据${" + ParamName.PARAM_DATA_TYPE
            + "}【${" + ParamName.PARAM_DATA_KEY + "}】未命中";

    /**
     * 数据操作事务中断
     */
    String DESC_DATA_OPERATION_TRANSACTION_TERMINATED = "事务操作【${" + ParamName.PARAM_OPERATE_TYPE + "} ${"
            + ParamName.PARAM_DATA_TYPE + "}】被中断,总数量${" + ParamName.PARAM_TOTAL_SIZE
            + "}，中断位置${" + ParamName.PARAM_TERMINATE_POSITION + "}";

    /**
     * 太多数据被查询
     */
    String DESC_DATA_OPERATION_TOO_MANY_DELETE = "太多${" + ParamName.PARAM_DATA_TYPE + "}数据【${"
            + ParamName.PARAM_DATA_SIZE + "}】被删除，超出限制 ${" + ParamName.PARAM_LIMIT_SIZE + "}】";

    /**
     * 太多数据被查询
     */
    String DESC_DATA_OPERATION_TOO_MANY_QUERY = "太多${" + ParamName.PARAM_DATA_TYPE + "}数据【${"
            + ParamName.PARAM_DATA_SIZE + "}】被查询，超出限制 ${" + ParamName.PARAM_LIMIT_SIZE + "}】";

    /**
     * 方法未实现
     */
    String DESC_METHOD_NOT_SUPPORTED = "类【${" + ParamName.PARAM_CLASS_NAME + "}】中的方法【${"
            + ParamName.PARAM_METHOD_NAME + "}】尚未实现";

    /**
     * 字符集不支持
     */
    String DESC_CHARSET_NOT_SUPPORTED = "字符集【${" + ParamName.PARAM_CHARSET_NAME + "}】不被系统支持";

    /**
     * 文件夹创建失败
     */
    String DESC_FILE_DIRECTORY_CREATE_FAIL = "文件夹【${" + ParamName.PARAM_FILE_DIRECTORY + "}】创建失败";

    /**
     * 文件夹名称被文件占用
     */
    String DESC_FILE_DIRECTORY_OCCUPIED_BY_FILE = "文件夹名称【${" + ParamName.PARAM_FILE_DIRECTORY + "}】被文件占用";

    /**
     * 文件名称被文件夹占用
     */
    String DESC_FILE_OCCUPIED_BY_DIRECTORY = "文件名称【${" + ParamName.PARAM_FILE_DIRECTORY + "}】被文件夹占用";

    /**
     * 数据流操作失败
     */
    String DESC_FILE_STREAM_OPERATION_FAIL = "数据流-路径【${" + ParamName.PARAM_FILE_PATH + "}】${ "
            + ParamName.PARAM_OPERATE_TYPE + "}失败";

    /**
     * 文件不存在
     */
    String DESC_FILE_NOT_EXIST = "文件【${" + ParamName.PARAM_FILE_PATH + "}】不存在";

    /**
     * 文件删除失败
     */
    String DESC_FILE_DELETE_FAIL = "文件【${" + ParamName.PARAM_FILE_PATH + "}】删除失败";

    /**
     * 文件删除失败
     */
    String DESC_ALL_DATA_PASSED = "所有数据处理成功";

    /**
     * 占位参数
     */
    interface ParamName {

        /**
         * 参数名
         */
        String PARAM_PARAM_NAME = "paramName";

        /**
         * 期望类名
         */
        String PARAM_EXPECT_CLASS = "expectClass";

        /**
         * 实际值
         */
        String PARAM_ACTUAL_VALUE = "actualValue";

        /**
         * 数据类型
         */
        String PARAM_DATA_TYPE = "dataType";

        /**
         * 异常类型
         */
        String EXCEPTION_TYPE = "exceptionType";

        /**
         * 实际值
         */
        String PARAM_DATA_KEY = "dataKey";

        /**
         * 结果数量
         */
        String PARAM_RESULT_SIZE = "resultSize";

        /**
         * 操作类型
         */
        String PARAM_OPERATE_TYPE = "operateType";

        /**
         * 类名
         */
        String PARAM_CLASS_NAME = "className";

        /**
         * 方法名
         */
        String PARAM_METHOD_NAME = "methodName";

        /**
         * 字符集名
         */
        String PARAM_CHARSET_NAME = "charsetName";

        /**
         * 总数量
         */
        String PARAM_TOTAL_SIZE = "totalSize";

        /**
         * 中断位置
         */
        String PARAM_TERMINATE_POSITION = "terminatePosition";

        /**
         * 中断位置
         */
        String PARAM_FILE_DIRECTORY = "fileDirectory";

        /**
         * 中断位置
         */
        String PARAM_FILE_PATH = "filePath";

        /**
         * 中断位置
         */
        String PARAM_DATA_SIZE = "dataSize";

        /**
         * 中断位置
         */
        String PARAM_LIMIT_SIZE = "limitSize";

    }
}
