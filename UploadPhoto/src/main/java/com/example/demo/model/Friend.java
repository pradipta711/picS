package com.example.demo.model;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@IdClass(CKey.class)
public class Friend {

	@Id
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;
	
	
	@Id
	private String friendId;
	
	
	public Friend()
	{}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getFriendId() {
		return friendId;
	}
	public void setFriendId(String friendId) {
		this.friendId = friendId;
	}

	/*@Override
	public String toString() {
		return "Friend [user=" + user + ", friendId=" + friendId + "]";
	}
	*/
			
	
	
	
}
