package com.sanqing.servlet;


import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sanqing.bean.Employee;
import com.sanqing.dao.EmployeeDAO;
import com.sanqing.factory.EmployeeDAOFactory;
import com.sanqing.util.DateUtil;
import com.sanqing.util.Encoding;

@SuppressWarnings("serial")
public class EmpRegister extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Encoding.setEncode(request, response);
		boolean isLead=false;
		boolean sex=false;
		String empName=request.getParameter("empName");
		String password=request.getParameter("password");
		String sx=request.getParameter("sex");
		String eBirth=request.getParameter("birthDate");
		String empPhone=request.getParameter("empPhone");
		String empPlace=request.getParameter("empPlace");
		String jTime=request.getParameter("joinTime");
		Date empBirth=DateUtil.StringToDate(eBirth);
		Date joinTime=DateUtil.StringToDate(jTime);
		
		if(sx.equals("男")){
			isLead=true;
			sex=true;
		}
		    Employee emp=new Employee();
			emp.setEmployeeName(empName);
			emp.setPassword(password);
			emp.setEmployeeSex(sex);
			emp.setEmployeeBirth(empBirth);
			emp.setEmployeePhone(empPhone);
			emp.setEmployeePlace(empPlace);
			emp.setJoinTime(joinTime);
			emp.setLead(isLead);
			
			EmployeeDAO empDAO=EmployeeDAOFactory.getEmployeeDAOInstance();
			
			empDAO.addEmployee(emp);
			
			request.getSession().setAttribute("regOk", emp.getEmployeeID());
			response.sendRedirect("index.jsp");
	}
}
