package org.cisco.asbp_role_responsibilites.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.cisco.asbp_role_responsibilites.domain.AddUserRoleDto;
import org.cisco.asbp_role_responsibilites.domain.UsersProfileDto;
import org.cisco.asbp_role_responsibilites.entity.ModuleConfigMapping;
import org.cisco.asbp_role_responsibilites.entity.Role;
import org.cisco.asbp_role_responsibilites.entity.UserRoleMapping;
import org.cisco.asbp_role_responsibilites.entity.Users;
import org.cisco.asbp_role_responsibilites.exception.ASBPException;
import org.cisco.asbp_role_responsibilites.repository.ModuleConfigMappingRepository;
import org.cisco.asbp_role_responsibilites.repository.RoleRepository;
import org.cisco.asbp_role_responsibilites.repository.UserRepository;
import org.cisco.asbp_role_responsibilites.repository.UserRoleMappingRepository;
import org.cisco.asbp_role_responsibilites.service.RolesService;
import org.cisco.asbp_role_responsibilites.service.UserService;
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
@SuppressWarnings("unused")
public class UserServiceImpl implements UserService {

	private static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private RoleRepository roleRepo;

	@Autowired
	private RolesService rolesService;

	@Autowired
	private UserRoleMappingRepository userRoleMappingRepo;

	@Autowired
	ModuleConfigMappingRepository moduleConfigMappingRepo;

	@Override
	public void deleteUser(Long userId) {
		userRepo.deleteById(userId);
	}

	@Override
	public Users saveUser(Users user) throws ASBPException {
		user.setCreatedAt(System.currentTimeMillis());
		user.setCreatedBy(1L);
		user.setModifiedBy(1L);
		user.setModifiedAt(System.currentTimeMillis());
		return userRepo.save(user);
	}

	@Override
	public Page<Users> getAllUsers(Pageable pageable) {
		return userRepo.findAll(pageable);
	}

	@Override
	public Users getUser(Long userId) {
		return userRepo.findFirstById(userId);
	}

	@Override
	public Users updateUser(Users user) throws Exception {
		Users usr = userRepo.findById(user.getId())
				.orElseThrow(() -> new Exception("user with id " + user.getId() + " doesn't exist"));
		usr.setModifiedBy(1L);
		usr.setModifiedAt(System.currentTimeMillis());
		return userRepo.save(usr);
	}

	// user role mapping
	@Override
	public UserRoleMapping saveUserRoleMapping(UserRoleMapping userRoleMapping) throws ASBPException {

		Users usr = userRepo.findById(userRoleMapping.getId().getUserId()).orElseThrow(
				() -> new ASBPException("user with id " + userRoleMapping.getId().getUserId() + " doesn't exist"));

		Role r = roleRepo.findById(userRoleMapping.getId().getRoleId()).orElseThrow(
				() -> new ASBPException("role with id " + userRoleMapping.getId().getRoleId() + " doesn't exist"));

		userRoleMapping.setCreatedAt(System.currentTimeMillis());
		userRoleMapping.setCreatedBy(1L);
		userRoleMapping.setModifiedBy(1L);
		userRoleMapping.setModifiedAt(System.currentTimeMillis());

		return userRoleMappingRepo.save(userRoleMapping);
	}

	@Override
	public UserRoleMapping updateUserRoleMapping(UserRoleMapping userRoleMapping) throws Exception {
		Users usr = userRepo.findById(userRoleMapping.getId().getUserId()).orElseThrow(
				() -> new Exception("user with id " + userRoleMapping.getId().getUserId() + " doesn't exist"));

		Role r = roleRepo.findById(userRoleMapping.getId().getRoleId()).orElseThrow(
				() -> new Exception("role with id " + userRoleMapping.getId().getRoleId() + " doesn't exist"));

		userRoleMapping.setModifiedBy(1L);
		userRoleMapping.setModifiedAt(System.currentTimeMillis());

		return userRoleMappingRepo.save(userRoleMapping);
	}

	@Override
	public void deleteRoleMappingByUser(Long userId) {
		// TODO Auto-generated method stub
		userRoleMappingRepo.deleteByIdUserId(userId);

	}

	@Override
	public UsersProfileDto getProfile(Long userId) {

		UsersProfileDto userProfile = new UsersProfileDto();
		userProfile.setUser(getUser(userId));
		userProfile.setModuleConfigMapping(getUserRoles(userId));
		return userProfile;

	}

	@Override
	public List<ModuleConfigMapping> getUserRoles(Long userId) {

		List<UserRoleMapping> usrMap = userRoleMappingRepo.findAllByIdUserId(userId);

		List<Long> roleIds = new ArrayList<Long>();

		if (usrMap != null) {
			for (UserRoleMapping urm : usrMap) {
				//
				roleIds.add(urm.getId().getRoleId());
			}
			// check
			if (roleIds.size() > 0) {
				List<ModuleConfigMapping> mcMap = moduleConfigMappingRepo.findByRoleIdIn(roleIds);

				return mcMap;
			}
		}
		return null;
	}

	@Override
	public List<ModuleConfigMapping> addUserRole(AddUserRoleDto userRole) {

		// check
		if (userRole.getRolesIds().size() > 0) {
			List<UserRoleMapping> usrMap = userRoleMappingRepo.findAllByIdUserId(userRole.getUserId());
			// check
			if (usrMap != null) {
				deleteRoleMappingByUser(userRole.getUserId());
			}
			// loop
			for (Long roleId : userRole.getRolesIds()) {

				Users usr = new Users();
				usr.setId(userRole.getUserId());
				Role role = new Role();
				role.setId(roleId);
				UserRoleMapping urm = new UserRoleMapping(usr, role);
				saveUserRoleMapping(urm);
			}

			return moduleConfigMappingRepo.findByRoleIdIn(userRole.getRolesIds());
		}
		return null;
	}

}
