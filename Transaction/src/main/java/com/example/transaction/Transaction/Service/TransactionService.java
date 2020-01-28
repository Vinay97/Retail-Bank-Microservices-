package com.example.transaction.Transaction.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.transaction.Transaction.Model.Account;
import com.example.transaction.Transaction.Model.Transaction;
import com.example.transaction.Transaction.Repo.TransactionRepository;

@Service
public class TransactionService {

	@Autowired
	TransactionRepository transRepo;
	@Autowired
	private LoadBalancerClient loadBalancer;
	
	public boolean insertdata(Transaction n)
	{
		Transaction ty=transRepo.save(n);
		if(ty==null)return false;
		return true;
	}
	public ArrayList<Transaction> getall()
	{
		return (ArrayList<Transaction>) transRepo.findAll();
	}
	public long deposit(Account s) {
		ServiceInstance serviceInstance=loadBalancer.choose("RETAIL_BANKING");	
		System.out.println(serviceInstance.getUri());
		String baseUrl=serviceInstance.getUri().toString();
		String url=baseUrl+"/api/account/updateBalance?accountId="+s.getAccountId()+"&amount="+s.getBalance()+"&type=credit";
		RestTemplate restTemplate = new RestTemplate();
		System.out.println("url-"+url);
		long ab = 0;
		try {
		ResponseEntity<Transaction> response=restTemplate.getForEntity(url,Transaction.class);
		Transaction tt=response.getBody();
		if(tt.getAbalance()<s.getBalance())
		{
			return ab;
		}
		else
		{
			//tt.setDate(new Date().getTime());
			tt.setDate(new Date());
			tt.setType("credit");
			ab = transRepo.save(tt).getTransId();
			return ab;
		}
		}catch(Exception t) {t.printStackTrace();}
		finally{return ab;}
	}
	public long withdraw(Account s)
	{
		ServiceInstance serviceInstance=loadBalancer.choose("RETAIL_BANKING");	
		System.out.println(serviceInstance.getUri());
		String baseUrl=serviceInstance.getUri().toString();
		String url=baseUrl+"/api/account/updateBalance?accountId="+s.getAccountId()+"&amount="+s.getBalance()+"&type=debit";
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Transaction> response=restTemplate.getForEntity(url,Transaction.class);
		Transaction tt=response.getBody();
		long ab=0;
		if(tt.getAbalance()<0)
		{
			return ab;
		}
		else
		{
			tt.setDate(new Date());
			tt.setType("Debit");
			ab = transRepo.save(tt).getTransId();
			return ab;
		}
	}
	public ArrayList<Transaction> viewbyaccid(long accid)
	{
		return transRepo.viewbyaccid(accid);
	}
	public ArrayList<Transaction> viewbyacciddate(long accid,String sdate,String edate)
	{
		ArrayList<Transaction> qw=new ArrayList<Transaction>();
		try {
			System.out.println("accid="+accid);
			System.out.println("sdate="+sdate);
			System.out.println("edate="+edate);
		qw= transRepo.viewbyacciddate(accid, sdate, edate);
		}
		catch(Exception t)
		{t.printStackTrace();}
		finally {return qw;}
	}
	public ArrayList<Transaction> viewbycustdate(long custid,String d1,String d2)
	{
		return transRepo.viewbycustdate(custid, d1, d2);
	}
	public ArrayList<Transaction> viewalldate(String d1,String d2)
	{
		return transRepo.viewalldate(d1, d2);
	}
}
