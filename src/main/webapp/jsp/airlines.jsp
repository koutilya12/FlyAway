<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import = "java.util.List,java.util.ArrayList" %>
<%@ page import = "com.airlines.flyaway.domain.AirLine" %>
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

List<AirLine> airlines = new ArrayList<>();
airlines = (List<AirLine>) request.getAttribute("airlines");

%>
</head>
<body>
<ul>
  <li><a class="active" href="/flyaway/home">Home</a></li>
  <li><a href="/flyaway/cities">Cities</a></li>
  <li><a href="/flyaway/airlines">AirLines</a></li>
  <li><a href="/flyaway/flightschedule">Flight Schedules</a></li>
  <li><a href="/flyaway/searchFlights">Book Tickets</a></li>
  
  <li style ="float:right"><a class="active" href="/flyaway/changePassword">Change Password</a></li>
  <li style="float:right"><a class="active" href="logout">Logout</a></li>  
</ul>

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
        <form action="/flyaway/airlines" method="post">  
          <input type="text" name="airLineName"/><br/>  
          <input type="submit" value="Add Airline"/>  
        </form>
    </div>


</body>
</html>