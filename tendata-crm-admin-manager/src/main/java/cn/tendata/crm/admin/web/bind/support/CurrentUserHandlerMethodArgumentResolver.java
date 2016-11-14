package cn.tendata.crm.admin.web.bind.support;

import java.security.Principal;

import cn.tendata.crm.admin.web.bind.annotation.CurrentUser;
import cn.tendata.crm.data.domain.User;
import cn.tendata.crm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;




public class CurrentUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver{
	
	@Autowired
	private UserService userService;

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
	     return parameter.getParameterAnnotation(CurrentUser.class)!=null&&parameter.getParameterType().equals(User.class);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		if (this.supportsParameter(parameter)) {
			Principal principal=webRequest.getUserPrincipal();
			if (principal!=null) {
				User user=(User) ((Authentication)principal).getPrincipal();
				if (user!=null) {
					return userService.findById(user.getId());
				}				
			}					
		}
		return null;
	}

}
