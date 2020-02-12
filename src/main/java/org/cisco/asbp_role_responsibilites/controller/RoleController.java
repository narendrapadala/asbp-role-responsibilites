package org.cisco.asbp_role_responsibilites.controller;

import javax.servlet.http.HttpServletRequest;

import org.cisco.asbp_common_utils.cu.model.GenericResponseDto;
import org.cisco.asbp_role_responsibilites.entity.Role;
import org.cisco.asbp_role_responsibilites.entity.RoleCategory;
import org.cisco.asbp_role_responsibilites.service.RolesService;
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
@RequestMapping("/roles")
public class RoleController {

	private RolesService rolesService;

	@Autowired
	public void setRolesService(RolesService rolesService) {
		this.rolesService = rolesService;
	}

	@GetMapping("/all")
	public GenericResponseDto<Page<Role>> getAllroles(@RequestParam(required = false) String searchColumn,
			@RequestParam(required = false) String searchValue, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, HttpServletRequest request) {
		return new GenericResponseDto.GenericResponseDtoBuilder<>(request,
				rolesService.getAllRoles(searchColumn, searchValue, PageRequest.of(page, size))).build();
	}

	@GetMapping("/getRole")
	public GenericResponseDto<Role> getRole(@RequestParam Long roleId, HttpServletRequest request) {
		return new GenericResponseDto.GenericResponseDtoBuilder<>(request, rolesService.getRole(roleId)).build();
	}

	@PostMapping("/addRole")
	public GenericResponseDto<Role> addRole(@RequestBody Role role, HttpServletRequest request) throws Exception {
		return new GenericResponseDto.GenericResponseDtoBuilder<>("Role saved sucessfully", request,
				rolesService.saveRole(role)).build();
	}

	@PutMapping("/updateRole")
	public GenericResponseDto<Role> updateRole(@RequestBody Role role, HttpServletRequest request) throws Exception {
		return new GenericResponseDto.GenericResponseDtoBuilder<>("Role updated sucessfully", request,
				rolesService.updateRole(role)).build();
	}

	@DeleteMapping("/deleteRole")
	public GenericResponseDto<Boolean> deleteRole(@RequestParam Long roleId, HttpServletRequest request) {
		rolesService.deleteRole(roleId);
		return new GenericResponseDto.GenericResponseDtoBuilder<>("Role deleted sucessfully", request, true).build();
	}

	@GetMapping("/allRoleCategories")
	public GenericResponseDto<Page<RoleCategory>> getAllRoleCategories(
			@RequestParam(required = false) String searchColumn, @RequestParam(required = false) String searchValue,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
			HttpServletRequest request) {
		return new GenericResponseDto.GenericResponseDtoBuilder<>(request,
				rolesService.getAllRoleCategories(PageRequest.of(page, size))).build();
	}

	@GetMapping("/getRoleCategory")
	public GenericResponseDto<RoleCategory> getRoleCategory(@RequestParam Long roleCategoryId, HttpServletRequest request) {
		return new GenericResponseDto.GenericResponseDtoBuilder<>(request, rolesService.getRoleCategory(roleCategoryId))
				.build();
	}

	@PostMapping("/addRoleCategory")
	public GenericResponseDto<RoleCategory> addRole(@RequestBody RoleCategory role, HttpServletRequest request)
			throws Exception {
		return new GenericResponseDto.GenericResponseDtoBuilder<>("Role category saved sucessfully", request,
				rolesService.saveRoleCategory(role)).build();
	}

	@PutMapping("/updateRoleCategory")
	public GenericResponseDto<RoleCategory> updateRole(@RequestBody RoleCategory role, HttpServletRequest request)
			throws Exception {
		return new GenericResponseDto.GenericResponseDtoBuilder<>("Role category updated sucessfully", request,
				rolesService.updateRoleCategory(role)).build();
	}

	@DeleteMapping("/deleteRoleCategory")
	public GenericResponseDto<Boolean> deleteRoleCategory(@RequestParam Long roleCategoryd, HttpServletRequest request) {
		rolesService.deleteRoleCategory(roleCategoryd);
		return new GenericResponseDto.GenericResponseDtoBuilder<>("Role category deleted sucessfully", request, true).build();
	}

}
