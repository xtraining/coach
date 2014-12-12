package com.coach.websocket.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.coach.util.Constants;
import com.coach.util.SessionContainer;

public class UserSecurityInterceptor implements HandlerInterceptor {   
  
    @Override  
    public boolean preHandle(HttpServletRequest request,   
            HttpServletResponse response, Object handler) throws Exception {   
    	 Object obj = request.getSession().getAttribute(Constants.SESSION_CONTAINER);
         if (obj == null || !(obj instanceof SessionContainer)) {
             response.sendRedirect(request.getContextPath() + "/login/index");
             return false;
         }
         return true; 
    }   
  
    @Override  
    public void postHandle(HttpServletRequest request,   
            HttpServletResponse response, Object handler,   
            ModelAndView modelAndView) throws Exception {   
    }   
  
    @Override  
    public void afterCompletion(HttpServletRequest request,   
            HttpServletResponse response, Object handler, Exception ex)   
            throws Exception {   
  
    }   
  
}  
