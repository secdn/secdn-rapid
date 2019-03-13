package com.secdn.secdnrapid.modules.sys.controller;


import com.secdn.secdnrapid.common.annotation.SysLog;
import com.secdn.secdnrapid.common.utils.Constant;
import com.secdn.secdnrapid.common.utils.MessageVoUtil;
import com.secdn.secdnrapid.common.utils.PageUtils;
import com.secdn.secdnrapid.common.validator.Assert;
import com.secdn.secdnrapid.common.validator.ValidatorUtils;
import com.secdn.secdnrapid.common.validator.group.AddGroup;
import com.secdn.secdnrapid.common.validator.group.UpdateGroup;
import com.secdn.secdnrapid.common.vo.MessageVo;
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
	public MessageVo list(@RequestParam Map<String, Object> params){
		//只有超级管理员，才能查看所有管理员列表
		if(getUserId() != Constant.SUPER_ADMIN){
			params.put("createUserId", getUserId());
		}
		PageUtils page = sysUserService.queryPage(params);

		return MessageVoUtil.success(page);
	}
	
	/**
	 * 获取登录的用户信息
	 */
	@GetMapping("/info")
	public MessageVo info(){
		HashMap<String, Object> stringObjectHashMap = new HashMap<>();
		stringObjectHashMap.put("user", getUser());
		return MessageVoUtil.success(stringObjectHashMap);
	}
	
	/**
	 * 修改登录用户密码
	 */
	@SysLog("修改密码")
	@PostMapping("/password")
	public MessageVo password(@RequestBody PasswordForm form){
		Assert.isBlank(form.getNewPassword(), "新密码不为能空");
		
		//sha256加密
		String password = new Sha256Hash(form.getPassword(), getUser().getSalt()).toHex();
		//sha256加密
		String newPassword = new Sha256Hash(form.getNewPassword(), getUser().getSalt()).toHex();
				
		//更新密码
		boolean flag = sysUserService.updatePassword(getUserId(), password, newPassword);
		if(!flag){
			return MessageVoUtil.fail("原密码不正确");
		}
		
		return MessageVoUtil.success();
	}
	
	/**
	 * 用户信息
	 */
	@GetMapping("/info/{userId}")
	@RequiresPermissions("sys:user:info")
	public MessageVo info(@PathVariable("userId") Long userId){
		SysUserEntity user = sysUserService.getById(userId);
		
		//获取用户所属的角色列表
		List<Long> roleIdList = sysUserRoleService.queryRoleIdList(userId);
		user.setRoleIdList(roleIdList);
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("user", user);
        return MessageVoUtil.success(stringObjectHashMap);
	}
	
	/**
	 * 保存用户
	 */
	@SysLog("保存用户")
	@PostMapping("/save")
	@RequiresPermissions("sys:user:save")
	public MessageVo save(@RequestBody SysUserEntity user){
		ValidatorUtils.validateEntity(user, AddGroup.class);
		
		user.setCreateUserId(getUserId());
		sysUserService.save(user);
		
		return MessageVoUtil.success();
	}
	
	/**
	 * 修改用户
	 */
	@SysLog("修改用户")
	@PostMapping("/update")
	@RequiresPermissions("sys:user:update")
	public MessageVo update(@RequestBody SysUserEntity user){
		ValidatorUtils.validateEntity(user, UpdateGroup.class);

		user.setCreateUserId(getUserId());
		sysUserService.update(user);
		
		return MessageVoUtil.success();
	}
	
	/**
	 * 删除用户
	 */
	@SysLog("删除用户")
	@PostMapping("/delete")
	@RequiresPermissions("sys:user:delete")
	public MessageVo delete(@RequestBody Long[] userIds){
		if(ArrayUtils.contains(userIds, 1L)){
			return MessageVoUtil.fail("系统管理员不能删除");
		}
		
		if(ArrayUtils.contains(userIds, getUserId())){
			return MessageVoUtil.fail("当前用户不能删除");
		}
		
		sysUserService.deleteBatch(userIds);
		
		return MessageVoUtil.success();
	}
}
