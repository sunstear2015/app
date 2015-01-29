package com.pajx.server.app.controller;

import com.pajx.server.app.service.ISchoolService;
import com.pajx.server.app.service.IUserService;
import com.pajx.server.app.utils.common.AjaxResponse;
import com.pajx.server.app.utils.security.Md5PwdEncoderUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by taller on 15/1/27.
 */
@Controller
@Scope
@RequestMapping("/")
public class SchoolController {
    private ISchoolService schoolService;
    /**
     * Description:     登陆  REST风格: /api/login?username=&password=&code=&type=
     * @param uuid,
     * @param rolecode,deptcode
     * @return      json
     */
    @RequestMapping(value = "/api/school/{uuid}/{rolecode}/{deptcode}/{managerflag}",method = RequestMethod.GET)
    public  @ResponseBody
    List school(@PathVariable String uuid,@PathVariable String rolecode,@PathVariable String deptcode,@PathVariable String managerflag){
       List list=schoolService.getSchools(uuid,rolecode,deptcode,managerflag);
        return list;
    }
   @Resource
    public void setSchoolService(ISchoolService schoolService) {
        this.schoolService = schoolService;
    }
}
