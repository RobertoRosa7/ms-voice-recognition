package br.com.cloudmessage.cloudmessage.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PublicKeyDto {
	private String publicKey;

	public PublicKeyDto(String publicKey) {
		this.publicKey = publicKey;
	}
}
