package com.pajx.server.app.base;

import com.alibaba.fastjson.JSONObject;
import com.pajx.server.app.service.one.*;
import com.pajx.server.app.service.two.ISaleService;
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
    protected ISaleService saleService;
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
    @Resource
    public void setSaleService(ISaleService saleService) {
        this.saleService = saleService;
    }
}
