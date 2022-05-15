package com;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UserAPI")
public class UserAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	User userObj = new User();
	
    public UserAPI() {
        super();
       
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String output = userObj.insertUser(request.getParameter("userName"),
				request.getParameter("userType"),
				request.getParameter("userEmail"),
				request.getParameter("userPhone"),
				request.getParameter("userPassword"));
		response.getWriter().write(output); 
	}

	private static Map getParasMap(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		try
		{
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			String queryString = scanner.hasNext() ?
					 
			scanner.useDelimiter("\\A").next() : "";
			scanner.close();
			 
			String[] params = queryString.split("&");
			 
			for (String param : params)
			{ 
				String[] p = param.split("=");
				map.put(p[0], p[1]);
			}
		}
		catch (Exception e)
			{
			}
		return map; 
		
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Map paras = getParasMap(request);
			String output = userObj.updateUser(paras.get("hidUserIdSave").toString(),
				paras.get("userName").toString(),
				paras.get("userType").toString(),
				paras.get("userEmail").toString(),
				paras.get("userPhone").toString(),
				paras.get("userPasword").toString());

		response.getWriter().write(output); 
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		 Map paras = getParasMap(request);
		 String output = userObj.deleteUser(paras.get("id").toString());
		 response.getWriter().write(output); 
	}

}
