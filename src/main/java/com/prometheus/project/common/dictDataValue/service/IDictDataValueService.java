package com.prometheus.project.common.dictDataValue.service;


import com.prometheus.project.common.dictDataValue.domain.DictDataValue;

import java.util.List;

/**
 * 字典表数据 服务层
 *
 * @author chenzhi
 * @date 2019-03-28
 */
public interface IDictDataValueService {
     /**
     * 查询 字典表数据
     * @param dictDataValue
     * @return 参数键值
     */
    public List<DictDataValue> selectDictDataList(DictDataValue dictDataValue);

}
