package cn.tendata.crm.admin.web.controller;

import cn.tendata.crm.admin.web.bind.annotation.CurrentUser;
import cn.tendata.crm.admin.web.util.SecurityAccess;
import cn.tendata.crm.data.domain.User;
import cn.tendata.crm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by Luo Min on 2017/1/16.
 */
@Controller
@RequestMapping("/admin/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "",method = RequestMethod.GET)
    public ResponseEntity<List<User>> users(@CurrentUser User user,@RequestParam("status") Integer status){
        List<User> users =  userService.getByStatus(status,user);
        return new ResponseEntity<>(users,HttpStatus.OK);
    }

    @RequestMapping(value = "approvers",method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAllApprovers(){
         List<User> approvers = userService.getApprovers(SecurityAccess.PERMISSION_ADMIN);
        return new ResponseEntity<>(approvers, HttpStatus.OK);
    }
}
