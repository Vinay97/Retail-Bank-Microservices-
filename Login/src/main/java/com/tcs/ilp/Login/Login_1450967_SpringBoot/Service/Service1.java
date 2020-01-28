package com.tcs.ilp.Login.Login_1450967_SpringBoot.Service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.tcs.ilp.Login.Login_1450967_SpringBoot.Model.Login;
import com.tcs.ilp.Login.Login_1450967_SpringBoot.Repo.LoginRepository;


@Component
public class Service1 {
	
	@Autowired
	private LoginRepository lr;
	
	@Autowired
	private LoginRepository loginrepo;
	
	public Boolean existsUser(String id)
	{
		boolean aa=lr.existsById(id);
		System.out.println("AAAA"+aa);
		return aa;
	}
	public ArrayList<Login> getall()
	{
		return (ArrayList<Login>) lr.findAll();
	}
	public boolean validateuser(Login inn)
	{
		Boolean n=existsUser(inn.getUsername());
		System.out.println("username="+inn.getUsername()+" ACC "+inn.getPassword()+"  "+n);
		if(n==false)
		{
			return false;
		}
		Login abc=null;abc=loginrepo.validateUser(inn.getUsername());
		System.out.println(abc.getPassword()+"   "+inn.getPassword());
		if(abc.getPassword().equals(inn.getPassword()))
		{
			return true;
		}
		return false;
	}

}
