package com.bjqh.util.databasse;

import com.quinn.util.database.DatabaseConnectionUtil;
import com.quinn.util.database.DatabaseMetadataUtil;
import com.quinn.util.database.model.DataSourceProvider;
import com.quinn.util.database.model.DatabaseTable;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.util.List;

/**
 * 数据库主测试
 *
 * @author Qunhua.Liao
 * @since 2020-05-14
 */
public class DatabaseMainTest {

    /**
     * 准备
     */
    @Before
    public void prepare() {
        System.setProperty("spring.datasource.driver-class-name", "oracle.jdbc.OracleDriver");
        System.setProperty("spring.datasource.url", "jdbc:oracle:thin:@172.16.2.167:1521:eam");
        System.setProperty("spring.datasource.username", "eamuser");
        System.setProperty("spring.datasource.password", "Seam*27");
    }

    /**
     * 测试表
     */
    @Test
    public void testTables() {
        Connection connection = null;
        try {
            connection = DataSourceProvider.getConnection();
            // 如果取不到数据库连接（则放弃测试）

            List<DatabaseTable> allDatabaseTables = DatabaseMetadataUtil
                    .getRegexTables(connection, null, "EAMUSER", "APP%");
            for (DatabaseTable databaseTable : allDatabaseTables) {
                System.out.println(databaseTable.getTableName());
            }
        } catch (Exception e) {
            System.out.println("Connection get failed");
            return;
        } finally {
            DatabaseConnectionUtil.close(connection);
        }
    }

}
