package com.prometheus.project.common.dictDataValue.mapper;


import com.prometheus.project.common.dictDataValue.domain.DictDataValue;

import java.util.List;

/**
 * 数据层
 *
 * @author chenzhi
 * @date 2019-04-15
 */
public interface DictDataValueMapper {


    /**
     * 查询 列表
     *
     * @param dictDataValue
     * @return 集合
     */
    public List<DictDataValue> selectDictDataList(DictDataValue dictDataValue);

}