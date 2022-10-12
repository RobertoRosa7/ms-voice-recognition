package br.com.cloudmessage.cloudmessage.document;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalTime;
import java.util.Arrays;

@Data
@Document("clients")
public class ClientDoc {

	@Id
	private ObjectId _id;
	private String name;
	private String email;
	private String password;
	private String token;
	private Integer[] phone;
	private String photo;
	private String date_birth;
	private String gender;
	private Boolean terms;
	private Boolean is_contributor;
	private Boolean has_biometric;
	private Boolean use_biometric;
	private Boolean notification;
	private Boolean verified;
	private Boolean activated;
	private Documents documents;
	private Address address;
	private LocalTime data_register;
	private String customer_id;
	private String recipient_id;

	@Override
	public String toString() {
		return "ClientDoc{" +
			"_id=" + _id +
			", name='" + name + '\'' +
			", email='" + email + '\'' +
			", password='" + password + '\'' +
			", token='" + token + '\'' +
			", phone=" + Arrays.toString(phone) +
			", photo='" + photo + '\'' +
			", date_birth='" + date_birth + '\'' +
			", gender='" + gender + '\'' +
			", terms=" + terms +
			", is_contributor=" + is_contributor +
			", has_biometric=" + has_biometric +
			", use_biometric=" + use_biometric +
			", notification=" + notification +
			", verified=" + verified +
			", activated=" + activated +
			", documents=" + documents +
			", address=" + address +
			", data_register=" + data_register +
			", customer_id='" + customer_id + '\'' +
			", recipient_id='" + recipient_id + '\'' +
			'}';
	}

	public static class Documents {
		private String type;
		private String number;
	}

	public static class Address {
		private String city;
		private String street;
		private String state;
		private String country;
		private String zipcode;
		private String street_number;
		private String neighborhood;
	}
}
