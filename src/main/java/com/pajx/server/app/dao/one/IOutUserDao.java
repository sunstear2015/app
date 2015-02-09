package com.pajx.server.app.dao.one;




import com.pajx.server.app.base.IBaseDao;

import java.util.List;

/**
 * Created by taller on 15/1/9.
 */
public interface IOutUserDao extends IBaseDao {
    public List getSql(String sql);
    public List getByOutUser(String username) ;
}
