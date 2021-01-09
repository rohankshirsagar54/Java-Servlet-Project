package com.rohan.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rohan.dao.EmployeeDAO;
import com.rohan.dao.EmployeeDAOImpl;
import com.rohan.entity.Employee;

public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	RequestDispatcher dispatcher = null;

	// create a reference variable for EmployeeDAO
	EmployeeDAO employeeDAO = null;

	// create constructor and initialize employeeDAO
	public EmployeeServlet() {
		employeeDAO = new EmployeeDAOImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

// depending on the action type we are calling switch operation
// so if action type is "LIST" then we are calling list operation, if action is edit then edit operation and same for delete

		String action = request.getParameter("action");

		if (action == null) {
			action = "LIST";
		}

		switch (action) {
		case "LIST":
			listEmployee(request, response);
			break;

		case "EDIT":
			getSingleEmployee(request, response);
			break;

		case "DELETE":
			deleteEmployee(request, response);
			break;

		default:
			listEmployee(request, response);
			break;
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id");
		String name = request.getParameter("firstname");
		String dob = request.getParameter("dob");
		String department = request.getParameter("department");

		// we need to create employee object and set the values of name, dob and
		// department to employe object
		Employee e = new Employee();

		e.setName(name);
		e.setDob(dob);
		e.setDepartment(department);

		if (id.isEmpty() || id == null) {

			// save operation
			if (employeeDAO.save(e)) {
				request.setAttribute("message", "Saved successfully");
			}

		} else {

			// update operation
			e.setId(Integer.parseInt(id));
			if (employeeDAO.update(e)) {
				request.setAttribute("message", "Updated successfully");
			}
		}

		// calling listEmployee after saving or adding employee to the database
		listEmployee(request, response);

	}

	public void listEmployee(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// call dao method to get the list of employees
		List<Employee> list = employeeDAO.get();

		// add list to request object
		request.setAttribute("list", list);

		// get the dispatcher servlet
		dispatcher = request.getRequestDispatcher("/WEB-INF/views/emplyee-list.jsp");

		// forward the request and response object
		dispatcher.forward(request, response);

	}

	public void getSingleEmployee(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id");
		Employee employee = employeeDAO.get(Integer.parseInt(id));
		request.setAttribute("employee", employee);

		// get the dispatcher servlet
		dispatcher = request.getRequestDispatcher("/WEB-INF/views/employeeAdd.jsp");

		// forward the request and response object
		dispatcher.forward(request, response);
	}

	public void deleteEmployee(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		if (employeeDAO.delete(Integer.parseInt(id))) {
			request.setAttribute("message", "Record has been Deleted");
		}
		listEmployee(request, response);

	}

}
