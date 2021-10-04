<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<style><%@include file="/css/login.css"%></style>
<title>Register User</title>
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

    String successMessage = (String) request.getAttribute("successMessage"); 
    if(successMessage != null){
    out.print(successMessage);
    }
%>
    <div class="form-section">
     
        <form action="/flyaway/register" method="post">
         <!--userName-->
        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
          <label class="mdl-textfield__label" for="userName">Name</label>
          <input class="mdl-textfield__input" type="text" id="userName" name="userName">
        </div>
        <br/>
        <br/>        
        <!--mobileNum-->
        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
          <label class="mdl-textfield__label" for="mobileNum">MobileNumber</label>
          <input class="mdl-textfield__input" type="text" id="mobileNum" name="mobileNum">
        </div>
        <br/>
        <br/>  
        <!--Email-->
        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
          <label class="mdl-textfield__label" for="emailId">Email</label>
          <input class="mdl-textfield__input" type="text" id="emailId" name="emailId">
        </div>
        <br/>
        <br/>
        <!--Password-->
        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
          <label class="mdl-textfield__label" for="password">Password</label>
          <input pattern=".{8,}" class="mdl-textfield__input" type="password" id="password" name="password">
        </div>
        <br/>
         <input type = "submit" value = "Register" />
      </form>
    </div><!--/.form-section-->
    
 </div><!--/.signup-->
</body>
</html>