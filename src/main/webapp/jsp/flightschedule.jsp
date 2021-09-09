<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import = "java.util.List,java.util.ArrayList" %>
<%@ page import = "com.airlines.flyaway.domain.FlightScheduleDetails" %>
<%@ page import = "com.airlines.flyaway.domain.AirLine" %>
<%@ page import = "com.airlines.flyaway.domain.City" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<style><%@include file="/css/home.css"%></style>

<title>Flight Schedules</title>
<%   
String errorMessage = (String) request.getAttribute("errorMessage"); 
if(errorMessage != null){
	out.print("Error "+errorMessage);
}
%>
</head>
<body>
<ul>
  <li><a class="active" href="/flyaway/home">Home</a></li>
  <li><a href="/flyaway/cities">Cities</a></li>
  <li><a href="/flyaway/airlines">AirLines</a></li>
  <li><a href="/flyaway/flightschedule">Flight Schedule</a></li>
  <li><a href="/flyaway/searchFlights">Book Tickets</a></li>
  
  <li style="float:right"><a class="active" href="logout">Logout</a></li>  
</ul>
<div align="center">

        <table border="1" cellpadding="5">
            <caption><h2>Flight schedule details</h2></caption>
            <tr>   
                <th>flightId</th>
                <th>source</th>
                <th>destination</th>
                <th>airlineName</th>
                <th>price</th>
                <th>departureTime</th>
                <th>arrivalTime</th>
                <th>capacity</th>
                <th>filledCapacity</th>
                <th>status</th>
            </tr>
            <c:forEach var="flightSchedule" items="${flights}">
                <tr>
                    <td>${flightSchedule.flightId}</td>
                    <td>${flightSchedule.source.cityName}</td>
                    <td>${flightSchedule.destination.cityName}</td>
                    <td>${flightSchedule.airLineId.airLineName}</td>
                    <td>${flightSchedule.price}</td>
                    <td>${flightSchedule.departureTime}</td>
                    <td>${flightSchedule.arrivalTime}</td>
                    <td>${flightSchedule.capacity}</td>
                    <td>${flightSchedule.filledCapacity}</td>
                    <td>${flightSchedule.status}</td> 
                </tr>
            </c:forEach>
        </table>
    </div>
    
     <div>
        <form action="/flyaway/flightschedule" method="post">  
          <label>Source</label>
          <input type="text" name="source"/><br>
          <label>Destination</label>
          <input type="text" name="destination"/><br>
          <label>AirLine Name</label>  
          <input type="text" name="airLineName"/><br>
          <label>Price</label>
          <input type="text" name="price"/><br>
          <label>Departure Time</label>
          <input type="text" name="departureTime"/><br>                    
          <label>Arrival Time</label>
          <input type="text" name="arrivalTime"/><br>
          <label>Flight Total Capacity</label>
          <input type="text" name="capacity"/><br>
          <label>Filled Capacity</label>
          <input type="text" name="filledCapacity"/><br>
          <label>Flight Status</label>
          <input type="text" name="status"/><br>
          <input type="submit" value="Add flight details"/>  
        </form>
    </div>

</body>
</html>