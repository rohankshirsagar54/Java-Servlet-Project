package com.rohan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.rohan.entity.Login;
import com.rohan.util.DBConnectionUtil;

public class LoginDAOImpl implements LoginDAO {

	@Override
	public String authenticate(Login login) {

		String sql = "SELECT * FROM tbl_login where email=? and password=?";

		try {
			Connection connection = DBConnectionUtil.openConnection();
			PreparedStatement s = connection.prepareStatement(sql);
			s.setString(1, login.getEmail());
			s.setString(2, login.getPassword());
			ResultSet rs = s.executeQuery();

			if (rs.next()) {
				return "true";
			} else {
				return "false";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "error";
	}

}
