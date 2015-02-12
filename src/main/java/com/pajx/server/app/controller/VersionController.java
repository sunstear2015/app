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

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

/**
 * Created by taller on 15/1/27.
 */
@Controller
@Scope
@RequestMapping("/")
public class VersionController extends BaseController {

    /**
     * Description:     运营分析
     *
     * @param call_id                     时间戳 System.currentTimeMillis()
     * @return json
     */
    @RequestMapping(value = "/api/v1/version",method = RequestMethod.POST)
    public
    @ResponseBody
    Object v1_version( @RequestParam String api_key, @RequestParam String pajx_sign, @RequestParam String call_id) {
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
            String sign = generate_sign();
            if (!sign.equals(pajx_sign)) {
                jsonObject.put("status", false);
                jsonObject.put("message", "非法请求");
                return jsonObject;
            }
            Properties props = new Properties();
            InputStream in = this.getClass().getClassLoader().getResourceAsStream(
                    "android_version.properties");
            props.load(in);
            Enumeration en = props.propertyNames();
            while (en.hasMoreElements()) {
                String key = (String) en.nextElement();
                jsonObject.put(key,props.getProperty(key));
            }
            jsonObject.put("status", true);
            jsonObject.put("message", "获取数据成功");
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
