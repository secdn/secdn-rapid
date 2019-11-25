

package com.secdn.secdnrapid.common.utils;


import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.secdn.secdnrapid.common.xss.SQLFilter;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 查询参数
 *
 */
public class Query<T> extends LinkedHashMap<String, Object> {
	private static final long serialVersionUID = 1L;
    /**
     * mybatis-plus分页参数
     */
    private Page<T> page;
    /**
     * 当前页码
     */
    private long pageNumber = 1;
    /**
     * 每页条数
     */
    private long pageSize = 10;

    public Query(Object params){
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String,Object> pageParams = null;
        try {
            pageParams = objectMapper.readValue(objectMapper.writeValueAsString(params), Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        Map<String,Object> pageParams = JSONObject.toJavaObject(JSON.parseObject(JSON.toJSONString(params)), Map.class);
        this.putAll(pageParams);

        //分页参数
        if(pageParams.get("pageNumber") != null){
            pageNumber = Long.parseLong("" + pageParams.get("pageNumber"));
        }
        if(pageParams.get("pageSize") != null){
            pageSize = Long.parseLong("" + pageParams.get("pageSize"));
        }

        this.put("offset", (pageNumber - 1) * pageSize);
        this.put("pageNumber", pageNumber);
        this.put("pageSize", pageSize);

        //防止SQL注入（因为sidx、order是通过拼接SQL实现排序的，会有SQL注入风险）
        String orderByField = SQLFilter.sqlInject((String)pageParams.get("orderByField"));
        String order = SQLFilter.sqlInject((String)pageParams.get("order"));
        this.put("orderByField", orderByField);
        this.put("order", order);

        //mybatis-plus分页
        this.page = new Page<>(pageNumber, pageSize);

        //排序
        if(StringUtils.isNotBlank(orderByField) && StringUtils.isNotBlank(order)){
            if("ASC".equalsIgnoreCase(order)) {
                this.page.addOrder(OrderItem.asc(orderByField));
            }else {
                this.page.addOrder(OrderItem.desc(orderByField));
            }
        }

    }

    public Page<T> getPage() {
        return page;
    }

    public long getPageNumber() {
        return pageNumber;
    }

    public long getPageSize() {
        return pageSize;
    }
}
