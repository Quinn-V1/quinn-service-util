package com.quinn.util.base.model;

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
     * 安全码
     */
    private String securityKey;

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 过期时间
     */
    private LocalDateTime expireDateTime;

    /**
     * 授权用户
     */
    private String author;

    /**
     * 用户数量
     */
    private Integer userNumber;

    /**
     * 涉及类名
     */
    private String[] classNames;

    /**
     * 授权模块
     */
    private String[] modules;

    /**
     * 校验证书是否合格
     *
     * @return
     */
    public BaseResult validate() {
        return BaseResult.SUCCESS;
    }

}
