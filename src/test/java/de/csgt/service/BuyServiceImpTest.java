package de.csgt.service;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import de.csgt.DemoApplication;
import de.csgt.domain.Assignment;
import de.csgt.domain.Buy;
import de.csgt.domain.Material;

@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DemoApplication.class)
@WebAppConfiguration
public class BuyServiceImpTest {

    public static final Logger logger = LoggerFactory.getLogger(BuyServiceImpTest.class);

    @Autowired
    private BuyService buyService;
    
    @Autowired
    private MaterialService materialService;
    
    @Autowired
    private AssignmentService assService;

    @Test
    @Transactional
    public void testSaveAndDeleteBuy() throws Exception {
    	List<Buy> buys = (List<Buy>) buyService.listAllBuys();
    	Assignment assignment = assService.listAllAssignments().iterator().next();
    	int assignmentSize = assignment.getBuys().size();
    	Double assigmentPrice = assignment.getPrice();
		int buysSizeBefore = buys.size();
    	List<Material> materials = (List<Material>) materialService.listAllMaterials();
    	
    	Material material = materials.get(0);
    	int materialBuysSizeBefore = material.getBuys().size();
    	int materialAvailableBefore = material.getAvailable();
		
    	Buy saveBuy = buyService.saveBuy(new Buy(null, new Date(new java.util.Date().getTime()), 7, 0, 1.1, false, 0, material, assignment));
		
    	assignment = assService.getAssignmentById(assignment.getId());
    	int assignmentSizeAfter = assignment.getBuys().size();
    	Double assigmentPriceAfter = assignment.getPrice();
    	material = materialService.getMaterialById(material.getId());
		int materialBuysSizeAfter = material.getBuys().size();
		buys = (List<Buy>) buyService.listAllBuys();
		int buysSizeAfter = buys.size();
		int materialAvailableAfter = material.getAvailable();
    	assertEquals(buysSizeBefore + 1, buysSizeAfter);
    	assertEquals(materialBuysSizeBefore + 1, materialBuysSizeAfter);
    	assertEquals(materialAvailableBefore + 7, materialAvailableAfter);
    	assertEquals(assignmentSize + 1, assignmentSizeAfter);
    	assertTrue(assigmentPrice < assigmentPriceAfter);
    	
    	buyService.deleteBuy(saveBuy.getId());
    	assignment = assService.getAssignmentById(assignment.getId());
    	assignmentSizeAfter = assignment.getBuys().size();
    	assigmentPriceAfter = assignment.getPrice();
    	buys = (List<Buy>) buyService.listAllBuys();
		buysSizeAfter = buys.size();
		material = materialService.getMaterialById(material.getId());
		materialAvailableAfter = material.getAvailable();
		materialBuysSizeAfter = material.getBuys().size();
		buysSizeAfter = buys.size();
		materialAvailableAfter = material.getAvailable();
		assertEquals(buysSizeBefore, buysSizeAfter);
		assertEquals(materialAvailableBefore, materialAvailableAfter);
    	assertEquals(materialBuysSizeBefore, materialBuysSizeAfter);
    	assertEquals(assignmentSize, assignmentSizeAfter);
    	assertEquals(assigmentPrice, assigmentPriceAfter);
    }

}