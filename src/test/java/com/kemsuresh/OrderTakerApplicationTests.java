package com.kemsuresh;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
//import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.kemsuresh.controller.OrderTakingController;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderTakerApplicationTests {
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private OrderTakingController ot;
	
	@Test
	public void contextLoads() throws Exception {
		String orderJson = 	  "{"
							+ "\"personName\" : \"Suresh K\","
							+ "\"phoneNumber\" : \"12345678\","
							+ "\"email\" : \"kemsuresh@gmail.com\","
							+ "\"preferredInstallationDate\" : \"23-02-2022\","
							+ "\"timeslot\" : 12 ,"
							+ "\"installationAddress\" : {"
													  + "\"addressline\" : \"No.6 Sivan Koil Street\","
													  + "\"city\" : \"Chennai\","
													  + "\"state\" : \"India\","
													  + "\"zipcode\" : \"600100\""
													  + "},"
						    + "\"requiredproduct\" : 1,"
						    + "\"packageId\" : 1"
							+ "}";
		
		this.mockMvc.perform(MockMvcRequestBuilders.post("/order").with(httpBasic("admin", "admin"))
				  .content(orderJson).contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
	}
	
}
