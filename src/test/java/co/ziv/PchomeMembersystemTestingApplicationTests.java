package co.ziv;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PchomeMembersystemTestingApplicationTests {
	
	@Autowired
	WebApplicationContext webApplicationContext;

	private MockMvc mvc;
	
	@Before
	public void setup() throws JsonProcessingException {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void loginTest() throws Exception {
		String uri = "/actuator/health";
		this.mvc.perform(MockMvcRequestBuilders.get(uri)).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk());
	}

}
