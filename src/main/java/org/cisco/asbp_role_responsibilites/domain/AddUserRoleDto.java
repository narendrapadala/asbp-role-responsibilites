package org.cisco.asbp_role_responsibilites.domain;

import java.util.List;

import lombok.Data;

@Data
public class AddUserRoleDto {

	private Long userId;
	
	private List<Long> rolesIds;
}
