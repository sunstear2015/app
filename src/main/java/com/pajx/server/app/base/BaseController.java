package com.pajx.server.app.base;

import com.alibaba.fastjson.JSONObject;
import com.pajx.server.app.service.one.*;
import com.pajx.server.app.service.two.ISchoolService;
import com.pajx.server.app.utils.security.MD5;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * Created by admin on 2015/2/2.
 */
public class BaseController {
    /*-----注入-------*/
    protected IUserService userService;
    protected IOutUserService outUserService;
    protected ISchoolService schoolService;
    protected IOneSchoolService oneSchoolService;
    protected IEquipmentService equipmentService;
    protected IOperateService operateService;
    /*-------常量--------*/
    protected static String api_key="a10086";
    protected static String api_security="5cd84126fba797979c39cbe3f7024259";
    //参数签名生成算法
  public static  String generate_sign(String ...strings){
        //对strings中的参数名称进行升序排序
        Arrays.sort(strings);
        String string="";
        //按照如下格式转换为string格式
        for(String str:strings){
            string+=str;
        }
        //string末端补充api_security密钥
         string+=api_security;
        return MD5.encrytion(string);
    }
    //校验合法数据算法
    public static  Object check_data(String username,String password,String usertype,String api_keys,String pajx_sign,String call_id ){
        JSONObject jsonObject = new JSONObject();
        if (StringUtils.isEmpty(pajx_sign)){
            jsonObject.put("status", false);
            jsonObject.put("message","参数签名为空");
            return jsonObject;
        }
        if (StringUtils.isEmpty(api_keys)) {
            jsonObject.put("status", false);
            jsonObject.put("message","api_key为空");
            return jsonObject;
        }else{
            if (!api_keys.equals(api_key)) {
                jsonObject.put("status", false);
                jsonObject.put("message","api_key错误");
                return jsonObject;
            }
        }
        String sign=generate_sign(username,password,usertype);
        if (!sign.equals(pajx_sign)) {
            jsonObject.put("status", false);
            jsonObject.put("message","非法请求");
            return jsonObject;
        }
        return null;
    }
    @Resource
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }
    @Resource
    public void setOutUserService(IOutUserService outUserService) {
        this.outUserService = outUserService;
    }
    @Resource
    public void setSchoolService(ISchoolService schoolService) {
        this.schoolService = schoolService;
    }
    @Resource
    public void setOneSchoolService(IOneSchoolService oneSchoolService) {
        this.oneSchoolService = oneSchoolService;
    }
    @Resource
    public void setEquipmentService(IEquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }
    @Resource
    public void setOperateService(IOperateService operateService) {
        this.operateService = operateService;
    }
}
