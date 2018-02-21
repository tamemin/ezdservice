package com.nime.ezdisco.rest;

import javax.xml.bind.annotation.XmlRootElement;

import com.nime.ezdisco.data.User;

@XmlRootElement
public class UsersResponse {
	
	private User[] users;

	public User[] getUsers() {
		return users;
	}

	public void setUsers(User[] users) {
		this.users = users;
	}
	
	

}
