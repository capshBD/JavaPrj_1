package com.sanqing.dao;

import java.util.List;

import com.sanqing.bean.Reply;
import com.sanqing.util.Page;

public interface ReplyDAO {
	public void addReply(Reply reply);
	public List<Reply> findReplyByMsgId(int messageID,Page page);
	public int findCountByMsgId(int messageID);
}
