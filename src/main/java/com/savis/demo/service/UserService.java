package com.savis.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.savis.demo.dao.User;

public interface UserService {

	List<User> findAll();

	List<User> getAllUsers();

	List<User> getAllUsersWithParams(String name);

	Page<User> findPaging(Pageable pageable);

	User findOne(int id);

	int create(User user);

	int update(User user);

	int delete(int id);

}
