package br.com.cloudmessage.cloudmessage.dto;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class NotificationDto implements Serializable {
	private Notification notification;

	public NotificationDto(Notification notification) {
		this.notification = notification;
	}

	public String serialize() {
		return new Gson().toJson(this);
	}

	@Getter
	@Setter
	public static class NotificationAction {
		private String action;
		private String title;
	}

	@Getter
	@Setter
	public static class Dir {
		String auto = "auto";
		String ltr = "ltr";
		String rtl = "rtl";
	}

	@Getter
	@Setter
	public static class Notification {
		private String icon;
		private String title;
		private String body;
		private String badge;
		private Object data;
		private Dir dir;
		private String image;
		private String lang;
		private Boolean renotify;
		private Boolean requireInteraction;
		private Boolean silent;
		private String tag;
		private String timestamp;
		private Integer[] vibrate;
		private NotificationAction[] actions;
	}
}
