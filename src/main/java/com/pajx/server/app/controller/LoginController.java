package com.pajx.server.app.controller;

import com.pajx.server.app.entity.SysInsideUser;
import com.pajx.server.app.service.IUserService;
import com.pajx.server.app.utils.common.AjaxResponse;
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
     *
     *
     * Description:     登陆  REST风格: /app.api/username/password/code/type
     * @param username,password.code.type
     * @return            视图页面 /WEB-INF/pages/user/detail.jsp页面
     *
     */
    @RequestMapping(value = "/app.api/{username}/{password}/{code}/{type}",method = RequestMethod.POST)
    public  @ResponseBody
    AjaxResponse login(@PathVariable String username,@PathVariable String password,@PathVariable String code,@PathVariable String type){
      // if (StringUtils)
        return new AjaxResponse(true,"保存成功");
    }
    @RequestMapping(value = "/api",method = RequestMethod.GET)
    public  @ResponseBody
    AjaxResponse loginx(){

        return new AjaxResponse(true,"保存成功");
    }
   @Resource
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }
}
