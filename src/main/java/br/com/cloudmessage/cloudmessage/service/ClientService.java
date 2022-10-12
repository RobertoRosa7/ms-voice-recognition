package br.com.cloudmessage.cloudmessage.service;

import br.com.cloudmessage.cloudmessage.document.ClientDoc;
import br.com.cloudmessage.cloudmessage.respository.ClientsRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

	@Autowired
	private ClientsRepository clientsRepository;

	public List<ClientDoc> getAll() {
		return this.clientsRepository.findAll();
	}

	public ClientDoc getClientById(ObjectId id) {
		return this.clientsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Client not found"));
	}
}
