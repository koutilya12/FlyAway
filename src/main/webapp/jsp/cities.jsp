<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import = "java.util.List,java.util.ArrayList" %>
<%@ page import = "com.airlines.flyaway.domain.City" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<style><%@include file="/css/home.css"%></style>

<title>Cities</title>
    <%   
String errorMessage = (String) request.getAttribute("errorMesssage"); 
if(errorMessage != null){
	out.print("Error "+errorMessage);
List<City> cities = new ArrayList<>();
cities = (List<City>) request.getAttribute("cities");
}
%>

</head>
<body>
<ul>
  <li><a class="active" href="/flyaway/home">Home</a></li>
  <li><a href="/flyaway/cities">Cities</a></li>
  <li><a href="#airlines">AirLines</a></li>
  <li><a href="#flightSchedules">Flight Schedules</a></li>
  <li style="float:right"><a class="active" href="logout">Logout</a></li>  
</ul>
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

</body>
</html>