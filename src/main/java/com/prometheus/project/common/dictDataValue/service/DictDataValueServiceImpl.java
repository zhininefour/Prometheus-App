package com.prometheus.project.common.dictDataValue.service;

import com.prometheus.project.common.dictDataValue.domain.DictDataValue;
import com.prometheus.project.common.dictDataValue.mapper.DictDataValueMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * id获取数据 服务层实现
 *
 * @author chenzhi
 * @date 2019-03-28
 */
@Service
public class DictDataValueServiceImpl implements IDictDataValueService {

    @Autowired
    private DictDataValueMapper dictDataValueMapper;

    /**
     * 查询 字典表数据
     *
     * @param dictDataValue
     * @return 参数键值
     */
    @Override
    public List<DictDataValue> selectDictDataList(DictDataValue dictDataValue) {
        return dictDataValueMapper.selectDictDataList(dictDataValue);
    }
}
