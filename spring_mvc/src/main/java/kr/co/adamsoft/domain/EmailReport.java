package kr.co.adamsoft.domain;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class EmailReport {
	private String email;
	private MultipartFile report;
}
