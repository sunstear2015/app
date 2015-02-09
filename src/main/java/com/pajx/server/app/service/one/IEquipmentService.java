package com.pajx.server.app.service.one;





import com.pajx.server.app.entity.Equipment;

import java.util.List;

/**
 * Created by taller on 15/1/9.
 */
public interface IEquipmentService {
    public List<Equipment> geEquipments(String sclid) throws Exception;
    public void save(Equipment equipment)throws  Exception;
    public void update(Equipment equipment)throws  Exception;
    public void delete(String uuid)throws  Exception;
    public Equipment getById(String equId) throws  Exception;
    public Equipment getByNo(int equno,int type) throws  Exception;
}

