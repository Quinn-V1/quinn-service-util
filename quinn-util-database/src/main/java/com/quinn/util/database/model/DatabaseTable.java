package com.quinn.util.database.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 表实体
 *
 * @author Qunhua.Liao
 * @since 2020-05-14
 */
@Getter
@Setter
public class DatabaseTable {

    /**
     * 表名
     */
    private String tableName;

    /**
     * 字段列表
     */
    private List<DatabaseColumn> columns;

}
