/**
 * 
 */
var app = angular.module('myApp',['ngRoute']);
app.run(function($rootScope){
$rootScope.flag = true;	
})

app.config(function($routeProvider){
	$routeProvider
	.when("/Login",{templateUrl:"Login.html",controller:"myController"})
	.when("/Logout",{template:"",controller:"logout"})
	.when("/AfterLogin",{templateUrl:"Links.html",controller:"naman"})
	.when("/Customer",{templateUrl:"CustomerFunc.html"})
	.when("/Account",{templateUrl:"AccountFunc.html"})
	.when("/Transaction",{templateUrl:"TransactionFunc.html"})
	.when("/CustomerForm",{templateUrl:"CustomerForm.html",
							controller:"CustomerController"})
	.when("/ByName",{templateUrl:"ByName.html",controller:"ByName"})
	.when("/BySSN",{templateUrl:"BySSN.html",controller:"BySSN"})
	.when("/ByContact",{templateUrl:"ByContact.html",controller:"ByContact"})
	.when("/ByEmail",{templateUrl:"ByEmail.html",controller:"ByEmail"})
	.when("/ByCity",{templateUrl:"ByCity.html",controller:"ByCity"})
	.when("/CustUpdate",{templateUrl:"CustUpdate.html",controller:"custupdate"})
	.when("/CustDelete",{templateUrl:"CustDelete.html",controller:'custdelete'})
	.when("/AccountForm",{templateUrl:"AccountForm.html",controller:"AccountController"})
	.when("/DeleteAccount",{templateUrl:"DeleteAccount.html",controller:"deleteaccount"})
	.when("/ByAccountId",{templateUrl:"ByAccountId.html",controller:"ByAccountId"})
	.when("/ByCustomerId",{templateUrl:"ByCustomerId.html",controller:"ByCustomerId"})
	.when("/CheckAccount",{templateUrl:"CheckAccount.html",controller:"CheckAccount"})
	.when("/Deposit",{templateUrl:"Deposit.html",controller:"Deposit"})
	.when("/Withdrawn",{templateUrl:"Withdraw.html",controller:"Withdraw"})
	.when("/TransactionByAId",{templateUrl:"TransactionByAId.html",controller:"Transactions"})
	.when("/TransactionByCId",{templateUrl:"TransactionByCId.html",controller:"Transactions"})
	.when("/ViewAllTrans",{templateUrl:"ViewAllTrans.html",controller:"Transactions"})
	.when("/CustSuccess",{templateUrl:"CustomerSuccess.html",controller:"CustSuccess"})
	.when("/ViewCustomers",{templateUrl:"ViewCustomers.html",controller:"ViewCustomerController"})
	.when("/CheckCustomer",{templateUrl:"CheckCustomer.html",controller:"CheckCustomer"})
	.when("/AccSuccess",{templateUrl:"AccountSuccess.html",controller:"AccSuccess"})
	.when("/AccError",{templateUrl:"AccountError.html"})
	.when("/ViewAllAccount",{templateUrl:"ViewAllAccount.html",controller:"ViewAccountController"})
	.when("/TransSuccess",{templateUrl:"TransSuccess.html",controller:"TransSuccess"})
	.when("/TransError",{templateUrl:"TransError.html"})
	.when("/Transviewsuccess",{templateUrl:"Transviewsuccess.html",controller:"Transviewsuccess"})
	.when("/Transviewerror",{templateUrl:"Transviewerror.html"})
	.otherwise({redirectTo: '/Login'})
})

app.controller('deleteaccount',function($rootScope,$scope,$location,$http)
{
	if($rootScope.ab==true){}
	else{alert("you are not authorized to view this page");$location.path('/Login')}
	$scope.id="";
	$scope.balance="";
	$scope.msg="";
	$scope.deleteit=function()
	{
		$http({
			method:'GET',
			url:'http://localhost:8000/api/account/deleteAccById?accountId='+$scope.id,
			headers:{
				'Access-Control-Allow-Origin':'*',
				'Content-Type': 'application/json'
			}
		}).then(function(response){
			$scope.balance=response.data;
			if($scope.balance==-1)
			{
				alert("Given iddonot exists");$scope.existsornot=false;
			}
			if($scope.balance>1){$scope.msg="Unable to delete id balance pending="+$scope.balance;
			console.log($scope.customerid+"    "+response.data);}
		},function(error){
			$scope.msg="error occured";
		})	
	}

}		
)

