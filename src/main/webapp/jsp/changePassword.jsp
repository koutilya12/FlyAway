<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import = "com.airlines.flyaway.domain.User" %>
<%@ page import = "com.airlines.flyaway.constants.UserTypes" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<style><%@include file="/css/home.css"%></style>
<title>password change</title>
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
<div class="form-section">
     
      <form align="center" action = "/flyaway/changePassword" method = "POST">
      <h2>Change Password</h2>
        <!--change password-->
        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
          <input  pattern=".{8,}" class="mdl-textfield__input" type="password" id="oldPassword" name="oldPassword">
          <label class="mdl-textfield__label" for="oldPassword">Old Password</label>
        </div>
        <br/>
        <br/>
        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
          <input  pattern=".{8,}" class="mdl-textfield__input" type="password" id="newPassword" name="newPassword">
          <label class="mdl-textfield__label" for="newPassword">New Password</label>
        </div>
        <br/>
        <!--Confirm New Password-->
        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
          <input pattern=".{8,}" class="mdl-textfield__input" type="password" id="confirmPassword" name="confirmPassword">
          <label class="mdl-textfield__label" for="confirmPassword">Confirm Password</label>
        </div>
        <br/>
         <input type = "submit" value = "Change Password" />
      </form>
    </div><!--/.form-section-->
 </div><!--/.change password-->
</body>
</html>