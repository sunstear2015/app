package com.pajx.server.app.dao.impl;


import com.pajx.server.app.base.BaseDaoImpl;
import com.pajx.server.app.dao.IOutUserDao;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by taller on 15/1/9.
 */
@Repository
public  class OutUserDaoImpl extends BaseDaoImpl implements IOutUserDao {
    @Override
    public List getSql(String sql) {
        return this.getCurrentSession().createSQLQuery(sql).list();
    }
    @Override
    public List getByOutUser(String username) {
        StringBuffer sb = new StringBuffer();
        sb.append("select u.ACCOUNT,u.PASSWORD,u.OSU_STATUS_FLAG from SYS_OUTSIDE_USER u where u.ACCOUNT='");
        sb.append(username).append("'");
        SQLQuery query=this.getCurrentSession().createSQLQuery(sb.toString());
        return query.list();
    }
}
