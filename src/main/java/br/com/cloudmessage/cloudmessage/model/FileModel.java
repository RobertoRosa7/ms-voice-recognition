package br.com.cloudmessage.cloudmessage.model;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public interface FileModel {
	String uploadImage(String path, MultipartFile file) throws IOException;

	InputStream getResources(String path, String fileName) throws FileNotFoundException;
}
