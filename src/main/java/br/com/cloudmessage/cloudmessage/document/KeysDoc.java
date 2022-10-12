package br.com.cloudmessage.cloudmessage.document;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KeysDoc {
	private String p256dh;
	private String auth;

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
}
