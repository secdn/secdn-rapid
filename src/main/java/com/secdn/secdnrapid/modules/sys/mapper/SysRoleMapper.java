package com.secdn.secdnrapid.modules.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.secdn.secdnrapid.modules.sys.entity.SysRoleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 角色管理
 * 

 */
@Repository
public interface SysRoleMapper extends BaseMapper<SysRoleEntity> {
	
	/**
	 * 查询用户创建的角色ID列表
	 */
	List<Long> queryRoleIdList(Long createUserId);
}
