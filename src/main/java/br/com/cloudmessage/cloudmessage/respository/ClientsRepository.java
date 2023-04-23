package br.com.cloudmessage.cloudmessage.respository;

import br.com.cloudmessage.cloudmessage.model.ClientModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientsRepository extends JpaRepository<ClientModel, Long> {
}
