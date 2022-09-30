<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
	 <script src="https://kit.fontawesome.com/c102305e9c.js" crossorigin="anonymous"></script>
<meta charset="ISO-8859-1">
<meta charset="ISO-8859-1">
<title>Insert title here</title>


</head>
<body>

	<div class="container p-3 my-3 border  bg-light text-dark ">
		<center>
			<h1>User Transactions</h1>
		</center><hr>

	<button type="button" name="back" onclick="history.back()"><i class="fa-sharp fa-solid fa-arrow-left"></i></button>
					<form action="UserController">
						<input type="hidden" value="${balance}" name="balance">
						<center><h6>Balance:&nbsp<c:out value="${balance}"></c:out></h6>
						 <c:if test = "${minimum==1000}">
        					 <c:out value = "${msg}"/>
     					 </c:if>
						
						<br> 
						TransactionType : <select name="command">
							<option >deposite</option>
							<option>withdraw</option>
						</select><br><br>
						
						<input type="hidden" name="acNo" value="${acNo}">
		
						
						<input type="text" name="ammount" placeholder="Enter Amount" required><br><br>
						
						<input  class="btn btn-outline-success " type="submit"></center>



					</form><hr>
				<center><a href="LogoutServlet" class="btn btn-outline-danger w-50">Logout</a></center>
				
		</div>
		<br>
	</div>



</body>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</html>