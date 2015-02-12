package com.pajx.server.app.controller;

import com.alibaba.fastjson.JSONObject;
import com.pajx.server.app.base.BaseController;
import com.pajx.server.app.utils.database.CustomerContextHolder;
import com.pajx.server.app.utils.file.FileUploadUtils;
import com.pajx.server.app.utils.file.FileUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by taller on 15/1/27.
 */
@Controller
@Scope
@RequestMapping("/")
public class UploadController extends BaseController {

    /**
     * Description:     用户查询
     * @param call_id 时间戳 System.currentTimeMillis()
     * @return json
     */
    @RequestMapping(value = "/api/v1/upload/user")
    public
    @ResponseBody
    Object v1_uploadUser(@RequestParam String uuid,@RequestParam File images,@RequestParam String api_key, @RequestParam String pajx_sign, @RequestParam String call_id) {
        JSONObject jsonObject = new JSONObject();
            if (!api_key.equals(this.api_key)) {
                jsonObject.put("status", false);
                jsonObject.put("message", "api_key错误");
                return jsonObject;
            }
      /* String sign = generate_sign();
        if (!sign.equals(pajx_sign)) {
            jsonObject.put("status", false);
            jsonObject.put("message", "非法请求");
            return jsonObject;
        }*/
        try {
            String filepath = "/statics/account/";
           // FileUtil.writeFile(images, "XX",request, filepath, uuid);
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
