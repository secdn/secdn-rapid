

package com.secdn.secdnrapid.modules.oss.service.impl;



import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.secdn.secdnrapid.common.utils.PageInfo;
import com.secdn.secdnrapid.common.utils.Query;
import com.secdn.secdnrapid.modules.oss.mapper.SysOssMapper;
import com.secdn.secdnrapid.modules.oss.entity.SysOssEntity;
import com.secdn.secdnrapid.modules.oss.service.SysOssService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("sysOssService")
public class SysOssServiceImpl extends ServiceImpl<SysOssMapper, SysOssEntity> implements SysOssService {

	@Override
	public PageInfo queryPage(Map<String, Object> params) {
		IPage<SysOssEntity> page = this.page(
				new Query<SysOssEntity>(params).getPage()
		);

		return new PageInfo(page);
	}
	
}
