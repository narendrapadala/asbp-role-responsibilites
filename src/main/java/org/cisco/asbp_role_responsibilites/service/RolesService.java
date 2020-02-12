/**
 * 
 */
package org.cisco.asbp_role_responsibilites.service;

import java.util.List;

import org.cisco.asbp_role_responsibilites.domain.AssignRolesAndResponsibilitesDto;
import org.cisco.asbp_role_responsibilites.domain.RoleAndResponsibilityMappingDto;
import org.cisco.asbp_role_responsibilites.entity.ModuleConfigMapping;
import org.cisco.asbp_role_responsibilites.entity.Role;
import org.cisco.asbp_role_responsibilites.entity.RoleCategory;
import org.cisco.asbp_role_responsibilites.exception.ASBPException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



/**
 * @author Narendra
 *
 */
public interface RolesService {

	Role saveRole(Role role) throws ASBPException;

	Role updateRole(Role role) throws Exception;

	void deleteRole(Long roleId);
	
	Role getRole(Long roleId);

	Page<Role> getAllRoles(String searchColumn, String searchValue, Pageable pageable);
	
	Page<RoleCategory> getAllRoleCategories(Pageable pageable);
	
	RoleCategory saveRoleCategory(RoleCategory roleCategory) throws ASBPException;

	RoleCategory updateRoleCategory(RoleCategory roleCategory) throws Exception;

	void deleteRoleCategory(Long roleId);
	
	RoleAndResponsibilityMappingDto getRoleAndResponsibilities();
	
	RoleCategory getRoleCategory(Long roleId);
	
	List<ModuleConfigMapping> getUserRolesModules(Long roleId,Long clientId);
	
	List<ModuleConfigMapping> addRolesAndResponsibilities(AssignRolesAndResponsibilitesDto assinRoles);
	
	
	



}
