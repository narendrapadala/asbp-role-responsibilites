package org.cisco.asbp_role_responsibilites.repository;

import org.cisco.asbp_role_responsibilites.entity.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long> {

	Module findFirstById(Long moduleId);

}
