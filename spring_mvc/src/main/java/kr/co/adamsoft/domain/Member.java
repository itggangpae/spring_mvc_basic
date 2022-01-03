package kr.co.adamsoft.domain;

import lombok.Data;

@Data
public class Member {
	private String email;
	private String pw;
	private String loginType;
}
