package com.zhiqin.coach.admin.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhiqin.coach.admin.common.Constants;
import com.zhiqin.coach.admin.entity.SysUser;
import com.zhiqin.coach.admin.service.SysUserService;
import com.zhiqin.coach.admin.util.JsonBinder;
import com.zhiqin.coach.admin.util.JsonUtils;

@Controller
@RequestMapping ("/login/")
public class LoginController
{
	@Autowired
	private AuthenticationManager myAuthenticationManager;
	
	@Resource
	private SysUserService userService;
	
	@RequestMapping ("login")
	public String login(Model model,HttpServletRequest request)
	{
		//重新登录时销毁该用户的Session
		Object o = request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
		if(null != o){
			request.getSession().removeAttribute("SPRING_SECURITY_CONTEXT");
		}
		return "/login";
	}
	
	@RequestMapping ("loginCheck")
	public String loginCheck(String username,String password,HttpServletRequest request, HttpServletResponse response){
		try {
			if (!request.getMethod().equals("POST")) {
				request.setAttribute("error","支持POST方法提交！");
			}
			// 验证用户账号与密码是否正确
			SysUser user = userService.getUserByCredentials(username, password);
			if (user == null || user.getId() == 0) {
				JsonUtils.write(response, JsonBinder.toJson("result", "input"));
			    return null;
			}
			if(user.getStatus() == 1){
				JsonUtils.write(response, JsonBinder.toJson("result", "INVALID"));
			    return null;
			}
			Authentication authentication = myAuthenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(username,password));
			SecurityContext securityContext = SecurityContextHolder.getContext();
			securityContext.setAuthentication(authentication);
			HttpSession session = request.getSession(true);  
		    session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);  
		    // 当验证都通过后，把用户信息放在session里
			request.getSession().setAttribute(Constants.USER_SESSION, user);
		} catch (AuthenticationException ae) {  
			request.setAttribute("error", "登录异常，请联系管理员！");
		    return "/login";
		}
		JsonUtils.write(response, JsonBinder.toJson("result", "success"));
		return null;
	}
	
	/**
	 * @return
	 */
	@RequestMapping ("index")
	public String index(Model model)
	{
		return "/index";
	}
	
}
