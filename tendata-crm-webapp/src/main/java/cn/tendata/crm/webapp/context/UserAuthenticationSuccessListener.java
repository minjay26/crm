package cn.tendata.crm.webapp.context;

import cn.tendata.crm.data.domain.User;
import cn.tendata.crm.data.repository.UserRepository;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class UserAuthenticationSuccessListener implements ApplicationListener<AuthenticationSuccessEvent>{

    @Autowired
    private UserRepository userRepository;
    
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        if(event.getAuthentication().getPrincipal() instanceof User){
            User loginUser = (User)event.getAuthentication().getPrincipal();
            if (loginUser.getId() > 0) {
                User user = userRepository.findOne(loginUser.getId());
                if (user == null) {
                    user = new User(loginUser.getId(), loginUser.getUsername());
                }
                user.setLoginCount(user.getLoginCount() + 1);
                user.setLastLoginAt(DateTime.now());
                user.setStatus(User.UserStatus.ONLINE);
                userRepository.save(user);
            }
        }
    }
}
