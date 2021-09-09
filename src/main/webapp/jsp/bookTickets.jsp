<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Book Your Tickets</title>
<style>
input {
	display: inline;
}
</style>
</head>
<body>
	<%
	Integer noOfPersons = request.getParameter("noOfPersons") != null
			? Integer.parseInt(request.getParameter("noOfPersons"))
			: 0;
	%>

	<form id="bookTicketForm" action="/flyaway/bookTickets" method="post">
		<table>

			<%
			for (int index = 0; index < noOfPersons; index++) {
			%>
			<tr>
			    <td>
				<label>Passenger Name</label>
				<input type="text" name="passengerName-<%=index%>"/>
				</td>
				<td>
				<label>Gender</label>
				<br><input type="radio" name="gender-<%=index%>" value="M" checked=true/>male
				<br><input type="radio" name="gender-<%=index %>" value="F"/>female
				<br><input type="radio" name="gender-<%=index %>" value="O"/>others<br>
				</td>
				<td>
				<label>Age</label>
				<input type="text" name="age-<%=index %>"/> 
				</td>
				<td>
				<label>Mobile Number</label>
				<input type="text" name="mobileNumber-<%=index%>"/>
				</td>
				<td>
				<label for="govDocumentType">Document</label><br>
                <select id="govDocumentType" name="govDocumentType-<%=index%>" size="4">
                <option value="A" selected>Aadhaar card</option>
                <option value="P">Pancard</option>
                <option value="L">Driver's License</option>
                <option value="V">Voter Id</option>
                </select>
				</td>
				<td>
				<label>Document ID</label>
				<input type="text" name="govDocumentId-<%=index%>"/>
				</td>
			</tr>
			<% } %>
		</table>
		<input type="button" onclick="bookTickets()" value="Book Tickets"/>
	</form>
	
<script>
function bookTickets() {
	var errorMsg = validateInputs();
	if(errorMsg != null){
		alert(errorMsg);
	}else {
	var bookTicketForm = document.getElementById("bookTicketForm");
	
    var flightId = document.createElement("input");    
    flightId.value=<%=request.getParameter("flightId")%>;
    flightId.name="flightId";
    bookTicketForm.appendChild(flightId);
    
    var noOfPersons = document.createElement("input");  
    noOfPersons.value=<%=noOfPersons%>;
    noOfPersons.name="noOfPersons";
    bookTicketForm.appendChild(noOfPersons);

	bookTicketForm.submit();
	}
}
var agePattern = new RegExp("^[1-9][0-9]{0,1}$");
var mobilePattern = new RegExp("^[6-9][0-9]{9}$");

function validateInputs() {
	for(let index = 0; index < <%=noOfPersons%>; index++) {
	if(document.getElementsByName("passengerName-"+index)[0].value == "") {
		return "Name shouldn't be empty at row "+(index +1);
	}
	if(document.getElementsByName("gender-"+index)[0].value == ""){
		return "Gender shouldn't be empty at row " +index +1;
	}
    var age = document.getElementsByName("age-"+index)[0].value;
	if(age == "" || !agePattern.test(age)) {
		return "Age should be valid at row "+index +1;
	}
    var mno = document.getElementsByName("mobileNumber-"+index)[0].value;
	if(mno == "" || !mobilePattern.test(mno)) {
		return "Mobile Number should be valid at row "+index +1;
	}
	if(document.getElementsByName("govDocumentType-"+index)[0].value == "") {
		return "Document Type shouldn't be empty at row "+index +1;
	}
	if(document.getElementsByName("govDocumentId-"+index)[0].value == "") {
		return "Document Id shouldn't be empty at row "+index +1;
	}
  }
	return null;
}

</script>
</body>
</html>