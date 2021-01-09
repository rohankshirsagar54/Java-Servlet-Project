package com.rohan.dao;

import com.rohan.entity.Login;

public interface LoginDAO {

	String authenticate(Login login);
	
}
