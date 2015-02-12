package com.pajx.server.app.service.two.impl;


import com.pajx.server.app.dao.ISchoolDao;
import com.pajx.server.app.service.two.ISchoolService;
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
    @Resource
    public void setSchoolDao(ISchoolDao schoolDao) {
        this.schoolDao = schoolDao;
    }
    @Override
    public List getSchools(String uuid, String rolecode, String deptcode, String managerflag) throws Exception {
        //全部数据  特殊权限
        if (rolecode.equals("1001") || rolecode.equals("1202") || rolecode.equals("1401") || rolecode.equals("1402") || rolecode.equals("1201")) {
            StringBuffer sclSql = new StringBuffer();
            sclSql.append("select sch.SCL_ID,sch.SCL_NAME,sch.YW_USE_RATE,sch.EQU_OVER_RATE,sch.EQU_FAULT_RATE,sch.MON_CLOSE_RATE,sch.INSCH_OPEN_NUM from YW_RPT_SCH_DAYDETAIL sch  where 1=1");
            sclSql.append(" and sch.YW_ENDDATE=(select max(p.YW_ENDDATE) from YW_RPT_SCH_DAYDETAIL p)");
            sclSql.append(" order by sch.SCL_NAME asc");
            List schoolsList = schoolDao.getSql(sclSql.toString());
            System.out.print(schoolsList);
            if (schoolsList != null && !schoolsList.isEmpty()) {
                return schoolsList;
            }
        } else {
            //部门数据
            if (managerflag.equals("1")&&rolecode.equals("1102")) {
                StringBuffer sclSql = new StringBuffer();
                sclSql.append("select  sch.SCL_ID,sch.SCL_NAME,sch.YW_USE_RATE,sch.EQU_OVER_RATE,sch.EQU_FAULT_RATE,sch.MON_CLOSE_RATE,sch.INSCH_OPEN_NUM from PAJX_DBAK.YW_RPT_SCH_DAYDETAIL sch  where 1=1");
                sclSql.append(" and sch.YW_ENDDATE=(select max(p.YW_ENDDATE) from PAJX_DBAK.YW_RPT_SCH_DAYDETAIL p)");
                sclSql.append(" and sch.AREA_CODE in (").append(deptcode).append(")");
                sclSql.append(" order by sch.SCL_NAME asc");
                List schoolsList3 = schoolDao.getSql(sclSql.toString());
                if (schoolsList3 != null && !schoolsList3.isEmpty()) {
                    return schoolsList3;
                }
            } else if (rolecode.equals("1103")) {
                StringBuffer sclSql = new StringBuffer();
                sclSql.append("select sch.SCL_NAME,sch.YW_USE_RATE,sch.EQU_OVER_RATE,sch.EQU_FAULT_RATE,sch.MON_CLOSE_RATE,sch.INSCH_OPEN_NUM from YW_RPT_SCH_DAYDETAIL sch  where 1=1");
                sclSql.append(" and sch.YW_ENDDATE=(select max(p.YW_ENDDATE) from YW_RPT_SCH_DAYDETAIL p)");
                sclSql.append(" and sch.SALE_KEEP_ID='").append(uuid).append("'");
                sclSql.append(" order by sch.SCL_NAME asc");
                List schoolsList2 = schoolDao.getSql(sclSql.toString());
                if (schoolsList2 != null && !schoolsList2.isEmpty()) {
                    return schoolsList2;
                }
            }else{
                StringBuffer sclSql = new StringBuffer();
                sclSql.append("select sch.SCL_NAME,sch.YW_USE_RATE,sch.EQU_OVER_RATE,sch.EQU_FAULT_RATE,sch.MON_CLOSE_RATE,sch.INSCH_OPEN_NUM from YW_RPT_SCH_DAYDETAIL sch  where 1=1");
                sclSql.append(" and sch.YW_ENDDATE=(select max(p.YW_ENDDATE) from YW_RPT_SCH_DAYDETAIL p)");
                sclSql.append(" and sch.SALE_USER_ID='").append(uuid).append("'");
                sclSql.append(" order by sch.SCL_NAME asc");
                List schoolsList2 = schoolDao.getSql(sclSql.toString());
                if (schoolsList2 != null && !schoolsList2.isEmpty()) {
                    return schoolsList2;
                }
            }
        }
        return null;
    }

    @Override
    public List getOutSchools(String uuid) {
        StringBuffer sclSql = new StringBuffer();
        sclSql.append("select sch.SCL_NAME,sch.YW_USE_RATE,sch.EQU_OVER_RATE,sch.EQU_FAULT_RATE,sch.MON_CLOSE_RATE from YW_RPT_SCH_DAYDETAIL sch  where 1=1");
        sclSql.append(" and sch.YW_ENDDATE=(select max(p.YW_ENDDATE) from YW_RPT_SCH_DAYDETAIL p)");
        sclSql.append(" order by sch.SCL_NAME asc");
        sclSql.append(" and sch.SALE_USER_ID='").append(uuid).append("'");
        sclSql.append(" order by sch.SCL_NAME asc");
        List schoolsList = schoolDao.getSql(sclSql.toString());
        if (schoolsList != null && !schoolsList.isEmpty()) {
            return schoolsList;
        }
        return null;
    }

    @Override
    public List getSchoolDetail(String sclid) throws Exception {
        StringBuffer sclSql = new StringBuffer();
        sclSql.append(" select sch.SCL_NAME,sch.AREA_NAME,sch.EQU_ATT_NUM,sch.EQU_TEL_NUM,sch.SALE_USER_NAME,sch.MOB_MANAGER_NAME,sch.INSCH_NUM,sch.INSCH_OPEN_NUM,sch.DAY_OPEN_NUM,sch.DAY_CLOSE_NUM,sch.YW_ENDDATE");
        sclSql.append(" from YW_RPT_SCH_DAYDETAIL sch  where 1=1");
        sclSql.append(" and sch.SCL_ID='").append(sclid).append("'");
        sclSql.append(" and sch.YW_ENDDATE=").append("(select max(p.YW_ENDDATE) from YW_RPT_SCH_DAYDETAIL p)");
        List schoolsList = schoolDao.getSql(sclSql.toString());
        return schoolsList;
    }

    @Override
    public List getSchoolDetail(String sclid, String enddate) throws Exception {
        StringBuffer sclSql = new StringBuffer();
        sclSql.append(" select sch.DAY_OPEN_NUM,sch.DAY_CLOSE_NUM,sch.DAY_OPEN_NUM,sch.DAY_CLOSE_NUM");
        sclSql.append(" from YW_RPT_SCH_DAYDETAIL sch  where 1=1");
        sclSql.append(" and sch.SCL_ID='").append(sclid).append("'");
        sclSql.append(" and sch.YW_ENDDATE='").append(enddate).append("'");
        List schoolsList = schoolDao.getSql(sclSql.toString());
        return schoolsList;
    }
}