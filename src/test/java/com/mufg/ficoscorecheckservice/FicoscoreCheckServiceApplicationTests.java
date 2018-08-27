package com.mufg.ficoscorecheckservice;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mufg.ficoscorecheckservice.model.FicoscoreCheckModel;
import org.junit.Assert;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Sumalathav
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class FicoscoreCheckServiceApplicationTests {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext context;

	ObjectMapper om = new ObjectMapper();

	private FicoscoreCheckModel ficoscoreCheckModel;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	public void getFicoscoreCheckTest() throws Exception {
		MvcResult result = mockMvc.perform(
				get("http://localhost:8091/mufg/api/ficoscorecheck/ssn/10").content(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
		String resultContent = result.getResponse().getContentAsString();
		ficoscoreCheckModel = om.readValue(resultContent, FicoscoreCheckModel.class);
		Assert.assertTrue(ficoscoreCheckModel.isStatus() == Boolean.TRUE);

	}
}
