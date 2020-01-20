package com.prometheus.project.common.dictDataValue.domain;


import com.prometheus.framework.web.domain.BaseEntity;
import lombok.Data;

/**
 * datavalue
 *
 * @author chenzhi
 * @date 2019-04-15
 */
@Data
public class DictDataValue extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 编码
     */
    private String dataCode;
    /**
     * 名称
     */
    private String dataName;
    /**
     * 名称
     */
    private String parentCode;
    /**
     * 客户编码
     */
    private Long clientId;
    /**
     * 客户编码
     */
    private String tableName;
    /**
     * 字典表类型
     */
    private String dictType;

    /**
     * 字典表类型
     */
    private String[] dictTypes;


}
