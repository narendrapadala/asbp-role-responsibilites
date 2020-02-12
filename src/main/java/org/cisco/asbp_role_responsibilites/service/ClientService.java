/**
 * 
 */
package org.cisco.asbp_role_responsibilites.service;

import org.cisco.asbp_role_responsibilites.entity.Client;
import org.cisco.asbp_role_responsibilites.exception.ASBPException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


/**
 * @author Narendra
 *
 */
public interface ClientService {

	Client saveClient(Client client) throws ASBPException;

	Client updateClient(Client client) throws Exception;

	void deleteClient(Long clientId);

	Page<Client> getAllClients(Pageable pageable);

	Client getClient(Long clientId);
}
