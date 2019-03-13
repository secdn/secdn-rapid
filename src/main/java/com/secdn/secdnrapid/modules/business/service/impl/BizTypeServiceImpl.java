package com.secdn.secdnrapid.modules.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.secdn.secdnrapid.common.utils.PageUtils;
import com.secdn.secdnrapid.common.utils.Query;
import com.secdn.secdnrapid.modules.business.bean.BizType;
import com.secdn.secdnrapid.modules.business.mapper.BizTypeMapper;
import com.secdn.secdnrapid.modules.business.service.BizTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 *   @description : BizType 服务实现类
 *   ---------------------------------
 * 	 @author secdn
 *   @since 2019-03-13
 */
@Service
public class BizTypeServiceImpl extends ServiceImpl<BizTypeMapper, BizType> implements BizTypeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<BizType> page = this.page(
                new Query<BizType>(params).getPage(),
                new QueryWrapper<BizType>()
        );

        return new PageUtils(page);
    }

}