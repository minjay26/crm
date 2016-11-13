package cn.tendata.crm.admin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Luo Min on 2016/11/4.
 */
@Controller
@RequestMapping("/admin")
public class HomeController {

    @RequestMapping(value = "/")
    public String Home(){
       return "admin/home";
    }
}
