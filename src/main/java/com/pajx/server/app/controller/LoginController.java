package com.pajx.server.app.controller;

import com.alibaba.fastjson.JSONObject;
import com.pajx.server.app.base.BaseController;
import com.pajx.server.app.utils.security.MD5;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * Created by taller on 15/1/27.
 */
@Controller
@Scope
@RequestMapping("/")
public class LoginController extends BaseController {

    /**
     * Description:  欢迎界面
     *
     * @return 页面转到/WEB-INF/pages/hello.jsp
     */
    @RequestMapping(method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        model.addAttribute("message", "Hello world!");
        return "hello";
    }

    /**
     * Description:     登陆  REST风格: /api/login?username=&password=&usertype=
     *
     * @param username,password.usertype,
     * @param call_id                     时间戳 System.currentTimeMillis()
     * @return json
     */
    @RequestMapping(value = "/api/v1/login",method = RequestMethod.POST)
    public
    @ResponseBody
    Object v1_login(@RequestParam String username, @RequestParam String password, @RequestParam String usertype, @RequestParam String api_key, @RequestParam String pajx_sign, @RequestParam String call_id) {
        JSONObject jsonObject = new JSONObject();
        if (StringUtils.isEmpty(password)) {
            jsonObject.put("status", false);
            jsonObject.put("message", "密码为空");
            return jsonObject;
        }
        if (StringUtils.isEmpty(usertype)) {
            jsonObject.put("status", false);
            jsonObject.put("message", "用户类型为空");
            return jsonObject;
        }
        if (StringUtils.isEmpty(username)) {
            jsonObject.put("status", false);
            jsonObject.put("message", "用户名为空");
            return jsonObject;
        }
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
        String sign = generate_sign(username, password, usertype);
        if (!sign.equals(pajx_sign)) {
            jsonObject.put("status", false);
            jsonObject.put("message", "非法请求");
            return jsonObject;
        }
        Object[] o = new Object[11];
        try {
            if (usertype.equals("1")) {
                o = userService.getByUserName(username, usertype);
            } else {
                o = outUserService.getByOutUser(username);
            }
            if (o == null) {
                jsonObject.put("status", false);
                jsonObject.put("message", "用户不存在");
                return jsonObject;
            }
            if (!password.equals(o[1])) {
                jsonObject.put("status", false);
                jsonObject.put("message", "用户密码错误");
                return jsonObject;
            }
            if (o[2].equals("0")) {
                jsonObject.put("status", false);
                jsonObject.put("message", "该账户已经被禁用");
                return jsonObject;
            }
            jsonObject.put("status", true);
            jsonObject.put("message", "登陆成功");
            jsonObject.put("ACCOUNT", o[0]);
            jsonObject.put("ISU_STATUS_FLAG", o[2]);
            jsonObject.put("ISU_GROUP_CODE", o[3]);
            jsonObject.put("ISU_ROLE_CODE", o[4]);
            jsonObject.put("DEPT_CODE", o[5]);
            jsonObject.put("ISU_NAME", o[6]);
            jsonObject.put("ISU_SEX", o[7]);
            jsonObject.put("ISU_PHONE", o[8]);
            jsonObject.put("MANAGER_FLAG", o[9]);
            jsonObject.put("ISU_ID", o[10]);
            jsonObject.put("DEPT_NAME", o[11]);
            return jsonObject;
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.put("status", false);
            jsonObject.put("message", "获取接口失败");
            return jsonObject;
        }

    }

    @RequestMapping(value = "/api", method = RequestMethod.GET)
    public
    @ResponseBody
    Object loginx() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", true);
        jsonObject.put("message", "平安家校app接口");
        return jsonObject;
    }
}
