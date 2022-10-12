package br.com.cloudmessage.cloudmessage.service;

import br.com.cloudmessage.cloudmessage.document.ClientDoc;
import br.com.cloudmessage.cloudmessage.document.KeysDoc;
import br.com.cloudmessage.cloudmessage.document.NotificationDoc;
import br.com.cloudmessage.cloudmessage.dto.NotificationDto;
import br.com.cloudmessage.cloudmessage.model.TemplateMessage;
import br.com.cloudmessage.cloudmessage.respository.NotificationRepository;
import nl.martijndwars.webpush.Notification;
import nl.martijndwars.webpush.PushService;
import nl.martijndwars.webpush.Subscription;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bson.types.ObjectId;
import org.jetbrains.annotations.NotNull;
import org.jose4j.lang.JoseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.Security;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;


@Service
public class NotificationService {
	private final Map<String, TemplateMessage> exec = new HashMap<>();

	@Autowired
	NotificationRepository notificationRepository;

	@Autowired
	ClientService clientService;

	@Value("${vapid.public.key}")
	private String publicKey;

	@Value("${vapid.private.key}")
	private String privateKey;

	private PushService pushService;

	@NotNull
	private static List<NotificationDto.NotificationAction> getNotificationActions() {
		List<NotificationDto.NotificationAction> actionList = new ArrayList<>();

		NotificationDto.NotificationAction action = new NotificationDto.NotificationAction();
		action.setTitle("Excluir");
		action.setAction("delete");

		NotificationDto.NotificationAction action2 = new NotificationDto.NotificationAction();
		action2.setTitle("Confirmar");
		action2.setAction("confirm");

		actionList.add(action);
		actionList.add(action2);
		return actionList;
	}

	public NotificationDto.Notification messageWelcome(@NotNull ClientDoc client) {
		NotificationDto.Notification notification = new NotificationDto.Notification();
		NotificationDto.NotificationAction[] actions = this.convert(getNotificationActions());

		notification.setTitle("Welcome " + client.getName());
		notification.setBody("test body");
		notification.setIcon("http://localhost:8081/file/image/kakashi.jpg");
		notification.setActions(actions);

		return notification;
	}

	public NotificationDto.Notification messageGoodBye(@NotNull ClientDoc client) {
		NotificationDto.Notification notification = new NotificationDto.Notification();

		notification.setTitle("Good Bye " + client.getName());
		notification.setBody("test body");
		notification.setIcon("http://localhost:8081/file/image/kakashi.jpg");

		return notification;
	}

	@PostConstruct // used when call service
	private void init() throws GeneralSecurityException {
		Security.addProvider(new BouncyCastleProvider());
		pushService = new PushService(publicKey, privateKey);

		exec.put("welcome", this::messageWelcome);
		exec.put("goodbye", this::messageGoodBye);
	}

	public String getPublicKey() {
		return publicKey;
	}

	public List<NotificationDoc> getAll() {
		return this.notificationRepository.findAll();
	}

	public NotificationDoc findByClientId(ObjectId id) {
		return this.notificationRepository.findByClientId(id);
	}

	public void sendNotification(@NotNull Subscription subscription, @NotNull String messageJson) {
		try {
			pushService.send(new Notification(subscription, messageJson));
		} catch (GeneralSecurityException | IOException | JoseException | ExecutionException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void subscribe(@NotNull NotificationDoc subscription) {
		System.out.println("Subscribed to " + subscription.getEndpoint());
		NotificationDoc notificationDoc = new NotificationDoc();
		KeysDoc key = new KeysDoc();

		key.setP256dh(subscription.getKeys().getP256dh());
		key.setAuth(subscription.getKeys().getAuth());

		notificationDoc.setEndpoint(subscription.getEndpoint());
		notificationDoc.setKeys(key);
		notificationDoc.setClient_id(subscription.getClient_id());

		this.notificationRepository.save(notificationDoc);
	}

	public void unsubscribe(@NotNull NotificationDoc subscription) throws Exception {
		System.out.println("Unsubscribe from " + subscription.getEndpoint());
		NotificationDoc doc = findNotificationDocByClient(subscription);

		if (doc == null) {
			throw new Exception("Document not found");
		}

		this.notificationRepository.deleteById(doc.get_id());
	}

	private NotificationDoc findNotificationDocByClient(@NotNull NotificationDoc notificationDoc) {
		return this.notificationRepository.findByClientId(notificationDoc.getClient_id());
	}

	public void sendMessage(@NotNull NotificationDoc body, @NotNull String type) {
		ClientDoc client = this.clientService.getClientById(body.getClient_id());

		NotificationDto notificationDto = new NotificationDto(this.getNotification(client, type));
		Subscription.Keys keys = new Subscription.Keys(body.getKeys().getP256dh(), body.getKeys().getAuth());
		Subscription subscribe = new Subscription(body.getEndpoint(), keys);

		this.sendNotification(subscribe, notificationDto.serialize());
	}

	private NotificationDto.Notification getNotification(@NotNull ClientDoc client, @NotNull String type) {
		return exec.get(type).execute(client);
	}

	@NotNull
	private NotificationDto.NotificationAction[] convert(@NotNull List<NotificationDto.NotificationAction> list) {
		NotificationDto.NotificationAction[] actions = new NotificationDto.NotificationAction[list.size()];

		for (int i = 0; i < list.size(); i++) {
			NotificationDto.NotificationAction action = list.get(i);
			actions[i] = action;
		}

		return actions;
	}
}
