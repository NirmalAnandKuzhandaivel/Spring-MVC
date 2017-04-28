package com.webtools.airline.model;

import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity  
@Table(name = "AIRLINE_CUSTOMERS")  
@PrimaryKeyJoinColumn(name="CUSTOMER_ID")
public class Customer extends User {
	
	@OneToMany(cascade={CascadeType.ALL})
    @JoinColumn(name="CUSTOMER_ID")
	private ArrayList<Order> OrderHistory;
	

	public ArrayList<Order> getOrderHistory() {
		return OrderHistory;
	}
	public void setOrderHistory(ArrayList<Order> orderHistory) {
		OrderHistory = orderHistory;
	}
	
	
}
