package org.cisco.asbp_role_responsibilites.repository;

import java.util.List;

import org.cisco.asbp_role_responsibilites.entity.UserRoleMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleMappingRepository extends JpaRepository<UserRoleMapping, Long> {
	
	List<UserRoleMapping> findAllByIdUserId(Long userId);

	void deleteByIdUserId(Long userId);
}
