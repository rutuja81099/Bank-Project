function validation() {
	var valid=false;
	var x=document.myform.userType;
	
	for(var i=0;i<x.length;i++){
		if(x[i].checked){
			valid=true;
			break;
		}
	}if(!valid){
		alert("Please select a userType")
		return false;
	}
	
	var email = document.getElementById('emails').value;
	var emailpattern=  /^\w+([\.-]?\w+)@\w+([\.-]?\w+)(\.\w{2,3})+$/;
	if(email==""){
		alert("Please enter Email id")
		return false;
	}
	
	if(emailpattern.test(email)){
	}else{
	alert("please enter valid email Id");
	return false;
	}
	
	var pass = document.getElementById('pass').value;
	if (pass == "") {
		alert("Please enter the password");
		return false;
	}
	
}

function validateform() {
	var acno = document.getElementById('acno').value;
	if (acno=="") {
		alert("Please enter valid account No");
		return false;
	}
	
	var name = document.getElementById('name').value;
	var letters = /^[A-Za-z]+$/;
	if(name==""){
		alert("username cant be empty")
		return false;
	}
	if(letters.test(name)){}
	else{
	alert('Username must have alphabet characters only');
	return false;
	}
	
	var age = document.getElementById('age').value;
	var agepattern= /^[0-9]{2}$/;
	if(age==""){
		alert("age cant be empty")
		return false;
	}
	if(agepattern.test(age)){}
	else{
	alert('age must have numbers only');
	return false;
	}
	
	var email = document.getElementById('email').value;
	var emailpattern=  /^\w+([\.-]?\w+)@\w+([\.-]?\w+)(\.\w{2,3})+$/;
	if(email==""){
		alert("Please enter Email id")
		return false;
	}
	
	if(emailpattern.test(email)){
	}else{
	alert("please enter valid email Id");
	return false;
	}
	
	var pass = document.getElementById('pass').value;
	if (pass == "") {
		alert("Please enter the password");
		return false;
	}
	if(pass.length<6){
		alert("Password must contain 6 digits");
		return false;

	}
	
	var valid=false;
	var x=document.myform.gender;
	var balance = document.getElementById('balance').value;

	
	for(var i=0;i<x.length;i++){
		if(x[i].checked){
			valid=true;
			break;
		}
	}if(!valid){
		alert("Please select a Gender");
		return false;
	}
	
	var mobile = document.getElementById('mobile').value;
	var mobilepattern= /^[0-9]{10}$/;
	if (mobile == "") {
		alert("Please enter the mobile number");
		return false;
	}
	if(mobilepattern.test(mobile)){}
	else{
		alert("please enter valid Mobile number");
		return false;
		}
	
	if (balance < 5000) {
		alert("Please input minimum deposit amount");
		return false;
	}	
	
}