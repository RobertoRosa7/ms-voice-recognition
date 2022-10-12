package br.com.cloudmessage.cloudmessage.controller;

import br.com.cloudmessage.cloudmessage.dto.PublicKeyDto;
import br.com.cloudmessage.cloudmessage.endpoint.MessageEndpoint;
import br.com.cloudmessage.cloudmessage.service.MessageService;
import nl.martijndwars.webpush.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(MessageEndpoint.notification)
public class PushMessageController {

	@Autowired
	private MessageService messageService;

	@GetMapping(value = "/request-publickey", produces = "application/json")
	public ResponseEntity<PublicKeyDto> subscription() {
		String publicKey = this.messageService.getPublicKey();

		return ResponseEntity.ok(new PublicKeyDto(publicKey));
	}

	@PostMapping(value = "/subscribe")
	public ResponseEntity<HttpStatus> subscribe(@RequestBody Subscription sub) {
		this.messageService.subscribe(sub);
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@PostMapping(value = "/unsubscribe")
	public ResponseEntity<HttpStatus> unsubscribe(@RequestBody Subscription sub) {
		this.messageService.unsubscribe(sub.endpoint);
		return ResponseEntity.ok(HttpStatus.OK);
	}
}
