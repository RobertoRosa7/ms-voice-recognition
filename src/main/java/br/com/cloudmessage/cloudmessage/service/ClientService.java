package br.com.cloudmessage.cloudmessage.service;

import br.com.cloudmessage.cloudmessage.model.ClientModel;
import br.com.cloudmessage.cloudmessage.respository.ClientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientsRepository clientsRepository;

    public List<ClientModel> getAll() {
        return this.clientsRepository.findAll();
    }

    public ClientModel getClientById(Long id) {
        return this.clientsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Client not found"));
    }
}
