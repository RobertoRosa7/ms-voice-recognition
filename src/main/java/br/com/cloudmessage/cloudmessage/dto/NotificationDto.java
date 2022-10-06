package br.com.cloudmessage.cloudmessage.dto;

import com.google.gson.Gson;

import java.io.Serializable;


public class NotificationDto implements Serializable {
	private Notification notification;

	public NotificationDto(String title, String body) {
		this.notification = new Notification(title, body);
	}

	public String serialize() {
		return new Gson().toJson(this);
	}

	public static class NotificationAction {
		private String action;
		private String title;
	}

	public static class Dir {
		String auto = "auto";
		String ltr = "ltr";
		String rtl = "rtl";
	}

	public static class Notification {
		private String title = "teste";
		private String body = "teste";
		private String badge;
		private Object data;
		private Dir dir;
		private String icon;
		private String image;
		private String lang;
		private Boolean renotify;
		private Boolean requireInteraction;
		private Boolean silent;
		private String tag;
		private String timestamp;
		private Integer[] vibrate;
		private NotificationAction[] actions;

		public Notification(String title, String body) {
			this.title = title;
			this.body = body;
		}
	}
}
