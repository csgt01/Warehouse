package de.csgt.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

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

import com.fasterxml.jackson.databind.ser.std.DateSerializer;

import de.csgt.DemoApplication;
import de.csgt.domain.Buy;
import de.csgt.domain.Material;
import de.csgt.domain.Product;
import de.csgt.domain.Sell;
import de.csgt.service.BuyServiceInterface;
import de.csgt.service.MaterialServiceInterface;
import de.csgt.service.ProductServiceInterface;
import de.csgt.service.SellServiceInterface;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DemoApplication.class)
@WebAppConfiguration
public class SellControllerTest {

	@Autowired
	private ProductServiceInterface productService;
	@Autowired
	private MaterialServiceInterface materialService;
	@Autowired
	private BuyServiceInterface buyService;
	
	@Autowired
	private SellServiceInterface sellService;
	
    @Before
    public void setup() {
        createTestData();
    }
    @Test
    public void tetsTewt() throws Exception {
    	System.out.println("");
    	Sell sell = new Sell();
    	
    }
    
    
    @After
    public void rollBack() {
    	List<Sell> listAllSells = (List<Sell>) sellService.listAllSells();
    	for (Sell sell : listAllSells) {
			sellService.deleteSell(sell.getId());
		}
    	List<Buy> listAllBuys = (List<Buy>) buyService.listAllBuys();
    	for (Buy buy : listAllBuys) {
			buyService.deleteBuy(buy.getId());
		}
    	List<Product> listAllProducts = (List<Product>) productService.listAllProducts();
    	for (Product product : listAllProducts) {
			productService.deleteProduct(product.getId());
		}
    	List<Material> listAllMaterials = (List<Material>) materialService.listAllMaterials();
    	for (Material material : listAllMaterials) {
			materialService.deleteMaterial(material.getId());
		}
    }
    
    @Transactional
    private void createTestData() {
    	Calendar now = Calendar.getInstance();
    	now.setTime(new java.util.Date());
    	
    	Calendar yesterday = Calendar.getInstance();
    	yesterday.setTime(new java.util.Date());
    	yesterday.set(Calendar.DAY_OF_MONTH, now.get(Calendar.DAY_OF_MONTH) - 1);
    	
		Product product = new Product(1, "Schnullerkette", "Schnullerkette Desc", "");
		productService.saveProduct(product);
		
		Material mat = new Material(1, "Sicherheitsperle", "weiß", null, 5);
		mat = materialService.saveMaterial(mat);
		Date date = new Date(now.getTimeInMillis());
		Buy buy = new Buy(1L, date, 1, 0, 2.5, false, 0, mat);
		buyService.saveBuy(buy);
		buy = new Buy(2L, date, 4, 0, 3.5, false, 0, mat);
		buyService.saveBuy(buy);
    }

}
