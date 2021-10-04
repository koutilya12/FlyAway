<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import = "com.airlines.flyaway.domain.User" %>
<%@ page import = "com.airlines.flyaway.constants.UserTypes" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<style><%@include file="/css/home.css"%></style>

<title>Home</title>
</head>
<%   

User  user = (User) session.getAttribute("userDetails");
boolean isAdmin = (user != null && UserTypes.ADMIN.equals(user.getType())) ? true : false;
boolean isCustomer = (user != null && UserTypes.CUSTOMER.equals(user.getType())) ? true : false;


%>

<body>
	<ul>
		<li><a class="active" href="/flyaway/home">Home</a></li>
		<%
		if (isAdmin) {
		%>
		<li><a href="/flyaway/cities">Cities</a></li>
		<li><a href="/flyaway/airlines">AirLines</a></li>
		<li><a href="/flyaway/flightschedule">Flight Schedules</a></li>
		<%
		}
		%>
		<%
		if (isCustomer) {
		%>
		<li><a href="/flyaway/searchFlights">Book Tickets</a></li>
		<li><a href="/flyaway/getTickets">Get Tickets</a></li>

		<%
		}
		%>

		<li style="float: right"><a class="active"
			href="/flyaway/changePassword">Change Password</a></li>
		<li style="float: right"><a class="active" href="logout">Logout</a></li>
	</ul>


<h1> Welcome to Flyaway</h1>
</body>

</html>
