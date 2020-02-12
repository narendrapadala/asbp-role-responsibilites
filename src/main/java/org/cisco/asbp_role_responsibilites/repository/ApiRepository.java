package org.cisco.asbp_role_responsibilites.repository;

import java.util.List;

import org.cisco.asbp_role_responsibilites.entity.Api;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiRepository extends JpaRepository<Api, Long> {
	
	public List<Api> findByName(String name);
	
	public Api findByNameAndType(String name, HttpMethod type);
	
	public Api findByNameLikeAndType(String name, HttpMethod type);

	public Api findFirstById(Long apiId);
}
