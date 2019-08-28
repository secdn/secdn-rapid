package com.secdn.secdnrapid.modules.business.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author secdn
 * @create 2019-08-28
 */
@Data
public class TestConvertEntity {
  private String userName;
  private String password;
  private String address;
  private String email;
  private Date createTime;
  private Date updateTime;
}
