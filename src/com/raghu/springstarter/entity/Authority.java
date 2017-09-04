package com.raghu.springstarter.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name="authority")
public class Authority implements GrantedAuthority{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="authority")
	private String authority;
	
    public Authority() {
		super();
	}
    
    public Authority(String authority){
    	super();
    	this.authority = authority;
    }
    
	@Override
	public String toString() {
		return "Authority [id=" + id + ", authority=" + authority + "]";
	}

	@ManyToOne(cascade=CascadeType.ALL) 
	private User user;
	
	public Authority(String username, String authority) {
		super();
		this.authority = authority;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
	 
    public User getUser()  
    {  
        return user;  
    }  
    public void setEmployer(User user)  
    {  
        this.user = user;  
    } 


}
