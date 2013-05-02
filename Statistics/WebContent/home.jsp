<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Assessment</title>
<%
	String message = (String) request.getAttribute("message");
	Double average = (Double) request.getAttribute("average");
	Double median = (Double) request.getAttribute("median");
%>
<script type="text/javascript">
	function validate() {
		if (document.assessment.numbers.value == '') {
			alert('Please enter input Numbers');
			return false;
		}

		return true;
	}
</script>
</head>
<body>
	<%
		if (message != null) {
	%>
	<font color="red"><b><%=message%></b></font>
	<%
		} else if (average != null) {
	%>
	Average:
	<%=average%>

	Median:
	<%=median%>
	<%
		}
	%>
	<form action="assessment" name="assessment">

		<table align="center">
			<tr>
				<th>Technical Assesment</th>
			</tr>
			<tr>
				<td>Enter 5 digit Numbers in separate lines</td>
			</tr>
			<tr>
				<td><textarea rows="5" name="numbers" cols="50"></textarea></td>
			</tr>
			<tr>
				<td><input type="submit" value="Submit" /></td>
			</tr>
		</table>

	</form>

</body>
</html>