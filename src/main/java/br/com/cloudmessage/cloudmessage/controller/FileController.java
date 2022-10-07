package br.com.cloudmessage.cloudmessage.controller;

import br.com.cloudmessage.cloudmessage.dto.FileResponseDto;
import br.com.cloudmessage.cloudmessage.endpoint.MessageEndpoint;
import br.com.cloudmessage.cloudmessage.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping(MessageEndpoint.file)
public class FileController {

	public static final String MESSAGE = "Image was uploaded";
	public static final String IS_NOT_UPLOADED = "Image is not uploaded";

	@Value("${icon.path}")
	private String iconPath;

	@Value("${image.path}")
	private String imagePath;

	@Autowired
	private FileService fileService;

	@PostMapping(value = "/upload")
	public ResponseEntity<FileResponseDto> fileUpload(@RequestParam("image") MultipartFile image) {
		try {
			return ResponseEntity.ok(new FileResponseDto(this.fileService.uploadImage(this.iconPath, image), MESSAGE));
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<>(new FileResponseDto(null, IS_NOT_UPLOADED), HttpStatus.BAD_GATEWAY);
		}
	}

	@GetMapping(value = "/icons/{name}")
	public void downloadIcon(@PathVariable("name") String name, HttpServletResponse response) throws IOException {
		String path = this.fileService.fullPath(this.iconPath);
		InputStream resources = this.fileService.getResources(path, name);
		response.setContentType(String.valueOf(MediaType.APPLICATION_XML));
		StreamUtils.copy(resources, response.getOutputStream());
	}

	@GetMapping(value = "/image/{name}")
	public void downloadImage(@PathVariable("name") String name, HttpServletResponse response) throws IOException {
		String path = this.fileService.fullPath(this.imagePath);
		InputStream resources = this.fileService.getResources(path, name);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resources, response.getOutputStream());
	}
}
