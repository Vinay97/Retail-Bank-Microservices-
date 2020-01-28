package com.example.transaction.Transaction.Model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@SequenceGenerator(
name = "seqid-gen", 
sequenceName = "transid",
initialValue = 500000001, allocationSize = 1)
@Table(name = "transaction_1450967")
public class Transaction {

	@Id
	@Column(name="transId")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqid-gen")
	private long transId;
	
	@Column(name = "pbalance")
	private double pbalance;
	
	@Column(name = "abalance")
	private double abalance;
	
	@Column(name = "accountId")
	private long accountId;
	
	@Column(name = "customerId")
	private long customerId;

	@Column(name = "datee")
	Date date;

	@Column(name = "type")
	String type;
	


	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getTransId() {
		return transId;
	}

	public void setTransId(long transId) {
		this.transId = transId;
	}

	public double getPbalance() {
		return pbalance;
	}

	public void setPbalance(double pbalance) {
		this.pbalance = pbalance;
	}

	public double getAbalance() {
		return abalance;
	}

	public void setAbalance(double abalance) {
		this.abalance = abalance;
	}

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	
		
	
		/*@Column(name="transDate")
		//@Temporal(TemporalType.DATE)
		@CreationTimestamp
		private Date transDate;*/
}
