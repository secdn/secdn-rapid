package com.secdn.secdnrapid.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author secdn
 * @create 2019-03-23
 */
@Data
@ApiModel
public class BaseDto implements Serializable {


    private static final long serialVersionUID = 4074373147689080138L;

    /**
     * 当前页
     */
    @ApiModelProperty("当前页")
    private long pageNumber = 1;

    /**
     * 每页条数
     */
    @ApiModelProperty("每页条数")
    private long pageSize = 10;

    /**
     * 排序字段
     */
    @ApiModelProperty("排序字段")
    private String orderByField;

    /**
     * 排序方式
     */
    @ApiModelProperty("排序方式")
    private String orderBy;
}