app.controller('logout',function($rootScope,$location){
	console.log("logout");
	$rootScope.flag = true;
	$rootScope.ab = false;
	$location.path('/Login')
})
app.controller('custdelete',function($rootScope,$scope,$location,$http){
	if($rootScope.ab==true){}
	else{alert("you are not authorized to view this page");$location.path('/Login')}
	console.log("inside customer dletet");
	$scope.existsornot="";
	$scope.customer=[];
	$scope.msg="";
	$scope.deleteit=function()
	{
		$http({
			method:'GET',
			url:'http://localhost:8000/api/customer/deleteCustomer?customerid='+$scope.customer.customerid,
			headers:{
				'Access-Control-Allow-Origin':'*',
				'Content-Type': 'application/json'
			}
		}).then(function(response){
			if(response.data==true)
			{alert("Given id delted successfully");$scope.existsornot=false;}
			else {$scope.existsornot=false;alert("Unable to delete id some error occurs");
			console.log($scope.customerid+"    "+response.data);}
		},function(error){
			$scope.msg="error occured";
		})	
	}
	$scope.checkit=function()
	{
		console.log("inside customer checkit dletet customerid="+$scope.customer.customerid);
		$http({
			method:'GET',
			url:'http://localhost:8000/api/customer/checkCustomer?customerid='+$scope.customer.customerid,
			headers:{
				'Access-Control-Allow-Origin':'*',
				'Content-Type': 'application/json'
			}
		}).then(function(response){
			if(response.data==true)
			{
			$scope.existsornot=true;
			{
			$http({
				method:'GET',
				url:'http://localhost:8000/api/customer/viewCustomerById?customerid='+$scope.customer.customerid,
				headers:{
					'Access-Control-Allow-Origin':'*',
					'Content-Type': 'application/json'
				}
			}).then(function(response){
				$scope.customer=response.data;
				console.log($scope.customer.customerid+"    "+response.data.customername);
			},function(error){
				//$scope.existsornot=false;
			})
			}
			
			}
			else {$scope.existsornot=false;$scope.msg="Given id donot exists";
			console.log($scope.id+"    "+response.data);}
		},function(error){
			$scope.existsornot=false;
			alert("error occurs");
			$scope.msg="Some error occurs";
		})
	}
	
	
})
app.controller('custupdate',function($rootScope,$scope,$location,$http){
	if($rootScope.ab==true){}
	else{alert("you are not authorized to view this page");$location.path('/Login')}
	$scope.customer;
	$scope.msg="";
	$scope.existsornot=false;
	$scope.updateit=function()
	{
		$http({
			method:'POST',
			url:'http://localhost:8000/api/customer/updateCustomer',
			data:$scope.customer,
			headers:{
				'Access-Control-Allow-Origin':'*',
				'Content-Type': 'application/json'
			}
		}).then(function(response){
			if(response.data==true){
				alert("customer updated successfully");
			console.log($scope.customer.customerid+"    "+response.data);}
		},function(error){
			alert("failed to update some error occurs");
			//$scope.existsornot=false;
		})
		
	}
	$scope.checkit=function()
	{
		$http({
			method:'GET',
			url:'http://localhost:8000/api/customer/checkCustomer?customerid='+$scope.customer.customerid,
			headers:{
				'Access-Control-Allow-Origin':'*',
				'Content-Type': 'application/json'
			}
		}).then(function(response){
			if(response.data==true)
				{
				$scope.existsornot=true;
				console.log("inside exist customer update");
				$http({
					method:'GET',
					url:'http://localhost:8000/api/customer/viewCustomerById?customerid='+$scope.customer.customerid,
					headers:{
						'Access-Control-Allow-Origin':'*',
						'Content-Type': 'application/json'
					}
				}).then(function(response){
					$scope.customer=response.data;
					console.log($scope.customer.customerid+"    "+response.data.customername);
				},function(error){
					//$scope.existsornot=false;
				})
				}
			else $scope.existsornot=false;
			console.log($scope.customer.customerid+"    "+response.data);
		},function(error){
			$scope.existsornot=false;
			$scope.msg="Given Id Donot exists";
		})
		/*if($scope.existsornot==true)
			{
			console.log("inside exist customer update");
			$http({
				method:'GET',
				url:'http://localhost:8000/api/customer/viewCustomerById?customerid='+$scope.customer.customerid,
				headers:{
					'Access-Control-Allow-Origin':'*',
					'Content-Type': 'application/json'
				}
			}).then(function(response){
				$scope.customer=response.data;
				console.log($scope.customer.customerid+"    "+response.data.customername);
			},function(error){
				//$scope.existsornot=false;
			})
			
			}*/
	}
})
app.controller('naman',function($rootScope,$scope,$location){
	$rootScope.flag = false;
	console.log("inside naman="+$rootScope.ab);
	if($rootScope.ab==true){}
	else{alert("you are not authorized to view this page");$location.path('/Login')}
})
app.controller('myController',function($rootScope,$scope,$location,$http){//first controller
	console.log("inside mycontroller:");
	$scope.login;
	$rootScope.ab=false;	
	$scope.take = function(){
		console.log("login value="+$scope.login.username+"   password="+$scope.login.password);
		$http({
			method:'POST',
			url:'http://localhost:7030/validateUser',
			data:$scope.login,
			headers:{
				'Access-Control-Allow-Origin':'*',
				'Content-Type': 'application/json'
			}
		}).then(function(response){
			$rootScope.ab = response.data;
			console.log("app====="+$rootScope.ab);
			console.log("value returned="+response.data+"   ROOT="+$rootScope.ab);
			if($rootScope.ab==true)
			{
			console.log("ab sis true");
			$location.path('/AfterLogin');
			}
		else
			{
			alert("Invalid data");
			$location.path('/Login');
			}
		},function(error){
			$scope.cust = error;
			$rootScope.ab=false;
		})		
	}
})

