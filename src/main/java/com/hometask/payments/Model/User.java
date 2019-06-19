package com.hometask.payments.Model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "users")
public class User implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name = "name")
	private String name;
	@Id
	@Column(name = "userid")
	private String userId;
	@Column(name = "balance")
	private int balance;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public User(String name, String userId, int balance) {
		this.name = name;
		this.userId = userId;
		this.balance = balance;
	}
	
	public User() {
		
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	@Override
	public String toString() {
		return "User [name=" + name + ", userId=" + userId + ", balance=" + balance + "]";
	}
	
	public void credit(int amount)
	{
		this.setBalance(amount+getBalance());
	}
	
	public String debit(int amount)
	{
		if(amount>getBalance())
			return "FAILURE";
		
		this.setBalance(getBalance()-amount);
		return "SUCCESS";
	}
	
}
