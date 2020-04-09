package io.gitlab.donespeak.tutorial.springboottest.controller;

import io.gitlab.donespeak.tutorial.springboottest.entity.FileInfo;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Yang Guanrong
 * @date 2020/04/04 15:55
 */
@RestController
@RequestMapping("/file")
public class FileController {

	private String folder = "";

	@PostMapping
	public FileInfo upload(@RequestParam("file") MultipartFile multipartFile) throws IOException {

		String id = System.currentTimeMillis() + "";
		File file = new File(folder, id + ".txt");
		multipartFile.transferTo(file);

		// 这里可以替换为上传到OSS或者S3
		// 这里需要返回url
		return new FileInfo(id);
	}

	// TODO 如果使用OSS，这个还需要吗？
	@GetMapping("{id}")
	public void download(@PathVariable String id, HttpServletResponse response, HttpServletRequest request)
		throws IOException {
		// TODO 如果不关闭这里的流，会导致什么后果
		try(InputStream inputStream = new FileInputStream(new File(folder, id + ".txt"));
			OutputStream outputStream = response.getOutputStream()) {
			response.setContentType("application/x-download");
			response.setHeader("Content-Disposition", "attachment;filename=test.txt");
			IOUtils.copy(inputStream, outputStream);
			outputStream.flush();
		}
	}
}
