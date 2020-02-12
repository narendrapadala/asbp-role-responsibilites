package org.cisco.asbp_role_responsibilites.repository;

import org.cisco.asbp_role_responsibilites.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

	Users findFirstById(Long userId);

}
