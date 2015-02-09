package com.pajx.server.app.service.one.impl;


import com.pajx.server.app.dao.one.ISchoolDao;
import com.pajx.server.app.service.one.IOneSchoolService;
import com.pajx.server.app.service.one.IOperateService;
import com.pajx.server.app.utils.database.CustomerContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by taller on 15/1/9.
 */
@Service
@Transactional
public class OperateServiceImpl implements IOperateService {
    private ISchoolDao schoolDao;
    @Resource
    public void setSchoolDao(ISchoolDao schoolDao) {
        this.schoolDao = schoolDao;
    }

    @Override
    public List getSchoolDetail(String sclid) throws Exception{
        StringBuffer sclSql = new StringBuffer();
        sclSql.append(" select sch.SCL_NUMBER,sch.INPUT_TIME,sch.CARD_TYPE_FLAG,sch.D_KEYA,(SELECT ou.OSU_NAME from HOME_SCHOOL.SYS_OUTSIDE_USER  ou where ou.OSU_ID=sch.SCL_MANAGER_ID and ou.OSU_STATUS_FLAG='1') as SCL_MANAGER_NAME");
        sclSql.append(" from HOME_SCHOOL.HS_SCHOOL_INFO  sch  where 1=1");
        sclSql.append(" and sch.SCL_ID='").append(sclid).append("'");
        List schoolsList = schoolDao.getSql(sclSql.toString());
        return schoolsList;
    }
    @Override
    public List getSchoolDetail(String sclid,String startdate, String enddate,String type) throws Exception {
        StringBuffer sclSql = new StringBuffer();
        if (type .equals("1")) {//开通中
            sclSql.append(" SELECT substr(biz.APPLY_TIME,1,8),count(*) as count");
            sclSql.append(" FROM HOME_SCHOOL.BOSS_BIZBILL biz,HOME_SCHOOL.HS_FAMILY_INFO fam,HOME_SCHOOL.HS_STUDENT_INFO stu");
            sclSql.append(" WHERE biz.BILL_ID=fam.LAST_BIZ_ID AND fam.STU_ID=stu.STU_ID");
            sclSql.append(" AND biz.BILL_STATUS_FLAG in ('1','2','3')");
            sclSql.append(" AND biz.ORD_TYPE ='03'");
            sclSql.append(" AND fam.BIZ_STATUS_FLAG='0'");
            sclSql.append(" AND fam.FAM_STATUS_FLAG='1'");
            sclSql.append(" AND stu.STU_STATUS_FLAG='1'");
            sclSql.append(" AND biz.price='D'");
            sclSql.append(" AND stu.GRADUATE_FLAG='0'");
            sclSql.append(" AND biz.APPLY_TIME>='").append(startdate+"000000").append("'");
            sclSql.append(" AND biz.APPLY_TIME<'").append(enddate+"000000").append("'");
            sclSql.append(" AND biz.APPLY_TIME IS NOT NULL");
            sclSql.append(" AND stu.SCL_ID='").append(sclid).append("'");
            sclSql.append(" GROUP BY substr(biz.APPLY_TIME,1,8)");
        }else if (type .equals("2")) {//--已开通
            sclSql.append(" SELECT substr(biz.DEAL_TIME,1,8),count(*) as count");
            sclSql.append(" FROM HOME_SCHOOL.BOSS_BIZBILL biz,HOME_SCHOOL.HS_FAMILY_INFO fam,HOME_SCHOOL.HS_STUDENT_INFO stu");
            sclSql.append(" WHERE biz.BILL_ID=fam.LAST_BIZ_ID AND fam.STU_ID=stu.STU_ID");
            sclSql.append(" AND biz.BILL_STATUS_FLAG ='4'");
            sclSql.append(" AND biz.ORD_TYPE ='03'");
            //sclSql.append(" AND fam.BIZ_STATUS_FLAG='1'");
            sclSql.append(" AND fam.FAM_STATUS_FLAG='1'");
            sclSql.append(" AND stu.STU_STATUS_FLAG='1'");
            sclSql.append(" AND biz.price='D'");
            sclSql.append(" AND stu.GRADUATE_FLAG='0'");
            sclSql.append(" AND biz.DEAL_TIME>='").append(startdate+"000000").append("'");
            sclSql.append(" AND biz.DEAL_TIME<'").append(enddate+"000000").append("'");
            sclSql.append(" AND biz.DEAL_TIME IS NOT NULL");
            sclSql.append(" AND stu.SCL_ID='").append(sclid).append("'");
            sclSql.append(" GROUP BY substr(biz.DEAL_TIME,1,8)");
        }else if (type .equals("3")) {//---已退订
            sclSql.append(" SELECT  substr(biz.DEAL_TIME,1,8),count(*) as count");
            sclSql.append(" FROM HOME_SCHOOL.BOSS_BIZBILL biz,HOME_SCHOOL.HS_FAMILY_INFO fam,HOME_SCHOOL.HS_STUDENT_INFO stu");
            sclSql.append(" WHERE biz.BILL_ID=fam.LAST_BIZ_ID AND fam.STU_ID=stu.STU_ID");
            sclSql.append(" AND biz.BILL_STATUS_FLAG = '4'");
            sclSql.append(" AND biz.ORD_TYPE in ('04','09')");
           // sclSql.append(" AND fam.BIZ_STATUS_FLAG='1'");
            sclSql.append(" AND fam.FAM_STATUS_FLAG='1'");
            sclSql.append(" AND stu.STU_STATUS_FLAG='1'");
            sclSql.append(" AND biz.price='D'");
            sclSql.append(" AND stu.GRADUATE_FLAG='0'");
            sclSql.append(" AND biz.DEAL_TIME>='").append(startdate+"000000").append("'");
            sclSql.append(" AND biz.DEAL_TIME<'").append(enddate+"000000").append("'");
            sclSql.append(" AND biz.DEAL_TIME IS NOT NULL");
            sclSql.append(" AND stu.SCL_ID='").append(sclid).append("'");
            sclSql.append(" GROUP BY substr(biz.DEAL_TIME,1,8)");
        }else{//---退订处理中
            sclSql.append(" SELECT substr(biz.APPLY_TIME,1,8),count(*) as count");
            sclSql.append(" FROM HOME_SCHOOL.BOSS_BIZBILL biz,HOME_SCHOOL.HS_FAMILY_INFO fam,HOME_SCHOOL.HS_STUDENT_INFO stu");
            sclSql.append(" WHERE biz.BILL_ID=fam.LAST_BIZ_ID AND fam.STU_ID=stu.STU_ID");
            sclSql.append(" AND biz.BILL_STATUS_FLAG in ('1','2','3')");
            sclSql.append(" AND biz.ORD_TYPE in ('04','09')");
            sclSql.append(" AND fam.BIZ_STATUS_FLAG='0'");
            sclSql.append(" AND fam.FAM_STATUS_FLAG='1'");
            sclSql.append(" AND stu.STU_STATUS_FLAG='1'");
            sclSql.append(" AND biz.price='D'");
            sclSql.append(" AND stu.GRADUATE_FLAG='0'");
            sclSql.append(" AND biz.APPLY_TIME>='").append(startdate+"000000").append("'");
            sclSql.append(" AND biz.APPLY_TIME<'").append(enddate+"000000").append("'");
            sclSql.append(" AND biz.APPLY_TIME IS NOT NULL");
            sclSql.append(" AND stu.SCL_ID='").append(sclid).append("'");
            sclSql.append(" GROUP BY substr(biz.APPLY_TIME,1,8)");
        }
        List schoolsList = schoolDao.getSql(sclSql.toString());
        return schoolsList;
    }

    @Override
    public List getAreaCode(String deptcode) throws Exception {
        StringBuffer sb=new StringBuffer();
        sb.append("select AREA_CODE from SYS_DEPT_AREA ");
        System.out.println("now db =======>"+ CustomerContextHolder.getCustomerType());
        List schoolsList = schoolDao.getAreaCode(sb.toString());
        return schoolsList;
    }
}