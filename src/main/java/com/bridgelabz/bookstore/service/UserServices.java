/**
 * 
 */
package com.bridgelabz.bookstore.service;

import java.util.List;

import com.bridgelabz.bookstore.dto.UserDto;
import com.bridgelabz.bookstore.entity.UserInformation;
import com.bridgelabz.bookstore.request.LoginInformation;
import com.bridgelabz.bookstore.request.PasswordUpdate;

/**
 * @author HP
 *
 */
public interface UserServices {

	UserInformation login(LoginInformation information);
	boolean register(UserDto ionformation);
	boolean verify(String token) throws Exception;
	boolean isUserExist(String email);
	boolean update(PasswordUpdate information, String token);
	List<UserInformation> getUsers();
	UserInformation getSingleUser(String token);
}