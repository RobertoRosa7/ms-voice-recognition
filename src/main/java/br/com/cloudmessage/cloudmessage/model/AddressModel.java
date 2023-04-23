package br.com.cloudmessage.cloudmessage.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "TB_ADDRESS")
public class AddressModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String city;
    private String street;
    private String state;
    private String country;
    private String zipcode;
    private String street_number;
    private String neighborhood;
}