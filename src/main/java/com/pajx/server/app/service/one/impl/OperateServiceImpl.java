package com.pajx.server.app.service.one.impl;


import com.pajx.server.app.dao.IOperateDao;
import com.pajx.server.app.dao.ISchoolDao;
import com.pajx.server.app.service.one.IOperateService;
import com.pajx.server.app.utils.database.CustomerContextHolder;
import org.apache.commons.lang3.StringUtils;
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
    private IOperateDao operateDao;
    @Override
    public List getOperate(String deptcode) throws Exception {
        StringBuffer sb=new StringBuffer();
        sb.append(" SELECT * FROM (SELECT SUM(TOTAL_USER) as PER_ADD_NUM, SUM(PER_CLOSE_NUM) as PER_CLOSE_NUM,SUM(PER_OPEN_NUM) as PER_OPEN_NUM,YW_ENDDATE FROM YW_RPT_TOTAL_DAY");
        if (StringUtils.isNotEmpty(deptcode)) {
            sb.append(" where SALE_DEPT_ID = '").append(deptcode).append("'");
        }
        sb.append(" GROUP BY  YW_ENDDATE ORDER BY YW_ENDDATE  DESC )WHERE ROWNUM<=7 ORDER BY YW_ENDDATE");
        return operateDao.getOperate(sb.toString());
    }
    @Resource
    public void setOperateDao(IOperateDao operateDao) {
        this.operateDao = operateDao;
    }
}