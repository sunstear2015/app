package com.pajx.server.app.base;

import java.util.List;

/**
 *  通用数据持久化操作接口
 *  @param<T> //需进行持久化操作的对象类型
 * Created by taller on 15/1/12.
 */
public interface IBaseDao<T>{
    /**
     * 保存实体
     * @param entity  需要保存的实体对象
    *
   */
    void save(T entity);
    /**
     * 删除实体
     * @param uuid //需要删除实体的uuid标识
    */
    void delete(String uuid);
    /**
     * 更新实体
     * @param entity //需要更新的实体对象
     *
    */
    void update(T entity);
    /**
    *根据uuid查询对象
     * @param uuid //需要查询对象的uuid标识
     * @return 符合条件的对象
    */
    T getById(String uuid);
    /**
     * 查询所有
     * @return 集合
    */
    @Deprecated
    List<T> findAll();
    /**
     * 查询所有
     * @param hql
     * @return list
    */
    List<T> findList(String hql);
    /**
     * 查询所有
     * @param sql
     * @return list
     */
    List<T> getByJdbcSql(String sql);
}
