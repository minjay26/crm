package cn.tendata.crm.webapp.context;

import cn.tendata.crm.data.domain.User;
import cn.tendata.crm.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.session.SessionDestroyedEvent;
import org.springframework.security.web.session.HttpSessionDestroyedEvent;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Luo Min on 2017/1/22.
 */
@Transactional
public class UserDestroySessionListener implements ApplicationListener<SessionDestroyedEvent> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void onApplicationEvent(SessionDestroyedEvent event) {
        User user = (User) ((SecurityContextImpl) ((HttpSessionDestroyedEvent) event).getSession().getAttribute("SPRING_SECURITY_CONTEXT")).getAuthentication().getPrincipal();
        user.setStatus(User.UserStatus.OFFLINE);
        userRepository.save(user);
    }
}
