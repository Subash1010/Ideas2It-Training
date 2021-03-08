package com.I2I.aws.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StorageService {

	@Autowired
	private AmazonS3 s3Client;

	private final static String BUCKET_NAME = "i2i.input";

	public String uploadFile(MultipartFile inputFile) {
		File convertedFile = convertMultiPartFileToFile(inputFile);
		try {
			String fileName = System.currentTimeMillis() + "_" + inputFile.getOriginalFilename();
			s3Client.putObject(new PutObjectRequest(BUCKET_NAME, fileName, convertedFile));
			convertedFile.delete();
			return "File Uploaded successfully";
		} catch (Exception exception) {
			log.error("Issue Exists in uploading the File" + exception);
		}
		return "File is not Uploaded";
	}

	public byte[] downloadFile(String fileName) {
		S3Object s3Obj = s3Client.getObject(BUCKET_NAME, fileName);
		S3ObjectInputStream inputStream = s3Obj.getObjectContent();
		try {
			byte[] content = IOUtils.toByteArray(inputStream);
			return content;
		} catch (IOException exception) {
			exception.printStackTrace();

		}
		return null;
	}

	public String deleteFile(String fileName) {
		s3Client.deleteObject(BUCKET_NAME, fileName);
		return fileName + "Removed";
	}

	public File convertMultiPartFileToFile(MultipartFile inputFile) {
		File convertedFile = new File(inputFile.getOriginalFilename());
		try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
			fos.write(inputFile.getBytes());
		} catch (IOException exception) {
			log.error("Error in converting multipart file to file", exception);
		}
		return convertedFile;
	}
}
