package com.zf.sysservice.service.impl;

import com.zf.sysservice.entity.Dictionary;
import com.zf.sysservice.mapper.DictionaryMapper;
import com.zf.sysservice.service.DictionaryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 字典表 服务实现类
 * </p>
 *
 * @author ZF
 * @since 2022-10-29
 */
@Service
public class DictionaryServiceImpl extends ServiceImpl<DictionaryMapper, Dictionary> implements DictionaryService {

    @Override
    public List<Dictionary> selectDictionaryDetailList() {
        return baseMapper.selectDictionaryDetailList();
    }
}
