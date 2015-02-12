package com.pajx.server.app.service.one.impl;


import com.pajx.server.app.dao.IUserDao;
import com.pajx.server.app.service.one.IUserService;
import com.pajx.server.app.utils.common.DicUtils;
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
    public Object[] getByUserName(String username,String type)throws Exception{
        List usersList=userDao.getByUsername(username);
        if(usersList!=null&&!usersList.isEmpty()){
           Object[] o= (Object[]) usersList.get(0);
            return o;
        }
        return null;
    }

    @Override
    public List getSearchUser(String type, String content) throws Exception {
        String sql="";
        if (type.equals("2")) {
             sql="SELECT stu.STU_NO,fam.CREATE_TIME FROM HS_FAMILY_INFO fam,HS_STUDENT_INFO stu WHERE  fam.STU_ID=stu.STU_ID AND fam.FAM_STATUS_FLAG='1' AND stu.STU_STATUS_FLAG='1' and fam.FAM_PHONE='"+content+"' GROUP BY stu.STU_NO,fam.CREATE_TIME ";
        }
        if (type.equals("1")) {
            sql="SELECT stu.STU_NO,STU_ID FROM HS_STUDENT_INFO stu WHERE   stu.STU_STATUS_FLAG='1' and stu.STU_NO='"+content+"' ";
        }
        List usersList=userDao.getSql(sql);
        return usersList;
    }

    @Override
    public List getSearchUser(String stuno) throws Exception {
        StringBuffer sb=new StringBuffer();
        sb.append(" SELECT stu.STU_NO,(select scl.SCL_NAME from HS_SCHOOL_INFO scl where scl.SCL_ID=stu.SCL_ID) as SCL_NAME,");
        sb.append(" (SELECT gra.GRA_NAME||'('||gra.GRA_SHOW_NAME||')' FROM HS_GRADE_INFO gra where gra.GRA_ID=stu.GRA_ID) as GRA_NAME,");
        sb.append(" (SELECT cls.CLS_NAME||'('||cls.CLS_SHOW_NAME||')' FROM HS_CLASS_INFO cls where cls.CLS_ID=stu.CLS_ID) as CLS_NAME,");
        sb.append(" stu.STU_NAME,stu.GRADUATE_FLAG,stu.STU_ID");
        sb.append(" FROM HS_STUDENT_INFO stu WHERE  stu.STU_STATUS_FLAG='1' AND stu.STU_NO='").append(stuno).append("'");
        List usersList=userDao.getSql(sb.toString());
        return usersList;
    }

    @Override
    public List getSearchUserId(String stuid) throws Exception {
        StringBuffer sb=new StringBuffer();
        sb.append(" SELECT fam.FAM_RELATION||'('||fam.FAM_ORDER||')',fam.FAM_PHONE,biz.PRICE,fam.LAST_DEAL_TIME,biz.ORD_TYPE,biz.BILL_STATUS_FLAG");
        sb.append(" FROM HS_FAMILY_INFO fam  ");
        sb.append(" left join BOSS_BIZBILL biz on  biz.BILL_ID=fam.LAST_BIZ_ID");
        sb.append(" where fam.FAM_STATUS_FLAG='1'");
        sb.append(" and fam.STU_ID='").append(stuid).append("'");
        sb.append(" GROUP BY fam.FAM_PHONE,biz.ORD_TYPE,biz.BILL_STATUS_FLAG,fam.LAST_DEAL_TIME,fam.FAM_ORDER,fam.FAM_RELATION,biz.PRICE");
        List usersList=userDao.getSql(sb.toString());
        return usersList;
    }

    @Override
    public List getDept() throws Exception {
        StringBuffer sb=new StringBuffer();
        sb.append(" SELECT dept.DEPT_CODE,dept.DEPT_NAME FROM SYS_DEPARTMENT dept where dept.PARENT_CODE='").append(DicUtils.DEPT_PARENT).append("'");
        List usersList=userDao.getSql(sb.toString());
        return usersList;
    }
}
