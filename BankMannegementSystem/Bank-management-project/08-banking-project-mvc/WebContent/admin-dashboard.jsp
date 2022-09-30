<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
<meta charset="ISO-8859-1">
<title>admin dashboard</title>
</head>
<body>
<div class="container p-3 my-3 border  bg-light text-dark " >
   <center class=><h1 class="text-Seconday">ADMIN DASHBOARD</h1></center><br><hr><br>
   <form action="AdminController">
	<center>
		 <button type="submit" class="btn btn-outline-info w-50 p-5 h-50 d-inline-block" name="command" value="userDetail">User Information</button><br><br>
         <button type="submit" class="btn btn-outline-success w-50 p-5 h-50 d-inline-block" name="command" value="passbookAdmin">Passbook Details</button><br><br>
         <!-- <button type="submit" class="btn btn-outline-danger w-50" name="command" value="logout">Log Out</button><br><br><hr> -->
         <a href="LogoutServlet" class="btn btn-outline-danger w-50">Logout</a> 
	
	</center>
	</form>
	  
    
	



</div>

</body>
</html>