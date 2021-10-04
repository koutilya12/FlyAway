<%@page import="com.airlines.flyaway.constants.UserTypes"%>
<%@page import="com.airlines.flyaway.constants.convertors.UserTypesConvertor"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import = "java.util.List,java.util.ArrayList" %>
<%@ page import = "com.airlines.flyaway.domain.AirLine" %>
<%@ page import = "com.airlines.flyaway.domain.User" %>
<%@ page import = "com.airlines.flyaway.constants.UserTypes" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<style><%@include file="/css/home.css"%></style>
<title>AirLines</title>
<%   
String errorMessage = (String) request.getAttribute("errorMessage"); 
if(errorMessage != null){
	out.print("Error "+errorMessage);
}

String successMessage = (String) request.getAttribute("successMessage"); 
if(successMessage != null){
out.print(successMessage);
}

User  user = (User) session.getAttribute("userDetails");
boolean isAdmin = (user != null && UserTypes.ADMIN.equals(user.getType())) ? true : false;
boolean isCustomer = (user != null && UserTypes.CUSTOMER.equals(user.getType())) ? true : false;

List<AirLine> airlines = new ArrayList<>();
airlines = (List<AirLine>) request.getAttribute("airlines");

%>
</head>
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

	<%
	if (isAdmin) {
	%>
<div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of airlines</h2></caption>
            <tr>
                <th>ID</th>
                <th>Airline</th>
            </tr>
            <c:forEach var="airLine" items="${airlines}">
                <tr>
                    <td><c:out value="${airLine.airLineId}" /></td>
                    <td><c:out value="${airLine.airLineName}" /></td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <div>
        <form align="center" action="/flyaway/airlines" method="post"> 
        <h2>Insert Airline</h2> 
          <input type="text" name="airLineName"/><br/>  
          <input type="submit" value="Add Airline"/>  
        </form>
    </div>
   <% } %>


</body>
</html>