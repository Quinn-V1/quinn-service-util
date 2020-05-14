package com.quinn.util.database;

import com.quinn.util.database.model.DatabaseTable;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据库元数据工具
 *
 * @author Qunhua.Liao
 * @since 2020-05-14
 */
public final class DatabaseMetadataUtil {

    private DatabaseMetadataUtil() {
    }

    /**
     * 获取数据库结构信息
     *
     * @param metadata 元数据
     * @param schema   schema
     * @param catalog  catalog
     * @return 数据库结构信息
     */
    public static String getDatabaseStructureInfo(DatabaseMetaData metadata, String schema, String catalog) {
        ResultSet schemaRs = null;
        ResultSet catalogRs = null;
        String nl = System.getProperty("line.separator");
        StringBuffer sb = new StringBuffer(nl);

        sb.append("Configured schema:").append(schema).append(nl);
        sb.append("Configured catalog:").append(catalog).append(nl);

        try {
            schemaRs = metadata.getSchemas();
            sb.append("Available schemas:").append(nl);
            while (schemaRs.next()) {
                sb.append("  ").append(schemaRs.getString("TABLE_SCHEM")).append(nl);
            }
        } catch (SQLException e2) {
            sb.append("  ?? Couldn't get schemas ??").append(nl);
        } finally {
            DatabaseConnectionUtil.close(schemaRs);
        }

        try {
            catalogRs = metadata.getCatalogs();
            sb.append("Available catalogs:").append(nl);
            while (catalogRs.next()) {
                sb.append("  ").append(catalogRs.getString("TABLE_CAT")).append(nl);
            }
        } catch (SQLException e2) {
            sb.append("  ?? Couldn't get catalogs ??").append(nl);
        } finally {
            DatabaseConnectionUtil.close(catalogRs);
        }
        return sb.toString();
    }

    /**
     * 获取所有表
     *
     * @param catalog catalog
     * @param schema  schema
     * @return 所有表
     */
    public static List<DatabaseTable> getAllTables(Connection connection, String catalog, String schema) {
        ResultSet rs = null;
        try {
            DatabaseMetaData dbMetaData = connection.getMetaData();
            rs = dbMetaData.getTables(catalog, schema, null, null);
            List<DatabaseTable> databaseTables = new ArrayList<>();
            while (rs.next()) {
                databaseTables.add(createTable(rs));
            }
            return databaseTables;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseConnectionUtil.close(rs);
        }
    }

    /**
     * 获取所有表
     *
     * @param catalog      catalog
     * @param schemaPatten schema
     * @param tablePatten  表样式
     * @return 匹配样式的表
     */
    public static List<DatabaseTable> getRegexTables(
            Connection connection, String catalog, String schemaPatten, String tablePatten) {
        ResultSet rs = null;
        try {
            DatabaseMetaData dbMetaData = connection.getMetaData();
            rs = dbMetaData.getTables(catalog, schemaPatten, tablePatten, null);
            List<DatabaseTable> databaseTables = new ArrayList<>();
            while (rs.next()) {
                databaseTables.add(createTable(rs));
            }
            return databaseTables;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseConnectionUtil.close(rs);
        }
    }

    @SneakyThrows
    private static DatabaseTable createTable(ResultSet rs) {
        DatabaseTable databaseTable = new DatabaseTable();
        databaseTable.setTableName(rs.getString("TABLE_NAME"));
        return databaseTable;
    }

}
