package com.webtools.airline.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.webtools.airline.Utility.Utility;
import com.webtools.airline.model.FlightDetails;
import com.webtools.airline.model.User;
import com.webtools.airline.model.UserJson;
@Repository
public class AdminDAO extends DAO{
	
	@Autowired
	private Utility utility;

	public int saveUser(FlightDetails flightDetails) throws Exception{
		try{ 
		long travelhours=utility.getTravelHours(flightDetails.getStartDate(),
				flightDetails.getEndDate(),flightDetails.getStartTime(),flightDetails.getEndTime());
		System.out.println(travelhours);
		flightDetails.setJourneyhours(travelhours + " hours");
		begin();		 
		getSession().save(flightDetails);
		 commit();
		 return 0;
		}
		 catch(Exception e){
			// e.printStackTrace();
			 return -1;
		 }
	}
	
    public List<FlightDetails> getFlightTrips() throws Exception{
		
		Query query = getSession().createQuery("from FlightDetails"); 
		List<FlightDetails> list = query.list();
		System.out.println(list.size());
		
		return list;
		
	}
    
	public List<UserJson> getUserList() throws Exception {

		Query query = getSession().createQuery("from User");
		List<User> list = query.list();
		List<UserJson> userJson = new ArrayList<UserJson>();
		for (User u : list) {
			if (u.getRoleType().equalsIgnoreCase("CUSTOMER")||u.getRoleType().equalsIgnoreCase("SUPPORT")) {
				UserJson json = new UserJson();
				json.setEmailID(u.getEmailID());
				json.setFirstName(u.getFirstName());
				json.setId(u.getId());
				json.setLastName(u.getLastName());
				json.setPassword(u.getPassword());
				json.setRoleType(u.getRoleType());
				userJson.add(json);
			}
		}
		return userJson;
	}
	
	public int deleteRecord(int id) throws Exception{
		try{
		Query query = getSession().createQuery("from User where id=:id");
		query.setInteger("id",id);
		User user=(User)query.uniqueResult();
		begin();
		getSession().delete(user);
		commit();
		
		return 0;
		}
		catch(Exception e){
			//e.printStackTrace();
			return -1;
		}
	}
		
		public int deleteFlightRecord(int id) throws Exception{
			try{
			Query query = getSession().createQuery("from FlightDetails where flightID=:flightID");
			query.setInteger("flightID",id);
			FlightDetails fd=(FlightDetails)query.uniqueResult();
			begin();
			getSession().delete(fd);
			commit();
			
			return 0;
			}
			catch(Exception e){
				e.printStackTrace();
				return -1;
			}
		
	}
}
