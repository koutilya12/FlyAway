<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<style><%@include file="/css/login.css"%></style>
<title>login page</title>
</head>

<body>

  <div class="signin">
    <div class="back-img">
      <div class="sign-in-text">
        <h2 class="active">Sign In</h2>
      </div><!--/.sign-in-text-->
      <div class="layer">
      </div><!--/.layer-->
      <p class="point">&#9650;</p>
    </div><!--/.back-img-->
    <%   
String errorMessage = (String) request.getAttribute("errorMessage"); 
if(errorMessage != null){
	out.print("Error "+errorMessage);  
}
%>
    <div class="form-section">
     
      <form action = "/flyaway/login" method = "POST">
        <!--Email-->
        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
          <input class="mdl-textfield__input" type="text" id="emailId" name="emailId">
          <label class="mdl-textfield__label" for="emailId">Email</label>
        </div>
        <br/>
        <br/>
        <!--Password-->
        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
          <input pattern=".{8,}" class="mdl-textfield__input" type="password" id="password" name="password">
          <label class="mdl-textfield__label" for="password">Password</label>
        </div>
        <br/>
         <input type = "submit" value = "Sign In" />
      </form>
      <a href="/flyaway/register">
         <button>Register</button>
         </a>
    </div><!--/.form-section-->
    
 </div><!--/.signin-->
</body>
</html>