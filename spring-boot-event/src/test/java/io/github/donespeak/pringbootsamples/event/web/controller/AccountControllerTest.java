package io.github.donespeak.pringbootsamples.event.web.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author Yang Guanrong
 * @date 2020/04/04 12:02
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountControllerTest {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void whenQuerySuccess() throws Exception {
		mockMvc.perform(get("/accounts")
			.param("page", "3")
			.param("size", "12")
			.param("sort", "age,desc")
			.contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.length()").value(3));
	}
}
