package kr.co.adamsoft.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

public class DownloadView extends AbstractView {

	//생성자 - 뷰의 종류를 설정
	public DownloadView() {
		setContentType("application/download; charset=utf-8");
	}

	//뷰를 그리는 메서드
	@Override
	protected void renderMergedOutputModel(
			Map<String, Object> model,
			HttpServletRequest request, 
			HttpServletResponse response)
					throws Exception {
		//다운로드 할 파일을 Controller에 전달해준 데이터로 생성
		File file = (File) model.get("downloadFile");
		response.setContentType(getContentType());
		response.setContentLength((int) file.length());

		//UserAgent 속성의 값을 읽습니다.
		//UserAgent 에 접속한 클라이언트의 운영체제 와 브라우저 종류에 대한 내용이
		//저장되어 있습니다.
		String userAgent = request.getHeader("User-Agent");
		//접속한 브라우저가 Explorer 인지 판별
		boolean ie = userAgent.indexOf("rv") > -1;
		
		//IE 일 때 파일이름을 인코딩
		String fileName = null;
		if (ie) {
			fileName = URLEncoder.encode(file.getName(), "utf-8");
		} else {
			fileName = new String(file.getName().getBytes("utf-8"),
					"iso-8859-1");
		}
		//다운로드 정보 설정
		response.setHeader("Content-Disposition", "attachment; filename=\""
				+ fileName + "\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
		//파일 다운로드
		OutputStream out = response.getOutputStream();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			FileCopyUtils.copy(fis, out);
		} finally {
			if (fis != null)
				try {
					fis.close();
				} catch (IOException ex) {
				}
		}
		out.flush();
	}
};

