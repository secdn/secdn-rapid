package com.secdn.secdnrapid.modules.sys.service;

import com.alibaba.fastjson.JSONObject;
import com.secdn.secdnrapid.common.wrapper.Wrapper;
import com.secdn.secdnrapid.modules.sys.entity.SysUserEntity;

/**
 * @author secdn
 * @create 2019-08-12
 */
public interface TokenService {
  /**
   * 生成token
   *
   * @param user 用户名
   */
  Wrapper<JSONObject> createToken(SysUserEntity user);

  /**
   * 退出，修改token值
   *
   * @param userName 用户名
   */
  void logout(String userName);
}
