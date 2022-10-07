package br.com.cloudmessage.cloudmessage.service;

import br.com.cloudmessage.cloudmessage.model.FileModel;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileService implements FileModel {
	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {

		String name = file.getOriginalFilename();
		String randomId = UUID.randomUUID().toString();
		String fileName = randomId.concat(name.substring(name.lastIndexOf(".")));
		String filePath = this.getPath(path, fileName);

		File f = new File(path);
		if (!f.exists()) {
			f.mkdir();
		}

		Files.copy(file.getInputStream(), Paths.get(filePath));
		return name;
	}

	@Override
	public InputStream getResources(String path, String fileName) throws FileNotFoundException {
		return new FileInputStream(this.getPath(path, fileName));
	}

	private String getPath(String relativePath, String fileName) {
		return relativePath + File.separator + fileName;
	}

	public String fullPath(String relativePath) throws IOException {
		return new ClassPathResource(relativePath, this.getClass().getClassLoader()).getFile().getPath();
	}
}
