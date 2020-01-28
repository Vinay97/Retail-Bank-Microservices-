package com.tcs.ilp.Account.Account.Repo;

import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tcs.ilp.Account.Account.Model.Account;

@Repository
public interface AccountRepo extends JpaRepository<Account,Long>{

	@Query(value="select * from account_1450967 where CUSTOMER_ID=?1",nativeQuery = true)
	ArrayList<Account> viewAccountByCId(long cid);
	
	@Query(value="select * from account_1450967 where CUSTOMER_ID=?1 and ACCOUNT_TYPE=?2",nativeQuery = true)
	Account viewAccountByCA(long customerId,String accountType);
	
	@Query(value="select balance from account_1450967 where account_Id=?1",nativeQuery=true)
	double viewbalance(long accid);
	
	@Modifying
	@Transactional
	@Query(value="update account_1450967 set balance=?1 where account_id=?2",nativeQuery=true)
	int updatebalance(double bal,long accid);
	
	@Query(value="select account_type from account_1450967 where customer_id=?1",nativeQuery=true)
	ArrayList<String> checkaccount(long customerid);
	
	@Query(value="select balance from account_1450967 where account_id=?1",nativeQuery=true)
	long checkbalance(long accid);

}
