package com.pajx.server.app.dao.impl;


import com.pajx.server.app.base.BaseDaoImpl;
import com.pajx.server.app.dao.IOperateDao;
import com.pajx.server.app.dao.ISaleDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by taller on 15/1/9.
 */
@Repository
public  class OperateDaoImpl extends BaseDaoImpl implements IOperateDao {
    @Override
    public List getOperate(String sql) throws Exception {
        return this.getCurrentSession().createSQLQuery(sql).list();
    }
}
