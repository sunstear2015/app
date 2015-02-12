package com.pajx.server.app.dao.impl;


import com.pajx.server.app.base.BaseDaoImpl;
import com.pajx.server.app.dao.IEquipmentDao;
import com.pajx.server.app.entity.Equipment;
import org.springframework.stereotype.Repository;

/**
 * Created by taller on 15/1/9.
 */
@Repository
public  class EquipmentDaoImpl extends BaseDaoImpl<Equipment> implements IEquipmentDao {
    @Override
    public Equipment getById(String equid)throws Exception{
        return (Equipment)this.getCurrentSession().get(Equipment.class,equid);
    }

    @Override
    public void delete(String uuid) throws Exception {
        Equipment equ=getById(uuid);
        if (equ!=null)
        this.getCurrentSession().delete(equ);
    }

    @Override
    public void save(Equipment entity) throws Exception {
        this.getCurrentSession().save(entity);
    }
    @Override
    public void update(Equipment entity) throws Exception {
        this.getCurrentSession().update(entity);
    }
    public Equipment getByNo(String hql) throws Exception{
        return (Equipment) this.getCurrentSession().createQuery(hql).uniqueResult();    }
}
