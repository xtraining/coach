package com.zhiqin.domain;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IndexServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		int index = uri.lastIndexOf("/");
		String tunnel = null;
		if(index+1 < uri.length()){
			tunnel = uri.substring(index+1, uri.length());
		} 
		DomainHistory h = new DomainHistory();
		h.setAccessUrl(request.getRequestURL().toString());
		h.setIpAddress(request.getRemoteAddr());
		h.setTunnel(tunnel);
		DbAction dbAction = new DbAction();
		dbAction.save(h);
		if(tunnel != null){
			String forwardUrl = dbAction.getForwardUrlByTunnel(tunnel);
			if(forwardUrl != null && forwardUrl.length() > 0){
				if(forwardUrl.startsWith("http")){
					response.sendRedirect(forwardUrl);
				} else {
					response.sendRedirect("http://" + forwardUrl);
				}
			} else {
				response.sendRedirect(Config.getProperty("default_forward_url"));
			}
		} else{
			response.sendRedirect(Config.getProperty("default_forward_url"));
		}
	}

}
