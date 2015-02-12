package com.pajx.server.app.dao.impl;


import com.pajx.server.app.base.BaseDaoImpl;
import com.pajx.server.app.dao.IRoleDao;
import com.pajx.server.app.dao.ISaleDao;
import com.pajx.server.app.service.two.ISaleService;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by taller on 15/1/9.
 */
@Repository
public  class SaleDaoImpl extends BaseDaoImpl implements ISaleDao {
    @Override
    public List getSaleWeek(String sql) throws Exception {
            return this.getCurrentSession().createSQLQuery(sql).list();
    }

    @Override
    public List getSaleYear(String sql) throws Exception {
        return this.getCurrentSession().createSQLQuery(sql).list();
    }
}
