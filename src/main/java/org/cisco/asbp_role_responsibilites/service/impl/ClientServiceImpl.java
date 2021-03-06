package org.cisco.asbp_role_responsibilites.service.impl;
import javax.transaction.Transactional;

import org.cisco.asbp_role_responsibilites.entity.Client;
import org.cisco.asbp_role_responsibilites.exception.ASBPException;
import org.cisco.asbp_role_responsibilites.repository.ClientRepository;
import org.cisco.asbp_role_responsibilites.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author Narendra
 *
 */
@Service
@Transactional
public class ClientServiceImpl implements ClientService {

	private static Logger log = LoggerFactory.getLogger(ClientServiceImpl.class);
	@Autowired
	private ClientRepository clientRepo;

	@Override
	public Client saveClient(Client client) throws ASBPException {		
		client.setCreatedAt(System.currentTimeMillis());
		client.setCreatedBy(1L);
		client.setModifiedBy(1L);
		client.setModifiedAt(System.currentTimeMillis());
		//return
		return clientRepo.save(client);
	}

	@Override
	public Client updateClient(Client client) throws Exception {
		Client cli = clientRepo.findById(client.getId())
				.orElseThrow(() -> new Exception("Client with id " + client.getId() + " doesn't exist"));
		cli.setModifiedBy(1L);
		cli.setModifiedAt(System.currentTimeMillis());		
		//return
		return clientRepo.save(cli);
	}

	@Override
	public void deleteClient(Long clientId) {
		clientRepo.deleteById(clientId);
		
	}

	@Override
	public Page<Client> getAllClients(Pageable pageable) {
			return clientRepo.findAll(pageable);
	}

	@Override
	public Client getClient(Long clientId) {
		// return
		return clientRepo.findFirstById(clientId);
	}

}
