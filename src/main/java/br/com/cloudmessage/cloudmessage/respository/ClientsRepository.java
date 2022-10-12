package br.com.cloudmessage.cloudmessage.respository;

import br.com.cloudmessage.cloudmessage.document.ClientDoc;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClientsRepository extends MongoRepository<ClientDoc, ObjectId> {
}
