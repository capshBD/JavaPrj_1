package com.sanqing.servlet;


import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sanqing.bean.Employee;
import com.sanqing.bean.Reply;
import com.sanqing.dao.ReplyDAO;
import com.sanqing.factory.ReplyDAOFactory;
import com.sanqing.util.Encoding;
import com.sanqing.util.Page;
import com.sanqing.util.PageUtil;

public class GetReplyList extends HttpServlet {
	private static final long serialVersionUID = 6683563893091626579L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Encoding.setEncode(request, response);
		int messageID=Integer.parseInt(request.getParameter("messageID"));
		String currentPageStr=request.getParameter("currentPage");
		ServletContext context=getServletContext();
		RequestDispatcher dispatcher=null;
		
		int currentPage=0;
		if(currentPageStr==null||("").equals(currentPageStr)){
			currentPage=1;
		}else{
			currentPage=Integer.parseInt(currentPageStr);
		}
		
		ReplyDAO replyDAO=ReplyDAOFactory.getReplyDAOInstance();
		Page page=PageUtil.createPage(5, replyDAO.findCountByMsgId(messageID), currentPage);
		
		List<Reply> replys=replyDAO.findReplyByMsgId(messageID, page);
		
		HttpSession session=request.getSession();
		Employee employee=(Employee) session.getAttribute("employee");
		if(employee==null){
			request.setAttribute("error", "请先进行身份认证");
			dispatcher=context.getRequestDispatcher("/msgList.jsp");
			dispatcher.forward(request, response);
			return;
		}
		session.setAttribute("employeeID", employee.getEmployeeID());
		session.setAttribute("rePage", page);
		session.setAttribute("replys", replys);
		response.sendRedirect("showMsg.jsp");
	}
}
