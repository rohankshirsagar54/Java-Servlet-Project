package com.rohan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.rohan.entity.Employee;
import com.rohan.util.DBConnectionUtil;

public class EmployeeDAOImpl implements EmployeeDAO {

	Connection connection = null;
	Statement statement = null;
	ResultSet resultSet = null;

	PreparedStatement preparedStatement = null;

	@Override
	public List<Employee> get() {

		// create reference variable

		List<Employee> list = null;
		Employee employee = null;

		try {
			list = new ArrayList<Employee>();

			// create sql query
			String sql = "SELECT * FROM tbl_employee";

			// get the db connection
			connection = DBConnectionUtil.openConnection();

			// create a statment
			statement = connection.createStatement();

			// execute the sql query
			resultSet = statement.executeQuery(sql);

			// process the resultset
			while (resultSet.next()) {
				employee = new Employee();
				employee.setId(resultSet.getInt("id"));
				employee.setName(resultSet.getString("name"));
				employee.setDob(resultSet.getString("dob"));
				employee.setDepartment(resultSet.getString("department"));

				// add employee to list
				list.add(employee);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		// return list
		return list;

	}

	@Override
	public boolean save(Employee e) {

		boolean flag = false;

		try {
			String sql = "INSERT INTO tbl_employee(name, dob, department) VALUES ('" + e.getName() + "', '" + e.getDob()
					+ "', '" + e.getDepartment() + "')";
			connection = DBConnectionUtil.openConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.executeUpdate();
			flag = true;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return flag;
	}

	@Override
	public Employee get(int id) {

		Employee employee = null;

		try {
			employee = new Employee();
			String sql = "SELECT  * FROM tbl_employee WHERE id=" + id; // id coming from parameter in
																		// getSingleEmployee()
			connection = DBConnectionUtil.openConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				employee.setId(resultSet.getInt("id"));
				employee.setName(resultSet.getString("name"));
				employee.setDob(resultSet.getString("dob"));
				employee.setDepartment(resultSet.getString("department"));

			}
		} catch (SQLException exp) {
			exp.printStackTrace();
		}
		// System.out.println("employee name"+employee.getName()+"employee dob"+
		// employee.getDob() + "employee department"+ employee.getDepartment() );
		return employee;

	}

	@Override
	public boolean update(Employee e) {

		boolean flag = false;

		try {
			String sql = "UPDATE tbl_employee SET name='" + e.getName() + "',dob='" + e.getDob() + "', department='"
					+ e.getDepartment() + "' WHERE id=" + e.getId();
			connection = DBConnectionUtil.openConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.executeUpdate();
			flag = true;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return flag;
	}

	@Override
	public boolean delete(int id) {

		boolean flag = false;
		try {
			String sql = "DELETE FROM tbl_employee where id=" + id;
			connection = DBConnectionUtil.openConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return flag;
	}

}
