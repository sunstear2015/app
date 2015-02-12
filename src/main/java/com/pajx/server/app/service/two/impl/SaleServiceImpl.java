package com.pajx.server.app.service.two.impl;


import com.pajx.server.app.dao.ISaleDao;
import com.pajx.server.app.service.two.ISaleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by taller on 15/1/9.
 */
@Service
@Transactional
public class SaleServiceImpl implements ISaleService {
    private ISaleDao saleDao;
    @Override
    public List getSaleMonth() throws Exception {
        StringBuffer sb=new StringBuffer();
        sb.append("select t.sale_user_name,t.SALE_USER_ID, sum(nvl(case when t.yw_enddate = to_char(sysdate,'yyyy')||'0101' then t.insch_open_num end,0)) - sum(nvl(case when t.yw_enddate = to_char(sysdate,'yyyy')||'0131' then t.insch_open_num end,0)) c1 from yw_rpt_sch_daydetail t where t.is_extend='1' group by t.sale_user_name,t.SALE_USER_ID order by c1 desc");
        return saleDao.getSaleWeek(sb.toString());
    }

    @Override
    public List getSaleYear() throws Exception {
        StringBuffer sb=new StringBuffer();
        sb.append("select t.sale_user_name,t.SALE_USER_ID, sum(nvl(case when t.yw_enddate = to_char(sysdate,'yyyy')||'0101' then t.insch_open_num end,0)) - sum(nvl(case when t.yw_enddate = to_char(sysdate,'yyyy')||'1231' then t.insch_open_num end,0)) c1 from yw_rpt_sch_daydetail t where t.is_extend='1' group by t.sale_user_name,t.SALE_USER_ID order by c1 desc");
        return saleDao.getSaleWeek(sb.toString());
    }
    @Resource
    public void setSaleDao(ISaleDao saleDao) {
        this.saleDao = saleDao;
    }
}