/**
 * 
 */
package org.cisco.asbp_role_responsibilites.service;

import org.cisco.asbp_role_responsibilites.entity.Users;

import java.util.List;

import org.cisco.asbp_role_responsibilites.domain.AddUserRoleDto;
import org.cisco.asbp_role_responsibilites.domain.UsersProfileDto;
import org.cisco.asbp_role_responsibilites.entity.ModuleConfigMapping;
import org.cisco.asbp_role_responsibilites.entity.Role;
import org.cisco.asbp_role_responsibilites.entity.UserRoleMapping;
import org.cisco.asbp_role_responsibilites.exception.ASBPException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


/**
 * @author Narendra
 *
 */
public interface UserService {

	Users saveUser(Users user) throws ASBPException;

	Users updateUser(Users user) throws Exception;

	void deleteUser(Long userId);

	Page<Users> getAllUsers(Pageable pageable);

	Users getUser(Long userId);
	
	UsersProfileDto getProfile(Long userId);
	
	List<ModuleConfigMapping> getUserRoles(Long userId);

	
	
	//user role mapping
	UserRoleMapping saveUserRoleMapping(UserRoleMapping userRoleMapping) throws ASBPException;

	UserRoleMapping updateUserRoleMapping(UserRoleMapping userRoleMapping) throws Exception;
	
	
	
	//user role
	//List<UserRoleMapping> getRoleMappingByUser(Long userId) throws ASBPException;
	
	void deleteRoleMappingByUser(Long userId);

	List<ModuleConfigMapping> addUserRole(AddUserRoleDto userRole);


	


}
