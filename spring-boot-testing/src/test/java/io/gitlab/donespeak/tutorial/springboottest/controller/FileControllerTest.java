package io.gitlab.donespeak.tutorial.springboottest.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Yang Guanrong
 * @date 2020/04/04 15:58
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FileControllerTest {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void whenUploadSuccess() throws Exception {
		mockMvc.perform(multipart("file")
			.file(new MockMultipartFile("file", "test.txt", "multipart/form-data", "file content".getBytes())))
			.andExpect(status().isOk());
	}
}
