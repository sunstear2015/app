package com.pajx.server.app.service.one.impl;


import com.pajx.server.app.dao.IEquipmentDao;
import com.pajx.server.app.entity.Equipment;
import com.pajx.server.app.service.one.IEquipmentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by taller on 15/1/9.
 */
@Service
public class EquipmentServiceImpl implements IEquipmentService {
    private IEquipmentDao equipmentDao;
    @Resource
    public void setEquipmentDao(IEquipmentDao equipmentDao) {
        this.equipmentDao = equipmentDao;
    }
    @Override
    public List<Equipment> geEquipments(String sclid) throws Exception{
        StringBuffer hql = new StringBuffer();
        hql.append(" from Equipment equ where 1=1");
        hql.append(" and equ.SCL_ID='").append(sclid).append("'");
        hql.append(" and equ.EQU_STATUS_FLAG='1'");
        return equipmentDao.findList(hql.toString());
    }

    @Override
    @Transactional
    public void save(Equipment equipment)throws Exception {
        equipmentDao.save(equipment);
    }

    @Override
    public Equipment getById(String equId) throws Exception {
        return equipmentDao.getById(equId);
    }

    @Override
    @Transactional
    public void delete(String uuid) throws Exception {
        equipmentDao.delete(uuid);
    }

    @Override
    @Transactional
    public void update(Equipment equipment) throws Exception {
        equipmentDao.update(equipment);
    }

    @Override
    public Equipment getByNo(int equno,int type) throws Exception {
        String sql="from Equipment e where 1=1 and e.EQU_TYPE='"+type+"'and e.EQU_STATUS_FLAG='1' and e.EQU_NO="+equno;
        return equipmentDao.getByNo(sql);
    }
}