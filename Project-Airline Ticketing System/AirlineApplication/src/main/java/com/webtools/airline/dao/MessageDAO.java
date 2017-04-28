package com.webtools.airline.dao;

import java.util.List;
import java.util.Random;

import org.hibernate.Query;

import org.springframework.stereotype.Repository;

import com.webtools.airline.model.MessageBean;
import com.webtools.airline.model.User;

@Repository
public class MessageDAO extends DAO{
	
	public  void saveMessagetoDB(String emailID, String mailSubject, String mailBody) throws Exception{
		try{
		MessageBean message=new MessageBean();
		message.setUserEmail(emailID);
		message.setMessageText(mailBody);
		message.setMessageHeader(mailSubject);
		message.setMessageType("USER REQUEST");
		message.setStatus(1);
		Random randomno = new Random();	      
	    long value = randomno.nextLong();
	    message.setReferenceID(value);
	    begin();
	    getSession().save(message);
	    commit();
		}
	    catch(Exception e){
	    	e.printStackTrace();
	    }
	    
		
	}
	
	public void update(MessageBean msgBean) throws Exception{
		begin();
		msgBean.setStatus(2);
		getSession().update(msgBean);
		commit();
	}
	
	public  void saveReplytoDB(String emailID,long referenceid, String mailSubject, String mailBody) throws Exception{
		try{
		MessageBean message=new MessageBean();
		message.setUserEmail(emailID);
		message.setMessageText(mailBody);
		message.setMessageHeader(mailSubject);
		message.setMessageType("SUPPORT RESPONSE");		
	    message.setReferenceID(referenceid);
	    message.setStatus(2);
	    begin();
	    getSession().save(message);
	    commit();
		}
	    catch(Exception e){
	    	e.printStackTrace();
	    }
	     
		
	}
	
	public List<MessageBean> getMessages() throws Exception{
		
		Query query = getSession().createQuery("from MessageBean"); 
		List<MessageBean> list = query.list();
		return list;	
		
		
	}
	
public MessageBean getMessage(int messageID) throws Exception{
		
		Query query = getSession().createQuery("from MessageBean where messageID=:msgID"); 
		query.setInteger("msgID", messageID);
		MessageBean bean = (MessageBean)query.uniqueResult();
		return bean;	
		
		
	}

}
