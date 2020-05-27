/**
 * 
 */
package com.bridgelabz.bookstore.service;

import java.util.List;

import com.bridgelabz.bookstore.dto.UserDto;
import com.bridgelabz.bookstore.entity.UserInformation;
import com.bridgelabz.bookstore.request.PasswordUpdate;
import com.bridgelabz.bookstore.response.UserInfo;

/**
 * @author HP
 *
 */
public interface UserServices {

	UserInfo login(UserInformation information);
	boolean register(UserDto ionformation);
	boolean verify(String token) throws Exception;
	boolean isUserExist(String email);
	boolean update(PasswordUpdate information, String token);
	List<UserInformation> getUsers();
	UserInformation getSingleUser(String token);
}