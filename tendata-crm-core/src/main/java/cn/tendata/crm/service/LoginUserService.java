package cn.tendata.crm.service;

import cn.tendata.crm.data.domain.User;
import cn.tendata.crm.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by minjay on 2016/11/13.
 */
public class LoginUserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user=repository.findByUsername(username);
        if (user==null) {
            throw new UsernameNotFoundException("user :"+username+" not exist");
        }
        return user;
    }
}
