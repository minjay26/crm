package cn.tendata.crm.admin.web.controller;

import cn.tendata.crm.admin.web.bind.annotation.CurrentUser;
import cn.tendata.crm.data.domain.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Luo Min on 2016/11/4.
 */
@Controller
@RequestMapping("/admin")
public class HomeController {

    @PreAuthorize("hasAuthority('all')")
    @RequestMapping(value = "/")
    public String Home(@CurrentUser User user){
      user.getAuthorities();
       return "admin/home";
    }
}
