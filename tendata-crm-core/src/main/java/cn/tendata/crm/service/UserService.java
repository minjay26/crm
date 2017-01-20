package cn.tendata.crm.service;

import cn.tendata.crm.data.domain.User;

import java.util.List;

/**
 * Created by minjay on 2016/11/14.
 */
public interface UserService extends EntityService<User,Integer> {

    List<User> getApprovers(String stringAuthorities);

    List<User> getByStatus(Integer status,User user);
}
