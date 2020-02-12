package org.cisco.asbp_role_responsibilites.repository;

import org.cisco.asbp_role_responsibilites.entity.ModuleFeature;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuleFeatureRepository extends JpaRepository<ModuleFeature, Long> {

	ModuleFeature findFirstById(Long moduleFeatureId);

	Page<ModuleFeature> findAllByModuleId(Long moduleId, Pageable pageable);
	
	
}
