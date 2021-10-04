 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import = "java.util.List,java.util.ArrayList" %>
<%@ page import = "com.airlines.flyaway.domain.City" %>
<%@ page import = "com.airlines.flyaway.domain.User" %>
<%@ page import = "com.airlines.flyaway.constants.UserTypes" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<style><%@include file="/css/home.css"%></style>

<title>Cities</title>
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

List<City> cities = new ArrayList<>();
cities = (List<City>) request.getAttribute("cities");
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
            <caption><h2>List of cities</h2></caption>
            <tr>
                <th>ID</th>
                <th>Name</th>
            </tr>
            <c:forEach var="city" items="${cities}">
                <tr>
                    <td><c:out value="${city.cityId}" /></td>
                    <td><c:out value="${city.cityName}" /></td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <div>
        <form align="center" action="/flyaway/cities" method="post">  
                <h6>Insert City</h6>
          <input type="text" name="cityName"/><br/>  
          <input type="submit" value="Add City"/>  
        </form>
    </div>
   <% } %>

</body>
</html>