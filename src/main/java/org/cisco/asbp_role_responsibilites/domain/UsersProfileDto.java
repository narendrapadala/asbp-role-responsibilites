package org.cisco.asbp_role_responsibilites.domain;

import java.util.List;

import org.cisco.asbp_role_responsibilites.entity.ModuleConfigMapping;
import org.cisco.asbp_role_responsibilites.entity.Users;

import lombok.Data;

@Data
public class UsersProfileDto {

	private Users user;
	
	//private List<Role> roles;
	
	//private List<Module> modules;
	
	//private List<ModuleFeature> moduleFeatures;
	
	private List<ModuleConfigMapping> moduleConfigMapping;
	
}
