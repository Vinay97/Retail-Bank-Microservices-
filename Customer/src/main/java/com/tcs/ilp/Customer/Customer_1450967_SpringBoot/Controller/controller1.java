package com.tcs.ilp.Customer.Customer_1450967_SpringBoot.Controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.ilp.Customer.Customer_1450967_SpringBoot.Model.Customer;
import com.tcs.ilp.Customer.Customer_1450967_SpringBoot.Repo.CustomerRepository;
import com.tcs.ilp.Customer.Customer_1450967_SpringBoot.Service.Service1;

@RestController
@CrossOrigin
public class controller1 {
	

	@Autowired
	private Service1 customerservice;
	
	@Autowired
	private CustomerRepository customerrepo;
	
	@Autowired
	private LoadBalancerClient loadBalancer;
	
	@RequestMapping(value="/viewCustomersByName",method=RequestMethod.GET)
	public ResponseEntity<ArrayList<Customer>> viewCustomersByName(@RequestParam("customername") String cname)
	{
		return new ResponseEntity<ArrayList<Customer>>(customerrepo.viewCustomersByName(cname), HttpStatus.OK);
	}
	@RequestMapping(value="/viewCustomersByEmail",method=RequestMethod.GET)
	public ResponseEntity<ArrayList<Customer>> viewCustomersByEmail(@RequestParam("email") String email)
	{
		return new ResponseEntity<ArrayList<Customer>>(customerrepo.viewCustomersByEmail(email), HttpStatus.OK);
	}
	@RequestMapping(value="/viewCustomersByContactNo",method=RequestMethod.GET)
	public ResponseEntity<ArrayList<Customer>> viewCustomersByContactNo(@RequestParam("contactno") int contactno)
	{
		return new ResponseEntity<ArrayList<Customer>>(customerrepo.viewCustomersByContactNo(contactno), HttpStatus.OK);
	}
	@RequestMapping(value="/viewCustomersByCity",method=RequestMethod.GET)
	public ResponseEntity<ArrayList<Customer>> viewCustomersByCity(@RequestParam("city") String city)
	{
		return new ResponseEntity<ArrayList<Customer>>(customerrepo.viewCustomersByCity(city), HttpStatus.OK);
	}
	@RequestMapping(value="/viewCustomerById",method=RequestMethod.GET)
	public ResponseEntity<Customer> viewCustomerBySsn(@RequestParam("customerid") long id)
	{
		return new ResponseEntity<Customer>(customerservice.viewbyId(id), HttpStatus.OK);
	}
	@RequestMapping(value="/viewidssn",method=RequestMethod.POST)//old user magically appears
	public ResponseEntity<Customer> viewidssn(@RequestParam("customerid") long id,@RequestParam("customerssnid") long ssn)
	{
		return new ResponseEntity<Customer>(customerservice.viewidssn(id, ssn), HttpStatus.OK);
	}
	@RequestMapping(value="/createCustomer",method=RequestMethod.POST)
	public ResponseEntity<Long> createCustomer(@RequestBody Customer cm)
	{
		boolean b=customerservice.checkbyssn(cm.getCustomerssnid());//customer id and status active
		long result=0;
		if(b==false)//customer donot exists
		{
		return new ResponseEntity<Long>(customerservice.addCustomer(cm), HttpStatus.OK);
		}
		else//customer exists check its status
		{
			String status=customerservice.checkstatus(customerservice.viewbyssnid(cm.getCustomerssnid()).getCustomerid());
			System.out.println("Status==="+status);
			if(status.equals("inactive"))
					{
				result=-1;//customer id exists but inactive will return -1;
					}
			return new ResponseEntity<Long>(result, HttpStatus.OK);//customer exists and active this id cnnot be used
		}
	}
	@RequestMapping(value="/checkstatus",method=RequestMethod.GET)
	public ResponseEntity<String> checkstatus(@RequestParam("customerid") long id)
	{
		return new ResponseEntity<String>(customerservice.checkstatus(id), HttpStatus.OK);
	}
	@RequestMapping(value="/checkCustomer",method=RequestMethod.GET)
	public ResponseEntity<Boolean> checkCustomer(@RequestParam("customerid") long id)
	{
		return new ResponseEntity<Boolean>(customerservice.checkCustomer(id), HttpStatus.OK);
	}
	@RequestMapping(value="/viewAllCustomers",method=RequestMethod.GET)
	public ResponseEntity<ArrayList<Customer>> viewAllCustomers()
	{
		return new ResponseEntity<ArrayList<Customer>>(customerservice.viewallCustomers(), HttpStatus.OK);		
	}
	@RequestMapping(value="/deleteCustomer",method=RequestMethod.GET)
	public ResponseEntity<Boolean> deleteCustomer(@RequestParam("customerid") long id)
	{
		boolean b=customerservice.checkCustomer(id);
		if(b==false)//customer donot exists cannot be deleted
		{
			return new ResponseEntity<Boolean>(b, HttpStatus.OK);
		}
		int ab=customerservice.changestatus("inactive",id);//customer exists mark it as inactive
		if(ab>0)//ideally it should be 1
		{
			return new ResponseEntity<Boolean>(b, HttpStatus.OK);		
		}
		else
			{b=false;return new ResponseEntity<Boolean>(b, HttpStatus.OK);}		
	}
	@RequestMapping(value="/updateCustomer",method=RequestMethod.POST)
	public ResponseEntity<Boolean> updateCustomer(@RequestBody Customer cm)
	{
		boolean b=customerservice.checkCustomer(cm.getCustomerid());
		if(b==true)
		{
		customerservice.addCustomer(cm);
		return new ResponseEntity<Boolean>(b, HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<Boolean>(b, HttpStatus.OK);	
		}
	}

}
