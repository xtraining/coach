package com.zhiqin.coach.admin.controller;

import java.io.IOException;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.patchca.color.RandomColorFactory;
import org.patchca.filter.predefined.CurvesRippleFilterFactory;
import org.patchca.filter.predefined.DiffuseRippleFilterFactory;
import org.patchca.filter.predefined.DoubleRippleFilterFactory;
import org.patchca.filter.predefined.MarbleRippleFilterFactory;
import org.patchca.filter.predefined.WobbleRippleFilterFactory;
import org.patchca.service.ConfigurableCaptchaService;
import org.patchca.utils.encoder.EncoderHelper;
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
	public String loginCheck(String username,String password, String valificationCode, HttpServletRequest request, HttpServletResponse response){
		try {
			if (!request.getMethod().equals("POST")) {
				request.setAttribute("error","支持POST方法提交！");
			}
			HttpSession session = request.getSession(true);  
			String capchaInSession = (String) session.getAttribute(Constants.VALIFICATION_CODE_SESSION_NAME);
			if(!StringUtils.equalsIgnoreCase(capchaInSession, valificationCode)){
				JsonUtils.write(response, JsonBinder.toJson("result", "error"));
			    return null;
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
	
	@RequestMapping ("capcha")
	public String loginCheck(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		int counter = new Random().nextInt(5);
		ConfigurableCaptchaService cs = new ConfigurableCaptchaService();
		cs.setColorFactory(new RandomColorFactory());
		switch (counter) {
		case 0:
			cs.setFilterFactory(new CurvesRippleFilterFactory(cs.getColorFactory()));
			break;
		case 1:
			cs.setFilterFactory(new MarbleRippleFilterFactory());
			break;
		case 2:
			cs.setFilterFactory(new DoubleRippleFilterFactory());
			break;
		case 3:
			cs.setFilterFactory(new WobbleRippleFilterFactory());
			break;
		case 4:
			cs.setFilterFactory(new DiffuseRippleFilterFactory());
			break;
		}
		String word = EncoderHelper.getChallangeAndWriteImage(cs, "png", response.getOutputStream());
		HttpSession session = request.getSession(true);
		session.setAttribute(Constants.VALIFICATION_CODE_SESSION_NAME, word);
		return null;
	}

}
