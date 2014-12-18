package com.zhijing.survey;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

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
		Enumeration <String> e = request.getParameterNames();
		while(e.hasMoreElements()){
			String key = e.nextElement();
			System.out.println(key + " = " + request.getParameter(key));
		}

		Survey survey = new Survey();
		String a1 = request.getParameter("q1");
		survey.setA1(a1);
		String a2 = request.getParameter("q2");
		survey.setA2(a2);
		String a3 = request.getParameter("q3");
		survey.setA3(a3);
		String a4 = request.getParameter("q4");
		survey.setA4(a4);
		String a5 = request.getParameter("q5");
		survey.setA5(a5);
		String a6 = request.getParameter("q6");
		survey.setA6(a6);
		String a7 = request.getParameter("q7");
		survey.setA7(a7);
		String a = request.getParameter("a");
		survey.setA(a);
		String addressStr = request.getParameter("city");
		String []address = addressStr.split(",");
		if(address != null && address.length > 0){
			survey.setProvince(address[0]);
			if(address.length > 1){
				survey.setCity(address[1]);
			} 
			if(address.length > 2){
				survey.setRegion(address[2]);
			}
		}
		survey.setIpAddress(request.getRemoteAddr());
		DbAction action = new DbAction();
		action.save(survey);
		
		response.setContentType("text/json");
		PrintWriter out = response.getWriter();
		out.println("{\"status\":200, \"data\":\"success\"}");
		out.flush();
		out.close();
	}

}
