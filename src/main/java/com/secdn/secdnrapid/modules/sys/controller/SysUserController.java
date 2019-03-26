package com.secdn.secdnrapid.modules.sys.controller;


import com.secdn.secdnrapid.common.annotation.SysLog;
import com.secdn.secdnrapid.common.utils.Constant;
import com.secdn.secdnrapid.common.utils.PageInfo;
import com.secdn.secdnrapid.common.validator.Assert;
import com.secdn.secdnrapid.common.validator.ValidatorUtils;
import com.secdn.secdnrapid.common.validator.group.AddGroup;
import com.secdn.secdnrapid.common.validator.group.UpdateGroup;
import com.secdn.secdnrapid.common.wrapper.WrapMapper;
import com.secdn.secdnrapid.common.wrapper.Wrapper;
import com.secdn.secdnrapid.modules.sys.entity.SysUserEntity;
import com.secdn.secdnrapid.modules.sys.form.PasswordForm;
import com.secdn.secdnrapid.modules.sys.service.SysUserRoleService;
import com.secdn.secdnrapid.modules.sys.service.SysUserService;
import org.apache.commons.lang.ArrayUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统用户
 *
 */
@RestController
@RequestMapping("/sys/user")
public class SysUserController extends AbstractController {
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysUserRoleService sysUserRoleService;


	/**
	 * 所有用户列表
	 */
	@GetMapping("/list")
	@RequiresPermissions("sys:user:list")
	public Wrapper<PageInfo> list(@RequestParam Map<String, Object> params){
		//只有超级管理员，才能查看所有管理员列表
		if(getUserId() != Constant.SUPER_ADMIN){
			params.put("createUserId", getUserId());
		}
		PageInfo page = sysUserService.queryPage(params);

		return WrapMapper.ok(page);
	}
	
	/**
	 * 获取登录的用户信息
	 */
	@GetMapping("/info")
	public Wrapper<HashMap<String, Object>> info(){
		HashMap<String, Object> stringObjectHashMap = new HashMap<>();
		stringObjectHashMap.put("user", getUser());
		return WrapMapper.ok(stringObjectHashMap);
	}
	
	/**
	 * 修改登录用户密码
	 */
	@SysLog("修改密码")
	@PostMapping("/password")
	public Wrapper<Object> password(@RequestBody PasswordForm form){
		Assert.isBlank(form.getNewPassword(), "新密码不为能空");
		
		//sha256加密
		String password = new Sha256Hash(form.getPassword(), getUser().getSalt()).toHex();
		//sha256加密
		String newPassword = new Sha256Hash(form.getNewPassword(), getUser().getSalt()).toHex();
				
		//更新密码
		boolean flag = sysUserService.updatePassword(getUserId(), password, newPassword);
		if(!flag){
			return WrapMapper.error("原密码不正确");
		}
		
		return WrapMapper.ok();
	}
	
	/**
	 * 用户信息
	 */
	@GetMapping("/info/{userId}")
	@RequiresPermissions("sys:user:info")
	public Wrapper<HashMap<String, Object>> info(@PathVariable("userId") Long userId){
		SysUserEntity user = sysUserService.getById(userId);
		
		//获取用户所属的角色列表
		List<Long> roleIdList = sysUserRoleService.queryRoleIdList(userId);
		user.setRoleIdList(roleIdList);
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("user", user);
        return WrapMapper.ok(stringObjectHashMap);
	}
	
	/**
	 * 保存用户
	 */
	@SysLog("保存用户")
	@PostMapping("/save")
	@RequiresPermissions("sys:user:save")
	public Wrapper<Object> save(@RequestBody SysUserEntity user){
		ValidatorUtils.validateEntity(user, AddGroup.class);
		
		user.setCreateUserId(getUserId());
		sysUserService.saveUser(user);
		
		return WrapMapper.ok();
	}
	
	/**
	 * 修改用户
	 */
	@SysLog("修改用户")
	@PostMapping("/update")
	@RequiresPermissions("sys:user:update")
	public Wrapper<Object> update(@RequestBody SysUserEntity user){
		ValidatorUtils.validateEntity(user, UpdateGroup.class);

		user.setCreateUserId(getUserId());
		sysUserService.update(user);
		
		return WrapMapper.ok();
	}
	
	/**
	 * 删除用户
	 */
	@SysLog("删除用户")
	@PostMapping("/delete")
	@RequiresPermissions("sys:user:delete")
	public Wrapper<Object> delete(@RequestBody Long[] userIds){
		if(ArrayUtils.contains(userIds, 1L)){
			return WrapMapper.error("系统管理员不能删除");
		}
		
		if(ArrayUtils.contains(userIds, getUserId())){
			return WrapMapper.error("当前用户不能删除");
		}
		
		sysUserService.deleteBatch(userIds);
		
		return WrapMapper.ok();
	}
}
