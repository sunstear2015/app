package com.pajx.server.app.service;





import java.util.List;

/**
 * Created by taller on 15/1/9.
 */
public interface ISchoolService {
    public void save();
    public void delete(String id);
    public List getAllUser();
    public void update();
    public List getSchools(String uuid, String rolecode,String deptcode,String managerflag);
}

