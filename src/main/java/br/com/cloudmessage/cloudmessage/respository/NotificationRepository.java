package br.com.cloudmessage.cloudmessage.respository;

import br.com.cloudmessage.cloudmessage.model.NotificationModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<NotificationModel, Long> {

//	@Query("{$and [{'idade': {$gt: ?0}}, {'idade': {$lt: ?1}}]}")
//	public ExampleDoc findByPeriod(Integer dtstart, Integer dtend);
}
