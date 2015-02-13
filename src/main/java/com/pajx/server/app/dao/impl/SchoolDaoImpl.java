package com.pajx.server.app.dao.impl;


import com.pajx.server.app.base.BaseDaoImpl;
import com.pajx.server.app.dao.ISchoolDao;
import com.pajx.server.app.utils.database.CustomerContextHolder;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by taller on 15/1/9.
 */
@Repository
public  class SchoolDaoImpl extends BaseDaoImpl implements ISchoolDao {

    @Override
    public List getSchools(String uuid,String code) {
        StringBuffer sb = new StringBuffer();
        sb.append("select s.* from HS_SCHOOL_INFO s  where 1=1");
       // sb.append(uuid).append("'");
        SQLQuery query=this.getCurrentSession().createSQLQuery(sb.toString());
        return query.list();
    }

    @Override
    public List getSql(String sql) {
        return this.getCurrentSession().createSQLQuery(sql).list();
    }

    @Override
    public List getAreaCode(String sql) {
       return this.getCurrentSession().createSQLQuery(sql).list();
    }
}