app.controller('CustomerController',function($rootScope,$scope,$http,$location,myService){
	if($rootScope.ab==true){}
	else{alert("you are not authorized to view this page");$location.path('/Login')}
	$scope.cust="";
	$scope.createCustomer = function(){
	$http({
		method:'POST',
		url:'http://localhost:8000/api/customer/createCustomer',
		data:$scope.customer,
		headers:{
			'Access-Control-Allow-Origin':'*',
			'Content-Type': 'application/json'
		}
	}).then(function(response){
		$scope.cust = response.data;
		console.log($scope.cust+"    "+response.data);
		if(response.data>1)
			{
			myService.set($scope.cust);
			$location.path('/CustSuccess')
			}
		else 
			$location.path('/CustError')
			
	},function(error){
		$scope.cust = error;
		$location.path('/CustError')
	})
	}
})

app.controller("CustSuccess",function($location,$rootScope,$scope,myService){
	if($rootScope.ab==true){}
	else{alert("you are not authorized to view this page");$location.path('/Login')}
	$scope.cust = myService.get();
})

app.controller('ViewCustomerController',function($location,$rootScope,$scope,$http){
	if($rootScope.ab==true){}
	else{alert("you are not authorized to view this page");$location.path('/Login')}
	$http({
		method:'GET',
		url:'http://localhost:8000/api/customer/viewAllCustomers',
		headers:{
			'Access-Control-Allow-Origin':'*',
			'Content-Type': 'application/json'
		}
	}).then(function(response){
		$scope.customers = response.data;
	},function(error){
		$scope.customers = error;
	})
})

app.controller('ByName',function($location,$rootScope,$scope,$http){
	if($rootScope.ab==true){}
	else{alert("you are not authorized to view this page");$location.path('/Login')}
	$scope.st = true;
	console.log("inside by Name");
	$scope.customers;
	$scope.getByName = function(){
		console.log("inside getbyname");
		$http({
			method:'GET',
		url:'http://localhost:8000/api/customer/viewCustomersByName?customername='+$scope.customers.name,
		headers:{
			'Access-Control-Allow-Origin':'*',
			'Content-Type': 'application/json'
		}
		}).then(function(response){
			$scope.customers = response.data;
			console.log("response data="+response.data);
			$scope.st = true;
		},function(error){
			$scope.st = false;
		})
	}
})

app.controller('ByEmail',function($location,$rootScope,$scope,$http){
	if($rootScope.ab==true){}
	else{alert("you are not authorized to view this page");$location.path('/Login')}
	$scope.st = true;
	$scope.getByEmail = function(){
		$http({
			method:'GET',
		url:'http://localhost:8000/api/customer/viewCustomersByEmail?email='+$scope.email,
		headers:{
			'Access-Control-Allow-Origin':'*',
			'Content-Type': 'application/json'
		}
		}).then(function(response){
			$scope.customers = response.data;
			$scope.st = true;
		},function(error){
			$scope.st = false;
		})
	}
})

