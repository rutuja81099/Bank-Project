<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<style>
.container{
	margin-top:130px;

}
</style>
<body>

<div class="container p-3 border  bg-light text-dark " >
<form name="myform" action="LoginController" onsubmit="return validation()" method="post">
<center class=""><h1>Login</h1></center>
<input type="radio"  id="admin" name="userType" value="admin">
<label for="admin">ADMIN</label>
<input type="radio"   id="user" name="userType" value="user">
<label for="user">USER</label><br>

<br> <label for="email">Email : </label> <input type="text" class="w-100" name="email" id="emails" value=""><br>
<br> <label for="passward">PassWord :</label> <input type="passward" class="w-100" name="password" id="pass" value=""><br><br>

<button type="submit" class="btn btn-secondary" value="login">Login</button>
	<c:out value="${msg}"/>

</form>
</div>

</body>
 <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
 <script src="script.js"></script>
</html>