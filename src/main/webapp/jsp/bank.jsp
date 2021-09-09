<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
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


</body>
</html>