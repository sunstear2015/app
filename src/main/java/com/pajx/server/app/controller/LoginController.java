package com.pajx.server.app.controller;

import com.alibaba.fastjson.JSONObject;
import com.pajx.server.app.entity.SysInsideUser;
import com.pajx.server.app.service.IUserService;
import com.pajx.server.app.utils.common.AjaxResponse;
import com.pajx.server.app.utils.security.Md5PwdEncoderUtil;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.core.Constants;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by taller on 15/1/27.
 */
@Controller
@Scope
@RequestMapping("/")
public class LoginController {
    private IUserService userService;
    /**
     *
     *
     * Description:  欢迎界面
     * @return   页面转到/WEB-INF/pages/hello.jsp
     *
     */
    @RequestMapping(method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        model.addAttribute("message", "Hello world!");
        return "hello";
    }
    /**
     * Description:     登陆  REST风格: /api/login?username=&password=&code=&type=
     * @param username,password.code.type
     * @return      json
     */
    @RequestMapping(value = "/api/login",method = RequestMethod.GET)
    public  @ResponseBody
    AjaxResponse login(@RequestParam String username,@RequestParam String password,@RequestParam String code,@RequestParam String type){
       if (StringUtils.isEmpty(code)){
           return new AjaxResponse(true,"验证码为空");
       }
        if (StringUtils.isEmpty(password)){
            return new AjaxResponse(true,"密码为空");
        }
        if (StringUtils.isEmpty(type)){
            return new AjaxResponse(true,"用户类型为空");
        }
        if (StringUtils.isEmpty(username)){
            return new AjaxResponse(true,"用户名为空");
        }
        Object[] o=userService.getByUserName(username,type);
        if (o==null){
            return new AjaxResponse(true,"用户不存在");
        }
        String pwd= Md5PwdEncoderUtil.encrytion(password);
        if (!pwd .equals(o[1])) {
            return new AjaxResponse(true,"用户密码错误");
        }
        if (o[2].equals("0")) {
            return new AjaxResponse(true,"该账户已经被禁用");
        }
        return new AjaxResponse(true,"登陆成功");
    }
    @RequestMapping(value = "/api",method = RequestMethod.GET)
    public  @ResponseBody
    Object  loginx(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", true);
        jsonObject.put("message","平安家校app接口");
        return jsonObject;
    }
   @Resource
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }
}
