<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://kit.fontawesome.com/c102305e9c.js"
	crossorigin="anonymous"></script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>

<body>
	<form action="AdminController" name="myform" method="post" onsubmit="return validateform()">
	<div class="container p-3 my-3 border  bg-light text-dark ">
		<button type="button" name="back" onclick="history.back()" class="btn-dark" ><i class="fa-sharp fa-solid fa-arrow-left"></i></button>
		<center class=>
			<h1 class="text-Seconday" >Add New User</h1>
		</center>
		<hr>
		
		<label for="acno">Account Number : </label> <input type="text" name="acno" class="form-control" id="acno"><br>
		<label for="name">Name : </label> <input type="text" name="name" class="form-control" id="name" ><br>
		<label for="age">Age : </label> <input type="text" name="age" class="form-control" id="age" ><br>
		<label for="email">Email : </label> <input type="text" name="email" class="form-control" id="email" ><br>
		<label for="password">Password : </label> <input type="text" name="password" class="form-control" id="pass"><br>
		<label for="gender" >Gender : </label>
		
		<input type="radio"  id="gender" name="gender" value="Male" class="form-check-input" >
		<label for="male" class="form-check-label">Male</label>
		<input type="radio"   id="female" name="gender" value="Female" class="form-check-input" >
		<label for="female" class="form-check-label">Female</label><br><br>
		
		<label for="mobile">Mobile Number : </label> <input type="text" name="mobile" class="form-control" id="mobile"><br>
		<label for="balance">Balance : </label> <input type="text" name="balance" class="form-control" id="balance"><br>
		
		<center>
		<br> <input type="hidden" name="command" value="add"> 
		<input type="submit" class="btn btn-primary w-50" value="submit"></center> <br>
		<br>
		
	</div>
	</form>

</body>
 <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="script.js" ></script>
</html>