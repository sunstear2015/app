package com.pajx.server.app.service.one;





import java.util.List;

/**
 * Created by taller on 15/1/9.
 */
public interface IOperateService {
    public List getSchoolDetail(String sclid) throws Exception;
    public List getSchoolDetail(String sclid, String startdate, String enddate, String type) throws Exception;
    public List getAreaCode(String sql) throws Exception;
}

