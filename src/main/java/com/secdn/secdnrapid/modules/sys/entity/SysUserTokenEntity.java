package com.secdn.secdnrapid.modules.sys.entity;




import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;


/**
 * 系统用户Token
 */
@TableName("sys_user_token")
public class SysUserTokenEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//用户ID
	@TableId(type = IdType.INPUT)
	private Long userId;
	//token
	private String token;
	//过期时间
	private Long expireTime;
	//更新时间
	private Long upLongTime;

	/**
	 * 设置：用户ID
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	/**
	 * 获取：用户ID
	 */
	public Long getUserId() {
		return userId;
	}
	/**
	 * 设置：token
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * 获取：token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * 设置：过期时间
	 */
	public void setExpireTime(Long expireTime) {
		this.expireTime = expireTime;
	}

	/**
	 * 获取：过期时间
	 */
	public Long getExpireTime() {
		return expireTime;
	}

	/**
	 * 设置：更新时间
	 */
	public void setUpLongTime(Long upLongTime) {
		this.upLongTime = upLongTime;
	}

	/**
	 * 获取：更新时间
	 */
	public Long getUpLongTime() {
		return upLongTime;
	}
}
