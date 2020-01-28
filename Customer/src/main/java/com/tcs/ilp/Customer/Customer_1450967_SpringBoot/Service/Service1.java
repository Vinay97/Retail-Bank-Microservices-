package com.tcs.ilp.Customer.Customer_1450967_SpringBoot.Service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tcs.ilp.Customer.Customer_1450967_SpringBoot.Model.Customer;
import com.tcs.ilp.Customer.Customer_1450967_SpringBoot.Repo.CustomerRepository;

@Component
public class Service1 {
	
	@Autowired
	private CustomerRepository cr;
	
	public Customer viewbyssnid(long ssnid)
	{
		return cr.viewCustomerBySsnid(ssnid);
	}
	public Customer viewbyId(long id)
	{
		Customer ab= cr.findById(id).get();
		if(ab==null || ab.getStatus().equals("inactive"))return null;
		return ab;
	}
	public Customer viewidssn(long id,long ssn)
	{
		if(checkbyssn(ssn)==false)//though our flow will only come here if given ssn data exists
				{                 // to verify that no manipulation have occured inroute we are checking it again  
			return null;		 // this function can be avoided	 
				}
		Customer cs=viewbyssnid(ssn); 
		if(cs.getCustomerid()==id)	
		return cs;
		else 
			return null;
	}
	public boolean checkCustomer(long id)//to check the existence of customers
	{
		if(cr.existsById(id))
		{
			String as=cr.checkstatus(id);
			if(as.equals("active"))
				return true;
			else
				return false;
		}
		else
			return false;
	}
	public boolean checkbyssn(long ssn)
	{
		Object ab=cr.checkbyssnid(ssn);// we have taken object as return type because we are not sure whether an entry exits here or not
		if(ab!=null)//if instead of object we have taken long it will throw an error since the value returned will be null
		{
			System.out.println(ssn);//if id exists already it will print its value
			return true;
		}
		return false;
	}
	public long addCustomer(Customer cm)//will be used to update and add
	{
	cm.setStatus("active");	
	 return  cr.save(cm).getCustomerid();
		
	}
	public int changestatus(String status,long cid)
	{
		return cr.changestatus(status,cid);
	}
	public String checkstatus(long id)//check customer status
	{
		return cr.checkstatus(id);
	}
	public void deleteCustomer(long id)// to delete customer
	{
		cr.deleteById(id);
	}
	public ArrayList<Customer> viewallCustomers()//to view all customers
	{
		ArrayList<Customer> csm=new ArrayList<Customer>();
		csm=(ArrayList<Customer>) cr.findAll();
		return csm;
	}
	
	public Customer viewCustomerByCId(long cid) {
		Customer c = null;
		c = cr.viewCustomerByCId(cid);
		if(c!=null) {
			return c;
		}
		return c;
	}

}
