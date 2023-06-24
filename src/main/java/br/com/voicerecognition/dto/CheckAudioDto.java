package br.com.voicerecognition.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CheckAudioDto implements Serializable {
    private String keyword;

    @Override
    public String toString() {
        return "CheckAudioDto{" +
                "keyword='" + keyword + '\'' +
                '}';
    }
}
