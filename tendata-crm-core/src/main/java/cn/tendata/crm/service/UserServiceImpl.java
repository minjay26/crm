package cn.tendata.crm.service;

import cn.tendata.crm.data.domain.User;
import cn.tendata.crm.data.repository.UserPredicates;
import cn.tendata.crm.data.repository.UserRepository;
import cn.tendata.crm.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by minjay on 2016/11/14.
 */
@Service
public class UserServiceImpl extends EntityServiceSupport<User,Integer,UserRepository> implements UserService{

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        super(repository);
    }

    @Override
    public List<User> getApprovers(String stringAuthorities) {
        return getRepository().findAllByStringAuthorities(stringAuthorities);
    }

    @Override
    public List<User> getByStatus(Integer status,User user) {
        return CollectionUtils.toList(getRepository().findAll(UserPredicates.queryByStatus(status,user)));
    }
}
