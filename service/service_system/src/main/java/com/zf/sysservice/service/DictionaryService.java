package com.zf.sysservice.service;

import com.zf.sysservice.entity.Dictionary;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 字典表 服务类
 * </p>
 *
 * @author ZF
 * @since 2022-10-29
 */
public interface DictionaryService extends IService<Dictionary> {

    List<Dictionary> selectDictionaryDetailList();
}
