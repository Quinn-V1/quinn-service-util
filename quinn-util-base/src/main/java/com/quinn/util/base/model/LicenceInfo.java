package com.quinn.util.base.model;

import com.quinn.util.base.CollectionUtil;
import com.quinn.util.base.StringUtil;
import com.quinn.util.base.enums.CommonMessageEnum;
import com.quinn.util.constant.enums.LicenceExceptionType;
import com.quinn.util.constant.enums.SystemExitTypeEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 许可证信息
 *
 * @author Qunhua.Liao
 * @since 2020-04-18
 */
@Setter
@Getter
public class LicenceInfo {

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 授权环境
     */
    private String profile;

    /**
     * 用户数量
     */
    private Integer licenceNumber;

    /**
     * 过期时间
     */
    private LocalDateTime expireDateTime;

    /**
     * 授权用户
     */
    private String author;

    /**
     * 安全码
     */
    private String securityKey;

    /**
     * 涉及类名
     */
    private String[] classNames;

    /**
     * 授权模块
     */
    private String[] modules;

    /**
     * 授权模块
     */
    private String[] supportLangCodes;

    /**
     * 校验证书是否合格
     *
     * @return
     */
    public BaseResult validate() {
        if (StringUtil.isEmpty(securityKey) || StringUtil.isEmpty(companyName) || expireDateTime == null
                || licenceNumber == null || CollectionUtil.isEmpty(classNames) || CollectionUtil.isEmpty(modules)
                || StringUtil.isEmpty(profile) || StringUtil.isEmpty(author)) {
            Integer errCode = LicenceExceptionType.FILE_DESTROYED.code + SystemExitTypeEnum.LICENCE_ERROR.code;
            System.err.println(CommonMessageEnum.LICENCE_EXCEPTION.name() + "[" + errCode + "]");
            System.exit(errCode);
        }

        if (expireDateTime.isBefore(LocalDateTime.now())) {
            Integer errCode = LicenceExceptionType.AUTHORIZE_EXPIRE.code + SystemExitTypeEnum.LICENCE_ERROR.code;
            System.err.println(CommonMessageEnum.LICENCE_EXCEPTION.name() + "[" + errCode + "]");
            System.exit(errCode);
        }

        return BaseResult.SUCCESS;
    }

    /**
     * 获取支持语言
     *
     * @return 支持语言
     */
    public String[] getSupportLangCodes() {
        return supportLangCodes;
    }
}
