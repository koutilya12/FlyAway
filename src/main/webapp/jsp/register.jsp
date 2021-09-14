<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register User</title>
<%   
String errorMessage = (String) request.getAttribute("errorMessage"); 
if(errorMessage != null){
	out.print("Error "+errorMessage);
}

String successMessage = (String) request.getAttribute("successMessage"); 
if(successMessage != null){
out.print(successMessage);
}
%>
</head>
<body>
    <div>
        <form action="/flyaway/register" method="post">
          <label for="userName">User Name</label><br>  
          <input type="text" name="userName"/><br/>  
          <label for="mobileNum">Mobile Number</label><br>
          <input type="text" name="mobileNum"/><br>
          <label for="emailId">Email Id</label><br>
          <input type="text" name="emailId"/><br>
          <label for="password">Password</label><br>
          <input type="text" name="password"/><br>
          <input type="submit" value="Register"/>  
        </form>
    </div>
</body>
</html>