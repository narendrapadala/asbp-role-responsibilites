/**
 * 
 */
package org.cisco.asbp_role_responsibilites.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.cisco.asbp_common_utils.cu.model.GenericResponseDto;
import org.cisco.asbp_role_responsibilites.domain.AddUserRoleDto;
import org.cisco.asbp_role_responsibilites.domain.UsersProfileDto;
import org.cisco.asbp_role_responsibilites.entity.ModuleConfigMapping;
import org.cisco.asbp_role_responsibilites.entity.Users;
import org.cisco.asbp_role_responsibilites.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Narendra
 *
 */
@RestController
@RequestMapping("/users")
public class UserController {

	private UserService userService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/all")
	public GenericResponseDto<Page<Users>> getAllUsers(@RequestParam(required = false) String searchColumn,
			@RequestParam(required = false) String searchValue, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, HttpServletRequest request) {
		return new GenericResponseDto.GenericResponseDtoBuilder<>(request,
				userService.getAllUsers(PageRequest.of(page, size))).build();
	}

	@GetMapping("/getUser")
	public GenericResponseDto<Users> getUser(@RequestParam Long userId, HttpServletRequest request) {

		return new GenericResponseDto.GenericResponseDtoBuilder<>(request, userService.getUser(userId)).build();
	}

	@PostMapping("/addUser")
	public GenericResponseDto<Users> addUser(@RequestBody Users user, HttpServletRequest request) throws Exception {
		return new GenericResponseDto.GenericResponseDtoBuilder<>("User saved sucessfully", request,
				userService.saveUser(user)).build();
	}
	
	@PostMapping("/addUserRole")
	public GenericResponseDto<List<ModuleConfigMapping>> addUserRole(@RequestBody AddUserRoleDto userRole, HttpServletRequest request) throws Exception {
		return new GenericResponseDto.GenericResponseDtoBuilder<>("User roles saved sucessfully", request,
				userService.addUserRole(userRole)).build();
	}

	@PutMapping("/updateUser")
	public GenericResponseDto<Users> updateUser(@RequestBody Users user, HttpServletRequest request) throws Exception {
		return new GenericResponseDto.GenericResponseDtoBuilder<>("User updated sucessfully", request,
				userService.updateUser(user)).build();
	}

	@DeleteMapping("/deleteUser")
	public GenericResponseDto<Boolean> deleteUser(@RequestParam Long userId, HttpServletRequest request) {
		userService.deleteUser(userId);
		return new GenericResponseDto.GenericResponseDtoBuilder<>("User deleted sucessfully", request, true).build();
	}

	@GetMapping("/getProfile")
	public GenericResponseDto<UsersProfileDto> getProfile(@RequestParam Long userId, HttpServletRequest request) {
		return new GenericResponseDto.GenericResponseDtoBuilder<>(request, userService.getProfile(userId)).build();
	}

}
