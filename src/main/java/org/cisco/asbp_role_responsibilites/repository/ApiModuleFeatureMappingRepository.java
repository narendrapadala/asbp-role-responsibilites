package org.cisco.asbp_role_responsibilites.repository;

import org.cisco.asbp_role_responsibilites.entity.ApiModuleFeatureMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiModuleFeatureMappingRepository extends JpaRepository<ApiModuleFeatureMapping, Long> {

}
