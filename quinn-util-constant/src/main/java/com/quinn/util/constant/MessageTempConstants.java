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
    String DESC_LICENCE_EXCEPTION = "开发许可异常【${" + CommonParamName.EXCEPTION_TYPE + "}】，请联系管理员";

    /**
     * 分页参数没有提供
     */
    String DESC_PARAM_PAGE_NOT_PROVIDED = "分页参数${" + CommonParamName.PARAM_PARAM_NAME + "}没有提供";

    /**
     * 方法未实现
     */
    String DESC_PARAM_SHOULD_NOT_NULL = "必填参数【${" + CommonParamName.PARAM_PARAM_NAME + "}】不可为空";

    /**
     * 数据样式不匹配
     */
    String DESC_DATA_STYLE_NOT_MATCHED = "数据样式不匹配，期望类型：${" + CommonParamName.PARAM_EXPECT_CLASS
            + "}，实际值：${" + CommonParamName.PARAM_ACTUAL_VALUE + "}";

    /**
     * 数据未找到
     */
    String DESC_RESULT_NOT_FOUND = "未找到符合条件的${\" + ParamName.PARAM_DATA_TYPE + \"}数据";

    /**
     * 数据未找到
     */
    String DESC_RESULT_NOT_UNIQUE = "数据${" + CommonParamName.PARAM_DATA_TYPE + "}业务主键【${"
            + CommonParamName.PARAM_DATA_KEY + "}】不唯一（${" + CommonParamName.PARAM_RESULT_SIZE + "}）";

    /**
     * 操作数据未命中
     */
    String DESC_DATA_OPERATION_MISS_HINT = "${" + CommonParamName.PARAM_OPERATE_TYPE + "}数据${" + CommonParamName.PARAM_DATA_TYPE
            + "}【${" + CommonParamName.PARAM_DATA_KEY + "}】未命中";

    /**
     * 数据操作事务中断
     */
    String DESC_DATA_OPERATION_TRANSACTION_TERMINATED = "事务操作【${" + CommonParamName.PARAM_OPERATE_TYPE + "} ${"
            + CommonParamName.PARAM_DATA_TYPE + "}】被中断,总数量${" + CommonParamName.PARAM_TOTAL_SIZE
            + "}，中断位置${" + CommonParamName.PARAM_TERMINATE_POSITION + "}";

    /**
     * 太多数据被查询
     */
    String DESC_DATA_OPERATION_TOO_MANY_DELETE = "太多${" + CommonParamName.PARAM_DATA_TYPE + "}数据【${"
            + CommonParamName.PARAM_DATA_SIZE + "}】被删除，超出限制 ${" + CommonParamName.PARAM_LIMIT_SIZE + "}】";

    /**
     * 太多数据被查询
     */
    String DESC_DATA_OPERATION_TOO_MANY_QUERY = "太多${" + CommonParamName.PARAM_DATA_TYPE + "}数据【${"
            + CommonParamName.PARAM_DATA_SIZE + "}】被查询，超出限制 ${" + CommonParamName.PARAM_LIMIT_SIZE + "}】";

    /**
     * 方法未实现
     */
    String DESC_METHOD_NOT_SUPPORTED = "类【${" + CommonParamName.PARAM_CLASS_NAME + "}】中的方法【${"
            + CommonParamName.PARAM_METHOD_NAME + "}】尚未实现";

    /**
     * 字符集不支持
     */
    String DESC_CHARSET_NOT_SUPPORTED = "字符集【${" + CommonParamName.PARAM_CHARSET_NAME + "}】不被系统支持";

    /**
     * 字符集不支持
     */
    String DESC_STRATEGY_NOT_SUPPORTED = "策略【${" + CommonParamName.STRATEGY_TYPE + "}】不被系统支持";

    /**
     * 文件夹创建失败
     */
    String DESC_FILE_DIRECTORY_CREATE_FAIL = "文件夹【${" + CommonParamName.PARAM_FILE_DIRECTORY + "}】创建失败";

    /**
     * 文件夹名称被文件占用
     */
    String DESC_FILE_DIRECTORY_OCCUPIED_BY_FILE = "文件夹名称【${" + CommonParamName.PARAM_FILE_DIRECTORY + "}】被文件占用";

    /**
     * 文件名称被文件夹占用
     */
    String DESC_FILE_OCCUPIED_BY_DIRECTORY = "文件名称【${" + CommonParamName.PARAM_FILE_DIRECTORY + "}】被文件夹占用";

    /**
     * 数据流操作失败
     */
    String DESC_FILE_STREAM_OPERATION_FAIL = "数据流-路径【${" + CommonParamName.PARAM_FILE_PATH + "}】${ "
            + CommonParamName.PARAM_OPERATE_TYPE + "}失败";

    /**
     * 文件不存在
     */
    String DESC_FILE_NOT_EXIST = "文件【${" + CommonParamName.PARAM_FILE_PATH + "}】不存在";

    /**
     * 文件删除失败
     */
    String DESC_FILE_DELETE_FAIL = "文件【${" + CommonParamName.PARAM_FILE_PATH + "}】删除失败";

    /**
     * 所有数据处理成功
     */
    String DESC_ALL_DATA_PASSED = "所有数据处理成功";

    /**
     * 未发生任何事情
     */
    String DESC_NOTHING_HAPPENED = "未发生任何事情";

    /**
     * 主数据丢失
     */
    String DESC_MASTER_DATA_LOST = "【${" + CommonParamName.PARAM_DATA_TYPE + "}】主数据【${"
            + CommonParamName.PARAM_DATA_KEY + "}】没有找到";
}
