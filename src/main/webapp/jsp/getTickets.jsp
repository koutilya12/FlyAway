<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<style><%@include file="/css/home.css"%></style>
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
<ul>
  <li><a class="active" href="/flyaway/getTickets">Home</a></li>
  <li><a href="/flyaway/cities">Cities</a></li>
  <li><a href="/flyaway/airlines">AirLines</a></li>
  <li><a href="/flyaway/flightschedule">Flight Schedules</a></li>
  <li><a href="/flyaway/searchFlights">Book Tickets</a></li>
  <li><a href="/flyaway/getTickets">Get Tickets</a></li>  
 
  <li style="float:right"><a class="active" href="logout">Logout</a></li>  
</ul>
</body>
</html>