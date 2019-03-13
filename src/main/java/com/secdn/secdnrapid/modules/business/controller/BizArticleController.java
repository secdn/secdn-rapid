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

import com.secdn.secdnrapid.modules.business.service.BizArticleService;
import com.secdn.secdnrapid.modules.business.bean.BizArticle;



/**
 *
 *   @description : BizArticle 控制器
 *   ---------------------------------
 * 	 @author secdn
 *   @since 2019-03-13
 */
@RestController
@Api(value="/bizArticle", description="BizArticle 控制器")
@RequestMapping("/bizArticle")
public class BizArticleController {
    private final Logger logger = LoggerFactory.getLogger(BizArticleController.class);

    @Autowired
    public BizArticleService bizArticleService;

    /**
     * @description : 获取分页列表
     * ---------------------------------
     * @author : secdn
     * @since : Create in 2019-03-13
     */
    @PostMapping("/getBizArticleList")
    @ApiOperation(value="/getBizArticleList", notes="获取分页列表")
    @RequiresPermissions("business:BizArticle:list")
    public MessageVo list(@RequestParam Map<String, Object> params){
        PageUtils page = bizArticleService.queryPage(params);

        return MessageVoUtil.success(page);
    }


    /**
     * @description : 通过id获取BizArticle
     * ---------------------------------
     * @author : secdn
     * @since : Create in 2019-03-13
     */
    @RequestMapping("/getBizArticleById/{id}}")
    @RequiresPermissions("business:BizArticle:info")
    public MessageVo info(@PathVariable("id") Long id){
        BizArticle bizArticle = bizArticleService.getById(id);

        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("bizArticle", bizArticle);
        return MessageVoUtil.success(stringObjectHashMap);
    }

    /**
     * @description : 添加BizArticle
     * ---------------------------------
     * @author : secdn
     * @since : Create in 2019-03-13
     */
    @PostMapping("/addBizArticle")
    @ApiOperation(value="/addBizArticle", notes="添加BizArticle")
    @RequiresPermissions("business:BizArticle:save")
    public MessageVo save(@RequestBody BizArticle bizArticle){
        bizArticleService.save(bizArticle);

        return MessageVoUtil.success();
    }

    /**
     * @description : 通过id更新BizArticle
     * ---------------------------------
     * @author : secdn
     * @since : Create in 2019-03-13
     */
    @PostMapping("/updateBizArticleById")
    @ApiOperation(value="/updateBizArticleById", notes="通过id更新BizArticle")
    @RequiresPermissions("business:BizArticle:update")
    public MessageVo update(@RequestBody BizArticle bizArticle){
        bizArticleService.updateById(bizArticle);

        return MessageVoUtil.success();
    }

    /**
     * @description : 通过id删除BizArticle
     * ---------------------------------
     * @author : secdn
     * @since : Create in 2019-03-13
     */
    @PostMapping("/delete")
    @RequiresPermissions("business:BizArticle:delete")
    @ApiOperation(value="/deleteBizArticleById", notes="通过id删除BizArticle")
    public MessageVo delete(@RequestBody Long[] ids){
        bizArticleService.removeByIds(Arrays.asList(ids));

        return MessageVoUtil.success();
    }

}
