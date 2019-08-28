package com.secdn.secdnrapid.modules.business.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author secdn
 * @create 2019-08-28
 */
@Data
public class TestConvertDto {
  private String name;
  private String address;
  private String email;
  private Date createTime;
  private Date updateTime;
}
