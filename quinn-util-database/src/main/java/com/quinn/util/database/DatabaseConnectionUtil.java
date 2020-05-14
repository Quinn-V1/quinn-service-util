package com.quinn.util.database;

import java.sql.*;

/**
 * 数据库连接工具
 *
 * @author Qunhua.Liao
 * @since 2020-05-14
 */
public class DatabaseConnectionUtil {

    public static void loadJdbcDriver(String driverClass) {
        try {
            if (driverClass == null || "".equals(driverClass.trim())) {
                throw new IllegalArgumentException("jdbc 'driverClass' must not be empty");
            }
            Class.forName(driverClass.trim());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("not found jdbc driver class:[" + driverClass + "]", e);
        }
    }

    public static Connection getConnection(String driverClass, String url, String username, String password) {
        loadJdbcDriver(driverClass);
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void close(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (Exception e) {
            }
        }
    }

    public static void close(PreparedStatement s) {
        if (s != null) {
            try {
                s.close();
            } catch (Exception e) {
            }
        }
    }

    public static void close(Statement s) {
        if (s != null) {
            try {
                s.close();
            } catch (Exception e) {
                //ignore
            }
        }
    }

    public static void close(ResultSet s) {
        if (s != null) {
            try {
                s.close();
            } catch (Exception e) {
            }
        }
    }

    public static void close(Connection conn, ResultSet rs) {
        close(conn);
        close(rs);
    }

    public static void close(Connection conn, PreparedStatement ps, ResultSet rs) {
        close(conn);
        close(ps);
        close(rs);
    }

    public static void close(PreparedStatement ps, ResultSet rs) {
        close(ps);
        close(rs);
    }

    public static void close(Connection conn, Statement s, ResultSet rs) {
        close(conn);
        close(s);
        close(rs);
    }

    public static void close(Statement s, ResultSet rs) {
        close(s);
        close(rs);
    }

    public static void rollback(Connection conn) {
        if (conn != null) {
            try {
                conn.rollback();
            } catch (SQLException e) {
            }
        }
    }

}
