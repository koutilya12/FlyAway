<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import = "java.util.List,java.util.ArrayList" %>
<%@ page import = "com.airlines.flyaway.domain.FlightTicketBooking" %>
<%@ page import = "com.airlines.flyaway.domain.User" %>
<%@ page import = "com.airlines.flyaway.constants.UserTypes" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<style><%@include file="/css/home.css"%></style>
<title>Get Tickets</title>
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
List<FlightTicketBooking> ticketdetails = new ArrayList<>();
ticketdetails = (List<FlightTicketBooking>) request.getAttribute("ticketdetails");

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
	if (isCustomer) {
	%>

	<div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>Ticket Details</h2></caption>
            <tr>
                <th>Booking ID</th>
                <th>Booking Time</th>
                <th>User ID</th>
                <th>Flight ID</th>
                <th>Passenger Count</th>
                <th>Total Price</th>
                <th>Transaction ID</th>
                <th>Booking Status</th>
            </tr>
            <c:forEach var="ticketdetails" items="${ticketdetails}">
                <tr>
                    <td><c:out value="${ticketdetails.bookingId}" /></td>
                    <td><c:out value="${ticketdetails.bookingTime}" /></td>
                    <td><c:out value="${ticketdetails.user.userId}" /></td>
                    <td><c:out value="${ticketdetails.flight.flightId}" /></td>
                    <td><c:out value="${ticketdetails.noOfPersons}" /></td>
                    <td><c:out value="${ticketdetails.totPrice}" /></td>
                    <td><c:out value="${ticketdetails.transactionId}" /></td>
                    <td><c:out value="${ticketdetails.flightBookingStatus}" /></td>
                </tr>
            </c:forEach>
        </table>
</div>
   <% } %>

</body>
</html>