package com.savis.demo.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.savis.demo.dao.User;
import com.savis.demo.dao.UserRepository;
import com.savis.demo.service.UserService;

@Service // thông báo cho JPA class này là service thực thi
public class UserServiceImpl implements UserService {

	@Autowired // khởi tạo repository
	private UserRepository userRepository;

	@PersistenceContext
	private EntityManager em;

	private Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		try {
			// Hàm findAll lấy ra list đối tượng
			return userRepository.findAll();
		} catch (Exception e) {
			// TODO: handle exception
			log.error(e.getMessage());
			return null;
		}
	}

	@Override
	public Page<User> findPaging(Pageable pageable) {
		// TODO Auto-generated method stub
		try {
			/**
			 * Hàm findAll này lấy ra đối tượng page của spring đối tượng page này gồm các
			 * thông tin: data, tổng số trang, tổng số bản ghi trên một trang,....
			 */
			return userRepository.findAll(pageable);
		} catch (Exception e) {
			// TODO: handle exception
			log.error(e.getMessage());
			return null;
		}
	}

	@Override
	public User findOne(int id) {
		// TODO Auto-generated method stub
		try {
			return userRepository.findById(id);
		} catch (Exception e) {
			// TODO: handle exception
			log.error(e.getMessage());
			return null;
		}
	}

	/**
	 * Hàm save sử dụng cho 2 chức năng: create và update JPA kiểm tra với ID truyền
	 * vào, nếu chưa tồn tại ID thì create, nếu đã tồn tại ID thì update
	 */
	@Override
	public int create(User user) {
		// TODO Auto-generated method stub
		try {
			userRepository.save(user);
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			log.error(e.getMessage());
			return 0;
		}
	}

	@Override
	public int update(User user) {
		// TODO Auto-generated method stub
		try {
			userRepository.save(user);
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			log.error(e.getMessage());
			return 0;
		}
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		try {
			User user = userRepository.findById(id);
			// kiểm tra sự tồn tại của user trước khi xóa
			if (user != null) {
				userRepository.delete(user);
				return 1;
			} else {
				return 0;
			}

		} catch (Exception e) {
			// TODO: handle exception
			log.error(e.getMessage());
			return 0;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		StoredProcedureQuery findAllByProcedure = em.createNamedStoredProcedureQuery("getAllUsers");
		return findAllByProcedure.getResultList();
	}

	@Override
	public List<User> getAllUsersWithParams(String name) {
		// TODO Auto-generated method stub
		StoredProcedureQuery findAllByProcedure = em.createNamedStoredProcedureQuery("getAllUsersWithParams");
		findAllByProcedure.setParameter("name", name);
		return findAllByProcedure.getResultList();
	}

}
