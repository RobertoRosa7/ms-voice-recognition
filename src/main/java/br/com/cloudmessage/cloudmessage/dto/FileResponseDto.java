package br.com.cloudmessage.cloudmessage.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FileResponseDto {
	private String fileName;
	private String message;

}
