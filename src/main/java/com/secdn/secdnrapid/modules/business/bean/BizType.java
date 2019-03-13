package com.secdn.secdnrapid.modules.business.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;


import lombok.Data;
import lombok.experimental.Accessors;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import io.swagger.annotations.ApiModelProperty;

/**
 *　　
 *   @description : BizType 实体类
 *   ---------------------------------
 * 	 @author secdn
 *   @since 2019-03-13
 */
@Data
@Accessors(chain = true)
public class BizType implements Serializable {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
	private Long pid;
    /**
     * 文章类型名
     */
	@ApiModelProperty("文章类型名")
	private String name;
    /**
     * 类型介绍
     */
	@ApiModelProperty("类型介绍")
	private String description;
    /**
     * 排序
     */
	@ApiModelProperty("排序")
	private Integer sort;
    /**
     * 图标
     */
	@ApiModelProperty("图标")
	private String icon;
    /**
     * 是否可用
     */
	@ApiModelProperty("是否可用")
	private Boolean available;
    /**
     * 添加时间
     */
	@ApiModelProperty("添加时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createTime;
    /**
     * 更新时间
     */
	@ApiModelProperty("更新时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date updateTime;
	private String url;



}
