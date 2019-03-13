package com.secdn.secdnrapid.modules.sys.mapper;



import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.secdn.secdnrapid.modules.sys.entity.SysUserTokenEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 系统用户Token
 *
 */
@Repository
public interface SysUserTokenMapper extends BaseMapper<SysUserTokenEntity> {

    SysUserTokenEntity queryByToken(String token);
	
}
