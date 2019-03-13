package com.secdn.secdnrapid.modules.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.secdn.secdnrapid.common.utils.PageUtils;
import com.secdn.secdnrapid.common.utils.Query;
import com.secdn.secdnrapid.modules.business.bean.BizArticle;
import com.secdn.secdnrapid.modules.business.mapper.BizArticleMapper;
import com.secdn.secdnrapid.modules.business.service.BizArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 *   @description : BizArticle 服务实现类
 *   ---------------------------------
 * 	 @author secdn
 *   @since 2019-03-13
 */
@Service
public class BizArticleServiceImpl extends ServiceImpl<BizArticleMapper, BizArticle> implements BizArticleService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<BizArticle> page = this.page(
                new Query<BizArticle>(params).getPage(),
                new QueryWrapper<BizArticle>()
        );

        return new PageUtils(page);
    }

}