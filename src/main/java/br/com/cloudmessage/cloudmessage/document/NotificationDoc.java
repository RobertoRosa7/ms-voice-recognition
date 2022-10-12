package br.com.cloudmessage.cloudmessage.document;

import com.google.gson.Gson;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("notifications")
public class NotificationDoc {

	@Id
	private String _id;
	private ObjectId client_id;
	private String endpoint;
	private Integer expirationTime;
	private KeysDoc keys;

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
}
