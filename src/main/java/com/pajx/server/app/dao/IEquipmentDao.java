package com.pajx.server.app.dao;




import com.pajx.server.app.base.IBaseDao;
import com.pajx.server.app.entity.Equipment;

/**
 * Created by taller on 15/1/9.
 */
public interface IEquipmentDao extends IBaseDao<Equipment> {
  public Equipment getByNo(String hql) throws Exception;
}
