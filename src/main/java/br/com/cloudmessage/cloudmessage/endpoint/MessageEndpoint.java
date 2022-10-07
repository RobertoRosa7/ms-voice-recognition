package br.com.cloudmessage.cloudmessage.endpoint;

public interface MessageEndpoint {
	String notification = "/notification";
	String notificationSubscribe = "/notification/subscribe";
	String notificationUnsubscribe = "/notification/unsubscribe";
	String notificationGetPublicKey = "/notification/getPublicKey";
	String file = "/file";
}
