
package com.secdn.secdnrapid.modules.sys.service;





import com.baomidou.mybatisplus.extension.service.IService;
import com.secdn.secdnrapid.common.utils.PageInfo;
import com.secdn.secdnrapid.modules.sys.entity.SysLogEntity;

import java.util.Map;


/**
 * 系统日志
 *
 */
public interface SysLogService extends IService<SysLogEntity> {

    PageInfo queryPage(Map<String, Object> params);

}
