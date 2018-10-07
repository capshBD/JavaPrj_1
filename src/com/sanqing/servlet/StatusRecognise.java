package com.sanqing.servlet;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sanqing.bean.Employee;
import com.sanqing.dao.EmployeeDAO;
import com.sanqing.factory.EmployeeDAOFactory;
import com.sanqing.util.Encoding;

public class StatusRecognise extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			Encoding.setEncode(request, response);
			ServletContext servletContext=getServletContext();
			RequestDispatcher dispatcher=null;
			String employeeID=request.getParameter("employeeID");
			String password=request.getParameter("password");
			
			if(employeeID==null||password==null||employeeID.equals("")||password.equals("")){
				request.setAttribute("error", "请输入员工编号或口令!");
				dispatcher=servletContext.getRequestDispatcher("/statusRecognise.jsp");
				
			}else{
				
				EmployeeDAO employeeDAO=EmployeeDAOFactory.getEmployeeDAOInstance();
				
				Employee employee=employeeDAO.findEmployeeById(Integer.parseInt(employeeID));
				
				if(employee==null){
					request.setAttribute("error", "该员工编号不存在!");
					dispatcher=servletContext.getRequestDispatcher("/statusRecognise.jsp");
				}else{
					if(!password.equals(employee.getPassword())){
						request.setAttribute("error", "口令错误!");
						dispatcher=servletContext.getRequestDispatcher("/statusRecognise.jsp");
					}else{
						HttpSession session=request.getSession();
						session.setAttribute("employee", employee);
						session.setAttribute("info", "欢迎你"+employee.getEmployeeName());
						response.sendRedirect("index.jsp");
						return;
					}
				}
			}
			dispatcher.forward(request, response);
	}
}
