package com.nime.ezdisco.business;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.nime.ezdisco.data.User;

@Named(value="userSession")
@Scope(value="request")
public class UserSessionImp implements UserSession {
	

	@PersistenceContext
    private EntityManager em;
	
	
	public UserSessionImp() {
		
		System.out.println("ctor");

	}
	
	@PostConstruct
	public void init() {
		System.out.println("init");
	}

	@Override
	public void doSomething() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<User> getUsers() {
		Query q = em.createNamedQuery("User.findAll");
		List<User> users = q.getResultList();
		return users;
	}

	@Override
	public void saveUser(User user) {
		
		
		User found = em.find(User.class, user.getUsername());
		if (found==null) {
			em.persist(user);
		} else {
			em.merge(user);
		}
	
	}

}
