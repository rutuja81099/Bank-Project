<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
 <script src="https://kit.fontawesome.com/c102305e9c.js" crossorigin="anonymous"></script>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div class="container p-3 my-3 border  bg-light text-dark " >


<form action="UserController">
		<center><h4>Edit User Information</h4></center><hr>
		<button type="button" name="back" onclick="history.back()"><i class="fa-sharp fa-solid fa-arrow-left"></i></button>
		<br>
		<br> <label for="email">Email : </label> <input type="text" class="w-100" name="email" value="${user.email}"><br>
		<br> <label for="password">Password :</label> <input type="text" class="w-100" name="password" value="${user.password }"><br>
		<br> <label for="mobile">mobile No :</label> <input type="text" class="w-100" name="mobile" value="${user.mobile }"><br>
		
		<input type="hidden" name="command" value="update">
		<input  type="hidden" name="acNo" value="${user.acNo}">
		<center><button type="submit" class="btn btn-outline-primary w-50 mt-5px mt-5">Save Update</button></center>
		
		<br><br>
	

</form>

</div>

</body>
</html>