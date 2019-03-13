package com.secdn.secdnrapid.modules.sys.controller;


import com.secdn.secdnrapid.common.annotation.SysLog;
import com.secdn.secdnrapid.common.utils.Constant;
import com.secdn.secdnrapid.common.utils.MessageVoUtil;
import com.secdn.secdnrapid.common.utils.PageUtils;
import com.secdn.secdnrapid.common.validator.ValidatorUtils;
import com.secdn.secdnrapid.common.vo.MessageVo;
import com.secdn.secdnrapid.modules.sys.entity.SysRoleEntity;
import com.secdn.secdnrapid.modules.sys.service.SysRoleMenuService;
import com.secdn.secdnrapid.modules.sys.service.SysRoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 角色管理
 *
 */
@RestController
@RequestMapping("/sys/role")
public class SysRoleController extends AbstractController {
	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private SysRoleMenuService sysRoleMenuService;

	/**
	 * 角色列表
	 */
	@GetMapping("/list")
	@RequiresPermissions("sys:role:list")
	public MessageVo list(@RequestParam Map<String, Object> params){
		//如果不是超级管理员，则只查询自己创建的角色列表
		if(getUserId() != Constant.SUPER_ADMIN){
			params.put("createUserId", getUserId());
		}

		PageUtils page = sysRoleService.queryPage(params);

		return MessageVoUtil.success(page);
	}
	
	/**
	 * 角色列表
	 */
	@GetMapping("/select")
	@RequiresPermissions("sys:role:select")
	public MessageVo select(){
		Map<String, Object> map = new HashMap<>();
		
		//如果不是超级管理员，则只查询自己所拥有的角色列表
		if(getUserId() != Constant.SUPER_ADMIN){
			map.put("create_user_id", getUserId());
		}
		List<SysRoleEntity> list = (List<SysRoleEntity>) sysRoleService.listByMap(map);

        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("list", list);
        return MessageVoUtil.success(stringObjectHashMap);
	}
	
	/**
	 * 角色信息
	 */
	@GetMapping("/info/{roleId}")
	@RequiresPermissions("sys:role:info")
	public MessageVo info(@PathVariable("roleId") Long roleId){
		SysRoleEntity role = sysRoleService.getById(roleId);
		
		//查询角色对应的菜单
		List<Long> menuIdList = sysRoleMenuService.queryMenuIdList(roleId);
		role.setMenuIdList(menuIdList);

        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("role", role);
        return MessageVoUtil.success(stringObjectHashMap);
	}
	
	/**
	 * 保存角色
	 */
	@SysLog("保存角色")
	@PostMapping("/save")
	@RequiresPermissions("sys:role:save")
	public MessageVo save(@RequestBody SysRoleEntity role){
		ValidatorUtils.validateEntity(role);
		
		role.setCreateUserId(getUserId());
		sysRoleService.save(role);
		
		return MessageVoUtil.success();
	}
	
	/**
	 * 修改角色
	 */
	@SysLog("修改角色")
	@PostMapping("/update")
	@RequiresPermissions("sys:role:update")
	public MessageVo update(@RequestBody SysRoleEntity role){
		ValidatorUtils.validateEntity(role);
		
		role.setCreateUserId(getUserId());
		sysRoleService.update(role);
		
		return MessageVoUtil.success();
	}
	
	/**
	 * 删除角色
	 */
	@SysLog("删除角色")
	@PostMapping("/delete")
	@RequiresPermissions("sys:role:delete")
	public MessageVo delete(@RequestBody Long[] roleIds){
		sysRoleService.deleteBatch(roleIds);
		
		return MessageVoUtil.success();
	}
}
