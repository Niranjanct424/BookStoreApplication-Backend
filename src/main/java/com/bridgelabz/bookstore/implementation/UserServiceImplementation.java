/**
 * 
 */
package com.bridgelabz.bookstore.implementation;

import java.time.LocalDateTime;
import java.util.List;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bridgelabz.bookstore.dto.UserDto;
import com.bridgelabz.bookstore.entity.UserInformation;
import com.bridgelabz.bookstore.exception.UserException;
import com.bridgelabz.bookstore.repository.IUserRepository;
import com.bridgelabz.bookstore.request.PasswordUpdate;
import com.bridgelabz.bookstore.response.UserInfo;
import com.bridgelabz.bookstore.service.UserServices;


@Service
public class UserServiceImplementation implements UserServices {
	private UserInformation userInformation= new UserInformation();
	@Autowired
	private IUserRepository repository;

	@Autowired
	private BCryptPasswordEncoder encryption;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserInfo login(UserInformation information) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public boolean register(UserDto information) {
		UserInformation user = repository.getUser(information.getEmail());
		if (user == null) {
			userInformation = modelMapper.map(information, UserInformation.class);
			userInformation.setCreatedDate(LocalDateTime.now());
			String epassword = encryption.encode(information.getPassword());
			// setting the some extra information and encrypting the password
			userInformation.setPassword(epassword);
			System.out.println("password is" + epassword);
			userInformation.setVerified(false);
			// calling the save method
			userInformation = repository.save(userInformation);
			return true;
		} else {
			throw new UserException("user already exist with the same mail id");

		}
			
	}

	@Override
	public boolean verify(String token) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isUserExist(String email) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(PasswordUpdate information, String token) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<UserInformation> getUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserInformation getSingleUser(String token) {
		// TODO Auto-generated method stub
		return null;
	}

}
