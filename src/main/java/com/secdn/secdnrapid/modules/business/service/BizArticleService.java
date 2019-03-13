package com.secdn.secdnrapid.modules.business.service;

import com.secdn.secdnrapid.common.utils.PageUtils;
import com.secdn.secdnrapid.modules.business.bean.BizArticle;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 *   @description : BizArticle 服务接口
 *   ---------------------------------
 * 	 @author secdn
 *   @since 2019-03-13
 */
public interface BizArticleService extends IService<BizArticle> {

    PageUtils queryPage(Map<String, Object> params);
}

