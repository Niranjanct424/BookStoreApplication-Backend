/**
 * 
 */
package com.bridgelabz.bookstore.repository;

import java.util.List;

import com.bridgelabz.bookstore.entity.UserInformation;
import com.bridgelabz.bookstore.request.PasswordUpdate;

/**
 * @author HP
 *
 */
public interface IUserRepository {
	UserInformation save(UserInformation userInformation);

	UserInformation getUser(String email);

	boolean verify(Long id);

	boolean upDate(PasswordUpdate information, Long token);

	UserInformation getUserById(Long id );

	List<UserInformation> getUsers();

}
