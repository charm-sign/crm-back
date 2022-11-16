package com.zf.sysservice.mapper;

import com.zf.sysservice.entity.Dictionary;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 字典表 Mapper 接口
 * </p>
 *
 * @author ZF
 * @since 2022-10-29
 */
public interface DictionaryMapper extends BaseMapper<Dictionary> {

    List<Dictionary> selectDictionaryDetailList();
}
