package br.com.cloudmessage.cloudmessage.controller;

import br.com.cloudmessage.cloudmessage.document.NotificationDoc;
import br.com.cloudmessage.cloudmessage.dto.PublicKeyDto;
import br.com.cloudmessage.cloudmessage.endpoint.MessageEndpoint;
import br.com.cloudmessage.cloudmessage.service.NotificationService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(MessageEndpoint.notification)
public class PushMessageController {

	@Autowired
	private NotificationService notificationService;


	@GetMapping(value = "/request-publickey", produces = "application/json")
	public ResponseEntity<PublicKeyDto> subscription() {
		String publicKey = this.notificationService.getPublicKey();
		return ResponseEntity.ok(new PublicKeyDto(publicKey));
	}

	@PostMapping(value = "/subscribe")
	public ResponseEntity<HttpStatus> subscribe(@RequestBody NotificationDoc body) {
		this.notificationService.subscribe(body);
//		NotificationDto notificationDto = new NotificationDto(getNotification());
//		Subscription.Keys keys = new Subscription.Keys(body.getKeys().getP256dh(), body.getKeys().getAuth());
//		Subscription subscribe = new Subscription(body.getEndpoint(), keys);
//		this.notificationService.sendNotification(subscribe, notificationDto.serialize());
		this.notificationService.sendMessage(body, "welcome");
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@PostMapping(value = "/unsubscribe")
	public ResponseEntity<HttpStatus> unsubscribe(@RequestBody NotificationDoc body) throws Exception {
		this.notificationService.unsubscribe(body);


		this.notificationService.sendMessage(body, "goodbye");

		return ResponseEntity.ok(HttpStatus.OK);
	}

	@GetMapping(value = "")
	public ResponseEntity<HttpStatus> findById(@RequestParam String id) {

		if (id == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "client id is required");
		}

		NotificationDoc doc = this.notificationService.findByClientId(new ObjectId(id));

		if (doc == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found");
		}

//		NotificationDto notificationDto = new NotificationDto(getNotification());
//		Subscription.Keys keys = new Subscription.Keys(client.getKeys().getP256dh(), client.getKeys().getAuth());
//		Subscription subscribe = new Subscription(client.getEndpoint(), keys);
//		this.notificationService.sendNotification(subscribe, notificationDto.serialize());
		this.notificationService.sendMessage(doc, "welcome");

		return ResponseEntity.ok(HttpStatus.OK);
	}


}
