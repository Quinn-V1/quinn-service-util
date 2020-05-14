package com.quinn.util.database.enums;

import com.quinn.util.base.StringUtil;

/**
 * 数据库类型枚举
 *
 * @author Qunhua.Liao
 * @since 2020-05-14
 */
public enum DataBaseTypeEnum {

    // Mysql
    MYSQL("mysql", "com.mysql.jdbc.Driver", "jdbc:mysql", "mysql"),

    // Oracle
    ORACLE("oracle", "oracle.jdbc.driver.OracleDriver", "jdbc:oracle", "oracle"),

    // SqlServer 2005
    SQL_SERVER_2005("sqlserver2005", "com.microsoft.jdbc.sqlserver.SQLServerDriver",
            "jdbc:microsoft:sqlserver", "com.microsoft.sqlserver.jdbc.sqlserverdriver"),

    // SqlSever MICR
    SQL_SERVER_MICR("sqlserver", "com.microsoft.sqlserver.jdbc.SQLServerDriver", "jdbc:sqlserver",
            "microsoft"),

    // SqlServer JTDS
    SQL_SERVER_JTDS("sqlserver", "net.sourceforge.jtds.jdbc.Driver", "jdbc:jtds:sqlserver",
            "jtds"),

    // Post Gre Sql
    POST_GRE_SQL("postgresql", "org.postgresql.Driver", "jdbc:postgresql", "postgresql"),

    // SysBase
    SYBASE("sybase", "com.sybase.jdbc.SybDriver", "jdbc:sybase:Tds", "sybase"),

    // DB2
    DB2("db2", "com.ibm.db2.jdbc.app.DB2Driver", "jdbc:db2", "db2"),

    // HSQLDB
    HSQLDB("hsqldb", "org.hsqldb.jdbcDriver", "jdbc:hsqldb", "hsqldb"),

    // DERBY
    DERBY("derby", "org.apache.derby.jdbc.ClientDriver", "jdbc:derby", "derby"),

    // H2
    H2("h2", "org.h2.Driver", "jdbc:h2:tcp", "h2");

    /**
     * 数据库名称
     */
    public final String NAME;

    /**
     * 数据库驱动
     */
    public final String DRIVER;

    /**
     * URL起始字符串
     */
    public final String URL_START;

    /**
     * 驱动关键字
     */
    public final String DRIVER_KEYWORD;

    DataBaseTypeEnum(String name, String driver, String urlStart, String driverKeyWord) {
        this.NAME = name;
        this.DRIVER = driver;
        this.URL_START = urlStart;
        this.DRIVER_KEYWORD = driverKeyWord;
    }

    /**
     * 根据驱动获取数据库类型
     *
     * @param driver 驱动全名
     * @return 数据库类型
     */
    public static String getDatabaseTypeByJdbcDriver(String driver) {
        if (StringUtil.isEmpty(driver)) {
            throw new RuntimeException("");
        }

        driver = driver.toLowerCase();
        for (DataBaseTypeEnum dataBaseType : DataBaseTypeEnum.values()) {
            if (driver.contains(dataBaseType.DRIVER_KEYWORD)) {
                return dataBaseType.NAME;
            }
        }

        throw new RuntimeException("");
    }

    /**
     * 根据驱动获取数据库类型
     *
     * @param url 访问路径
     * @return 驱动全名
     */
    public static DataBaseTypeEnum getDatabaseTypeByUrl(String url) {
        if (StringUtil.isEmpty(url)) {
            throw new RuntimeException("");
        }

        url = url.toLowerCase();
        for (DataBaseTypeEnum dataBaseType : DataBaseTypeEnum.values()) {
            if (url.contains(dataBaseType.URL_START)) {
                return dataBaseType;
            }
        }

        throw new RuntimeException("");
    }

}
