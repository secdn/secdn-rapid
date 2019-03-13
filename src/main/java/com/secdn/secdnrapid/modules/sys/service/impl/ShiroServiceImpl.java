package com.secdn.secdnrapid.modules.sys.service.impl;


import com.secdn.secdnrapid.common.utils.Constant;
import com.secdn.secdnrapid.modules.sys.entity.SysMenuEntity;
import com.secdn.secdnrapid.modules.sys.entity.SysUserEntity;
import com.secdn.secdnrapid.modules.sys.entity.SysUserTokenEntity;
import com.secdn.secdnrapid.modules.sys.mapper.SysMenuMapper;
import com.secdn.secdnrapid.modules.sys.mapper.SysUserMapper;
import com.secdn.secdnrapid.modules.sys.mapper.SysUserTokenMapper;
import com.secdn.secdnrapid.modules.sys.service.ShiroService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ShiroServiceImpl implements ShiroService {
    @Autowired
    private SysMenuMapper sysMenuMapper;
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysUserTokenMapper sysUserTokenMapper;

    @Override
    public Set<String> getUserPermissions(long userId) {
        List<String> permsList;

        //系统管理员，拥有最高权限
        if(userId == Constant.SUPER_ADMIN){
            List<SysMenuEntity> menuList = sysMenuMapper.selectList(null);
            permsList = new ArrayList<>(menuList.size());
            for(SysMenuEntity menu : menuList){
                permsList.add(menu.getPerms());
            }
        }else{
            permsList = sysUserMapper.queryAllPerms(userId);
        }
        //用户权限列表
        Set<String> permsSet = new HashSet<>();
        for(String perms : permsList){
            if(StringUtils.isBlank(perms)){
                continue;
            }
            permsSet.addAll(Arrays.asList(perms.trim().split(",")));
        }
        return permsSet;
    }

    @Override
    public SysUserTokenEntity queryByToken(String token) {
        return sysUserTokenMapper.queryByToken(token);
    }

    @Override
    public SysUserEntity queryUser(Long userId) {
        return sysUserMapper.selectById(userId);
    }
}