app.controller('ByContact',function($location,$rootScope,$scope,$http){
	if($rootScope.ab==true){}
	else{alert("you are not authorized to view this page");$location.path('/Login')}
	$scope.st = true;
	$scope.getByContact = function(){
		$http({
			method:'GET',
		url:'http://localhost:8000/api/customer/viewCustomersByContactNo?contactno='+$scope.contactno,
		headers:{
			'Access-Control-Allow-Origin':'*',
			'Content-Type': 'application/json'
		}
		}).then(function(response){
			$scope.customers = response.data;
			$scope.st = true;
		},function(error){
			$scope.st = false;
		})
	}
})

app.controller('ByCity',function($location,$rootScope,$scope,$http){
	if($rootScope.ab==true){}
	else{alert("you are not authorized to view this page");$location.path('/Login')}

	$scope.st = true;
	$scope.getByCity = function(){
		$http({
			method:'GET',
		url:'http://localhost:8000/api/customer/viewCustomersByCity?city='+$scope.city,
		headers:{
			'Access-Control-Allow-Origin':'*',
			'Content-Type': 'application/json'
		}
		}).then(function(response){
			$scope.customers = response.data;
			$scope.st = true;
		},function(error){
			$scope.st = false;
		})
	}
})

app.controller('BySSN',function($location,$rootScope,$scope,$http){
	if($rootScope.ab==true){}
	else{alert("you are not authorized to view this page");$location.path('/Login')}

	$scope.st = true;
	$scope.getBySSN = function(){
		$http({
			method:'GET',
			url:'http://localhost:8000/api/customer/viewCustomerById?customerid='+$scope.cid,
		headers:{
			'Access-Control-Allow-Origin':'*',
			'Content-Type': 'application/json'
		}
		}).then(function(response){
			$scope.customers = response.data;
			$scope.st = true;
		},function(error){
			$scope.st = false;
		})
	}
})

app.controller('CheckCustomer',function($location,$rootScope,$scope,$http){
	if($rootScope.ab==true){}
	else{alert("you are not authorized to view this page");$location.path('/Login')}

	$scope.st = true;
	$scope.getByCId = function(){
		$http({
			method:'GET',
			url:'http://localhost:8000/api/customer/checkCustomer?customerid='+$scope.cid,
		headers:{
			'Access-Control-Allow-Origin':'*',
			'Content-Type': 'application/json'
		}
		}).then(function(response){
			$scope.msg = response.data;
			$scope.st = true;
		},function(error){
			$scope.st = false;
		})
	}
})

//app.controller('updatecustomer',function($scope,$http,$location){
//	$scope.cust="";
//	$scope.existsornot=false;
//	$scope.checkit=function()//check whether id exists or not
//	{
//		
//		
//		
//		
//	}
//	$scope.fetchDetails=function()
//	{
//		$http({
//			method:'POST',
//			url:'http://localhost:8000/api/customer/createCustomer',
//			data:$scope.customer,
//			headers:{
//				'Access-Control-Allow-Origin':'*',
//				'Content-Type': 'application/json'
//			}
//		}).then(function(response){
//			$scope.cust = response.data;
//			console.log($scope.cust+"    "+response.data);
//			$location.path('/CustSuccess')
//		},function(error){
//			$scope.cust = error;
//			$location.path('/CustError')
//		})
//		
//	}
//	$scope.createCustomer = function(){
//	$http({
//		method:'POST',
//		url:'http://localhost:8000/api/customer/createCustomer',
//		data:$scope.customer,
//		headers:{
//			'Access-Control-Allow-Origin':'*',
//			'Content-Type': 'application/json'
//		}
//	}).then(function(response){
//		$scope.cust = response.data;
//		console.log($scope.cust+"    "+response.data);
//		$location.path('/CustSuccess')
//	},function(error){
//		$scope.cust = error;
//		$location.path('/CustError')
//	})
//	}
//})
app.controller('AccountController',function($rootScope,$scope,$http,$location,myService){
	if($rootScope.ab==true){}
	else{alert("you are not authorized to view this page");$location.path('/Login')}

	$scope.account={};
	console.log("inside account controller");
	$scope.createAccount = function(){
		console.log("DATA received="+$scope.account.customerId);
	$http({
		method:'POST',
		url:'http://localhost:8000/api/account/createAccount',
		data:$scope.account,
		headers:{
			'Access-Control-Allow-Origin':'*',
			'Content-Type': 'application/json'
		}
	}).then(function(response){
		$scope.acct = response.data;
		console.log($scope.acct+"    "+response.data);
		myService.set($scope.acct);
		$location.path('/AccSuccess')
	},function(error){
		$scope.acct = error;
		$location.path('/AccError')
	})
	}
})

