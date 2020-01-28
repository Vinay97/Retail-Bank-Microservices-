package com.tcs.ilp.Login.Login_1450967_SpringBoot.Controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.ilp.Login.Login_1450967_SpringBoot.Model.Login;
import com.tcs.ilp.Login.Login_1450967_SpringBoot.Repo.LoginRepository;
import com.tcs.ilp.Login.Login_1450967_SpringBoot.Service.Service1;

@CrossOrigin
@RestController
public class controller1 {

	@Autowired
	private Service1 loginservice;
	
	@RequestMapping(value="/validateUser",method=RequestMethod.POST)
	public ResponseEntity<Boolean> validateUser(@RequestBody Login inn)
	{
		return new ResponseEntity<Boolean>(loginservice.validateuser(inn), HttpStatus.OK);
	}
	@RequestMapping(value="/getall",method=RequestMethod.GET)
	public ResponseEntity<ArrayList<Login>> getall()
	{
		return new ResponseEntity<ArrayList<Login>>(loginservice.getall(), HttpStatus.OK);
	}
	
}
