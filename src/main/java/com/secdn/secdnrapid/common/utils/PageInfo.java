
package com.secdn.secdnrapid.common.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 分页工具类
 *
 */
@Data
public class PageInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	//总记录数
	private int totalCount;
	//每页记录数
	private long pageSize;
	//总页数
	private int totalPage;
	//当前页数
	private long currPage;
	//列表数据
	private List<?> records;
	
	/**
	 * 分页
	 * @param records        列表数据
	 * @param totalCount  总记录数
	 * @param pageSize    每页记录数
	 * @param currPage    当前页数
	 */
	public PageInfo(List<?> records, int totalCount, int pageSize, int currPage) {
		this.records = records;
		this.totalCount = totalCount;
		this.pageSize = pageSize;
		this.currPage = currPage;
		this.totalPage = (int)Math.ceil((double)totalCount/pageSize);
	}

	/**
	 * 分页
	 */
	public PageInfo(IPage<?> page) {
		this.records = page.getRecords();
		this.totalCount = (int)page.getTotal();
		this.pageSize = page.getSize();
		this.currPage = page.getCurrent();
		this.totalPage = (int)page.getPages();
	}

	
}
