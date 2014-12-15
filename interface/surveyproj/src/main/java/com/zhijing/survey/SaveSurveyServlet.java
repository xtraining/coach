package com.zhijing.survey;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SaveSurveyServlet extends HttpServlet {

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

		Survey survey = new Survey();
		String a1 = request.getParameter("a1");
		survey.setA1(a1);
		String a2 = request.getParameter("a2");
		survey.setA2(a2);
		String a3 = request.getParameter("a3");
		survey.setA3(a3);
		String a4 = request.getParameter("a4");
		survey.setA4(a4);
		String a5 = request.getParameter("a5");
		survey.setA5(a5);
		String a6 = request.getParameter("a6");
		String []address = a6.split(",");
		if(address != null && address.length > 0){
			survey.setA61(address[0]);
			if(address.length > 1){
				survey.setA62(address[1]);
			} 
			if(address.length > 2){
				survey.setA63(address[2]);
			}
		}
		String a7 = request.getParameter("a7");
		survey.setA7(a7);
		survey.setIpAddress(request.getRemoteAddr());
		DbAction action = new DbAction();
		action.save(survey);
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("success");
		out.flush();
		out.close();
	}

}
