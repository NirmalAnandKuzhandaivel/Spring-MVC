package com.webtools.airline.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.webtools.airline.Utility.Utility;
import com.webtools.airline.model.FlightDetails;
import com.webtools.airline.model.Order;
import com.webtools.airline.model.Passenger;
import com.webtools.airline.model.PassengerJsonBean;
import com.webtools.airline.model.Ticket;
import com.webtools.airline.model.TicketJsonBean;
import com.webtools.airline.model.User;

@Repository
public class CustomerDAO extends DAO{
	
	@Autowired
	private Utility utility;

    
    
     public List<FlightDetails> getFlightTrips(String fromPlace,String toPlace,String departDate) throws Exception{
		
	   
	  
		Query query = getSession().createQuery("from FlightDetails where from_place=:fromPlace and to_place=:toPlace"
				+ " and startDate=:startDate");
		query.setString("fromPlace", fromPlace);
		query.setString("toPlace", toPlace);
		query.setString("startDate",utility.convertDate(departDate));
		List<FlightDetails> list = query.list();
		return list;
		
	}
     
     public FlightDetails getDepartureDetails(String flightID){
    	 
    	 Query query=getSession().createQuery("from FlightDetails where flightID=:flightID");
    	 query.setString("flightID", flightID);
    	 FlightDetails fd=(FlightDetails) query.uniqueResult();
    	 
		return fd;
    	 
     }
     
 public Order getOrder(int orderID){
    	 
    	 Query query=getSession().createQuery("from Order where orderID=:ordID");
    	 query.setInteger("ordID", orderID);
    	 Order fd=(Order) query.uniqueResult();
    	 
		return fd;
    	 
     }
     
     

	public Order saveOrder(Ticket ticket1, Ticket ticket2, List<Passenger> passengerList, Order order) {
		 
		begin();
		getSession().save(order);
		ticket1.setOrder(order);
		order.getTickets().add(ticket1);
		if (ticket2 != null) {
			ticket2.setOrder(order);
			getSession().save(ticket2);
		}
		getSession().save(ticket1);
		for(Passenger passenger:passengerList){
			passenger.setOrder(order);
			order.getPassengerList().add(passenger);
			getSession().save(passenger);
		}

		commit();
		
		return order;
	} 
	
	public List<Order> getOrderHistory(User user) throws Exception{
		
		
		Query q = getSession().createQuery("from User where email = :email");
        q.setString("email", user.getEmailID());
        User user1=(User)q.uniqueResult();
		 List<Order> orderList = user1.getOrderList();
		 for(Order o:orderList){
			 System.out.println(o.getNoOfPassengers());
			 System.out.println(o.getPrice());
			 System.out.println(o.getOrderID());
			 System.out.println(o.getUser());
			 
		 }
			return orderList;
	}
	
	public List<TicketJsonBean> getTickets(int orderID) throws Exception{
		Query q = getSession().createQuery("from Order where orderID = :orderID");
        q.setInteger("orderID", orderID);
        Order o=(Order)q.uniqueResult();
        
        
       
        List<TicketJsonBean> ticketList=new ArrayList<TicketJsonBean>();
        System.out.println(o.getTickets().size());
       if(o.getTickets()!=null){
        for(Ticket tick:o.getTickets()){
        	if(tick!=null){
        	TicketJsonBean json=new TicketJsonBean();
        	json.setFlightID(tick.getFlightID());
        	json.setFlightCompany(tick.getFlightCompany());
        	json.setFromPlace(tick.getFromPlace());
        	json.setEndTime(tick.getEndTime());
        	json.setFromPlace(tick.getFromPlace());
        	json.setToPlace(tick.getToPlace());
        	json.setStartTime(tick.getStartTime());
        	json.setStartDate(tick.getStartDate());
        	json.setEndDate(tick.getEndDate());
        	json.setPrice(tick.getPrice());
        	json.setTicketID(tick.getTicketID());
        	json.setTicketType(tick.getTicketType());
        	ticketList.add(json);
        	}
        	
        }
       }
		return ticketList;
       
		
		
		
	}
	
	public List<PassengerJsonBean> getPassengers(int orderID) throws Exception{
		Query q = getSession().createQuery("from Order where orderID = :orderID");
        q.setInteger("orderID", orderID);
        Order o=(Order)q.uniqueResult();
        List<PassengerJsonBean> passengerList=new ArrayList<PassengerJsonBean>();
        if(o.getPassengerList()!=null){
        for(Passenger p:o.getPassengerList()){
        	PassengerJsonBean json=new PassengerJsonBean();
        	json.setAge(p.getAge());
        	json.setFirstName(p.getFirstName());
        	json.setLastName(p.getLastName());
        	json.setIdProof(p.getIdProof());
        	json.setPassengerID(p.getPassengerID());
        	
        	
        	passengerList.add(json);
        	
        	
        }
        }
		return passengerList;
		
		
		
	}
	
	public void updateFlights(Ticket t1,Ticket t2) throws Exception{
		try{
		String flightID1=t1.getFlightID();
		
		Query q1 = getSession().createQuery("from FlightDetails where flightID = :flightID1");
        q1.setString("flightID1", flightID1);
        FlightDetails f1=(FlightDetails)q1.uniqueResult();
        begin();
        f1.setTicketAvailability(f1.getTicketAvailability()-t1.getOrder().getNoOfPassengers());
		
		getSession().update(f1);
		if(t2!=null){
			String flightID2=t2.getFlightID();
			Query q2 = getSession().createQuery("from FlightDetails where flightID = :flightID2");
	        q1.setString("flightID2", flightID2);
	        FlightDetails f2=(FlightDetails)q2.uniqueResult();
	        f2.setTicketAvailability(f2.getTicketAvailability()-t2.getOrder().getNoOfPassengers());
	        getSession().update(f2);
		}
		commit();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
}
