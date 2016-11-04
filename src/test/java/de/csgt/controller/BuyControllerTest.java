package de.csgt.controller;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import de.csgt.DemoApplication;

@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DemoApplication.class)
@WebAppConfiguration
public class BuyControllerTest {

    public static final Logger logger = LoggerFactory.getLogger(BuyControllerTest.class);

    @Autowired
    private WebApplicationContext wac;

    protected MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = webAppContextSetup(this.wac).build();
    }
    
//    id:
//    	soldInt:0
//    	sold:false
//    	material:1
//    	assignment:1
//    	price:1.05
//    	quantity:10
//    	tempQuantity:0

    @Test
    public void testGetTicketsPerCustomer() throws Exception {
        MvcResult andReturn = this.mockMvc.perform(post("/buy")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{"
                		+ "\"soldInt\": \"0\", "
                		+ "\"sold\": \"false\", "
                		+ "\"price\": \"1.05\", "
                		+ "\"quantity\": \"10\", "
                		+ "\"tempQuantity\": \"0\", "
                		+ "\"material\": \"1\""
                		+ "}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

}