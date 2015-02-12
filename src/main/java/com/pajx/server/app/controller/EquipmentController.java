package com.pajx.server.app.controller;


import com.alibaba.fastjson.JSONObject;
import com.pajx.server.app.base.BaseController;
import com.pajx.server.app.entity.Equipment;
import com.pajx.server.app.utils.security.MD5;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2015/2/2.
 */
@Controller
@Scope
@RequestMapping("/")
public class EquipmentController extends BaseController {
    /**
     * Description:     查看学校设备
     *
     * @param sclid
     * @param call_id 时间戳 System.currentTimeMillis()
     * @return json
     */
    @RequestMapping(value = "/api/v1/equipment",method = RequestMethod.POST)
    public
    @ResponseBody
    Object v1_sclquioment(@RequestParam String sclid, @RequestParam String api_key, @RequestParam String pajx_sign, @RequestParam String call_id) {
        try {
            JSONObject jsonObject = new JSONObject();
            if (StringUtils.isEmpty(pajx_sign)) {
                jsonObject.put("status", false);
                jsonObject.put("message", "参数签名为空");
                return jsonObject;
            }
            if (StringUtils.isEmpty(api_key)) {
                jsonObject.put("status", false);
                jsonObject.put("message", "api_key为空");
                return jsonObject;
            } else {
                if (!api_key.equals(this.api_key)) {
                    jsonObject.put("status", false);
                    jsonObject.put("message", "api_key错误");
                    return jsonObject;
                }
            }
            String sign = generate_sign(sclid);
            List<Equipment> equList = equipmentService.geEquipments(sclid);
            if (!sign.equals(pajx_sign)) {
                jsonObject.put("status", false);
                jsonObject.put("message", "非法请求");
                return jsonObject;
            }
            List<JSONObject> listobj = new ArrayList<JSONObject>();
            for (int i = 0; i < equList.size(); i++) {
                Equipment equ = equList.get(i);
                JSONObject jsonObject1 = new JSONObject();
                jsonObject1.put("EQU_ID", equ.getEQU_ID());
                jsonObject1.put("EQU_NO", equ.getEQU_NO());
                jsonObject1.put("EQU_TYPE", equ.getEQU_TYPE());
                jsonObject1.put("EQU_ATT_INOUT_TYPE", equ.getEQU_ATT_INOUT_TYPE());
                jsonObject1.put("EQU_LAST_TIME", equ.getEQU_LAST_TIME());
                listobj.add(jsonObject1);
            }
            jsonObject.put("equioment", listobj);
            jsonObject.put("status", true);
            jsonObject.put("message", "调用接口成功");
            return jsonObject;
        } catch (Exception e) {
            e.printStackTrace();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("status", false);
            jsonObject.put("message", "调用接口失败");
            return jsonObject;
        }
    }

    /**
     * Description:     查看设备详情
     *
     * @param equid
     * @param call_id 时间戳 System.currentTimeMillis()
     * @return json
     */
    @RequestMapping(value = "/api/v1/equipment/detail",method = RequestMethod.POST)
    public
    @ResponseBody
    Object v1_equioment(@RequestParam String equid, @RequestParam String api_key, @RequestParam String pajx_sign, @RequestParam String call_id) {
        try {
            JSONObject jsonObject = new JSONObject();
            if (StringUtils.isEmpty(pajx_sign)) {
                jsonObject.put("status", false);
                jsonObject.put("message", "参数签名为空");
                return jsonObject;
            }
            if (StringUtils.isEmpty(api_key)) {
                jsonObject.put("status", false);
                jsonObject.put("message", "api_key为空");
                return jsonObject;
            } else {
                if (!api_key.equals(this.api_key)) {
                    jsonObject.put("status", false);
                    jsonObject.put("message", "api_key错误");
                    return jsonObject;
                }
            }
            String sign = generate_sign(equid);
            if (!sign.equals(pajx_sign)) {
                jsonObject.put("status", false);
                jsonObject.put("message", "非法请求");
                return jsonObject;
            }
            Equipment equ = equipmentService.getById(equid);
            String EQU_SOFT_TYPE = "";
            if (StringUtils.isNotEmpty(equ.getEQU_NAME_DC())) {
                if (equ.getEQU_NAME_DC().equals("1")) {
                    EQU_SOFT_TYPE = "数据网关";
                } else if (equ.getEQU_NAME_DC().equals("2")) {
                    EQU_SOFT_TYPE = "刷卡端";
                } else if (equ.getEQU_NAME_DC().equals("3")) {
                    EQU_SOFT_TYPE = "车载机/一体机";
                }
            }
            jsonObject.put("EQU_ID", equ.getEQU_ID());
            jsonObject.put("EQU_NO", equ.getEQU_NO());
            jsonObject.put("EQU_ATT_TYPE", equ.getEQU_ATT_TYPE());
            jsonObject.put("EQU_SOFT_TYPE", EQU_SOFT_TYPE);
            jsonObject.put("EQU_APN_SIM", equ.getEQU_APN_SIM());
            jsonObject.put("EQU_START_DATE", equ.getEQU_START_DATE());
            jsonObject.put("EQU_DESCRIP", equ.getEQU_DESCRIP());
            jsonObject.put("EQU_ATT_INOUT_TYPE", equ.getEQU_ATT_INOUT_TYPE());
            jsonObject.put("REMARK", equ.getREMARK());
            jsonObject.put("status", true);
            jsonObject.put("message", "调用接口成功");
            return jsonObject;
        } catch (Exception e) {
            e.printStackTrace();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("status", false);
            jsonObject.put("message", "调用接口失败");
            return jsonObject;
        }
    }

