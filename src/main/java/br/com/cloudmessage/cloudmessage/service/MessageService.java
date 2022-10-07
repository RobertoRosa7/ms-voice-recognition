package br.com.cloudmessage.cloudmessage.service;

import br.com.cloudmessage.cloudmessage.dto.NotificationDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.martijndwars.webpush.Notification;
import nl.martijndwars.webpush.PushService;
import nl.martijndwars.webpush.Subscription;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.jose4j.lang.JoseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.Security;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class MessageService {

	private final ObjectMapper mapper = new ObjectMapper();

	@Value("${vapid.public.key}")
	private String publicKey;

	@Value("${vapid.private.key}")
	private String privateKey;

	private String icon;

	private PushService pushService;
	private List<Subscription> subscriptions = new ArrayList<>();

	@PostConstruct // used when call service
	private void init() throws GeneralSecurityException {
		Security.addProvider(new BouncyCastleProvider());
		pushService = new PushService(publicKey, privateKey);
	}

	public String getPublicKey() {
		return publicKey;
	}

	public void subscribe(Subscription subscription) {
		System.out.println("Subscribed to " + subscription.endpoint);
		this.subscriptions.add(subscription);
	}

	public void unsubscribe(String endpoint) {
		System.out.println("Unsubscribe from " + endpoint);
		subscriptions = subscriptions.stream().filter(s -> !endpoint.equals(s.endpoint)).collect(Collectors.toList());
	}

	public void sendNotification(Subscription subscription, String messageJson) {
		try {
			pushService.send(new Notification(subscription, messageJson));
		} catch (GeneralSecurityException | IOException | JoseException | ExecutionException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Scheduled(fixedRate = 30000)
	private void sendNotifications() {
		System.out.println("Sending notifications to all subscribers: " + this.subscriptions.size());
		NotificationDto notificationDto = new NotificationDto(
			"Roberto",
			"Teste de Mensagem",
			this.icon
		);

		System.out.println(notificationDto.serialize());

		subscriptions.forEach(subscription -> sendNotification(subscription, notificationDto.serialize()));
	}
}
