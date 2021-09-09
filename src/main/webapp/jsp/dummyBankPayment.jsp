<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Dummy Bank payment </title>
</head>
<body>
 <form id="myForm" action="/flyaway/processPayments" method="post"> 
          <label>From City</label><br>
          <input type="button" value="Success"  onclick="processPayments('success')"/>  
          <input type="button" value="failure"  onclick="processPayments('failure')"/><br/>              
  </form>
</body>
<script>
function bookTickets(status) {

	var bookTicketForm = document.getElementById("myForm");
	
    var bookingId = document.createElement("input");    
    bookingId.value=<%=request.getParameter("bookingId")%>;
    bookingId.name="bookingId";
    bookingId.type="hidden";
    bookTicketForm.appendChild(bookingId);
    
    var transId = document.createElement("input");    
    transId.value = status == 'success' ?  Math.random() : null;
    transId.name="transId";
    transId.type="hidden";
    bookTicketForm.appendChild(transId);

	bookTicketForm.submit();
	}
}

</script>
</html>