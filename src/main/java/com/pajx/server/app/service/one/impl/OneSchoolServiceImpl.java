package com.pajx.server.app.service.one.impl;


import com.pajx.server.app.dao.ISchoolDao;
import com.pajx.server.app.service.one.IOneSchoolService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by taller on 15/1/9.
 */
@Service
@Transactional
public class OneSchoolServiceImpl implements IOneSchoolService {
    private ISchoolDao schoolDao;
    @Resource
    public void setSchoolDao(ISchoolDao schoolDao) {
        this.schoolDao = schoolDao;
    }

    @Override
    public List getSchoolDetail(String sclid) throws Exception{
        StringBuffer sclSql = new StringBuffer();
        sclSql.append(" SELECT sch.SCL_NAME,(SELECT AREA_NAME FROM SYS_AREA area where area.AREA_CODE=sch.AREA_CODE ) as AREA_NAME,");
        sclSql.append(" (SELECT count(1) FROM HS_EQUIPMENT_INFO equ where equ.EQU_STATUS_FLAG='1' and equ.EQU_TYPE='0' and equ.SCL_ID=sch.SCL_ID) as EQU_ATT_NUM,");
        sclSql.append(" (SELECT count(1) FROM HS_EQUIPMENT_INFO equ where equ.EQU_STATUS_FLAG='1' and equ.EQU_TYPE='1' and equ.SCL_ID=sch.SCL_ID) as EQU_TEL_NUM,");
        sclSql.append(" (SELECT ISU_NAME FROM SYS_INSIDE_USER isu WHERE isu.ISU_ID =sch.SALE_USER_ID AND isu.ISU_STATUS_FLAG='1') AS SALE_USER_NAME,");
        sclSql.append(" (SELECT ou.OSU_NAME FROM HOME_SCHOOL.SYS_OUTSIDE_USER ou WHERE ou.OSU_ID =sch.MOB_MANAGER_ID AND ou.OSU_STATUS_FLAG='1') AS MOB_MANAGER_NAME,");
        sclSql.append(" (SELECT count(1) FROM HS_STUDENT_INFO stu where stu.GRADUATE_FLAG='0' and stu.STU_STATUS_FLAG='1' and stu.SCL_ID=sch.SCL_ID)as INSCH_NUM,");
        sclSql.append(" (SELECT count(1) FROM HOME_SCHOOL.BOSS_BIZBILL biz,HOME_SCHOOL.HS_FAMILY_INFO fam,HOME_SCHOOL.HS_STUDENT_INFO stu WHERE biz.BILL_ID=fam.LAST_BIZ_ID AND fam.STU_ID=stu.STU_ID AND biz.BILL_STATUS_FLAG ='4' AND biz.ORD_TYPE='03' AND fam.FAM_STATUS_FLAG ='1' AND stu.STU_STATUS_FLAG  ='1' AND stu.GRADUATE_FLAG='0'AND stu.SCL_ID=sch.SCL_ID) as INSCH_OPEN_NUM,");
        sclSql.append(" (SELECT count(1) FROM HOME_SCHOOL.BOSS_BIZBILL biz, HOME_SCHOOL.HS_FAMILY_INFO fam, HOME_SCHOOL.HS_STUDENT_INFO stu WHERE biz.BILL_ID=fam.LAST_BIZ_ID AND fam.STU_ID=stu.STU_ID AND biz.BILL_STATUS_FLAG ='4' AND biz.ORD_TYPE         ='03' AND fam.FAM_STATUS_FLAG  ='1' AND stu.STU_STATUS_FLAG  ='1' AND stu.GRADUATE_FLAG    ='0' AND biz.DEAL_TIME like (select to_char(sysdate,'yyyyMMdd') from dual)||'%' AND biz.DEAL_TIME  IS NOT NULL AND stu.SCL_ID=sch.SCL_ID) as DAY_OPEN_NUM,");
        sclSql.append(" (SELECT count(1) FROM HOME_SCHOOL.BOSS_BIZBILL biz,HOME_SCHOOL.HS_FAMILY_INFO fam,HOME_SCHOOL.HS_STUDENT_INFO stu WHERE biz.BILL_ID        =fam.LAST_BIZ_ID AND fam.STU_ID           =stu.STU_ID AND biz.BILL_STATUS_FLAG = '4' AND biz.ORD_TYPE IN ('04','09') AND fam.FAM_STATUS_FLAG  ='1' AND stu.STU_STATUS_FLAG  ='1' AND stu.GRADUATE_FLAG='0' AND biz.DEAL_TIME like (select to_char(sysdate,'yyyyMMdd') from dual)||'%' AND biz.DEAL_TIME IS NOT NULL AND stu.SCL_ID =sch.SCL_ID) as DAY_CLOSE_NUM,");
        sclSql.append(" sch.SCL_NUMBER,sch.INPUT_TIME,sch.CARD_TYPE_FLAG,sch.D_KEYA,");
        sclSql.append(" (SELECT ou.OSU_NAME FROM HOME_SCHOOL.SYS_OUTSIDE_USER ou WHERE ou.OSU_ID=sch.SCL_MANAGER_ID AND ou.OSU_STATUS_FLAG='1') AS SCL_MANAGER_NAME,");
        sclSql.append(" (SELECT count(1) FROM HOME_SCHOOL.BOSS_BIZBILL biz,HOME_SCHOOL.HS_FAMILY_INFO fam,HOME_SCHOOL.HS_STUDENT_INFO stu WHERE biz.BILL_ID        =fam.LAST_BIZ_ID AND fam.STU_ID           =stu.STU_ID AND biz.BILL_STATUS_FLAG ='4' AND biz.ORD_TYPE='03' AND fam.FAM_STATUS_FLAG  ='1' AND stu.STU_STATUS_FLAG  ='1' AND biz.price='A' AND stu.GRADUATE_FLAG='0' AND stu.SCL_ID=sch.SCL_ID) as A1,");
        sclSql.append(" (SELECT count(1) FROM HOME_SCHOOL.BOSS_BIZBILL biz,HOME_SCHOOL.HS_FAMILY_INFO fam,HOME_SCHOOL.HS_STUDENT_INFO stu WHERE biz.BILL_ID =fam.LAST_BIZ_ID AND fam.STU_ID =stu.STU_ID AND biz.BILL_STATUS_FLAG ='4' AND biz.ORD_TYPE='03' AND fam.FAM_STATUS_FLAG  ='1' AND stu.STU_STATUS_FLAG  ='1' AND biz.price='B' AND stu.GRADUATE_FLAG='0' AND stu.SCL_ID =sch.SCL_ID) as B1,");
        sclSql.append(" (SELECT count(1) FROM HOME_SCHOOL.BOSS_BIZBILL biz,HOME_SCHOOL.HS_FAMILY_INFO fam, HOME_SCHOOL.HS_STUDENT_INFO stu WHERE biz.BILL_ID        =fam.LAST_BIZ_ID AND fam.STU_ID=stu.STU_ID AND biz.BILL_STATUS_FLAG ='4' AND biz.ORD_TYPE ='03'AND fam.FAM_STATUS_FLAG  ='1' AND stu.STU_STATUS_FLAG  ='1' AND biz.price='C' AND stu.GRADUATE_FLAG='0' AND stu.SCL_ID =sch.SCL_ID) as C1,");
        sclSql.append(" (SELECT count(1) FROM HOME_SCHOOL.BOSS_BIZBILL biz,HOME_SCHOOL.HS_FAMILY_INFO fam,HOME_SCHOOL.HS_STUDENT_INFO stu WHERE biz.BILL_ID =fam.LAST_BIZ_ID AND fam.STU_ID  =stu.STU_ID AND biz.BILL_STATUS_FLAG ='4' AND biz.ORD_TYPE='03' AND fam.FAM_STATUS_FLAG='1' AND stu.STU_STATUS_FLAG='1' AND biz.price='D' AND stu.GRADUATE_FLAG='0' AND stu.SCL_ID=sch.SCL_ID) as D1");
       sclSql.append(" ,(SELECT ou.OSU_PHONE FROM SYS_OUTSIDE_USER ou WHERE ou.OSU_ID =sch.MOB_MANAGER_ID AND ou.OSU_STATUS_FLAG='1') AS OSU_PHONE");
        sclSql.append(" ,(SELECT ISU_NAME FROM SYS_INSIDE_USER isu WHERE isu.ISU_ID =sch.SALE_KEEP_ID AND isu.ISU_STATUS_FLAG='1') AS SALE_KEEP_NAME");
        sclSql.append(" ,(SELECT AREA_NAME FROM SYS_AREA area where area.AREA_CODE=sch.COUNTY_CODE ) as COUNTY_NAME");
        sclSql.append(" FROM HOME_SCHOOL.HS_SCHOOL_INFO sch where 1=1");
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
    public List getSchoolDetail(String sclid,String startdate, String enddate) throws Exception {
        StringBuffer sclSql = new StringBuffer();
            sclSql.append(" SELECT * FROM(SELECT count(1) as count1 FROM HOME_SCHOOL.BOSS_BIZBILL biz,HOME_SCHOOL.HS_FAMILY_INFO fam,HOME_SCHOOL.HS_STUDENT_INFO stu WHERE biz.BILL_ID=fam.LAST_BIZ_ID AND fam.STU_ID=stu.STU_ID AND biz.BILL_STATUS_FLAG in ('1','2','3') AND biz.ORD_TYPE ='03' AND fam.BIZ_STATUS_FLAG='0' AND fam.FAM_STATUS_FLAG='1' AND stu.STU_STATUS_FLAG='1'  AND stu.GRADUATE_FLAG='0' AND biz.APPLY_TIME>='"+startdate+"' AND biz.APPLY_TIME<'"+enddate+"' AND biz.APPLY_TIME IS NOT NULL AND stu.SCL_ID='"+sclid+"'),");
            sclSql.append(" (SELECT count(1) as count2 FROM HOME_SCHOOL.BOSS_BIZBILL biz,HOME_SCHOOL.HS_FAMILY_INFO fam,HOME_SCHOOL.HS_STUDENT_INFO stu WHERE biz.BILL_ID=fam.LAST_BIZ_ID AND fam.STU_ID=stu.STU_ID AND biz.BILL_STATUS_FLAG ='4' AND biz.ORD_TYPE ='03' AND fam.FAM_STATUS_FLAG='1' AND stu.STU_STATUS_FLAG='1'  AND stu.GRADUATE_FLAG='0' AND biz.DEAL_TIME>='"+startdate+"' AND biz.DEAL_TIME<'"+enddate+"' AND biz.DEAL_TIME IS NOT NULL AND stu.SCL_ID='"+sclid+"' ),");
            sclSql.append(" (SELECT count(1) as count3 FROM HOME_SCHOOL.BOSS_BIZBILL biz,HOME_SCHOOL.HS_FAMILY_INFO fam,HOME_SCHOOL.HS_STUDENT_INFO stu WHERE biz.BILL_ID=fam.LAST_BIZ_ID AND fam.STU_ID=stu.STU_ID AND biz.BILL_STATUS_FLAG = '4' AND biz.ORD_TYPE in ('04','09') AND fam.FAM_STATUS_FLAG='1' AND stu.STU_STATUS_FLAG='1'  AND stu.GRADUATE_FLAG='0' AND biz.DEAL_TIME>='"+startdate+"' AND biz.DEAL_TIME<'"+enddate+"' AND biz.DEAL_TIME IS NOT NULL AND stu.SCL_ID='"+sclid+"' ),");
            sclSql.append(" (SELECT count(1) as count4 FROM HOME_SCHOOL.BOSS_BIZBILL biz,HOME_SCHOOL.HS_FAMILY_INFO fam,HOME_SCHOOL.HS_STUDENT_INFO stu WHERE biz.BILL_ID=fam.LAST_BIZ_ID AND fam.STU_ID=stu.STU_ID AND biz.BILL_STATUS_FLAG in ('1','2','3') AND biz.ORD_TYPE in ('04','09') AND fam.BIZ_STATUS_FLAG='0' AND fam.FAM_STATUS_FLAG='1' AND stu.STU_STATUS_FLAG='1' AND stu.GRADUATE_FLAG='0' AND biz.APPLY_TIME>='"+sclid+"' AND biz.APPLY_TIME<'"+enddate+"' AND biz.APPLY_TIME IS NOT NULL AND stu.SCL_ID='"+sclid+"')");
        List schoolsList = schoolDao.getSql(sclSql.toString());
        return schoolsList;
    }
    @Override
    public List getAreaCode(String deptcode) throws Exception {
        StringBuffer sb=new StringBuffer();
        sb.append("select AREA_CODE from SYS_DEPT_AREA where DEPT_CODE='").append(deptcode).append("'");
        List schoolsList = schoolDao.getAreaCode(sb.toString());
        return schoolsList;
    }
}