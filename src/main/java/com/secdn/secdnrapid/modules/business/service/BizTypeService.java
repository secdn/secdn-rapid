package com.secdn.secdnrapid.modules.business.service;

import com.secdn.secdnrapid.common.utils.PageUtils;
import com.secdn.secdnrapid.modules.business.bean.BizType;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 *   @description : BizType 服务接口
 *   ---------------------------------
 * 	 @author secdn
 *   @since 2019-03-13
 */
public interface BizTypeService extends IService<BizType> {

    PageUtils queryPage(Map<String, Object> params);
}

