package com.secdn.secdnrapid.modules.sys.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.secdn.secdnrapid.common.utils.PageInfo;
import com.secdn.secdnrapid.common.utils.Query;
import com.secdn.secdnrapid.modules.sys.entity.SysLogEntity;
import com.secdn.secdnrapid.modules.sys.mapper.SysLogMapper;
import com.secdn.secdnrapid.modules.sys.service.SysLogService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("sysLogService")
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLogEntity> implements SysLogService {

    @Override
    public PageInfo queryPage(Map<String, Object> params) {
        String key = (String)params.get("key");

        IPage<SysLogEntity> page = this.page(
            new Query<SysLogEntity>(params).getPage(),
            new QueryWrapper<SysLogEntity>().like(StringUtils.isNotBlank(key),"username", key)
        );

        return new PageInfo(page);
    }
}
