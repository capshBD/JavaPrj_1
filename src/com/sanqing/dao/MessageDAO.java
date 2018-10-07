package com.sanqing.dao;

import java.util.List;

import com.sanqing.bean.Message;
import com.sanqing.util.Page;

public interface MessageDAO {
	public void addMessage(Message message);
	public void updateMessage(Message message);
	public void deleteMessage(int messageID);
	public Message findMessageById(int messageID);
	public List<Message> findAllMessage(Page page);
	public int findAllCount();
	public List<Message> searchMessage(Page page,String key_word);
	public int searchMessageCount(String key_word);
}
