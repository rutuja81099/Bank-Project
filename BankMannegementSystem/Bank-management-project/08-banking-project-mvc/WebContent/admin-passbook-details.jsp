<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://kit.fontawesome.com/c102305e9c.js"
	crossorigin="anonymous"></script>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<meta charset="ISO-8859-1">
<meta charset="ISO-8859-1">
<title>Admin passbook page</title>
<style>
.blockend{
	display:inline-block;
	position:absolute;
	right:10%
	
}

.block{
	display:inline-block;
}
</style>
</head>
<body>

	<div class="container p-3 my-3 border  bg-light text-dark ">
		<center>
			<h1>Passbook Details</h1>
		</center>
		
		<form action="AdminController" class="block">
			<input type="hidden" name="command" value="searchadminpassbook">
			<button type="button" name="back" onclick="history.back()"><i class="fa-sharp fa-solid fa-arrow-left"></i></button>
			<input type="text" placeholder="enter ac/no" name="searchadmin" required>

			<span><input type="submit" value="Search" class="btn-primary" ></span>
		</form>
		
		<form action="AdminController" class="blockend">
			
			<input type="hidden" name="command" value="filterByDate">
			
			<input type="date"  name="fromDate" required>
			<input type="date"  name="toDate" required>
			<span><input type="submit" value="Search" class="btn-primary"></span>
		</form>
		
		
		<table
			class="table table-striped  table-hover table-light table-borderless">

			<tr class="text-center">
				<!-- <th>Account No</th> -->

				<th>Sr.No</th>
				<th>Ac/No</th>
				
				<th>Transaction Date</th>
				<th>Transaction Type</th>
				<th>Transaction Amount</th>
				<th>Transation balance</th>
				
			</tr>
			<c:forEach var="transactions" items="${listPassbook}">
				<tr class="text-center">
					<td>${transactions.srNo}</td>
					<td>${transactions.acNo}</td>			
					<td>${transactions.transactionDate}</td>
					<td>${transactions.transactionType}</td>
					<td>${transactions.ammount}</td>
					<td>${transactions.balance}</td>

				</tr>
			</c:forEach>
			<hr>
		</table>
		<hr>
		<center>
			<!-- <button type="button" class="btn btn-outline-primary w-50" onclick="window.location.href='login.jsp'">Log
				Out</button> -->
				<a href="LogoutServlet" class="btn btn-outline-danger w-50">Logout</a> 
			
		</center>
</body>
</html>