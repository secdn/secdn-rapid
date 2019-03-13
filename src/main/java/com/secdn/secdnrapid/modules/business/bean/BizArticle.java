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
 *   @description : BizArticle 实体类
 *   ---------------------------------
 * 	 @author secdn
 *   @since 2019-03-13
 */
@Data
@Accessors(chain = true)
public class BizArticle implements Serializable {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 文章标题
     */
	@ApiModelProperty("文章标题")
	private String title;
    /**
     * 用户ID
     */
	@ApiModelProperty("用户ID")
	private Long userId;
    /**
     * 文章封面图片
     */
	@ApiModelProperty("文章封面图片")
	private String coverImage;
    /**
     * 文章专属二维码地址
     */
	@ApiModelProperty("文章专属二维码地址")
	private String qrcodePath;
	private Boolean isMarkdown;
    /**
     * 文章内容
     */
	@ApiModelProperty("文章内容")
	private String content;
    /**
     * markdown版的文章内容
     */
	@ApiModelProperty("markdown版的文章内容")
	private String contentMd;
    /**
     * 是否置顶
     */
	@ApiModelProperty("是否置顶")
	private Boolean top;
    /**
     * 类型
     */
	@ApiModelProperty("类型")
	private Long typeId;
    /**
     * 状态
     */
	@ApiModelProperty("状态")
	private Integer status;
    /**
     * 文章简介，最多200字
     */
	@ApiModelProperty("文章简介，最多200字")
	private String description;
    /**
     * 文章关键字，优化搜索
     */
	@ApiModelProperty("文章关键字，优化搜索")
	private String keywords;
    /**
     * 是否开启评论
     */
	@ApiModelProperty("是否开启评论")
	private Boolean comment;
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
    /**
     * 审批人
     */
	@ApiModelProperty("审批人")
	private Long approval;
    /**
     * 审批意见
     */
	@ApiModelProperty("审批意见")
	private String approvalComment;
    /**
     * 部门
     */
	@ApiModelProperty("部门")
	private String department;
    /**
     * 副标题
     */
	@ApiModelProperty("副标题")
	private String subtitle;
    /**
     * 作者
     */
	@ApiModelProperty("作者")
	private String author;
    /**
     * 发布时间
     */
	@ApiModelProperty("发布时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date releaseTime;



}
