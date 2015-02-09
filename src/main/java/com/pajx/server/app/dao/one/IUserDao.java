package com.pajx.server.app.dao.one;




import com.pajx.server.app.base.IBaseDao;

import java.util.List;

/**
 * Created by taller on 15/1/9.
 */
public interface IUserDao extends IBaseDao {
    public List getByUsername(String username) ;
    public List getSql(String sql);
}
