package com.sanqing.servlet;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sanqing.bean.Message;
import com.sanqing.dao.MessageDAO;
import com.sanqing.factory.MessageDAOFactory;
import com.sanqing.util.Encoding;
import com.sanqing.util.Page;
import com.sanqing.util.PageUtil;

public class GetMessageList extends HttpServlet {
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
		int currentPage=0;
		String currentPageStr=request.getParameter("currentPage");
		String key_word=request.getParameter("key_word");
		if(currentPageStr==null||currentPageStr.equals("")){
			currentPage=1;
		}else{
			currentPage=Integer.parseInt(currentPageStr);
		}
		
		MessageDAO messageDAO=MessageDAOFactory.getMessageDAOInstance();
		Page page;
		List<Message> messageList;	
		if(key_word==null||key_word.isEmpty()){
			page=PageUtil.createPage(5,messageDAO.findAllCount() , currentPage);
			messageList=messageDAO.findAllMessage(page);
		}else{
			page=PageUtil.createPage(5,messageDAO.searchMessageCount(key_word) , currentPage);
			messageList=messageDAO.searchMessage(page, key_word);
		}
		
		HttpSession session=request.getSession();
		session.setAttribute("key_word", key_word);
		session.setAttribute("page", page);
		session.setAttribute("messageList",messageList);	
		response.sendRedirect("msgList.jsp");
	}
}
