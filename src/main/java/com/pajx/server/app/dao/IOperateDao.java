package com.pajx.server.app.dao;




import com.pajx.server.app.base.IBaseDao;

import java.util.List;

/**
 * Created by taller on 15/1/9.
 */
public interface IOperateDao extends IBaseDao {
    public List getOperate(String sql) throws Exception;
}
