package com.pajx.server.app.dao.one.impl;


import com.pajx.server.app.base.BaseDaoImpl;
import com.pajx.server.app.dao.one.IRoleDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by taller on 15/1/9.
 */
@Repository
public  class RoleDaoImpl extends BaseDaoImpl implements IRoleDao {
    @Override
    public List getSql(String sql) {
        return this.getCurrentSession().createSQLQuery(sql).list();
    }
}
