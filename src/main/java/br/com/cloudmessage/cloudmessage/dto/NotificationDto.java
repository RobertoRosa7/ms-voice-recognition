package br.com.cloudmessage.cloudmessage.dto;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;

@Getter
@Setter
public class NotificationDto implements Serializable {
	private Notification notification;
	@Value("${image.path.default}")
	private String imagePathDefault;

	public NotificationDto(String title, String body, String icon) {
		this.notification = new Notification();
		System.out.println(this.imagePathDefault);
		this.notification.setIcon(this.imagePathDefault);
		this.notification.setTitle(title);
		this.notification.setBody(body);
	}

	public String serialize() {
		return new Gson().toJson(this);
	}

	public class NotificationAction {
		private String action;
		private String title;
	}

	public class Dir {
		String auto = "auto";
		String ltr = "ltr";
		String rtl = "rtl";
	}

	@Getter
	@Setter
	public class Notification {
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
