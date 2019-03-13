
package com.secdn.secdnrapid.modules.sys.mapper;



import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.secdn.secdnrapid.modules.sys.entity.SysCaptchaEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 验证码
 *
 * @author Mark sunlightcs@gmail.com
 * @since 3.1.0 2018-02-10
 */
@Repository
public interface SysCaptchaMapper extends BaseMapper<SysCaptchaEntity> {

}
