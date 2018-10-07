package com.sanqing.servlet;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sanqing.bean.Message;
import com.sanqing.dao.MessageDAO;
import com.sanqing.factory.MessageDAOFactory;
import com.sanqing.util.Encoding;

public class GetMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Encoding.setEncode(request, response);
		String messageID=request.getParameter("messageID");
		int MsgID=Integer.valueOf(messageID);
		
		MessageDAO messageDAO=MessageDAOFactory.getMessageDAOInstance();
		Message message=messageDAO.findMessageById(MsgID);
		request.getSession().setAttribute("message", message);
		getServletContext().getRequestDispatcher("/GetReplyList?messageID="+messageID).forward(request, response);
	}
}
