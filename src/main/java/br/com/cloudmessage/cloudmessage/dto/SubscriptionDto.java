package br.com.cloudmessage.cloudmessage.dto;

import br.com.cloudmessage.cloudmessage.document.KeysDoc;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class SubscriptionDto {
	private String endpoint;
	private Integer expirationTime;
	private KeysDoc keys;
}
