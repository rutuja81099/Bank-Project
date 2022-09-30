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
<title>Insert title here</title>
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

	
		
	</form>

	<div class="container p-3 my-3 border  bg-light text-dark ">
		<center>
			<h1>User Details</h1>
		</center>
	<form action="AdminController" class="block">
			<input type="hidden" name="command" value="search">
			<button type="button" name="back" onclick="history.back()"><i class="fa-sharp fa-solid fa-arrow-left"></i></button>	
			<input type="text" placeholder="enter Ac/No " name="search"   required>
			<span><input type="submit" value="Search"  class = "btn-primary" ></span>
		</form>
		
		<input type="button" value="Add User" class="btn-sm btn-primary blockend" onclick="window.location.href='add-user-form.jsp';">

		<table
			class="table table-striped  table-hover table-light table-borderless">

			<tr>
				<th>Account No</th>
				<th>User Name</th>
				<th>Age</th>
				<th>Email</th>
				<th>Password</th>
				<th>Gender</th>
				<th>Mobile Number</th>
				<th>Balance</th>
				<!-- <th>Add</th> -->
				<th>Delete</th>

			</tr>
			
			<%-- <c:if test="${searchList == null}">
			<p>No records found</p>
			</c:if> --%>

			<c:forEach var="user" items="${listUser}">

				<c:url var="addLink" value="AdminController">
					<c:param name="command" value="add"></c:param>
					<c:param name="id" value="${user.acNo}"></c:param>
				</c:url>

				<c:url var="deleteLink" value="AdminController">
					<c:param name="command" value="delete"></c:param>
					<c:param name="acNo" value="${user.acNo}"></c:param>
				</c:url>


				<tr>
					<td>${user.acNo}</td>
					<td>${user.name}</td>
					<td>${user.age}</td>
					<td>${user.email}</td>
					<td>${user.password}</td>
					<td>${user.gender}</td>
					<td>${user.mobile}</td>
					<td>${user.balance}</td>

					<!-- <td>
						<button type="button" class="btn btn-link"
							>
							<i class='fas fa-user-plus' style='font-size: 24px; color: black'></i>
						</button>
					</td> -->
					<td><a href="${deleteLink}">
							<button type="button" class="btn btn-link">
								<i class="fa fa-trash-o" style="font-size: 24px; color: red"></i>
							</button>
					</a></td>

				</tr>

			</c:forEach>


			<hr>
		</table>
		 <hr>
		
		<center>
			<!-- <button type="button" class="btn btn-outline-danger w-50" onclick="window.location.href='login.jsp'">Log
				Out</button> -->
				
				<a href="LogoutServlet" class="btn btn-outline-danger w-50">Logout</a> 
				
			<hr>
		</center>
</body>
</html>