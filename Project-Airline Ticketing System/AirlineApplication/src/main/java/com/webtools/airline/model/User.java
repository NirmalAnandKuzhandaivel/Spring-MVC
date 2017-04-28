package com.webtools.airline.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GeneratorType;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name = "AIRLINE_USERS")
 
public class User {

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	@Column(name = "user_id",unique=true,nullable=false)
	private int id;

	@Column(name = "firstname")
	private String firstName;

	@Column(name = "lastname")
	private String lastName;

	@Column(name = "email")
	private String emailID;

	@Column(name = "password")
	private String password;
	
	@Column(name="role")
	private String roleType;
	
	@Column(name="photoPath_Name")
	private String photoPathName;
	
	@Transient
	@Column(name="photoPath")
	private MultipartFile photo;
	
	@JsonManagedReference
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private List<Order> orderList=new ArrayList<Order>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailID() {
		return emailID;
	}

	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public List<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}

	public MultipartFile getPhoto() {
		return photo;
	}

	public void setPhoto(MultipartFile photo) {
		this.photo = photo;
	}

	public String getPhotoPathName() {
		return photoPathName;
	}

	public void setPhotoPathName(String photoPathName) {
		this.photoPathName = photoPathName;
	}

	

	

	
	
	

}
