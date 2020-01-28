package com.tcs.ilp.Account.Account.Model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
public class Transaction {
	@Column(name = "pbalance")
	double pbalance;
	@Column(name = "abalance")
	double abalance;
	@Column(name = "accountId")
	long accountId;
	@Column(name = "customerId")
	long customerId;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Date")
	Date date;
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
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
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
}
