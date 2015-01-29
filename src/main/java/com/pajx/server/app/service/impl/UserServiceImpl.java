package com.pajx.server.app.service.impl;


import com.pajx.server.app.dao.IUserDao;
import com.pajx.server.app.service.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by taller on 15/1/9.
 */
@Service
@Transactional
public class UserServiceImpl implements IUserService {

    private IUserDao userDao;

    @Resource
    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void save() {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public List getAllUser() {
        return null;
    }

    @Override
    public void update() {

    }

    @Override
    public Object[] getByUserName(String username,String type) {
        List usersList=userDao.getByUsername(username);
        if(usersList!=null&&!usersList.isEmpty()){
           Object[] o= (Object[]) usersList.get(0);
            return o;
        }
        return null;
    }
}
