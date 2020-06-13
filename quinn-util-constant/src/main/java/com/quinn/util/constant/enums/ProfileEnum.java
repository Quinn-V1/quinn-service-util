package com.quinn.util.constant.enums;

/**
 * 应用环境
 *
 * @author Qunhua.Liao
 * @since 2020-03-31
 */
public enum ProfileEnum {

    // 开发环境
    DEV("开发环境", 10),

    // 内部测试
    TEST("内部测试", 90),

    // 用户测试
    QAS("用户测试", 180),

    // 仿真测试
    UAT("仿真测试", 360),

    // 生产环境
    PRD("生产环境", Integer.MAX_VALUE),

    ;

    /**
     * 描述
     */
    public final String desc;

    /**
     * 编码
     */
    public final int level;

    ProfileEnum(String desc, int level) {
        this.desc = desc;
        this.level = level;
    }

    /**
     * 根据名称获取等级
     *
     * @param name
     * @return
     */
    public static int codeByName(String name, ProfileEnum defLevel) {
        if (name == null || "".equals(name.length())) {
            return defLevel.level;
        }

        for (ProfileEnum profile : ProfileEnum.values()) {
            if (profile.name().equals(name.toUpperCase())) {
                return profile.level;
            }
        }

        return defLevel.level;
    }

    /**
     * 判断前者环境等级是否比后者低
     *
     * @param srcName 源环境名
     * @param tarName 目标环境名
     * @return 低：true
     */
    public static boolean higher(String srcName, String tarName) {
        int srcCode = codeByName(srcName, PRD);
        int targetCode = codeByName(tarName, DEV);
        return srcCode - targetCode > 0;
    }

}
