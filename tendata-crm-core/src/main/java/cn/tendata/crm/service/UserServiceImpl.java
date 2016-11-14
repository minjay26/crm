package cn.tendata.crm.service;

import cn.tendata.crm.data.domain.User;
import cn.tendata.crm.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by minjay on 2016/11/14.
 */
@Service
public class UserServiceImpl extends EntityServiceSupport<User,Integer,UserRepository> implements UserService{

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        super(repository);
    }

}
