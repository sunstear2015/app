package com.pajx.server.app.controller;

import com.alibaba.fastjson.JSONObject;
import com.pajx.server.app.base.BaseController;
import com.pajx.server.app.utils.database.CustomerContextHolder;
import com.pajx.server.app.utils.security.MD5;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 运营分析
 * Created by taller on 15/1/27.
 */
@Controller
@Scope
@RequestMapping("/")
public class OperateController extends BaseController {

    /**
     * Description:     运营分析
     * @param deptcode
     * @param call_id   时间戳 System.currentTimeMillis()
     * @return json
     */
    @RequestMapping(value = "/api/v1/operate",method = RequestMethod.POST)
    public
    @ResponseBody
    Object v1_operate(@RequestParam String deptcode, @RequestParam String api_key, @RequestParam String pajx_sign, @RequestParam String call_id) {
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
            String sign = generate_sign(deptcode);
            if (!sign.equals(pajx_sign)) {
                jsonObject.put("status", false);
                jsonObject.put("message", "非法请求");
                return jsonObject;
            }
            CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_ORACLE2);
            List operateList=operateService.getOperate(deptcode);
            List<JSONObject> listobj = new ArrayList<JSONObject>();
            if (operateList != null && !operateList.isEmpty()) {
                for (int i = 0; i < operateList.size(); i++) {
                    Object[] o= (Object[]) operateList.get(i);
                    JSONObject jsonObj = new JSONObject();
                    jsonObj.put("TOTAL_USER",o[0]);
                    jsonObj.put("PER_CLOSE_NUM",o[1]);
                    jsonObj.put("PER_OPEN_NUM",o[2]);
                    jsonObj.put("YW_ENDDATE",o[3]);
                    listobj.add(jsonObj);
                }
            }
            jsonObject.put("OPERATE",listobj);
            jsonObject.put("status", true);
            jsonObject.put("message", "获取数据成功");
            CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_ORACLE1);
            return jsonObject;
        } catch (Exception e) {
            e.printStackTrace();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("status", false);
            jsonObject.put("message", "获取数据失败");
            return jsonObject;
        }

    }
}