    /**
     * Description:     保存设备
     *
     * @param equipment
     * @param call_id   时间戳 System.currentTimeMillis()
     * @return json
     */
    @RequestMapping(value = "/api/v1/equipment/add",method = RequestMethod.POST)
    public
    @ResponseBody
    Object v1_addEquioment(@ModelAttribute Equipment equipment, @RequestParam String api_key, @RequestParam String pajx_sign, @RequestParam String call_id) {
        try {
            JSONObject jsonObject = new JSONObject();
            if (StringUtils.isEmpty(pajx_sign)) {
                jsonObject.put("status", false);
                jsonObject.put("message", "参数签名为空");
                return jsonObject;
            }
            if (StringUtils.isEmpty(api_key)) {
                jsonObject.put("status", false);
                jsonObject.put("message", "api_key为空");
                return jsonObject;
            } else {
                if (!api_key.equals(this.api_key)) {
                    jsonObject.put("status", false);
                    jsonObject.put("message", "api_key错误");
                    return jsonObject;
                }
            }
            /*String sign = generate_sign(equipment.toString()
            );
            if (!sign.equals(pajx_sign)) {
                jsonObject.put("status", false);
                jsonObject.put("message", "非法请求");
                return jsonObject;
            }*/
            equipmentService.save(equipment);
            jsonObject.put("status", true);
            jsonObject.put("message", "保存成功");
            return jsonObject;
        } catch (Exception e) {
            e.printStackTrace();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("status", false);
            jsonObject.put("message", "保存失败");
            return jsonObject;
        }
    }
    /**
     * Description:     校验设备
     *
     * @param equno
     * @param call_id 时间戳 System.currentTimeMillis()
     * @return json
     */
    @RequestMapping(value = "/api/v1/equipment/check",method = RequestMethod.POST)
    public
    @ResponseBody
    Object v1_checkEquioment(@RequestParam int equno, @RequestParam int equtype, @RequestParam String api_key, @RequestParam String pajx_sign, @RequestParam String call_id) {
        try {
            JSONObject jsonObject = new JSONObject();
            if (StringUtils.isEmpty(pajx_sign)) {
                jsonObject.put("status", false);
                jsonObject.put("message", "参数签名为空");
                return jsonObject;
            }
            if (StringUtils.isEmpty(api_key)) {
                jsonObject.put("status", false);
                jsonObject.put("message", "api_key为空");
                return jsonObject;
            } else {
                if (!api_key.equals(this.api_key)) {
                    jsonObject.put("status", false);
                    jsonObject.put("message", "api_key错误");
                    return jsonObject;
                }
            }
            String sign = generate_sign(String.valueOf(equno), String.valueOf(equtype));
            if (!sign.equals(pajx_sign)) {
                jsonObject.put("status", false);
                jsonObject.put("message", "非法请求");
                return jsonObject;
            }
            Equipment equipment = equipmentService.getByNo(equno, equtype);
            if (equipment == null) {
                jsonObject.put("status", true);
                jsonObject.put("message", "查询成功");
            } else {
                jsonObject.put("status", false);
                jsonObject.put("message", "设备流水号已存在");
            }

            return jsonObject;
        } catch (Exception e) {
            e.printStackTrace();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("status", false);
            jsonObject.put("message", "调用接口失败");
            return jsonObject;
        }
    }
    /**
     * Description:    删除设备
     *
     * @param equid
     * @param call_id 时间戳 System.currentTimeMillis()
     * @return json
     */
    @RequestMapping(value = "/api/v1/equipment/delete",method = RequestMethod.POST)
    public
    @ResponseBody
    Object v1_deleteEuioment(@RequestParam String equid, @RequestParam String api_key, @RequestParam String pajx_sign, @RequestParam String call_id) {
        try {
            JSONObject jsonObject = new JSONObject();
            if (StringUtils.isEmpty(pajx_sign)) {
                jsonObject.put("status", false);
                jsonObject.put("message", "参数签名为空");
                return jsonObject;
            }
            if (StringUtils.isEmpty(api_key)) {
                jsonObject.put("status", false);
                jsonObject.put("message", "api_key为空");
                return jsonObject;
            } else {
                if (!api_key.equals(this.api_key)) {
                    jsonObject.put("status", false);
                    jsonObject.put("message", "api_key错误");
                    return jsonObject;
                }
            }
            String sign = generate_sign(equid);
            if (!sign.equals(pajx_sign)) {
                jsonObject.put("status", false);
                jsonObject.put("message", "非法请求");
                return jsonObject;
            }
            Equipment equ = equipmentService.getById(equid);
            equ.setEQU_STATUS_FLAG("0");
            equipmentService.update(equ);
            jsonObject.put("status", true);
            jsonObject.put("message", "删除成功");
            return jsonObject;
        } catch (Exception e) {
            e.printStackTrace();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("status", false);
            jsonObject.put("message", "删除失败");
            return jsonObject;
        }
    }


}
