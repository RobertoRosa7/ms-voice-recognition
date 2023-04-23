package br.com.cloudmessage.cloudmessage.model;

import br.com.cloudmessage.cloudmessage.dto.NotificationDto;


public interface TemplateMessage {
	NotificationDto.Notification execute(ClientModel client);
}
