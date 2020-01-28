package com.tcs.ilp.Account.Account.Controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.ilp.Account.Account.Model.Account;
import com.tcs.ilp.Account.Account.Model.Transaction;
import com.tcs.ilp.Account.Account.Service.AccountService;

@CrossOrigin
@RestController
public class AccountController {

	@Autowired
	AccountService accountService;
	
	@RequestMapping(value="/createAccount",method=RequestMethod.POST)
	public ResponseEntity<Long> createAccount(@RequestBody Account a){
		return new ResponseEntity<Long>(accountService.createAccount(a),HttpStatus.OK);
	}
	@RequestMapping(value = "/viewAllAccount",method = RequestMethod.GET)
	public ResponseEntity<ArrayList<Account>> viewAllAccount(){
	   return new ResponseEntity<ArrayList<Account>>(accountService.viewAllAccount(),HttpStatus.OK);
	}
	
	@RequestMapping(value="/viewByAccountId",method = RequestMethod.GET)
	public ResponseEntity<Account> viewByAccountId(@RequestParam("accountId") long accid){
		return new ResponseEntity<Account>(accountService.viewAccountByAId(accid),HttpStatus.OK);
	}
	
	@RequestMapping(value="/viewAccByCustId",method = RequestMethod.GET)
	public ResponseEntity<ArrayList<Account>> viewByAccByCustId(@RequestParam("customerId") long cusid){
		return new ResponseEntity<ArrayList<Account>>(accountService.viewAccountByCId(cusid),HttpStatus.OK);
	}
	@RequestMapping(value="/checkAccById",method = RequestMethod.GET)
	public ResponseEntity<Boolean> checkAccById(@RequestParam("accountId") long accid){
		return new ResponseEntity<Boolean>(accountService.checkAccountById(accid),HttpStatus.OK);
	}
	@RequestMapping(value="/getBalanceById",method=RequestMethod.POST)
	public ResponseEntity<Double> viewbalance(@RequestParam("accountId") long accid)
	{
		return new ResponseEntity<Double>(accountService.getbalance(accid),HttpStatus.OK);
	}
	@RequestMapping(value="/updateBalance",method=RequestMethod.GET)
	public ResponseEntity<Transaction> updateBalance(@RequestParam(name="accountId") long accountId,@RequestParam(name="amount") double amount,@RequestParam(name="type") String type)
	{
		Transaction t=new Transaction();
		Object rows="";
		t.setAccountId(accountId);
		Account a=accountService.viewAccountByAId(accountId);t.setCustomerId(a.getCustomerId());
		t.setPbalance(a.getBalance());
		if(type.equals("credit"))
		{
			rows=accountService.updatebalance(a.getBalance()+amount,accountId);
			System.out.println("rowsfdf=="+rows);
			t.setAbalance(a.getBalance()+amount);
		}
		else if(type.equals("debit"))
		{
			System.out.println("TYPE debit="+type);
			if(a.getBalance()<amount)
			{
				t.setAbalance(-1);
			}
			else
			{
				rows=accountService.updatebalance(a.getBalance()-amount, accountId);
				System.out.println("rowdfds=="+rows);
				t.setAbalance(a.getBalance()-amount);
			}
		}
		System.out.println("rows=="+rows);
		return new ResponseEntity<Transaction>(t,HttpStatus.OK);
	}
	@RequestMapping(value="/deleteAccById",method = RequestMethod.GET)
	public ResponseEntity<Long> deleteAccById(@RequestParam("accountId") long accid){
		boolean ab=accountService.checkAccountById(accid);
		long qq=-1;
		if(ab==true)
	     return new ResponseEntity<Long>(accountService.deleteAccount(accid),HttpStatus.OK);
		else
			return new ResponseEntity<Long>(qq,HttpStatus.OK);
	}
	@RequestMapping(value="/updateAccount",method = RequestMethod.POST)
	public ResponseEntity<Boolean> updateAccount(@RequestBody Account ac){
		boolean ab=accountService.checkAccountById(ac.getAccountId());
		if(ab==true)
		return new ResponseEntity<Boolean>(accountService.updateAccount(ac),HttpStatus.OK);
		else
			return new ResponseEntity<Boolean>(ab,HttpStatus.OK);
	}
}
