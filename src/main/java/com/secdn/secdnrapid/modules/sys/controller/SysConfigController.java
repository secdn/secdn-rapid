
package com.secdn.secdnrapid.modules.sys.controller;



import com.secdn.secdnrapid.common.annotation.SysLog;
import com.secdn.secdnrapid.common.utils.PageInfo;
import com.secdn.secdnrapid.common.validator.ValidatorUtils;
import com.secdn.secdnrapid.common.wrapper.WrapMapper;
import com.secdn.secdnrapid.common.wrapper.Wrapper;
import com.secdn.secdnrapid.modules.sys.entity.SysConfigEntity;
import com.secdn.secdnrapid.modules.sys.service.SysConfigService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 系统配置信息
 *
 */
@RestController
@RequestMapping("/sys/config")
@Slf4j
public class SysConfigController extends AbstractController {
	@Autowired
	private SysConfigService sysConfigService;
	
	/**
	 * 所有配置列表
	 */
	@GetMapping("/list")
    @PostMapping
	@RequiresPermissions("sys:config:list")
	public Wrapper<PageInfo> list(@RequestParam Map<String, Object> params){
		PageInfo page = sysConfigService.queryPage(params);

		return WrapMapper.ok(page);
	}
	
	
	/**
	 * 配置信息
	 */
	@GetMapping("/info/{id}")
	@RequiresPermissions("sys:config:info")
	public Wrapper<SysConfigEntity> info(@PathVariable("id") Long id){
		SysConfigEntity config = sysConfigService.getById(id);
		
		return WrapMapper.ok(config);
	}
	
	/**
	 * 保存配置
	 */
	@SysLog("保存配置")
	@PostMapping("/save")
	@RequiresPermissions("sys:config:save")
	public Wrapper<Object> save(@RequestBody SysConfigEntity config){
		ValidatorUtils.validateEntity(config);

		sysConfigService.saveConfig(config);
		
		return WrapMapper.ok();
	}
	
	/**
	 * 修改配置
	 */
	@SysLog("修改配置")
	@PostMapping("/update")
	@RequiresPermissions("sys:config:update")
	public Wrapper<Object> update(@RequestBody SysConfigEntity config){
		ValidatorUtils.validateEntity(config);
		
		sysConfigService.update(config);
		
		return WrapMapper.ok();
	}
	
	/**
	 * 删除配置
	 */
	@SysLog("删除配置")
	@PostMapping("/delete")
	@RequiresPermissions("sys:config:delete")
	public Wrapper<Object> delete(@RequestBody Long[] ids){
		sysConfigService.deleteBatch(ids);
		
		return WrapMapper.ok();
	}

}
