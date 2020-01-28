package com.tcs.ilp.Account.Account.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.tcs.ilp.Account.Account.Model.Account;
import com.tcs.ilp.Account.Account.Model.Transaction;
import com.tcs.ilp.Account.Account.Repo.AccountRepo;

@Component
public class AccountService {

	@Autowired
	AccountRepo accountRepo;
	@Autowired
	private LoadBalancerClient loadBalancer;
	
	public long checkbalance(long accid)
	{
		return accountRepo.checkbalance(accid);
	}
	public Object updatebalance(double bal,long accid)
	{
		return accountRepo.updatebalance(bal,accid);
	}
	public ArrayList<Account> viewAccountByCId(long cusid)
	{
		return accountRepo.viewAccountByCId(cusid);
	}
	public ArrayList<String> checkaccount(long cid)
	{
		return accountRepo.checkaccount(cid);
	}
	public long createAccount(Account a) {
		ServiceInstance serviceInstance=loadBalancer.choose("RETAIL_BANKING");	
		System.out.println(serviceInstance.getUri());
		String baseUrl=serviceInstance.getUri().toString();
		String url=baseUrl+"/api/customer/checkCustomer?customerid="+a.getCustomerId();
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Boolean> response=restTemplate.getForEntity(url, Boolean.class);
		boolean v=false;v=response.getBody();
		if(v==true)
		{
			ArrayList<String> avv=checkaccount(a.getCustomerId());
			for(String g:avv)
			{
				if(g.equals(a.getAccountType()))
						v=false;
			}
			if(v==false)return -1;
			return accountRepo.save(a).getAccountId();
		}
		else
			return -1;
	}
	public double getbalance(long aid)
	{
		return accountRepo.viewbalance(aid);
	}
	public Account viewAccountByAId(long aid) {
		Account ac = null;
		ac = accountRepo.findById(aid).get();
		if(ac!=null) {
			return ac;
		}
		return ac;
	}
	
	public ArrayList<Account> viewAllAccount(){
		return (ArrayList<Account>) accountRepo.findAll();
	}
	
	public boolean checkAccountById(long id) {
		return accountRepo.existsById(id);
	}
	
	public long deleteAccount(long aid) {
		long ab=checkbalance(aid);
		if(ab>0)
			return  ab;
		accountRepo.deleteById(aid);
		return 1;
	}
	
	public boolean updateAccount(Account b) {
		Account as=accountRepo.save(b);
		if(as==null)return false;
		return true;
	}
}
