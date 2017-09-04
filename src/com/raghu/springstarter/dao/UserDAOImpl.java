package com.raghu.springstarter.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.raghu.springstarter.entity.User;

@Transactional
@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	public String addUser(String username, String password) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		User user = new User(username,password);
		session.save(user);
		return null;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		System.out.println("from dao impl"+username);
		Session session = sessionFactory.getCurrentSession();
		System.out.println(username);
		Query query = session.createQuery("from User where username = :username ");
		query.setParameter("username", username);
		List list = query.list();
		System.out.println(list.isEmpty());
		if(list.size()==0){
			throw new UsernameNotFoundException("No user found with that username");
		}
		return (User)list.get(0);
	}

}
