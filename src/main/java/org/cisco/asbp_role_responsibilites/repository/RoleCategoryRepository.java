package org.cisco.asbp_role_responsibilites.repository;

import org.cisco.asbp_role_responsibilites.entity.RoleCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleCategoryRepository extends JpaRepository<RoleCategory, Long> {
	RoleCategory findFirstById(Long roleCategoryId);
}
