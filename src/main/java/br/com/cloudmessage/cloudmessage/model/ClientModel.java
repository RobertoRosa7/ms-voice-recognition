package br.com.cloudmessage.cloudmessage.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.Arrays;

@Data
@Entity
@Table(name = "TB_CLIENT")
public class ClientModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
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
    private LocalTime data_register;
    private String customer_id;
    private String recipient_id;

    @OneToOne
    @JoinColumn(name = "address_id")
    private AddressModel address;

    @Override
    public String toString() {
        return "ClientModel{" +
                "id=" + id +
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
                ", data_register=" + data_register +
                ", customer_id='" + customer_id + '\'' +
                ", recipient_id='" + recipient_id + '\'' +
                '}';
    }

}
