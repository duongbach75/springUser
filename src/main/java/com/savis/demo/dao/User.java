package com.savis.demo.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "user", schema = "public")
@NamedStoredProcedureQueries({
		@NamedStoredProcedureQuery(name = "getAllUsers", procedureName = "fn_user_getall", resultClasses = User.class),
		@NamedStoredProcedureQuery(name = "getAllUsersWithParams", procedureName = "fn_user_getall_with_params", parameters = {
				@StoredProcedureParameter(name = "name", type = String.class, mode = ParameterMode.IN) }, resultClasses = User.class) })
public class User {
	private int id;
	private String code;
	private String name;
	private String email;
	private String phone;
	private String address;

	public User(int id, String code, String name, String email, String phone, String address) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.address = address;
	}

	public User() {
		super();
	}

	@Id // chú thích cho JPA hiểu trường này là khóa chính
	@Column(name = "id") // mapping với tên cột trong db
	@TableGenerator(name = "gen_id", table = "hibernate_gen_id", pkColumnName = "gen_name", valueColumnName = "gen_value", allocationSize = 1) // generate
																																				// id
																																				// tự
																																				// tăng
																																				// với
																																				// table
																																				// database
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "gen_id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "code")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "phone")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "address")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
