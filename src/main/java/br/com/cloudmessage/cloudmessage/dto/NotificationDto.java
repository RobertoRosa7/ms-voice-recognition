package br.com.cloudmessage.cloudmessage.dto;

import com.google.gson.Gson;

import java.io.Serializable;


public class NotificationDto implements Serializable {
	private Notification notification;

	public NotificationDto(String title, String body, String icon) {
		this.notification = new Notification(title, body, icon);
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
		private String title;
		private String body;
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

		public Notification(String title, String body, String icon) {
			this.title = title;
			this.body = body;
			this.icon = icon;
		}
	}
}
