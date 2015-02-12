package com.pajx.server.app.controller;

import com.alibaba.fastjson.JSONObject;
import com.pajx.server.app.base.BaseController;
import com.pajx.server.app.utils.security.MD5;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by taller on 15/1/27.
 */
@Controller
@Scope
@RequestMapping("/")
public class UserController extends BaseController {

    /**
     * Description:     用户查询
     *
     * @param content
     * @param call_id 时间戳 System.currentTimeMillis()
     * @return json
     */
    @RequestMapping(value = "/api/v1/user/search",method = RequestMethod.POST)
    public
    @ResponseBody
    Object v1_userSearch(@RequestParam String usertype, @RequestParam String content, @RequestParam String api_key, @RequestParam String pajx_sign, @RequestParam String call_id) {
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
      String sign = generate_sign(usertype,content);
        if (!sign.equals(pajx_sign)) {
            jsonObject.put("status", false);
            jsonObject.put("message", "非法请求");
            return jsonObject;
        }
        try {
            List userList = userService.getSearchUser(usertype, content);
            List<JSONObject> listobj = new ArrayList<JSONObject>();
            if (userList != null && !userList.isEmpty()) {
                for (int i = 0; i < userList.size(); i++) {
                    Object[] o = (Object[]) userList.get(i);
                    List userStunoList = userService.getSearchUser(o[0].toString());
                    String stuid = "";
                    List<JSONObject> listobjNo = new ArrayList<JSONObject>();
                    List<JSONObject> listobjfam = new ArrayList<JSONObject>();
                    if (userStunoList != null && !userStunoList.isEmpty()) {
                        for (int j = 0; j < userStunoList.size(); j++) {
                            Object[] ono = (Object[]) userStunoList.get(j);
                            JSONObject jsonObjectNO = new JSONObject();
                            jsonObjectNO.put("STU_NO", ono[0]);
                            jsonObjectNO.put("SCL_NAME", ono[1]);
                            jsonObjectNO.put("GRA_NAME", ono[2]);
                            jsonObjectNO.put("CLS_NAME", ono[3]);
                            jsonObjectNO.put("STU_NAME", ono[4]);
                            jsonObjectNO.put("STU_TYPE", ono[5]);
                            stuid = ono[6].toString();
                            listobjNo.add(jsonObjectNO);
                        }
                    }
                    if (StringUtils.isNotEmpty(stuid)) {
                        List famList = userService.getSearchUserId(stuid);
                        if (famList != null && !famList.isEmpty()) {
                            for (int x = 0; x < famList.size(); x++) {
                                Object[] ox = (Object[]) famList.get(x);
                                JSONObject jsonObjectStuId = new JSONObject();
                                String type = ox[4] == null ? "" : ox[4].toString();
                                String bizflag = ox[5] == null ? "" : ox[5].toString();
                                String biz = "";
                                String flag = "0";
                                if (type.equals("09")) {
                                    biz = "10086主退" + ox[2];
                                    flag = "1";
                                } else if (type.equals("03")) {
                                    if (bizflag.equals("4")) {
                                        biz = "已订购" + ox[2];
                                        flag = "1";
                                    } else {
                                        biz = "申请处理中" + ox[2];
                                    }
                                } else if (type.equals("04")) {
                                    if (bizflag.equals("4")) {
                                        biz = "已退订" + ox[2];
                                        flag = "1";
                                    } else {
                                        biz = "退订处理中" + ox[2];
                                    }
                                }
                                jsonObjectStuId.put("FAM_RELATION", ox[0]);
                                jsonObjectStuId.put("FAM_PHONE", ox[1]);
                                jsonObjectStuId.put("BIZ_STATUS_FLAG", flag);
                                jsonObjectStuId.put("BIZ_PRICE_TYPE", biz);
                                jsonObjectStuId.put("LAST_DEAL_TIME", ox[3]);
                                listobjfam.add(jsonObjectStuId);
                            }
                        }
                    }
                    JSONObject jsonObjectUser = new JSONObject();
                    jsonObjectUser.put("STUDENT", listobjNo);
                    jsonObjectUser.put("FAMILY", listobjfam);
                    listobj.add(jsonObjectUser);
                }
            }
            jsonObject.put("STU_NO", listobj);
            jsonObject.put("status", true);
            jsonObject.put("message", "获取接口成功");
            return jsonObject;
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.put("status", false);
            jsonObject.put("message", "获取接口失败");
            return jsonObject;
        }

    }

    /**
     * Description:     用户查询
     * @param call_id 时间戳 System.currentTimeMillis()
     * @return json
     */
    @RequestMapping(value = "/api/v1/user/dept",method = RequestMethod.POST)
    public
    @ResponseBody
    Object v1_userDept( @RequestParam String api_key, @RequestParam String pajx_sign, @RequestParam String call_id) {
        try{
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
            String sign = generate_sign();
            if (!sign.equals(pajx_sign)) {
                jsonObject.put("status", false);
                jsonObject.put("message", "非法请求");
                return jsonObject;
            }
            List<JSONObject> listobj = new ArrayList<JSONObject>();
            List list=userService.getDept();
            if (list != null && !list.isEmpty()) {
                for (int i = 0; i < list.size(); i++) {
                   Object[] o= (Object[]) list.get(i);
                    JSONObject deptObject = new JSONObject();
                    deptObject.put("DEPT_CODE",o[0]);
                    deptObject.put("DEPT_NAME",o[1]);
                    listobj.add(deptObject);
                }
            }
            jsonObject.put("DEPT", listobj);
            jsonObject.put("status", true);
            jsonObject.put("message", "获取接口成功");
            return jsonObject;
        } catch (Exception e) {
            e.printStackTrace();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("status", false);
            jsonObject.put("message", "获取接口失败");
            return jsonObject;
        }

    }
}
