

package com.secdn.secdnrapid.modules.sys.mapper;




import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.secdn.secdnrapid.modules.sys.entity.SysLogEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 系统日志
 *
 */
@Repository
public interface SysLogMapper extends BaseMapper<SysLogEntity> {
	
}
