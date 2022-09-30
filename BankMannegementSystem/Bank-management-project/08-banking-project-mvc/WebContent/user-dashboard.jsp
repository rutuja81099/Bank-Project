<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	
<!DOCTYPE html>
<html>
<head>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<meta charset="ISO-8859-1">
<title>user dashboard</title>
</head>
<body>
	<div class="container p-3 my-3 border  bg-light text-dark ">
		<center class=>
			<h1 class="text-Seconday">USER DASHBOARD</h1>
		</center>
		<hr>
		<c:set var="acNo"  value="${acNo}"/>
		<c:url var="printPassbookLink" value="UserController">
			<c:param name="command" value="passbook"></c:param>
			<c:param name="acNo" value="${isUser.acNo}"></c:param>
		</c:url>
		
		<c:url var="userTransactionLink" value="UserController">
			<c:param name="command" value="acInfo"></c:param>
			<c:param name="acNo" value="${isUser.acNo}"></c:param>
			<c:param name="balance" value="${balance}"></c:param>
			
		</c:url>
		
		<c:url var="loadUserLink" value="UserController">
			<c:param name="command" value="load"></c:param>
			<c:param name="acNo" value="${isUser.acNo}"></c:param>
		</c:url>
		

		

		<center>
		<td><a href="${userTransactionLink}">
			<button type="button"
				class="btn btn-outline-info w-50 p-5 h-25 d-inline-block"
				name="command" value="transaction" >Transaction</button>
				</a>
				
			<br>
			<br> <a href="${printPassbookLink}">
				<button type="button"
					class="btn btn-outline-success  w-50 p-5 h-25 d-inline-block">Print
					Passbook</button>
			</a><br>
			<br>
			
			<a href="${loadUserLink}">
			<button type="button"
				class="btn btn-outline-info   w-50 p-5 h-25 d-inline-block "
				>Edit User Information</button>
			</a>
				
			<br>
			<br>
			<%-- <a href="${logoutLink}">
			<button type="button" class="btn btn-outline-danger w-50" onclick="window.location.href='login.jsp'" >Log
				Out</button></a> --%>
				
				<a  href="LogoutServlet" class="btn btn-outline-danger w-50"> Logout</a>
			<hr>
			

		</center>






	</div>

</body>
</html>