

package com.secdn.secdnrapid.common.utils;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.secdn.secdnrapid.common.xss.SQLFilter;
import org.apache.commons.lang.StringUtils;

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

    public Query(Map<String, Object> params){
        this.putAll(params);

        //分页参数
        if(params.get("pageNumber") != null){
            pageNumber = Long.getLong((String)params.get("pageNumber"));
        }
        if(params.get("pageSize") != null){
            pageSize = Long.getLong((String)params.get("pageSize"));
        }

        this.put("offset", (pageNumber - 1) * pageSize);
        this.put("pageNumber", pageNumber);
        this.put("pageSize", pageSize);

        //防止SQL注入（因为sidx、order是通过拼接SQL实现排序的，会有SQL注入风险）
        String orderByField = SQLFilter.sqlInject((String)params.get("orderByField"));
        String order = SQLFilter.sqlInject((String)params.get("order"));
        this.put("orderByField", orderByField);
        this.put("order", order);

        //mybatis-plus分页
        this.page = new Page<>(pageNumber, pageSize);

        //排序
        if(StringUtils.isNotBlank(orderByField) && StringUtils.isNotBlank(order)){
            if("ASC".equalsIgnoreCase(order)) {
                this.page.setAsc(orderByField);
            }else {
                this.page.setDesc(orderByField);
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
