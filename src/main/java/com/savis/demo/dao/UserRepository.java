package com.savis.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

/**
 * Repository extend JpaRepository; JpaRepository cung cấp các hàm thao tác với
 * database như findOne, findAll, save, delete,....
 * 
 * @author HT_PT
 *
 */
public interface UserRepository extends JpaRepository<User, Integer> {

	User findById(int id);

	@Procedure
	List<User> getAllUsers();

}
