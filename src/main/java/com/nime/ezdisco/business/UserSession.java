package com.nime.ezdisco.business;

import java.util.List;

import com.nime.ezdisco.data.User;

public interface UserSession {
	
	void doSomething();
	List<User> getUsers();
	void saveUser(User user);

}
