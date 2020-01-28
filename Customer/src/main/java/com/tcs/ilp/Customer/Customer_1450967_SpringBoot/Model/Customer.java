package com.tcs.ilp.Customer.Customer_1450967_SpringBoot.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

@Entity
@SequenceGenerator(
name = "seqid-gen", 
sequenceName = "custid",
initialValue = 111111111, allocationSize = 1)

@Table(name="customer_1450967")
//@GeneratedValue(strategy = GenerationType.TABLE, generator = "my_sequence")    
public class Customer {
	
	@Column(name="customerssnid")
	private long customerssnid;
		
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqid-gen")
	@Column(name = "customerid")
	private long customerid;
	
	public long getCustomerid() {
		return customerid;
	}

	@Column(name="customername")
	private String customername;
	
	@Column(name="contactno")
	private long contactno;
	
	@Column(name="city")
	private String city;
	
	@Column(name="email")
	private String email;
	
	@Column(name="status")
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getCustomerssnid() {
		return customerssnid;
	}

	public void setCustomerssnid(long customerssnid) {
		this.customerssnid = customerssnid;
	}

	

	public void setCustomerid(long customerid) {
		this.customerid = customerid;
	}

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	public long getContactno() {
		return contactno;
	}

	public void setContactno(long contactno) {
		this.contactno = contactno;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	

}
