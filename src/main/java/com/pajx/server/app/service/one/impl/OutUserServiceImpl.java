package com.pajx.server.app.service.one.impl;


import com.pajx.server.app.dao.one.IOutUserDao;
import com.pajx.server.app.service.one.IOutUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by taller on 15/1/9.
 */
@Service
@Transactional
public class OutUserServiceImpl implements IOutUserService {

    private IOutUserDao outUserDao;
    @Resource
    public void setOutUserDao(IOutUserDao outUserDao) {
        this.outUserDao = outUserDao;
    }

    @Override
    public Object[] getByOutUser(String username) {
        List usersList=outUserDao.getByOutUser(username);
        if(usersList!=null&&!usersList.isEmpty()){
            Object[] o= (Object[]) usersList.get(0);
            return o;
        }
        return null;
    }
}
