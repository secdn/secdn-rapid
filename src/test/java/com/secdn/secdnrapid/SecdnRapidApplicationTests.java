package com.secdn.secdnrapid;

import com.secdn.secdnrapid.common.utils.JwtTokenUtil;
import com.secdn.secdnrapid.modules.sys.entity.SysUserEntity;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SecdnRapidApplicationTests {
  @Autowired private JwtTokenUtil jwtTokenUtil;
  @Test
  public void contextLoads() {}

  @Test
  public void tokenTest() {
    SysUserEntity sysUserEntity = new SysUserEntity();
    sysUserEntity.setUsername("admin");
    sysUserEntity.setUserId(111L);
    String token = jwtTokenUtil.generateToken(sysUserEntity.getUsername(), 24 * 60 * 60 * 1000);
    boolean flag = jwtTokenUtil.validateToken(token);
    Claims claims = jwtTokenUtil.getClaimsFromToken(token);
    String userName = jwtTokenUtil.getUsernameFromToken(token);
    Date expirationDate = jwtTokenUtil.getExpirationDateFromToken(token);
    log.info(token);
    log.info(flag + "");
    log.info(userName);
    log.info(expirationDate.getTime() + "");
    log.info(claims.get("userId").toString());
  }
}
