/**
 * 
 */
package com.bridgelabz.bookstore.repository.implementation;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bridgelabz.bookstore.entity.UserInformation;
import com.bridgelabz.bookstore.repository.IUserRepository;
import com.bridgelabz.bookstore.request.PasswordUpdate;

/**
 * @author HP
 *
 */
@Repository
public class UserRepositoryImplementation implements IUserRepository {
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public UserInformation save(UserInformation userInformation) {
		System.out.println("repository implementation called save" );
		Session session = entityManager.unwrap(Session.class);
		session.saveOrUpdate(userInformation);
		return userInformation;
	}

	@Override
	public UserInformation getUser(String email) {
		Session session = entityManager.unwrap(Session.class);
		@SuppressWarnings("rawtypes")
		Query q = session.createQuery(" FROM UserInformation where email=:email");
		q.setParameter("email", email);
		return (UserInformation) q.uniqueResult();
	}

	@Override
	public boolean verify(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean upDate(PasswordUpdate information, Long token) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public UserInformation getUserById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserInformation> getUsers() {
		// TODO Auto-generated method stub
		return null;
	}

}
