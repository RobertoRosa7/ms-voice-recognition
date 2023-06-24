package br.com.voicerecognition.controller;

import br.com.voicerecognition.dto.CheckAudioDto;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.FileOutputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("audio")
public class AudioController {

    private static final Log logger = LogFactory.getLog(AudioController.class);

    private final String url = "http://localhost:5000";
    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping(value = "/health")
    public ResponseEntity<String> health() {
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        String result = response.getBody();

        return ResponseEntity.ok(result);
    }

    @PostMapping(value = "/send-audio")
    public ResponseEntity<CheckAudioDto> sendAudio(@RequestBody() String audioBase64) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Map<String, String> body = new HashMap<>();
        body.put("audio", audioBase64);

        ResponseEntity<CheckAudioDto> response = restTemplate.postForEntity(url + "/check-audio", body, CheckAudioDto.class, headers);
        CheckAudioDto result = response.getBody();

        logger.debug("[AudioController > sendAudio]: " + result);

        return ResponseEntity.ok(result);
    }
}
