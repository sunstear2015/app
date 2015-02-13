package com.pajx.server.app.controller;

import com.alibaba.fastjson.JSONObject;
import com.pajx.server.app.base.BaseController;
import com.pajx.server.app.utils.database.CustomerContextHolder;
import com.pajx.server.app.utils.date.FormatDate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by taller on 15/1/27.
 */
@Controller
@Scope
@RequestMapping("/")
public class SchoolController extends BaseController {
    /**
     * Description:     学校列表首页接口
     *
     * @param uuid,       用户id
     * @param rolecode    角色编码
     * @param deptcode    部门编码
     * @param managerflag 是否是大区经理,0否，1是
     * @return json
     */
    @RequestMapping(value = "/api/v1/school",method = RequestMethod.POST)
    public
    @ResponseBody
    JSONObject v1_schoolIndex(@RequestParam String uuid, @RequestParam String rolecode, @RequestParam String deptcode, @RequestParam String managerflag, @RequestParam String usertype, @RequestParam String api_key, @RequestParam String pajx_sign, @RequestParam String call_id) {
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
        String sign = generate_sign(uuid,rolecode,deptcode,managerflag,usertype);
        if (!sign.equals(pajx_sign)) {
            jsonObject.put("status", false);
            jsonObject.put("message", "非法请求");
            return jsonObject;
        }
        String areaCode = "";
        try {
            if (usertype.equals("1")) {
                if (managerflag.equals("1") && rolecode.equals("1102")) {
                    areaCode = getAreaCode(deptcode);
                }
                CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_ORACLE2);
                List list = schoolService.getSchools(uuid, rolecode, areaCode, managerflag);
                List<JSONObject> listobj = new ArrayList<JSONObject>();
                if (list != null && !list.isEmpty()) {
                    for (int i = 0; i < list.size(); i++) {
                        Object[] o = (Object[]) list.get(i);
                        JSONObject jsonObject1 = new JSONObject();
                        jsonObject1.put("SCL_ID", o[0]);
                        jsonObject1.put("SCL_NAME", o[1]);
                        jsonObject1.put("YW_USE_RATE", o[2]);
                        jsonObject1.put("EQU_OVER_RATE", o[3]);
                        jsonObject1.put("EQU_FAULT_RATE", o[4]);
                        jsonObject1.put("MON_CLOSE_RATE", o[5]);
                        jsonObject1.put("INSCH_OPEN_NUM", o[6]);
                        listobj.add(jsonObject1);
                    }
                }
                jsonObject.put("school", listobj);
                jsonObject.put("status", true);
                jsonObject.put("message", "调用接口成功");
                CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_ORACLE1);
                return jsonObject;
            } else {
                List list = schoolService.getOutSchools(uuid);
                jsonObject.put("status", true);
                jsonObject.put("message", list);
                return jsonObject;
            }

        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.put("status", false);
            jsonObject.put("message", "获取接口失败");
            return jsonObject;
        }
    }
    public String getAreaCode(String deptcode) {
        CustomerContextHolder.clearCustomerType();
        CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_ORACLE1);
        String str = "";
        try {
            List areaCodeList = oneSchoolService.getAreaCode(deptcode);
            if (areaCodeList != null && !areaCodeList.isEmpty()) {
                for (int i = 0; i < areaCodeList.size(); i++) {
                    str += "'" + areaCodeList.get(i) + "',";
                }
            }
            return str.substring(0, str.length() - 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Description:    获取学校详细数据
     *
     * @param sclid, 学校id
     * @return json
     */
    @RequestMapping(value = "/api/v1/school/detail",method = RequestMethod.POST)
    public
    @ResponseBody
    JSONObject v1_schoolDetail(@RequestParam String sclid, @RequestParam String api_key, @RequestParam String pajx_sign, @RequestParam String call_id) {
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
            if (!sign.equals(pajx_sign)) {
                jsonObject.put("status", false);
                jsonObject.put("message", "非法请求");
                return jsonObject;
            }
            CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_ORACLE1);
            List oneSchoolsList = oneSchoolService.getSchoolDetail(sclid);
            if (oneSchoolsList != null && !oneSchoolsList.isEmpty()) {
                Object[] obj = (Object[]) oneSchoolsList.get(0);
                jsonObject.put("SCL_NAME", obj[0]);
                jsonObject.put("AREA_NAME", obj[1]);
                jsonObject.put("EQU_ATT_NUM", obj[2]);
                jsonObject.put("EQU_TEL_NUM", obj[3]);
                jsonObject.put("SALE_USER_NAME", obj[4]);
                jsonObject.put("MOB_MANAGER_NAME", obj[5]);
                jsonObject.put("INSCH_NUM", obj[6]);
                jsonObject.put("INSCH_OPEN_NUM", obj[7]);
                jsonObject.put("DAY_OPEN_NUM", obj[8]);
                jsonObject.put("DAY_CLOSE_NUM", obj[9]);
                jsonObject.put("SCL_NUMBER", obj[10]);
                jsonObject.put("INPUT_TIME", obj[11]);
                jsonObject.put("CARD_TYPE_FLAG", obj[12]);
                jsonObject.put("D_KEYA", obj[13]);
                jsonObject.put("SCL_MANAGER_NAME", obj[14]);
                jsonObject.put("A1", obj[15]);
                jsonObject.put("B1", obj[16]);
                jsonObject.put("C1", obj[17]);
                jsonObject.put("D1", obj[18]);
                jsonObject.put("MOB_MANAGER_PHONE", obj[19]);
                jsonObject.put("SALE_KEEP_NAME",obj[20]);
                jsonObject.put("COUNTY_NAME",obj[21]);
                jsonObject.put("SCL_ID", sclid);
                jsonObject.put("status", true);
                jsonObject.put("message", "调用接口成功");
            }else{
                jsonObject.put("status", false);
                jsonObject.put("message", "暂无数据");
            }

            return jsonObject;
        } catch (Exception e) {
            JSONObject jsonObject = new JSONObject();
            e.printStackTrace();
            jsonObject.put("status", false);
            jsonObject.put("message", "获取接口失败");
            return jsonObject;
        }
    }

    /**
     * Description: 七天回溯
     *
     * @param sclid, 学校id
     * @return json
     */
    @RequestMapping(value = "/api/v1/school/detail/track",method = RequestMethod.POST)
    public
    @ResponseBody
    JSONObject v1_schoolDetailTrack(@RequestParam String sclid, @RequestParam String api_key, @RequestParam String pajx_sign, @RequestParam String call_id) {
       String endDate = FormatDate.dateChange();
        //String endDate ="20140108";
        try {
            JSONObject jsonObject = new JSONObject();
            List<JSONObject> listobj = new ArrayList<JSONObject>();
            if (StringUtils.isNotEmpty(endDate)) {
                String startDate1 = FormatDate.daysBeforeOneDate(endDate, 1);
                String startDate2 = FormatDate.daysBeforeOneDate(endDate, 2);
                String startDate3 = FormatDate.daysBeforeOneDate(endDate, 3);
                String startDate4 = FormatDate.daysBeforeOneDate(endDate, 4);
                String startDate5 = FormatDate.daysBeforeOneDate(endDate, 5);
                String startDate6 = FormatDate.daysBeforeOneDate(endDate, 6);
                String startDate7 = FormatDate.daysBeforeOneDate(endDate, 7);
                //开通中
                List nowdate_1List = oneSchoolService.getSchoolDetail(sclid, startDate1, endDate);
                //开通
                List nowdate_2List = oneSchoolService.getSchoolDetail(sclid, startDate2, startDate1);
                //已退订
                List nowdate_3List = oneSchoolService.getSchoolDetail(sclid, startDate3, startDate2);
                //退订中
                List nowdate_4List = oneSchoolService.getSchoolDetail(sclid, startDate4, startDate3);
                List nowdate_5List = oneSchoolService.getSchoolDetail(sclid, startDate5, startDate4);
                List nowdate_6List = oneSchoolService.getSchoolDetail(sclid, startDate6, startDate5);
                List nowdate_7List = oneSchoolService.getSchoolDetail(sclid, startDate7, startDate6);
                if (nowdate_1List != null && !nowdate_1List.isEmpty()) {
                    for (int i = 0; i < nowdate_1List.size(); i++) {
                        Object[] obj = (Object[]) nowdate_1List.get(i);
                        JSONObject jsonObject_1 = new JSONObject();
                        jsonObject_1.put("DATE", startDate1);
                        jsonObject_1.put("DAY_OPEN_IN_NUM", obj[0]);
                        jsonObject_1.put("DAY_OPEN_NUM", obj[1]);
                        jsonObject_1.put("DAY_CLOSE_NUM", obj[2]);
                        jsonObject_1.put("DAY_CLOSE_IN_NUM", obj[3]);
                        listobj.add(jsonObject_1);
                    }
                }
                if (nowdate_2List != null && !nowdate_2List.isEmpty()) {
                    for (int i = 0; i < nowdate_2List.size(); i++) {
                        Object[] obj = (Object[]) nowdate_2List.get(i);
                        JSONObject jsonObject_1 = new JSONObject();
                        jsonObject_1.put("DATE", startDate2);
                        jsonObject_1.put("DAY_OPEN_IN_NUM", obj[0]);
                        jsonObject_1.put("DAY_OPEN_NUM", obj[1]);
                        jsonObject_1.put("DAY_CLOSE_NUM", obj[2]);
                        jsonObject_1.put("DAY_CLOSE_IN_NUM", obj[3]);
                        listobj.add(jsonObject_1);
                    }
                }
                if (nowdate_3List != null && !nowdate_3List.isEmpty()) {
                    for (int i = 0; i < nowdate_3List.size(); i++) {
                        Object[] obj = (Object[]) nowdate_3List.get(i);
                        JSONObject jsonObject_1 = new JSONObject();
                        jsonObject_1.put("DATE", startDate3);
                        jsonObject_1.put("DAY_OPEN_IN_NUM", obj[0]);
                        jsonObject_1.put("DAY_OPEN_NUM", obj[1]);
                        jsonObject_1.put("DAY_CLOSE_NUM", obj[2]);
                        jsonObject_1.put("DAY_CLOSE_IN_NUM", obj[3]);
                        listobj.add(jsonObject_1);
                    }
                }
                if (nowdate_4List != null && !nowdate_4List.isEmpty()) {
                    for (int i = 0; i < nowdate_4List.size(); i++) {
                        Object[] obj = (Object[]) nowdate_4List.get(i);
                        JSONObject jsonObject_1 = new JSONObject();
                        jsonObject_1.put("DATE", startDate4);
                        jsonObject_1.put("DAY_OPEN_IN_NUM", obj[0]);
                        jsonObject_1.put("DAY_OPEN_NUM", obj[1]);
                        jsonObject_1.put("DAY_CLOSE_NUM", obj[2]);
                        jsonObject_1.put("DAY_CLOSE_IN_NUM", obj[3]);
                        listobj.add(jsonObject_1);
                    }
                }
                if (nowdate_5List != null && !nowdate_5List.isEmpty()) {
                    for (int i = 0; i < nowdate_5List.size(); i++) {
                        Object[] obj = (Object[]) nowdate_5List.get(i);
                        JSONObject jsonObject_1 = new JSONObject();
                        jsonObject_1.put("DATE", startDate5);
                        jsonObject_1.put("DAY_OPEN_IN_NUM", obj[0]);
                        jsonObject_1.put("DAY_OPEN_NUM", obj[1]);
                        jsonObject_1.put("DAY_CLOSE_NUM", obj[2]);
                        jsonObject_1.put("DAY_CLOSE_IN_NUM", obj[3]);
                        listobj.add(jsonObject_1);
                    }
                }
                if (nowdate_6List != null && !nowdate_6List.isEmpty()) {
                    for (int i = 0; i < nowdate_6List.size(); i++) {
                        Object[] obj = (Object[]) nowdate_6List.get(i);
                        JSONObject jsonObject_1 = new JSONObject();
                        jsonObject_1.put("DATE", startDate6);
                        jsonObject_1.put("DAY_OPEN_IN_NUM", obj[0]);
                        jsonObject_1.put("DAY_OPEN_NUM", obj[1]);
                        jsonObject_1.put("DAY_CLOSE_NUM", obj[2]);
                        jsonObject_1.put("DAY_CLOSE_IN_NUM", obj[3]);
                        listobj.add(jsonObject_1);
                    }
                }
                if (nowdate_7List != null && !nowdate_7List.isEmpty()) {
                    for (int i = 0; i < nowdate_7List.size(); i++) {
                        Object[] obj = (Object[]) nowdate_7List.get(i);
                        JSONObject jsonObject_1 = new JSONObject();
                        jsonObject_1.put("DATE", startDate7);
                        jsonObject_1.put("DAY_OPEN_IN_NUM", obj[0]);
                        jsonObject_1.put("DAY_OPEN_NUM", obj[1]);
                        jsonObject_1.put("DAY_CLOSE_NUM", obj[2]);
                        jsonObject_1.put("DAY_CLOSE_IN_NUM", obj[3]);
                        listobj.add(jsonObject_1);
                    }
                }
            }
            jsonObject.put("track", listobj);
            jsonObject.put("status", true);
            jsonObject.put("message", "调用接口成功");
            return jsonObject;
        } catch (Exception e) {
            JSONObject jsonObject = new JSONObject();
            e.printStackTrace();
            jsonObject.put("status", false);
            jsonObject.put("message", "获取接口失败");
            return jsonObject;
        }
    }
}
