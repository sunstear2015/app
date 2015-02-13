package com.pajx.server.app.controller;

import com.alibaba.fastjson.JSONObject;
import com.pajx.server.app.base.BaseController;
import com.pajx.server.app.utils.database.CustomerContextHolder;
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
 * 销售排行榜
 * Created by taller on 15/1/27.
 */
@Controller
@Scope
@RequestMapping("/")
public class SaleController extends BaseController {

    /**
     * Description:    销售排行榜
     * @param call_id 时间戳 System.currentTimeMillis()
     * @return json
     */
    @RequestMapping(value = "/api/v1/sale",method = RequestMethod.POST)
    public
    @ResponseBody
    Object v1_sale( @RequestParam String api_key, @RequestParam String pajx_sign, @RequestParam String call_id) {
        CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_ORACLE2);
        JSONObject jsonObject = new JSONObject();
            if (!api_key.equals(this.api_key)) {
                jsonObject.put("status", false);
                jsonObject.put("message", "api_key错误");
                return jsonObject;
            }
       String sign = generate_sign();
        if (!sign.equals(pajx_sign)) {
            jsonObject.put("status", false);
            jsonObject.put("message", "非法请求");
            return jsonObject;
        }
        try {
            List saleList = saleService.getSaleMonth();
            List<JSONObject> saleListobj = new ArrayList<JSONObject>();
            if (saleList != null && !saleList.isEmpty()) {
                for (int i = 0; i < saleList.size(); i++) {
                    Object[] o= (Object[]) saleList.get(i);
                    JSONObject salejsonObject = new JSONObject();
                    salejsonObject.put("SALENAME",o[0]);
                    salejsonObject.put("SALENUM",o[2]);
                    salejsonObject.put("UUID",o[1]);
                    saleListobj.add(salejsonObject);
                }
            }
            List saleYearList = saleService.getSaleYear();
            List<JSONObject> saleYearListobj = new ArrayList<JSONObject>();
            if (saleYearList != null && !saleYearList.isEmpty()) {
                for (int i = 0; i < saleYearList.size(); i++) {
                    Object[] o= (Object[]) saleYearList.get(i);
                    JSONObject saleYearObject = new JSONObject();
                    saleYearObject.put("SALENAME",o[0]);
                    saleYearObject.put("SALENUM",o[2]);
                    saleYearObject.put("UUID",o[1]);
                    saleYearListobj.add(saleYearObject);
                }
            }
            CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_ORACLE1);
            jsonObject.put("SALEYEAR",saleYearListobj);
            jsonObject.put("SALEMONTH", saleListobj);
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
}
