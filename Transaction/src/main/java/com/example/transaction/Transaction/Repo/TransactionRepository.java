package com.example.transaction.Transaction.Repo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.transaction.Transaction.Model.Transaction;


@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{

	@Query(value="select * from transaction_1450967 where account_id=?1",nativeQuery = true)
	ArrayList<Transaction> viewbyaccid(long accid);
	
	/*@Query(value="select * from transaction_1450967 where account_id=?1 and trunc(datee) between to_date(?2,'YYYY-MM-DD') and to_date(?3,'YYYY-MM-DD')",nativeQuery=true)
	ArrayList<Transaction> viewbyacciddate(long accid,LocalDate sdate,LocalDate edate);*/
	
	@Query(value="select * from transaction_1450967 where customer_id=?1 and datee between to_date(?2,'YYYY-MM-DD') and to_date(?3,'YYYY-MM-DD')",nativeQuery=true)
	ArrayList<Transaction> viewbycustdate(long custid,String d1,String d2);
	
	@Query(value="select * from transaction_1450967 where datee between to_date(?1,'YYYY-MM-DD') and to_date(?2,'YYYY-MM-DD')",nativeQuery=true)
	ArrayList<Transaction> viewalldate(String d1,String d2);
	
	@Query(value="select * from transaction_1450967 where account_id=?1 and datee>=to_date(?2,'YYYY-MM-DD') and datee<=to_date(?3,'YYYY-MM-DD')",nativeQuery=true)
	ArrayList<Transaction> viewbyacciddate(long accid,String sdate,String edate);
}
