package org.cisco.asbp_role_responsibilites.repository;

import org.cisco.asbp_role_responsibilites.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

	Client findFirstById(Long clientId);

}
