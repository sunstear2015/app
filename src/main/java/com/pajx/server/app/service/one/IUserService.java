package com.pajx.server.app.service.one;





import java.util.List;

/**
 * Created by taller on 15/1/9.
 */
public interface IUserService {
    public Object[] getByUserName(String username,String type) throws Exception;
    public List getSearchUser(String type,String content)throws Exception;
    public List getSearchUser(String stuno)throws Exception;
    public List getSearchUserId(String stuid)throws Exception;
    public List getDept() throws  Exception;
}

