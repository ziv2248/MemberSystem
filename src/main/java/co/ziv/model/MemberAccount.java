package co.ziv.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "member_account")
public class MemberAccount {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String account;
	private String password;
	private String name;
	private String email;
	
	public MemberAccount() {
		super();
	}
	
	public MemberAccount(int id, String account, String password, String name, String email) {
		super();
		this.id = id;
		this.account = account;
		this.password = password;
		this.name = name;
		this.email = email;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