app.controller('AccSuccess',function($location,$rootScope,$scope,myService){
	if($rootScope.ab==true){}
	else{alert("you are not authorized to view this page");$location.path('/Login')}

	$scope.acct = myService.get();
})

app.controller('ByAccountId',function($location,$rootScope,$scope,$http){
	if($rootScope.ab==true){}
	else{alert("you are not authorized to view this page");$location.path('/Login')}
	$scope.st = true;
	$scope.aid="";
	console.log("inside account id controller");
	$scope.getByAccountId = function(){
		console.log("inside accountfgfg  sdfsf controller");
		$http({
			method:'GET',
			url:'http://localhost:8000/api/account/viewByAccountId?accountId='+$scope.aid,
		headers:{
			'Access-Control-Allow-Origin':'*',
			'Content-Type': 'application/json'
		}
		}).then(function(response){
			$scope.account = response.data;
			$scope.st = true;
		},function(error){
			$scope.st = false;
		})
	}
})

app.controller('ByCustomerId',function($location,$rootScope,$scope,$http){
	if($rootScope.ab==true){}
	else{alert("you are not authorized to view this page");$location.path('/Login')}
	$scope.st = true;
	$scope.cid="";
	console.log("inside customer id controller");
	$scope.account="";
	$scope.getByCustomerId = function(){
		console.log("inside accountfgfg  sdfsf controller");
		$http({
			method:'GET',
			url:'http://localhost:8000/api/account/viewAccByCustId?customerId='+$scope.cid,
		headers:{
			'Access-Control-Allow-Origin':'*',
			'Content-Type': 'application/json'
		}
		}).then(function(response){
			$scope.account = response.data;
			$scope.st = true;
		},function(error){
			$scope.st = false;
		})
	}
})

app.controller('ViewAccountController',function($location,$rootScope,$scope,$http){
	if($rootScope.ab==true){}
	else{alert("you are not authorized to view this page");$location.path('/Login')}
	$scope.account = "";
	$http({
		method:'GET',
		url:'http://localhost:8000/api/account/viewAllAccount',
		headers:{
			'Access-Control-Allow-Origin':'*',
			'Content-Type': 'application/json'
		}
	}).then(function(response){
		$scope.account = response.data;
	},function(error){
		$scope.account = error;
	})
})

app.controller('CheckAccount',function($location,$rootScope,$scope,$http){
	if($rootScope.ab==true){}
	else{alert("you are not authorized to view this page");$location.path('/Login')}
	$scope.st = true;
	$scope.getByAId = function(){
		$http({
			method:'GET',
			url:'http://localhost:8000/api/account/checkAccById?accountId='+$scope.aid,
		headers:{
			'Access-Control-Allow-Origin':'*',
			'Content-Type': 'application/json'
		}
		}).then(function(response){
			$scope.msg = response.data;
			$scope.st = true;
		},function(error){
			$scope.st = false;
		})
	}
})


app.controller('Deposit',function($rootScope,$scope,$http,$location,myService){
	if($rootScope.ab==true){}
	else{alert("you are not authorized to view this page");$location.path('/Login')}
	$scope.transId="";
	$scope.account={};
	console.log("inside deposit="+$scope.transId);
	$scope.Deposit = function(){
		$http({
			method:'POST',
			url:'http://localhost:8000/api/transaction/Deposit',
			data:$scope.account,
			headers:{
				'Access-Control-Allow-Origin':'*',
				'Content-Type': 'application/json'
			}	
		}).then(function(response){
			$scope.transId = response.data;
			console.log("SSSSS"+$scope.transId);
			myService.set($scope.transId);
			$location.path('/TransSuccess');
		},function(error){
			console.log(error);
			$location.path('/TransError');
		});
	}
})

app.controller('TransSuccess',function($location,$rootScope,$scope,myService){
	if($rootScope.ab==true){}
	else{alert("you are not authorized to view this page");$location.path('/Login')}
	$scope.transId = myService.get();
})

