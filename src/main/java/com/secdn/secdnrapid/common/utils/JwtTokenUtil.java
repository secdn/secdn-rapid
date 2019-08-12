package com.secdn.secdnrapid.common.utils;

import com.alibaba.fastjson.JSON;
import com.secdn.secdnrapid.modules.sys.entity.SysUserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.Serializable;
import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * JWT 工具类
 *
 * @author secdn
 * @create 2019-08-09
 */
@Component
public class JwtTokenUtil implements Serializable {

  @Autowired RedisUtils redisUtils;

  /** 存储过期Token Redis Key前缀 */
  private static final String REDIS_EXPIRED_TOKEN_KEY = "EXPIRED_TOKEN_";

  /** 过期时间 5分钟(毫秒) */
  private static final long EXPIRATION_TIME = 5 * 60 * 1000;

  /** JWT密码 */
  private static final String SECRET =
      "asdfljsafdjkaldsjfnejalskdfnvkjhasdfnasdjfhlksadfndvjhvasdnfnasnvlkjhasfdsadfasdfsadsdfjknsaasfdkjhasfdbhafd";

  // TODO 用户修改密码 登出 重新登录操作需要 Token失效 或者刷新Token ，Redis 存不可登录的Token 。用户携带Token 进来，验证token,拿到userId
  // ,和过期时间，去Redis 里面看看是否为黑名单Token，当修改密码，登出时，将原来的Token存进Redis里面，形成一个黑名单，这样子Token就失效了

  /** 签发JWT 自定义失效时间 */
  public String generateToken(SysUserEntity user, long ttlMillis) {
    // 自定义属性存入Token中
    Map<String, Object> claims = JSON.parseObject(JSON.toJSONString(user), Map.class);
    long nowMillis = System.currentTimeMillis();
    // 设置签名算法
    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;
    // 设置密钥
    byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET);
    Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
    // 设置JWT claims
    // setClaims()为设置私有声明，如果有私有声明，一定要先设置这个自己创建的私有的声明，这个是给builder的claim赋值，一旦写在标准的声明赋值之后，就是覆盖了那些标准的声明的
    JwtBuilder builder =
        Jwts.builder()
            .setClaims(claims)
            .setIssuer("secdn")
            .setSubject(user.getUsername())
            .setAudience("client")
            .setId(UUID.randomUUID().toString())
            .setIssuedAt(new Date())
            .signWith(signingKey, signatureAlgorithm);

    // 设置超时时间
    if (ttlMillis >= 0) {
      long expMillis = nowMillis + ttlMillis;
      Date exp = new Date(expMillis);
      builder.setExpiration(exp);
    }

    return builder.compact();
  }

  /** 验证JWT token 有效返回{@code true} */
  public Boolean validateToken(String token) {
    return !isTokenExpired(token) && !isTokenDisable(token);
  }

  /** 检查Token 是否被禁用 禁用返回{@code true} */
  public Boolean isTokenDisable(String token) {
    String userName = getUsernameFromToken(token);
    return StringUtils.isNotBlank(redisUtils.get(REDIS_EXPIRED_TOKEN_KEY + userName));
  }

  /** 获取token是否过期 过期返回 {@code true} */
  public Boolean isTokenExpired(String token) {
    Date expiration = getExpirationDateFromToken(token);
    return expiration.before(new Date());
  }

  /** 根据token获取username */
  public String getUsernameFromToken(String token) {
    String username = getClaimsFromToken(token).getSubject();
    return username;
  }

  /** 获取token的过期时间 */
  public Date getExpirationDateFromToken(String token) {
    Date expiration = getClaimsFromToken(token).getExpiration();
    return expiration;
  }

  /** 解析JWT */
  public Claims getClaimsFromToken(String token) {
    Claims claims =
        Jwts.parser()
            .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET))
            .parseClaimsJws(token)
            .getBody();
    return claims;
  }
}
