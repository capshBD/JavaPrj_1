package com.sanqing.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.sanqing.bean.Message;
import com.sanqing.dao.MessageDAO;
import com.sanqing.dao.ReplyDAO;
import com.sanqing.factory.ReplyDAOFactory;
import com.sanqing.util.DBConnection;
import com.sanqing.util.Page;

public class MessageDAOImpl implements MessageDAO {

	@Override
	public void addMessage(Message message) {
		Connection conn=DBConnection.getConnection();
		String addSql="insert into tb_message values(null,?,?,?,?)";
		PreparedStatement pstmt=null;
		
		try {
			pstmt=conn.prepareStatement(addSql);
			pstmt.setString(1, message.getMessageTitle());
			pstmt.setString(2, message.getMessageContent());
			pstmt.setInt(3, message.getEmployeeID());
			pstmt.setTimestamp(4, new Timestamp(message.getPublishTime().getTime()));
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBConnection.close(pstmt);
			DBConnection.close(conn);
		}

	}

	@Override
	public void updateMessage(Message message) {
		

	}

	@Override
	public void deleteMessage(int messageID) {
		

	}

	@Override
	public List<Message> findAllMessage(Page page) {
		Connection conn=DBConnection.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String findAllSql="select * from tb_message order by publishTime desc limit ?,?";
		List<Message> messages=new ArrayList<Message>();
		
		try {
			ReplyDAO replyDAO=ReplyDAOFactory.getReplyDAOInstance();
			
			pstmt=conn.prepareStatement(findAllSql);
			pstmt.setInt(1, page.getBeginIndex());
			pstmt.setInt(2, page.getEveryPage());
			rs=pstmt.executeQuery();
			while (rs.next()) {
				Message message=new Message();
				message.setMessageID(rs.getInt(1));
				message.setMessageTitle(rs.getString(2));
				message.setMessageContent(rs.getString(3));
				message.setEmployeeID(rs.getInt(4));
				message.setPublishTime(rs.getTimestamp(5));
				message.setReplyCount(replyDAO.findCountByMsgId(message.getMessageID()));
				messages.add(message);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBConnection.close(rs);
			DBConnection.close(pstmt);
			DBConnection.close(conn);
		}
		
		return messages;
	}

	@Override
	public Message findMessageById(int messageID) {
		Connection conn=DBConnection.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Message message=null;
		String findByIdSql="select * from tb_message where messageID=?";
		
		try {
			pstmt=conn.prepareStatement(findByIdSql);
			pstmt.setInt(1, messageID);
			rs=pstmt.executeQuery();
			if(rs.next()){
				message=new Message();
				message.setMessageID(rs.getInt(1));
				message.setMessageTitle(rs.getString(2));
				message.setMessageContent(rs.getString(3));
				message.setEmployeeID(rs.getInt(4));
				message.setPublishTime(rs.getTimestamp(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBConnection.close(rs);
			DBConnection.close(pstmt);
			DBConnection.close(conn);
		}
		
		return message;
	}

	@Override
	public int findAllCount() {
		Connection conn=DBConnection.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String findAllCountSql="select count(*) from tb_message";
		int count=0;
		
		try {
			pstmt=conn.prepareStatement(findAllCountSql);
			rs=pstmt.executeQuery();
			if(rs.next()){
			   count=rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBConnection.close(rs);
			DBConnection.close(pstmt);
			DBConnection.close(conn);
		}
		return count;
	}

	@Override
	public List<Message> searchMessage(Page page, String key_word) {
		Connection conn=DBConnection.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String findAllSql="select * from tb_message where messageTitle like '%"+key_word+"%'" +
				"or messageContent like '%"+key_word+"%' order by publishTime desc limit ?,?";
		List<Message> messages=new ArrayList<Message>();
		
		try {
			ReplyDAO replyDAO=ReplyDAOFactory.getReplyDAOInstance();
			
			pstmt=conn.prepareStatement(findAllSql);
			pstmt.setInt(1, page.getBeginIndex());
			pstmt.setInt(2, page.getEveryPage());
			rs=pstmt.executeQuery();
			while (rs.next()) {
				Message message=new Message();
				message.setMessageID(rs.getInt(1));
				message.setMessageTitle(rs.getString(2));
				message.setMessageContent(rs.getString(3));
				message.setEmployeeID(rs.getInt(4));
				message.setPublishTime(rs.getTimestamp(5));
				message.setReplyCount(replyDAO.findCountByMsgId(message.getMessageID()));
				messages.add(message);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBConnection.close(rs);
			DBConnection.close(pstmt);
			DBConnection.close(conn);
		}
		
		return messages;
	}

	@Override
	public int searchMessageCount(String key_word) {
		Connection conn=DBConnection.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String findAllCountSql="select count(*) from tb_message where messageTitle like '%"+key_word+"%'" +
				"or messageContent like '%"+key_word+"%'";
		int count=0;
		
		try {
			pstmt=conn.prepareStatement(findAllCountSql);
			rs=pstmt.executeQuery();
			if(rs.next()){
			   count=rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBConnection.close(rs);
			DBConnection.close(pstmt);
			DBConnection.close(conn);
		}
		return count;
	}

}
