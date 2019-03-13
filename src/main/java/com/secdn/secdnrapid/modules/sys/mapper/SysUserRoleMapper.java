package com.secdn.secdnrapid.modules.sys.mapper;



import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.secdn.secdnrapid.modules.sys.entity.SysUserRoleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户与角色对应关系
 *
 */
@Repository
public interface SysUserRoleMapper extends BaseMapper<SysUserRoleEntity> {
	
	/**
	 * 根据用户ID，获取角色ID列表
	 */
	List<Long> queryRoleIdList(Long userId);


	/**
	 * 根据角色ID数组，批量删除
	 */
	int deleteBatch(Long[] roleIds);
}
