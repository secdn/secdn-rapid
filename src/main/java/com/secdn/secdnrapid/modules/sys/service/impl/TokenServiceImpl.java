package com.secdn.secdnrapid.modules.sys.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.secdn.secdnrapid.common.utils.JwtTokenUtil;
import com.secdn.secdnrapid.common.utils.RedisUtils;
import com.secdn.secdnrapid.common.wrapper.WrapMapper;
import com.secdn.secdnrapid.common.wrapper.Wrapper;
import com.secdn.secdnrapid.modules.sys.entity.SysUserEntity;
import com.secdn.secdnrapid.modules.sys.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author secdn
 * @create 2019-08-12
 */
@Service
public class TokenServiceImpl implements TokenService {

  /** 存储过期Token Redis Key前缀 */
  private static final String REDIS_EXPIRED_TOKEN_KEY = "EXPIRED_TOKEN_";

  /** 过期时间 12小时(毫秒) */
  private static final long EXPIRATION_TIME = 12 * 60 * 60 * 1000;

  @Autowired JwtTokenUtil jwtTokenUtil;
  @Autowired RedisUtils redisUtils;

  @Override
  public Wrapper<JSONObject> createToken(SysUserEntity user) {
    String token = jwtTokenUtil.generateToken(user.getUsername(), EXPIRATION_TIME);
    token = "Bearer " + token;
    redisUtils.delete(REDIS_EXPIRED_TOKEN_KEY + user.getUsername());
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("token", token);
    jsonObject.put("expire", EXPIRATION_TIME);
    return WrapMapper.ok(jsonObject);
  }

  @Override
  public void logout(String token) {
    String userName = jwtTokenUtil.getUsernameFromToken(token);
    // 计算token还有多久过期，Redis里token黑名单只需要在token过期之前存在就好了
    long expire =
        jwtTokenUtil.getExpirationDateFromToken(token).getTime() - System.currentTimeMillis();
    redisUtils.set(REDIS_EXPIRED_TOKEN_KEY + userName, token, expire / 1000);
  }
}
