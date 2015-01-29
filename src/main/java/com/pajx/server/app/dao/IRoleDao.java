package com.pajx.server.app.dao;




import com.pajx.server.app.base.IBaseDao;

import java.util.List;

/**
 * Created by taller on 15/1/9.
 */
public interface IRoleDao extends IBaseDao {
    public List getSql(String sql);
}
