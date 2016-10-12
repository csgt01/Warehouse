package de.csgt.controller;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.util.Calendar;
import java.util.Date;

import javax.transaction.Transactional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import de.csgt.DemoApplication;
import de.csgt.domain.Material;
import de.csgt.domain.Product;
import de.csgt.service.BuyService;
import de.csgt.service.MaterialService;
import de.csgt.service.ProductService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DemoApplication.class)
@WebAppConfiguration
public class SellControllerTest {

	@Autowired
	private ProductService productService;
	@Autowired
	private MaterialService materialService;
	@Autowired
	private BuyService buyService;
	
	@Autowired
    private WebApplicationContext wac;

    protected MockMvc mockMvc;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = webAppContextSetup(this.wac).build();
        createTestData();
    }
    @Test
    public void tetsTewt() {
    	
    }
    
    
    @After
    public void rollBack() {
    	
    }
    
    @Transactional
    private void createTestData() {
    	Calendar now = Calendar.getInstance();
    	now.setTime(new Date());
    	
    	Calendar yesterday = Calendar.getInstance();
    	yesterday.setTime(new Date());
    	yesterday.set(Calendar.DAY_OF_MONTH, now.get(Calendar.DAY_OF_MONTH) - 1);
    	
		Product product = new Product(1, now, "Schnullerkette", "Schnullerkette Desc", "");
		productService.saveProduct(product);
		
		Material mat = new Material(1, now, "Sicherheitsperle", "weiß", null, 5);
		materialService.saveMaterial(mat);
		
		
    }

}
