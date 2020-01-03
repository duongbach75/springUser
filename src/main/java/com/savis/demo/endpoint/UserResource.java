package com.savis.demo.endpoint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.savis.demo.common.dto.ServiceResponse;
import com.savis.demo.dao.User;
import com.savis.demo.service.UserService;

/**
 * - @Controller: thông báo đây là controler
 * - @GetMapping, @PostMapping, @PutMapping, @DeleteMapping: mapping với các
 * phương thức GET, POST, PUT, DELETE - value ở trong các Mapping trên: đường
 * dẫn map đến phương thức. Khi request sẽ gọi cái này - produces: định dạng dữ
 * liệu response về - consumes: định dạng dữ liệu lấy lên từ client
 * - @ResponseBody: dữ liệu response về sẽ nằm trong body - Pageable chứa các
 * thông tin yêu cầu phân trang, sắp xếp - @PathVariable("id"): lấy dữ liệu trên
 * path khi gọi com/savis/demo/user/{id}
 * 
 * @author HT_PT
 *
 */
@Controller
public class UserResource {

	@Autowired
	private UserService userService;

	/**
	 * 
	 * @param headerRequestKey: lấy dữ lieuj trên header có key là
	 *        Header-request-key
	 * @return
	 */
	@GetMapping(value = "com/savis/demo/user/all", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody ServiceResponse<List<User>> findAll() {
		if (userService.findAll() != null) {
			return new ServiceResponse<List<User>>(1, "success", userService.findAll());
		} else {
			return new ServiceResponse<List<User>>(0, "error", null);
		}
	}

	@GetMapping(value = "com/savis/demo/user/list", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody ServiceResponse<List<User>> getAllUsers(@RequestParam String name) {
		if (name != null || !"".equals(name)) {
			return new ServiceResponse<List<User>>(1, "success", userService.getAllUsersWithParams(name));
		} else {
			return new ServiceResponse<List<User>>(1, "success", userService.getAllUsers());
		}
	}

	@GetMapping(value = "com/savis/demo/user/{id}", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody ServiceResponse<User> findById(@PathVariable("id") int id) {
		if (userService.findAll() != null) {
			return new ServiceResponse<User>(1, "success", userService.findOne(id));
		} else {
			return new ServiceResponse<User>(0, "error", null);
		}
	}

	@GetMapping(value = "com/savis/demo/user/paging", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody ServiceResponse<Page<User>> findPage(Pageable pageable) {
		if (userService.findAll() != null) {
			return new ServiceResponse<Page<User>>(1, "success", userService.findPaging(pageable));
		} else {
			return new ServiceResponse<Page<User>>(0, "error", null);
		}
	}

	@PostMapping(value = "com/savis/demo/user", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_UTF8_VALUE })

	public @ResponseBody ServiceResponse<Integer> create(@RequestBody User user) {
		if (userService.findAll() != null) {
			return new ServiceResponse<Integer>(1, "success", userService.create(user));
		} else {
			return new ServiceResponse<Integer>(0, "error", null);
		}
	}

	@PutMapping(value = "com/savis/demo/user", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody ServiceResponse<Integer> update(@RequestBody User user) {
		if (userService.findAll() != null) {
			return new ServiceResponse<Integer>(1, "success", userService.update(user));
		} else {
			return new ServiceResponse<Integer>(0, "error", null);
		}
	}

	@DeleteMapping(value = "com/savis/demo/user/{id}", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody ServiceResponse<Integer> delete(@PathVariable("id") int id) {
		if (userService.findAll() != null) {
			return new ServiceResponse<Integer>(1, "success", userService.delete(id));
		} else {
			return new ServiceResponse<Integer>(0, "error", null);
		}
	}
}
