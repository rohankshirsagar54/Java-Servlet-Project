<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Employee</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>

	<div class="container">

		<div class="float-right">
			<a href="${pageContext.request.contextPath}/logout.jsp">Logout</a>
		</div>

		<h1>Employee Directory</h1>

		<div class="row">

			<div class="col-md-4">
				<form action="${pageContext.request.contextPath}/EmployeeServlet"
					method="POST">
					<div class="form-group">
						<input type="text" name="firstname" value="${employee.name} "
							placeholder="Enter Name" class="form-control" /><br />
					</div>
					<div class="form-group">
						<input type="date" name="dob" value="${employee.dob} "
							placeholder="Enter Date of Birth" class="form-control" /><br />
					</div>
					<div class="form-group">
						<input type="text" name="department"
							value="${employee.department} " placeholder="Enter Department"
							class="form-control" /><br />
					</div>


					<input type="hidden" value="${employee.id}" name="id" />
					<button class="btn btn-primary" type="submit">Save
						Employee</button>
				</form>
			</div>



		</div>
	</div>

</body>
</html>