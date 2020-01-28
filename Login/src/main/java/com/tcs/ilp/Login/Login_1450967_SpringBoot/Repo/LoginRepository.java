package com.tcs.ilp.Login.Login_1450967_SpringBoot.Repo;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tcs.ilp.Login.Login_1450967_SpringBoot.Model.Login;

@Repository
public interface LoginRepository extends JpaRepository<Login,String>{
	@Query(value="select * from login_1450967 where username=?1",nativeQuery = true)
	Login validateUser(String name);
	
}
