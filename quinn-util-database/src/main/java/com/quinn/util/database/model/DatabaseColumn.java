package com.quinn.util.database.model;

import lombok.Getter;
import lombok.Setter;

/**
 * 表实体
 *
 * @author Qunhua.Liao
 * @since 2020-05-14
 */
@Getter
@Setter
public class DatabaseColumn {

    /**
     * 表名
     */
    private String tableName;

    /**
     * 字段名
     */
    private String columnName;

}
