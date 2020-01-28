package com.example.transaction.Transaction.Controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.transaction.Transaction.Model.Account;
import com.example.transaction.Transaction.Model.Transaction;
import com.example.transaction.Transaction.Service.TransactionService;

@RestController
@CrossOrigin
public class TransactionController {
	@Autowired
	TransactionService ts;
	@RequestMapping(value="/Deposit",method=RequestMethod.POST)
	public ResponseEntity<Long> deposit(@RequestBody Account a){
		return new ResponseEntity<Long>(ts.deposit(a),HttpStatus.OK);
	}
	@RequestMapping(value="/Withdraw",method=RequestMethod.POST)
	public ResponseEntity<Long> withdraw(@RequestBody Account a){
		return new ResponseEntity<Long>(ts.withdraw(a),HttpStatus.OK);
	}
	@RequestMapping(value="/insertdata",method=RequestMethod.POST)
	public ResponseEntity<Boolean> createAccount(@RequestBody Transaction n){
		System.out.println("nnn="+n.getPbalance());
		n.setDate(new Date());
		System.out.println("nnn="+n.getDate());
		System.out.println("nnn="+n.getAccountId());
		System.out.println("nnn="+n.getCustomerId());
		System.out.println("nnn="+n.getAbalance());
		System.out.println("nnn="+n.getType());
		System.out.println("nnn="+n.getTransId());
		try
		{
		return new ResponseEntity<Boolean>(ts.insertdata(n),HttpStatus.OK);
		}
		catch(Exception pp)
		{
			pp.printStackTrace();
			System.out.println("DF");
			System.out.println("DF");
		}
		finally
		{
			return new ResponseEntity<Boolean>(ts.insertdata(n),HttpStatus.OK);
		}
	}
	@RequestMapping(value="/showall",method=RequestMethod.POST)
	public ResponseEntity<ArrayList<Transaction>> getall()
	{
		return new ResponseEntity<ArrayList<Transaction>>(ts.getall(),HttpStatus.OK);
	}
	@RequestMapping(value="/viewTransactionByAccount",method=RequestMethod.GET)
	public ResponseEntity<ArrayList<Transaction>> viewbyiddate(@RequestParam("accountId") long accid,@RequestParam("sdate") String sdate,@RequestParam("edate") String edate)
	{
		return new ResponseEntity<ArrayList<Transaction>>(ts.viewbyacciddate(accid, sdate, edate),HttpStatus.OK);
	}
	@RequestMapping(value="/viewTransactionByCustomer",method=RequestMethod.GET)
	public ResponseEntity<ArrayList<Transaction>> viewbycustiddate(@RequestParam("customerId") long custid,@RequestParam("sdate") String d1,@RequestParam("edate") String d2)
	{
		return new ResponseEntity<ArrayList<Transaction>>(ts.viewbycustdate(custid, d1, d2),HttpStatus.OK);
	}
	@RequestMapping(value="/viewAllByDate",method=RequestMethod.GET)
	public ResponseEntity<ArrayList<Transaction>> viewallbydate(@RequestParam("sdate") String d1,@RequestParam("edate") String d2)
	{
		return new ResponseEntity<ArrayList<Transaction>>(ts.viewalldate(d1, d2),HttpStatus.OK);
	}
}