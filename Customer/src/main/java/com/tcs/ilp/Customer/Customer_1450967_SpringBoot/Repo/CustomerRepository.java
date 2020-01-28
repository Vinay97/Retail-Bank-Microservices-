package com.tcs.ilp.Customer.Customer_1450967_SpringBoot.Repo;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tcs.ilp.Customer.Customer_1450967_SpringBoot.Model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long>{
	@Query(value="select * from customer_1450967 where customername=?1 and status='active'",nativeQuery = true)
	ArrayList<Customer> viewCustomersByName(String name);
	
	@Query(value="select * from customer_1450967 where customerssnid=?1",nativeQuery = true)
	Customer viewCustomerBySsnid(long ssnid);
	
	@Query(value="select customerid from customer_1450967 where customerssnid=?1",nativeQuery = true)
	Object checkbyssnid(long ssnid);
	
	@Query(value="select * from customer_1450967 where contactno=?1 and status='active'",nativeQuery = true)
	ArrayList<Customer> viewCustomersByContactNo(long contactno);
	
	@Query(value="select * from customer_1450967 where email=?1 and status='active'",nativeQuery = true)
	ArrayList<Customer> viewCustomersByEmail(String email);
	
	@Query(value="select * from customer_1450967 where city=?1 and status='active'",nativeQuery = true)
	ArrayList<Customer> viewCustomersByCity(String city);

	@Query(value = "select * from customer_1450967 where CUSTOMERID=?1 and status='active'",nativeQuery = true)
	Customer viewCustomerByCId(long cid);
	
	@Query(value = "select status from customer_1450967 where customerid=?1",nativeQuery = true)
	String checkstatus(long cid);
	
	/*@Query(value = "update customer_1450967 set status='inactive' where customerid=?1",nativeQuery = true)
	Object changestatus(long cid);
	*/
	@Modifying//
	@Transactional
	@Query(value="update customer_1450967  SET status =?1 WHERE customerid =?2",nativeQuery=true)
    int changestatus(String status,long customerid);

}
