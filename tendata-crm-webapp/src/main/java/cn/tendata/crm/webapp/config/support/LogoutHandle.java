package cn.tendata.crm.webapp.config.support;

import cn.tendata.crm.data.domain.User;
import cn.tendata.crm.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Luo Min on 2017/1/20.
 */
public class LogoutHandle extends SimpleUrlLogoutSuccessHandler{

    @Autowired
    private UserRepository userRepository;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        User logoutUser = (User) authentication.getPrincipal();
        User user = userRepository.findOne(logoutUser.getId());
        user.setStatus(User.UserStatus.OFFLINE);
        userRepository.save(user);
        request.getRequestDispatcher("/admin/").forward(request,response);
    }
}
