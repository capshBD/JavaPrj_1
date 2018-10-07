package com.sanqing.servlet;


import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sanqing.bean.Employee;
import com.sanqing.bean.Message;
import com.sanqing.dao.MessageDAO;
import com.sanqing.factory.MessageDAOFactory;
import com.sanqing.util.Encoding;

public class MsgPublish extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Encoding.setEncode(request, response);
		ServletContext context=getServletContext();
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		RequestDispatcher dispatcher=null;
		
		Employee employee=(Employee) request.getSession().getAttribute("employee");
		
		if(employee==null){
			request.setAttribute("error", "必须先进行身份认证!");
			dispatcher=context.getRequestDispatcher("/publishNewMsg.jsp");
		}else{
			if(title.equals("")||title==null){
				request.setAttribute("error", "请输入消息标题!");
				dispatcher=context.getRequestDispatcher("/publishNewMsg.jsp");
			}else{
				Message message=new Message();
				message.setMessageTitle(title);
				message.setMessageContent(content);
				message.setEmployeeID(employee.getEmployeeID());
				message.setPublishTime(new Date());
				
				MessageDAO messageDAO=MessageDAOFactory.getMessageDAOInstance();
				messageDAO.addMessage(message);
				
				dispatcher=context.getRequestDispatcher("/GetMessageList");
			}
		}
		
		dispatcher.forward(request, response);
	}
}
