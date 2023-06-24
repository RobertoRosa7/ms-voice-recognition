package br.com.voicerecognition.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class ProductDto implements Serializable {

    private Long id;
    private String name;
    private BigDecimal price;
    private LocalDateTime createdAt;
}
