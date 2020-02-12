package org.cisco.asbp_role_responsibilites.service.impl;

import java.util.List;

import javax.transaction.Transactional;
import org.apache.commons.lang.StringUtils;
import org.cisco.asbp_common_utils.cu.model.CommonStatus;
import org.cisco.asbp_role_responsibilites.entity.ModuleConfigMapping;
import org.cisco.asbp_role_responsibilites.entity.Role;
import org.cisco.asbp_role_responsibilites.entity.RoleCategory;
import org.cisco.asbp_role_responsibilites.exception.ASBPException;
import org.cisco.asbp_role_responsibilites.repository.ModuleConfigMappingRepository;
import org.cisco.asbp_role_responsibilites.repository.RoleCategoryRepository;
import org.cisco.asbp_role_responsibilites.repository.RoleRepository;

import org.cisco.asbp_role_responsibilites.service.RolesService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author Narendra
 *
 */
@Service
@Transactional
public class RoleServiceImpl implements RolesService {

	@SuppressWarnings("unused")
	private static Logger log = LoggerFactory.getLogger(RoleServiceImpl.class);
	@Autowired
	private RoleRepository roleRepo;

	@Autowired
	private RoleCategoryRepository roleCategoryRepo;
	
	@Autowired
	private ModuleConfigMappingRepository moduleConfigMappingRepo;

	@SuppressWarnings("unused")
	@Override
	public Role saveRole(Role api) throws ASBPException {

		RoleCategory role = roleCategoryRepo.findById(api.getRoleCategory().getId()).orElseThrow(
				() -> new ASBPException("role category with id " + api.getRoleCategory() + " doesn't exist"));

		api.setCreatedAt(System.currentTimeMillis());
		api.setCreatedBy(1L);
		api.setModifiedBy(1L);
		api.setModifiedAt(System.currentTimeMillis());
		api.setStatus(CommonStatus.ENABLED);
		return roleRepo.save(api);
	}

	@Override
	public Role updateRole(Role api) throws Exception {
		Role ex_api = roleRepo.findById(api.getId())
				.orElseThrow(() -> new Exception("role with id " + api.getId() + " doesn't exist"));

		@SuppressWarnings("unused")
		RoleCategory role = roleCategoryRepo.findById(api.getRoleCategory().getId())
				.orElseThrow(() -> new Exception("role category with id " + api.getRoleCategory() + " doesn't exist"));

		ex_api.setModifiedBy(1L);
		ex_api.setModifiedAt(System.currentTimeMillis());
		return roleRepo.save(ex_api);
	}

	@Override
	public void deleteRole(Long roleId) {
		roleRepo.deleteById(roleId);

	}

	@Override
	public Page<Role> getAllRoles(String searchColumn, String searchValue, Pageable pageable) {
		if (StringUtils.isEmpty(searchColumn)) {
			return roleRepo.findAll(pageable);
		} else {
			// Specification.where(getFilterQuery(searchColumn, searchValue)),
			return roleRepo.findAll(pageable);
		}
	}

	@Override
	public Role getRole(Long roleId) {
		return roleRepo.findFirstById(roleId);
	}

	@Override
	public Page<RoleCategory> getAllRoleCategories(Pageable pageable) {
		return roleCategoryRepo.findAll(pageable);
	}

	@Override
	public RoleCategory saveRoleCategory(RoleCategory roleCategory) throws ASBPException {
		return roleCategoryRepo.save(roleCategory);
	}

	@Override
	public RoleCategory updateRoleCategory(RoleCategory roleCategory) throws Exception {
		RoleCategory roleCat = roleCategoryRepo.findById(roleCategory.getId())
				.orElseThrow(() -> new Exception("Role category with id " + roleCategory.getId() + " doesn't exist"));

		roleCat.setModifiedBy(1L);
		roleCat.setModifiedAt(System.currentTimeMillis());
		return roleCategoryRepo.save(roleCat);

	}

	@Override
	public void deleteRoleCategory(Long roleCatId) {
		roleCategoryRepo.deleteById(roleCatId);
	}

	@Override
	public RoleCategory getRoleCategory(Long roleCatId) {
		// return
		return roleCategoryRepo.findFirstById(roleCatId);
	}

	@Override
	public List<ModuleConfigMapping> getUserRolesModules(Long roleId,Long clientId) {
		return null;
	}

}
