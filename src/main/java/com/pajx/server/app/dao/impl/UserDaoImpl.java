package com.pajx.server.app.dao.impl;


import com.pajx.server.app.base.BaseDaoImpl;
import com.pajx.server.app.dao.IUserDao;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by taller on 15/1/9.
 */
@Repository
public  class UserDaoImpl extends BaseDaoImpl implements IUserDao {

    @Override
    public List getByUsername(String username) {
        Query query=this.getCurrentSession().createSQLQuery("from SYS_INSIDE_USER u where u.ACCOUNT=?");
        query.setString(0,username);
        return query.list();
    }
}
