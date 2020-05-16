package com.quinn.util.database.model;

import com.quinn.util.base.StringUtil;
import com.quinn.util.base.convertor.BaseConverter;
import com.quinn.util.constant.NumberConstant;
import com.quinn.util.database.DatabaseConnectionUtil;
import com.quinn.util.database.enums.DataBaseTypeEnum;
import lombok.SneakyThrows;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

/**
 * 数据源提供对象工具
 *
 * @author Qunhua.Liao
 * @since 2020-05-14
 */
public final class DataSourceProvider {

    /**
     * 默认的数据源名称
     */
    public static final String DEFAULT_DATASOURCE_NAME = "spring";

    /**
     * 数据源Map
     */
    private static final Map<String, DataSource> DATA_SOURCE_MAP = new ConcurrentHashMap();

    /**
     * 数据连接Map
     */
    private static final Map<String, Connection> CONNECTION_MAP = new ConcurrentHashMap<>();

    private DataSourceProvider() {
    }

    /**
     * 获取新连接
     *
     * @return 连接对象
     */
    public synchronized static Connection getNewConnection(String datasourceName) {
        try {
            return getDataSource(datasourceName).getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取默认连接
     *
     * @return 数据库连接
     */
    public synchronized static Connection getConnection() {
        return getConnection(DEFAULT_DATASOURCE_NAME);
    }

    /**
     * 获取旧连接（没有则创建）
     *
     * @param datasourceName 数据源名称
     * @return 连接对象
     */
    @SneakyThrows
    public synchronized static Connection getConnection(String datasourceName) {
        Connection connection = CONNECTION_MAP.get(datasourceName);
        if (connection != null && !connection.isClosed()) {
            return connection;
        }

        return getDataSource(datasourceName).getConnection();
    }

    /**
     * 设置数据源
     *
     * @param name       名称
     * @param dataSource 数据源
     */
    public static void addDataSource(String name, DataSource dataSource) {
        DataSourceProvider.DATA_SOURCE_MAP.put(name, dataSource);
    }

    /**
     * 获取数据源（没有，则先取JNDI；再没有则取默认简约实现）
     *
     * @return
     */
    @SneakyThrows
    public synchronized static DataSource getDataSource(String datasourceName) {
        DataSource dataSource = DATA_SOURCE_MAP.get(datasourceName);
        if (dataSource == null) {
            dataSource = lookupJndiDataSource(System.getProperty(datasourceName + ".dataSource.jndi-name"));
            if (dataSource == null) {
                dataSource = new DriverManagerDataSource(datasourceName);
            }
        }
        String timeOut = System.getProperty(datasourceName + ".dataSource.timeout", NumberConstant.INT_TWO + "");
        dataSource.setLoginTimeout(BaseConverter.staticConvert(timeOut, Integer.class));
        return dataSource;
    }

    /**
     * 查找JNDI数据源
     *
     * @param name 数据源名称
     * @return 数据源
     */
    private static DataSource lookupJndiDataSource(String name) {

        if (StringUtil.isEmpty(name)) {
            return null;
        }

        try {
            Context context = new InitialContext();
            return (DataSource) context.lookup(name);
        } catch (NamingException e) {
            return null;
        }
    }

    /**
     * 数据源默认简约实现
     *
     * @author Qunhua.Liao
     * @since 2020-05-14
     */
    public static class DriverManagerDataSource implements DataSource {

        /**
         * 数据源名称
         */
        private String name;

        /**
         * 指定名称数据源
         *
         * @param name 数据源名称
         */
        public DriverManagerDataSource(String name) {
            this.name = name;
        }

        @Override
        public Connection getConnection() throws SQLException {
            DatabaseConnectionUtil.loadJdbcDriver(getDriverClass());
            return DriverManager.getConnection(getUrl(), getUsername(), getPassword());
        }

        @Override
        public Connection getConnection(String username, String password) throws SQLException {
            DatabaseConnectionUtil.loadJdbcDriver(getDriverClass());
            return DriverManager.getConnection(getUrl(), username, password);
        }

        @Override
        public <T> T unwrap(Class<T> clazz) throws SQLException {
            if (clazz == null) {
                throw new IllegalArgumentException("Interface argument must not be null");
            }
            if (!DataSource.class.equals(clazz)) {
                throw new SQLException("DataSource of type [" + getClass().getName() +
                        "] can only be unwrapped as [javax.sql.DataSource], not as [" + clazz.getName());
            }
            return (T) this;
        }

        @Override
        public boolean isWrapperFor(Class<?> clazz) {
            return DataSource.class.equals(clazz);
        }

        /**
         * 连接路径
         *
         * @return 连接路径
         */
        private String getUrl() {
            return System.getProperty(name + ".datasource.url");
        }

        /**
         * 连接用户名
         *
         * @return 用户名
         */
        private String getUsername() {
            return System.getProperty(name + ".datasource.username");
        }

        /**
         * 连接密码
         *
         * @return 密码
         */
        private String getPassword() {
            return System.getProperty(name + ".datasource.password");
        }

        /**
         * 驱动类名
         *
         * @return 驱动类名
         */
        private String getDriverClass() {
            String driverClassName = System.getProperty(name + "datasource.driver-class-name");
            if (StringUtil.isEmpty(driverClassName)) {
                return DataBaseTypeEnum.getDatabaseTypeByUrl(getUrl()).DRIVER;
            }
            return driverClassName;
        }

        @Override
        public String toString() {
            return "DataSource: " + "url=" + getUrl() + " username=" + getUsername();
        }

        @Override
        public PrintWriter getLogWriter() {
            throw new UnsupportedOperationException("getLogWriter");
        }

        @Override
        public int getLoginTimeout() {
            throw new UnsupportedOperationException("getLoginTimeout");
        }

        @Override
        public Logger getParentLogger() {
            throw new UnsupportedOperationException("setLogWriter");
        }

        @Override
        public void setLogWriter(PrintWriter out) {
            throw new UnsupportedOperationException("setLogWriter");
        }

        @Override
        public void setLoginTimeout(int seconds) {
            throw new UnsupportedOperationException("setLoginTimeout");
        }
    }
}
