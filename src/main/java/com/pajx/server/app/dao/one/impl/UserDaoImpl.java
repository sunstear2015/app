package com.pajx.server.app.dao.one.impl;


import com.pajx.server.app.base.BaseDaoImpl;
import com.pajx.server.app.dao.one.IUserDao;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by taller on 15/1/9.
 */
@Repository
public  class UserDaoImpl extends BaseDaoImpl implements IUserDao {

    @Override
    public List getByUsername(String username) {
        StringBuffer sb = new StringBuffer();
        sb.append("select u.ACCOUNT,u.PASSWORD,u.ISU_STATUS_FLAG,u.ISU_GROUP_CODE,u.ISU_ROLE_CODE,u.DEPT_CODE,u.ISU_NAME,u.ISU_SEX,u.ISU_PHONE,u.MANAGER_FLAG,u.ISU_ID from SYS_INSIDE_USER u where u.ACCOUNT='");
        sb.append(username).append("'");
        SQLQuery query=this.getCurrentSession().createSQLQuery(sb.toString());
        return query.list();
    }

    @Override
    public List getSql(String sql) {

        return  this.getCurrentSession().createSQLQuery(sql).list();
    }
}
