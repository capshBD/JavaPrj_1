package com.sanqing.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.sanqing.bean.Reply;
import com.sanqing.dao.ReplyDAO;
import com.sanqing.util.DBConnection;
import com.sanqing.util.Page;

public class ReplyDAOImpl implements ReplyDAO {

	@Override
	public void addReply(Reply reply) {
		Connection conn=DBConnection.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs;
		int floor;
		String queryStatusSql="select max(floorStatus) from tb_reply where messageID=?";
		String addSql="insert into tb_reply values(null,?,?,?,?,?)";
		
		try {
			pstmt=conn.prepareStatement(queryStatusSql);
			pstmt.setInt(1, reply.getMessageID());
			rs=pstmt.executeQuery();
			if(rs.next()){
				 floor=rs.getInt(1);
			}else{
				 floor=0;
			}
			pstmt=conn.prepareStatement(addSql);
			pstmt.setString(1,reply.getReplyContent());
			pstmt.setInt(2,reply.getEmployeeID());
			pstmt.setTimestamp(3, new Timestamp(reply.getReplyTime().getTime()));
			pstmt.setInt(4,reply.getMessageID());
			pstmt.setInt(5,floor+1);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBConnection.close(pstmt);
			DBConnection.close(conn);
		}

	}

	@Override
	public List<Reply> findReplyByMsgId(int messageID, Page page) {
		Connection conn=DBConnection.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String findByMsgIdSql="select * from tb_reply where messageID=? order by replyTime  limit ?,?";
		List<Reply> replys=new ArrayList<Reply>();
		
		try {
			pstmt=conn.prepareStatement(findByMsgIdSql);
			pstmt.setInt(1, messageID);
			pstmt.setInt(2, page.getBeginIndex());
			pstmt.setInt(3, page.getEveryPage());
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				Reply reply=new Reply();
				reply.setReplyID(rs.getInt(1));
				reply.setReplyContent(rs.getString(2));
				reply.setEmployeeID(rs.getInt(3));
				reply.setReplyTime(rs.getTimestamp(4));
				reply.setMessageID(rs.getInt(5));
				reply.setFloor(rs.getInt(6));
				replys.add(reply);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBConnection.close(rs);
			DBConnection.close(pstmt);
			DBConnection.close(conn);
		}
		return replys;
	}

	@Override
	public int findCountByMsgId(int messageID) {
		Connection conn=DBConnection.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String findCountByMsgIdSql="select count(*) from tb_reply where messageID=?";
		int count=0;
		
		try {
			pstmt=conn.prepareStatement(findCountByMsgIdSql);
			pstmt.setInt(1,messageID);
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
