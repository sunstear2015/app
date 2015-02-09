package com.pajx.server.app.service.two;





import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * Created by taller on 15/1/9.
 */
public interface ISchoolService {
    public List getSchoolDetail(String sclid) throws Exception;
    public List getSchoolDetail(String sclid,String enddate) throws Exception;
    public List getSchools(String uuid, String rolecode,String deptcode,String managerflag) throws Exception;
    public List getOutSchools(String uuid);
}

