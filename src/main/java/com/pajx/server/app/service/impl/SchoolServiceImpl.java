package com.pajx.server.app.service.impl;


import com.pajx.server.app.dao.IRoleDao;
import com.pajx.server.app.dao.ISchoolDao;
import com.pajx.server.app.dao.IUserDao;
import com.pajx.server.app.service.ISchoolService;
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
public class SchoolServiceImpl implements ISchoolService {

    private ISchoolDao schoolDao;
    private IRoleDao roleDao;
    private IUserDao userDao;

    @Resource
    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }
    @Resource
    public void setSchoolDao(ISchoolDao schoolDao) {
        this.schoolDao = schoolDao;
    }

    @Resource
    public void setRoleDao(IRoleDao roleDao) {
        this.roleDao = roleDao;
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
    public List getSchools(String uuid, String rolecode,String deptcode,String managerflag) {
        //全部数据
        if (rolecode.equals("1001") || rolecode.equals("1202") || rolecode.equals("1401") || rolecode.equals("1402") || rolecode.equals("1201")) {
            List schoolsList = schoolDao.getSchools(uuid, rolecode);
            if (schoolsList != null && !schoolsList.isEmpty()) {
                return schoolsList;
            }
            return null;
        } else {
            StringBuffer roleSql = new StringBuffer();
            roleSql.append("select r.USR_DATA_RANGE,r.USR_DATA_RANGE,r.USR_GROUP_CODE from SYS_USER_ROLE r where 1=1");
            roleSql.append(" and r.USR_CODE='").append(rolecode).append("'");
            List roles = roleDao.getSql(roleSql.toString());
            if(roles!=null&&!roles.isEmpty()){
                Object[] o= (Object[]) roles.get(0);
                //个人数据
                if (o[1].equals("2")) {
                    StringBuffer sclSql = new StringBuffer();
                    sclSql.append("select s.* from HS_SCHOOL_INFO s  where 1=1");
                    sclSql.append(" and s.SALE_USER_ID='").append(uuid).append("'");
                    List schoolsList2 = schoolDao.getSql(sclSql.toString());
                    if (schoolsList2 != null && !schoolsList2.isEmpty()) {
                        return schoolsList2;
                    }
                    //部门数据
                }else if (o[1].equals("1")) {
                     if (managerflag.equals("1")){
                         StringBuffer userSql = new StringBuffer();
                         userSql.append("s.SCL_ID,s.SCL_NAME from SYS_INSIDE_USER u,HS_SCHOOL_INFO s where u.DEPT_CODE='");
                         userSql.append(" and u.DEPT_CODE'").append(deptcode).append("'").append(" and u.ISU_ID=s.SALE_USER_ID");
                        List schoolsList3=userDao.getSql(userSql.toString());
                         if (schoolsList3 != null && !schoolsList3.isEmpty()) {
                             return schoolsList3;
                         }
                     }else{
                         StringBuffer sclSql = new StringBuffer();
                         sclSql.append("select s.* from HS_SCHOOL_INFO s  where 1=1");
                         sclSql.append(" and s.SALE_USER_ID='").append(uuid).append("'");
                         List schoolsList2 = schoolDao.getSql(sclSql.toString());
                         if (schoolsList2 != null && !schoolsList2.isEmpty()) {
                             return schoolsList2;
                         }
                     }
                }
            }
        }
        return null;
    }
}