package br.com.cloudmessage.cloudmessage.respository;

import br.com.cloudmessage.cloudmessage.document.NotificationDoc;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface NotificationRepository extends MongoRepository<NotificationDoc, String> {

	@Query("{client_id: ?0}")
	public NotificationDoc findByClientId(ObjectId id);


//	@Query("{$and [{'idade': {$gt: ?0}}, {'idade': {$lt: ?1}}]}")
//	public ExampleDoc findByPeriod(Integer dtstart, Integer dtend);
}
