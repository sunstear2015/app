package com.pajx.server.app.service.impl;


import com.pajx.server.app.dao.IRoleDao;
import com.pajx.server.app.dao.ISchoolDao;
import com.pajx.server.app.service.IRoleService;
import com.pajx.server.app.service.ISchoolService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by taller on 15/1/9.
 */
@Service
@Transactional
public class RoleServiceImpl implements IRoleService {

    private IRoleDao roleDao;
    @Resource
    public void setRoleDao(IRoleDao roleDao) {
        this.roleDao = roleDao;
    }
    @Override
    public List getRole(String code) {
        return null;
    }
}
