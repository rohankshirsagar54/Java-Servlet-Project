<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/v/bs4/dt-1.10.21/datatables.min.css" />



<script type="text/javascript">
	function redirect() {
		window.location.href = "WEB-INF/views/employeeAdd.jsp";
	}
</script>

</head>


<body>

	<%
		String email = (String) session.getAttribute("email");

		if (email == null) {
			response.sendRedirect("index.jsp");
		}
	%>

	<div class="container">

		<div class="float-right">
			<a href="${pageContext.request.contextPath}/logout.jsp">Logout</a>
		</div>

		<%--pasting the message when employee added successfully --%>
		<p>${message}</p>

		<a href="views/employeeAdd.jsp">Add Employee</a> <br /> <br />



		<table border="1" class="table table-striped table-bordered"
			id="datatable">
			<thead>
				<tr class="thead-dark">
					<th>Name</th>
					<th>Date of birth</th>
					<th>Department</th>
					<th>Actions</th>
				</tr>
			</thead>

			<%-- using forEach to iterate over the list using jstl tglib --%>
			<%--  items is the name we set in setAttribute of request object using EL expressions --%>

			<tbody>
				<c:forEach items="${list}" var="employee">
					<tr>
						<td>${employee.name}</td>
						<td>${employee.dob}</td>
						<td>${employee.department}</td>

						<%-- same like everytime we get name, dob , department for each employee we are get employee id, 
        so using El expression we are getting employee id from database using which we are performing READ operation --%>
						<td><a
							href="${pageContext.request.contextPath}/EmployeeServlet?action=EDIT&id=${employee.id}">Edit</a>
							| <a
							href="${pageContext.request.contextPath}/EmployeeServlet?action=DELETE&id=${employee.id}">Delete</a>
						</td>


					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<script src="https://unpkg.com/jquery@3.3.1/dist/jquery.min.js"></script>
	<script type="text/javascript"
		src="https://cdn.datatables.net/v/bs4/dt-1.10.21/datatables.min.js"></script>

	<script>
		$(document).ready(function() {

			$("#datatable").DataTable()

		})
	</script>

</body>
</html>