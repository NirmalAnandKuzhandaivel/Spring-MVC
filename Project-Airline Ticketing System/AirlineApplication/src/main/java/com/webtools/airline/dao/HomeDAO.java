package com.webtools.airline.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.webtools.airline.model.User;

@Repository
public class HomeDAO extends DAO{
     
	public int saveUser(User user) throws Exception{
		 begin();
		 Query q = getSession().createQuery("from User where email = :email");
	        q.setString("email", user.getEmailID());
	        boolean result=(q.uniqueResult()!=null);
	        if(result) {
	            return -1;
	        }
	        else {
	        	getSession().save(user);
	   		    commit();
	        }
		 
		 return 0;
	}
	
	public boolean isValidUser(String email,String password) throws Exception{
		
		Query q = getSession().createQuery("from User where email = :email and password =:password");
        q.setString("email", email);
        q.setString("password", password);
        return (q.uniqueResult()!=null);
		
		
	}
	
    public User getUser(String email,String password){
		
		Query q = getSession().createQuery("from User where email = :email and password =:password");
        q.setString("email", email);
        q.setString("password", password);
        User user=(User)q.uniqueResult();
        return user;
		
		
	}
    
public User getUserEmail(String email){
		
		Query q = getSession().createQuery("from User where email = :email");
        q.setString("email", email);
        User user=(User)q.uniqueResult();
        return user;
		
		
	}
    
    public void updateUser(User user,String firstName,String lastName,String emailID,String photoName) throws Exception{
		
		Query q = getSession().createQuery("from User where id = :userid");
        q.setInteger("userid",user.getId());
        User u=(User)q.uniqueResult();
        u.setEmailID(emailID);
        u.setFirstName(firstName);
        u.setLastName(lastName);
        u.setPhotoPathName(photoName);
        begin();
        getSession().update(u);
        commit();
       
		
	}
    
    
	
}