app.controller('Withdraw',function($rootScope,$scope,$http,$location,myService){
	if($rootScope.ab==true){}
	else{alert("you are not authorized to view this page");$location.path('/Login')}
	$scope.transId="";
	$scope.account={};
	console.log("inside deposit="+$scope.transId);
	$scope.Withdraw = function(){
		$http({
			method:'POST',
			url:'http://localhost:8000/api/transaction/Withdraw',
			data:$scope.account,
			headers:{
				'Access-Control-Allow-Origin':'*',
				'Content-Type': 'application/json'
			}	
		}).then(function(response){
			$scope.transId = response.data;
			console.log("SSSSS"+$scope.transId);
			myService.set($scope.transId);
			$location.path('/TransSuccess');
		},function(error){
			console.log(error);
			$location.path('/TransError');
		});
	}
})

app.controller('Transactions',function($rootScope,$scope,$http,myService,$location){
	if($rootScope.ab==true){}
	else{alert("you are not authorized to view this page");$location.path('/Login')}
	console.log("inside get by account id");
	$scope.customer={};
	$scope.trans={};
	$scope.account={};
	$scope.customer={};
	$scope.transactions="";
	$scope.getByAccountId = function(){
	
		console.log("inside get by account id function");
		$scope.ssdate=$scope.account.sdate.toISOString().slice(0,10);
		$scope.eedate=$scope.account.edate.toISOString().slice(0,10);
		console.log("start date="+$scope.ssdate);
		console.log("end date="+$scope.eedate);
		console.log("accid="+$scope.account.accountId);
		console.log("sdate="+$scope.ssdate);
		console.log("edate="+$scope.eedate);
		var Indata = {'accountId': $scope.accountId, 'sdate': $scope.sdate,'edate': $scope.edate };
		$http({
			method:'GET',
			url:'http://localhost:7040/viewTransactionByAccount?accountId='+$scope.account.accountId+'&sdate='+$scope.ssdate+'&edate='+$scope.eedate,
			headers:{
				'Access-Control-Allow-Origin':'*',
				'Content-Type': 'application/json'
			}	
		}).then(function(response){
			$scope.transactions = response.data;
			console.log("inside account=");
			console.log($scope.transactions.date);
			console.log($scope.transactions);
			myService.set($scope.transactions);
			$location.path('/Transviewsuccess');
		},function(error){
			$location.path('/Transviewerror');
		})
	}
	
	$scope.getByCustomerId = function(){
		$scope.transactions={};
		$scope.ssdate=$scope.customer.sdate.toISOString().slice(0,10);
		$scope.eedate=$scope.customer.edate.toISOString().slice(0,10);
		$http({
			method:'GET',
			url:'http://localhost:8000/api/transaction/viewTransactionByCustomer?customerId='+$scope.customer.customerId+'&sdate='+$scope.ssdate+'&edate='+$scope.eedate,
			headers:{
				'Access-Control-Allow-Origin':'*',
				'Content-Type': 'application/json'
			}	
		}).then(function(response){
			$scope.transactions = response.data;
			console.log("transactions date=");
			console.log($scope.transactions.date);
			myService.set($scope.transactions);
			$location.path('/Transviewsuccess');
		},function(error){
			$location.path('/Transviewerror');
		})
	}
	
	$scope.getByDate = function(){
		$scope.transactions={};
		$scope.ssdate=$scope.trans.sdate.toISOString().slice(0,10);
		$scope.eedate=$scope.trans.edate.toISOString().slice(0,10);
		$http({
			method:'GET',
			url:'http://localhost:8000/api/transaction/viewAllByDate?sdate='+$scope.ssdate+'&edate='+$scope.eedate,
			headers:{
				'Access-Control-Allow-Origin':'*',
				'Content-Type': 'application/json'
			}	
		}).then(function(response){
			console.log("date="+$scope.transactions.date);
			console.log("date="+$scope.transactions.type);
			$scope.transactions = response.data;
			myService.set($scope.transactions);
			$location.path('/Transviewsuccess');
		},function(error){
			$location.path('/Transviewerror');
		})
	}
})

app.controller('Transviewsuccess',function($location,$rootScope,$scope,myService){
	if($rootScope.ab==true){}
	else{alert("you are not authorized to view this page");$location.path('/Login')}
	$scope.transactions =myService.get();
})

app.factory('myService', function() {
 var savedData = {}
 function set(data) {
   savedData = data;
 }
 function get() {
  return savedData;
 }

 return {
  set: set,
  get: get
 }

});