package br.com.cloudmessage.cloudmessage.dto;

import br.com.cloudmessage.cloudmessage.model.KeysModel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class SubscriptionDto {
	private String endpoint;
	private Integer expirationTime;
	private KeysModel keys;
}
