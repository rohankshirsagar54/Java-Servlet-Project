
<html>
<head>
<title></title>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>

<body>

	<%
		String email = (String) session.getAttribute("email");

		//if user is already logged in, as we are storing user in session
		if (email != null) {
			response.sendRedirect("EmployeeServlet?action=LIST");
		}

		String status = request.getParameter("status");

		if (status != null) {
			if (status.equals("false")) {
				out.println("Bad Credentials");
			} else if (status.equals("error")) {
				out.println("Errors Occurs");
			}
		}
	%>


	<div class="container">

		<form action="loginprocess" method="post">
			<div class="card">
				<div class="card-header">Login</div>
				<div class="card-body">
					<div class="form-group">

						<input type="text" name="email" class="form-control"
							placeholder="Enter Email" />
					</div>

					<div class="form-group">

						<input type="password" name="password" class="form-control"
							placeholder="Enter Password" />
					</div>
				</div>

				<div class="form-footer">

					<input type="submit" value="Login" class="btn btn-primary" />
				</div>

			</div>

		</form>

	</div>

</body>

</html>