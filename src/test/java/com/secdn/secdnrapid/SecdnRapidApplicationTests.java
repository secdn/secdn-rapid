package com.secdn.secdnrapid;

import com.alibaba.fastjson.JSON;
import com.secdn.secdnrapid.common.utils.JwtTokenUtil;
import com.secdn.secdnrapid.modules.business.convert.TestConvert;
import com.secdn.secdnrapid.modules.business.dto.TestConvertDto;
import com.secdn.secdnrapid.modules.business.entity.TestConvertEntity;
import com.secdn.secdnrapid.modules.sys.entity.SysUserEntity;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SecdnRapidApplicationTests {
  @Autowired private JwtTokenUtil jwtTokenUtil;
  @Autowired private TestConvert testConvertInstance;

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

  @Test
  public void mapStructTest() {
    TestConvertEntity testConvertEntity = new TestConvertEntity();
    testConvertEntity.setUserName("张三");
    testConvertEntity.setAddress("中国香港");
    testConvertEntity.setEmail("secdnb@gmail.com");
    testConvertEntity.setCreateTime(new Date());
    testConvertEntity.setUpdateTime(new Date());
    ArrayList<TestConvertEntity> testConvertEntities = new ArrayList<>(10);
    testConvertEntities.add(testConvertEntity);
    testConvertEntities.add(testConvertEntity);

    TestConvertDto entityToDto = testConvertInstance.entityToDto(testConvertEntity);
    TestConvertEntity dtoToEntity = testConvertInstance.dtoToEntity(entityToDto);
    Map<String, Object> entityMap = testConvertInstance.entityToMap(testConvertEntity);
    TestConvertEntity mapToEntity = testConvertInstance.mapToEntity(entityMap);
    Map<String, Object> dtoMap = testConvertInstance.dtoToMap(entityToDto);
    TestConvertDto mapToDto = testConvertInstance.mapToDto(dtoMap);
    List<TestConvertDto> entityToDtos =
        testConvertInstance.entityToDto(testConvertEntities.stream());
    List<TestConvertEntity> dtoToEntitys = testConvertInstance.dtoToEntity(entityToDtos.stream());
    List<Map<String, Object>> maps = testConvertInstance.listEntityToListMap(testConvertEntities);
    log.info("entityToDto:   " + JSON.toJSONString(entityToDto));
    log.info("dtoToEntity:   " + JSON.toJSONString(dtoToEntity));
    log.info("entityMap:   " + JSON.toJSONString(entityMap));
    log.info("mapToEntity:   " + JSON.toJSONString(mapToEntity));
    log.info("dtoMap:   " + JSON.toJSONString(dtoMap));
    log.info("mapToDto:   " + JSON.toJSONString(mapToDto));
    log.info("entityToDtos:   " + JSON.toJSONString(entityToDtos));
    log.info("dtoToEntitys:   " + JSON.toJSONString(dtoToEntitys));
    log.info("maps:   " + JSON.toJSONString(maps));
  }
}
