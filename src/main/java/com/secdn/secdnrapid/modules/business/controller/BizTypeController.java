package com.secdn.secdnrapid.modules.business.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.secdn.secdnrapid.common.utils.MessageVoUtil;
import com.secdn.secdnrapid.common.utils.PageUtils;
import com.secdn.secdnrapid.common.vo.MessageVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

import com.secdn.secdnrapid.modules.business.service.BizTypeService;
import com.secdn.secdnrapid.modules.business.bean.BizType;



/**
 *
 *   @description : BizType 控制器
 *   ---------------------------------
 * 	 @author secdn
 *   @since 2019-03-13
 */
@RestController
@Api(value="/bizType", description="BizType 控制器")
@RequestMapping("/bizType")
public class BizTypeController {
    private final Logger logger = LoggerFactory.getLogger(BizTypeController.class);

    @Autowired
    public BizTypeService bizTypeService;

    /**
     * @description : 获取分页列表
     * ---------------------------------
     * @author : secdn
     * @since : Create in 2019-03-13
     */
    @PostMapping("/getBizTypeList")
    @ApiOperation(value="/getBizTypeList", notes="获取分页列表")
    @RequiresPermissions("business:BizType:list")
    public MessageVo list(@RequestParam Map<String, Object> params){
        PageUtils page = bizTypeService.queryPage(params);

        return MessageVoUtil.success(page);
    }


    /**
     * @description : 通过id获取BizType
     * ---------------------------------
     * @author : secdn
     * @since : Create in 2019-03-13
     */
    @RequestMapping("/getBizTypeById/{id}}")
    @RequiresPermissions("business:BizType:info")
    public MessageVo info(@PathVariable("id") Long id){
        BizType bizType = bizTypeService.getById(id);

        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("bizType", bizType);
        return MessageVoUtil.success(stringObjectHashMap);
    }

    /**
     * @description : 添加BizType
     * ---------------------------------
     * @author : secdn
     * @since : Create in 2019-03-13
     */
    @PostMapping("/addBizType")
    @ApiOperation(value="/addBizType", notes="添加BizType")
    @RequiresPermissions("business:BizType:save")
    public MessageVo save(@RequestBody BizType bizType){
        bizTypeService.save(bizType);

        return MessageVoUtil.success();
    }

    /**
     * @description : 通过id更新BizType
     * ---------------------------------
     * @author : secdn
     * @since : Create in 2019-03-13
     */
    @PostMapping("/updateBizTypeById")
    @ApiOperation(value="/updateBizTypeById", notes="通过id更新BizType")
    @RequiresPermissions("business:BizType:update")
    public MessageVo update(@RequestBody BizType bizType){
        bizTypeService.updateById(bizType);

        return MessageVoUtil.success();
    }

    /**
     * @description : 通过id删除BizType
     * ---------------------------------
     * @author : secdn
     * @since : Create in 2019-03-13
     */
    @PostMapping("/delete")
    @RequiresPermissions("business:BizType:delete")
    @ApiOperation(value="/deleteBizTypeById", notes="通过id删除BizType")
    public MessageVo delete(@RequestBody Long[] ids){
        bizTypeService.removeByIds(Arrays.asList(ids));

        return MessageVoUtil.success();
    }

}
