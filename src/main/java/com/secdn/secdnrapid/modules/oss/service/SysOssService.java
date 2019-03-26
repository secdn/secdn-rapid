

package com.secdn.secdnrapid.modules.oss.service;




import com.baomidou.mybatisplus.extension.service.IService;
import com.secdn.secdnrapid.common.utils.PageInfo;
import com.secdn.secdnrapid.modules.oss.entity.SysOssEntity;

import java.util.Map;

/**
 * 文件上传
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-25 12:13:26
 */
public interface SysOssService extends IService<SysOssEntity> {

	PageInfo queryPage(Map<String, Object> params);
}
