package org.cisco.asbp_role_responsibilites.repository;

import java.util.List;

import org.cisco.asbp_role_responsibilites.entity.ModuleConfigMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuleConfigMappingRepository extends JpaRepository<ModuleConfigMapping, Long> {
	
	List<ModuleConfigMapping> findByRoleIdIn(List<Long> roleIds); 

}
