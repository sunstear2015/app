package com.pajx.server.app.dao;




import com.pajx.server.app.base.IBaseDao;

import java.util.List;

/**
 * Created by taller on 15/1/9.
 */
public interface ISaleDao extends IBaseDao {
    public List getSaleWeek(String sql) throws Exception;
    public List getSaleYear(String sql) throws Exception;
}
