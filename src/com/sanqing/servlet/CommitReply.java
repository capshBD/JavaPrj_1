package com.sanqing.servlet;


import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sanqing.bean.Reply;
import com.sanqing.dao.ReplyDAO;
import com.sanqing.factory.ReplyDAOFactory;
import com.sanqing.util.Encoding;

public class CommitReply extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Encoding.setEncode(request, response);
		
		int employeeID     =Integer.parseInt(request.getParameter("employeeID"));
		int msgemployeeID  =Integer.parseInt(request.getParameter("msgemployeeID"));
		int messageID     =Integer.parseInt(request.getParameter("messageID"));
		String content     =request.getParameter("content");
		ServletContext context=getServletContext();
		RequestDispatcher dispatcher=null;
		
		ReplyDAO replyDAO=ReplyDAOFactory.getReplyDAOInstance();
		
			if(msgemployeeID==employeeID){
				request.setAttribute("error", "不能回复自己的消息呀!");
				dispatcher=context.getRequestDispatcher("/showMsg.jsp");
			}else{
				Reply reply=new Reply();
				reply.setReplyContent(content);
				reply.setEmployeeID(employeeID);
				reply.setReplyTime(new Date());
				reply.setMessageID(messageID);
				replyDAO.addReply(reply);
				dispatcher=context.getRequestDispatcher("/GetReplyList?messageID="+reply.getMessageID());
			}
		
		dispatcher.forward(request, response);
	}

}
