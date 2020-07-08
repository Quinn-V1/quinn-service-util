package com.quinn.util.base.enums;

import com.quinn.util.base.handler.EnumMessageResolver;
import com.quinn.util.constant.MessageEnumFlag;
import com.quinn.util.constant.StringConstant;

import java.util.Locale;

import static com.quinn.util.constant.CommonMessageConstant.*;
import static com.quinn.util.constant.CommonParamName.*;

/**
 * 系统错误枚举类
 *
 * @author Qunhua.Liao
 * @since 2020-03-28
 */
public enum CommonMessageEnum implements MessageEnumFlag {

    // 授权：开发许可未获取
    LICENCE_EXCEPTION(DESC_LICENCE_EXCEPTION, EXCEPTION_TYPE),

    // 参数：分页参数未找到
    PARAM_PAGE_NOT_PROVIDED(DESC_PARAM_PAGE_NOT_PROVIDED, PARAM_PARAM_NAME),

    // 参数：必填参数为空
    PARAM_SHOULD_NOT_NULL(DESC_PARAM_SHOULD_NOT_NULL, PARAM_PARAM_NAME),

    // 结果：数据不匹配
    DATA_STYLE_NOT_MATCHED(DESC_DATA_STYLE_NOT_MATCHED, PARAM_EXPECT_CLASS, PARAM_ACTUAL_VALUE),

    // 结果：数据未找到
    RESULT_NOT_FOUND(DESC_RESULT_NOT_FOUND, PARAM_DATA_TYPE),

    // 结果：结果不唯一
    RESULT_NOT_UNIQUE(DESC_RESULT_NOT_UNIQUE, PARAM_DATA_TYPE, PARAM_DATA_KEY, PARAM_RESULT_SIZE),

    // 数据操作：被中断
    DATA_OPERATION_TRANSACTION_TERMINATED(DESC_DATA_OPERATION_TRANSACTION_TERMINATED, PARAM_OPERATE_TYPE
            , PARAM_DATA_TYPE, PARAM_TOTAL_SIZE, PARAM_TERMINATE_POSITION),

    // 数据操作：未命中
    DATA_OPERATION_MISS_HINT(DESC_DATA_OPERATION_MISS_HINT, PARAM_OPERATE_TYPE, PARAM_DATA_TYPE, PARAM_DATA_KEY),

    // 数据操作： 特定状态数据不支持操作
    DATA_OPERATION_NOT_SUPPORT_OF_STATUS(DESC_DATA_OPERATION_NOT_SUPPORT_OF_STATUS, PARAM_DATA_STATUS, PARAM_DATA_TYPE, PARAM_OPERATE_TYPE),

    // 数据操作：太多数据被删除
    DATA_OPERATION_TOO_MANY_DELETE(DESC_DATA_OPERATION_TOO_MANY_DELETE, PARAM_DATA_TYPE, PARAM_DATA_SIZE, PARAM_LIMIT_SIZE),

    // 数据操作：太多数据被查询
    DATA_OPERATION_TOO_MANY_QUERY(DESC_DATA_OPERATION_TOO_MANY_QUERY, PARAM_DATA_TYPE, PARAM_DATA_SIZE, PARAM_LIMIT_SIZE),

    // 文件夹创建失败
    FILE_DIRECTORY_CREATE_FAIL(DESC_FILE_DIRECTORY_CREATE_FAIL, PARAM_FILE_DIRECTORY),

    // 文件夹名称被文件占用
    FILE_DIRECTORY_OCCUPIED_BY_FILE(DESC_FILE_DIRECTORY_OCCUPIED_BY_FILE, PARAM_FILE_DIRECTORY),

    // 文件夹名称被文件占用
    FILE_OCCUPIED_BY_DIRECTORY(DESC_FILE_OCCUPIED_BY_DIRECTORY, PARAM_FILE_PATH),

    // 流读写失败
    FILE_STREAM_OPERATION_FAIL(DESC_FILE_STREAM_OPERATION_FAIL, PARAM_FILE_PATH, PARAM_OPERATE_TYPE),

    // 文件不存在
    FILE_NOT_EXIST(DESC_FILE_NOT_EXIST, PARAM_FILE_PATH),

    // 文件删除失败
    FILE_DELETE_FAIL(DESC_FILE_DELETE_FAIL, PARAM_FILE_PATH),

    // 方法未实现
    METHOD_NOT_SUPPORTED(DESC_METHOD_NOT_SUPPORTED, PARAM_CLASS_NAME, PARAM_METHOD_NAME),

    // 字符集不支持
    CHARSET_NOT_SUPPORTED(DESC_CHARSET_NOT_SUPPORTED, PARAM_CHARSET_NAME),

    // 策略不被支持
    STRATEGY_NOT_SUPPORTED(DESC_STRATEGY_NOT_SUPPORTED, STRATEGY_TYPE),

    // 主数据丢失
    MASTER_DATA_LOST(DESC_MASTER_DATA_LOST, PARAM_DATA_TYPE, PARAM_DATA_KEY),

    // 关键信息丢失
    KEY_INFO_MISS(DESC_KEY_INFO_MISS, PARAM_PROP_NAME, PARAM_DATA_TYPE),

    // 方法缺失
    METHOD_NOT_FOUND(DESC_METHOD_NOT_FOUND, PARAM_METHOD_NAME, PARAM_CLASS_NAME),

    // Bean 缺失
    MANDATORY_BEAN_MISS(DESC_MANDATORY_BEAN_MISS, PARAM_BEAN_NAME, PARAM_CLASS_NAME),

    // 文件格式与【${fileType}】格式不匹配
    FILE_FORMAT_NOT_CORRECT(DESC_FILE_FORMAT_NOT_CORRECT, PARAM_KEY_FILE_TYPE),

    // 文件格式与【${fileType}】格式不匹配
    SYSTEM_ERROR(DESC_SYSTEM_ERROR),

    ;

    /**
     * 默认描述
     */
    public final String defaultDesc;

    /**
     * 参数名
     */
    public final String[] paramNames;

    /**
     * 构造器
     *
     * @param defaultDesc 默认描述
     */
    CommonMessageEnum(String defaultDesc, String... paramNames) {
        this.defaultDesc = defaultDesc;
        this.paramNames = paramNames;
    }

    @Override
    public String defaultDesc() {
        return defaultDesc;
    }

    @Override
    public String[] paramNames() {
        return paramNames;
    }

    @Override
    public String key() {
        return StringConstant.DATA_TYPE_OF_MESSAGE + StringConstant.CHAR_COLON + name()
                + StringConstant.CHAR_POUND_SIGN + StringConstant.NONE_OF_DATA;
    }

    static {
        EnumMessageResolver.addContent(Locale.SIMPLIFIED_CHINESE, CommonMessageEnum.values());
    }

}
