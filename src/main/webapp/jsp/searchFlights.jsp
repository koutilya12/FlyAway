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
<title>Search flights</title>
</head>
<body>
<%   
String errorMessage = (String) request.getAttribute("errorMessage"); 
if(errorMessage != null){
	out.print("Error "+errorMessage);
}
List<FlightScheduleDetails> flightScheduleDetails = new ArrayList<>();
flightScheduleDetails = (List<FlightScheduleDetails>) request.getAttribute("flights");

String source = request.getParameter("source") != null ?  request.getParameter("source") : "";
String destination = request.getParameter("destination") != null ?  request.getParameter("destination") : "";
String departureTime = request.getParameter("departureTime") != null ?  request.getParameter("departureTime") : "";
String arrivalTime = request.getParameter("arrivalTime") != null ?  request.getParameter("arrivalTime") : "";
String noOfPersons = request.getParameter("noOfPersons") != null ?  request.getParameter("noOfPersons") : "";
%>
<ul>
  <li><a class="active" href="/flyaway/home">Home</a></li>
  <li><a href="/flyaway/cities">Cities</a></li>
  <li><a href="/flyaway/airlines">AirLines</a></li>
  <li><a href="/flyaway/flightschedule">Flight Schedules</a></li>
  <li><a href="/flyaway/searchFlights">Book Tickets</a></li>
  <li style="float:right"><a class="active" href="logout">Logout</a></li>  
</ul>

 <form id="myForm" action="/flyaway/searchFlights" method="post"> 
          <label>From City</label><br>
          <input id="source" type="text" name="source" value ="<%=source%>"/><br/>
          <label>To City</label><br>
          <input id="destination" type="text" name="destination" value="<%=destination%>"/><br/>
          <label>Departure Time</label><br>  
          <input id="departureTime" type="text" name="departureTime" value="<%=departureTime%>"/><br/>
          <label>Arrival Time</label><br>  
          <input id="arrivalTime" type="text" name="arrivalTime" value="<%=arrivalTime%>"/><br/>
          <label>Total Passengers</label><br> 
          <input id="noOfPersons" type="text" name="noOfPersons" value="<%=noOfPersons%>"/><br/>  
          <input type="submit" value="Search Flights"/>  
          <input type="button" value="reset"  onclick="resetForm()"/><br/>              
  </form>
  
  <div align="center">

        <table border="1" cellpadding="5">
            <caption><h2>Flight schedule details</h2></caption>
            <tr>   
                <th>flightId</th>
                <th>source</th>
                <th>destination</th>
                <th>airlineId</th>
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
                    <td><button onclick="bookTicket('${flightSchedule.flightId}')">Book Ticket</button></td>
                </tr>
            </c:forEach>
        </table>
    </div>
<script>
function bookTicket(flightId) {
	console.log(flightId);
	var bookForm = document.createElement("form");
    var flightElement = document.createElement("input"); 
    var noOfPersons = document.createElement("input");  

    bookForm.method = "GET";
    bookForm.action = "/flyaway/jsp/bookTickets.jsp";   

    flightElement.value=flightId;
    flightElement.name="flightId";
    flightElement.type="hidden";
    bookForm.appendChild(flightElement);  

    noOfPersons.value=<%=noOfPersons%>;
    noOfPersons.name="noOfPersons";
    noOfPersons.type="hidden";
    bookForm.appendChild(noOfPersons);

    document.body.appendChild(bookForm);

    bookForm.submit();
}

function resetForm() {
	console.log('hello here!');
	document.getElementById('source').value="";
	document.getElementById('destination').value="";
	document.getElementById('departureTime').value="";
	document.getElementById('arrivalTime').value="";
	document.getElementById('noOfPersons').value="";
}
</script>
</body>

</html>