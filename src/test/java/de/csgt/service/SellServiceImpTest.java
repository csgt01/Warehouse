package de.csgt.service;
import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.util.ArrayList;
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
import de.csgt.domain.Buy;
import de.csgt.domain.Material;
import de.csgt.domain.Product;
import de.csgt.domain.Sell;
import de.csgt.domain.SellMaterial;

@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DemoApplication.class)
@WebAppConfiguration
public class SellServiceImpTest {

    public static final Logger logger = LoggerFactory.getLogger(SellServiceImpTest.class);

    @Autowired
    private BuyService buyService;
    
    @Autowired
    private MaterialService materialService;
    
    @Autowired
    private AssignmentService assService;
    
    @Autowired
    private SellService sellService;
    
    @Autowired
    private ProductService prodService;

    @Test
    @Transactional
    public void testSaveAndDeleteSell() throws Exception {
    	
    	Material mat = null;
    	Product product = prodService.listAllProducts().iterator().next();
    	ArrayList<SellMaterial> sellMaterials = new ArrayList<SellMaterial>();
    	Sell sell = new Sell();
    	List<Material> listAllMaterials = (List<Material>) materialService.listAllMaterials();
    	
    	for (Material material : listAllMaterials) {
			if (material.getAvailable() > 1 && material.getBuys().size() > 1) {
				mat = material;
				SellMaterial e = new SellMaterial(sell, material);
				e.setQuantity(2);
				sellMaterials.add(e);
				break;
			}
		}
    	
    	Buy firstBuy = ((List<Buy>) buyService.listAllBuysByMaterialAndNotSold(mat)).get(0);
    	logger.info("q:" + firstBuy.getQuantity());
    	logger.info("s:" + firstBuy.getSoldInt());
    	int firstBuySoldBefore = firstBuy.getSoldInt();
    	int firstBuyQuantity = firstBuy.getQuantity();
    	
    	sell.setSellMaterials(sellMaterials);
    	sell.setAdditionalCosts(0.0);
    	sell.setPrice(10.0);
    	sell.setQuantity(1);
    	sell.setSoldAt( new Date(new java.util.Date().getTime()));
    	sell.setProduct(product);
    	mat = materialService.getMaterialById(mat.getId());
    	int sellSizeBefore = ((List<Sell>) sellService.listAllSells()).size();
    	int materialAvailBefore = mat.getAvailable();
		
    	sell = sellService.saveSell(sell);
		
    	mat = materialService.getMaterialById(mat.getId());
    	firstBuy = buyService.getBuyById(firstBuy.getId());
		int sellSizeAfter = ((List<Sell>) sellService.listAllSells()).size();
		int materialAvailAfter = mat.getAvailable();
		int firstBuySoldAfter = firstBuy.getSoldInt();
		
		assertEquals(sellSizeBefore + 1, sellSizeAfter);
		assertEquals(materialAvailBefore - 2, materialAvailAfter);
		assertEquals(firstBuySoldBefore + 2, firstBuySoldAfter);
		assertEquals(sell.getSellBuys().size(), 1);
		
		Sell sell2 = new Sell();
		ArrayList<SellMaterial> sellMaterials2 = new ArrayList<SellMaterial>();
		
		SellMaterial e = new SellMaterial(sell2, mat);
		e.setQuantity(firstBuyQuantity - 2);
		sellMaterials2.add(e);
		sell2.setSellMaterials(sellMaterials2);
		sell2.setAdditionalCosts(0.0);
		sell2.setPrice(10.0);
		sell2.setQuantity(1);
		sell2.setSoldAt( new Date(new java.util.Date().getTime()));
		sell2.setProduct(product);
		sell2 = sellService.saveSell(sell2);
		
		mat = materialService.getMaterialById(mat.getId());
    	firstBuy = buyService.getBuyById(firstBuy.getId());
		sellSizeAfter = ((List<Sell>) sellService.listAllSells()).size();
		materialAvailAfter = mat.getAvailable();
		firstBuySoldAfter = firstBuy.getSoldInt();
		
		assertEquals(sellSizeBefore + 2, sellSizeAfter);
		assertEquals(1, sell2.getSellMaterials().size());
		assertEquals(materialAvailBefore - firstBuyQuantity, materialAvailAfter);
		assertEquals(firstBuySoldBefore + firstBuyQuantity - 1, firstBuySoldAfter);
		assertEquals(sell.getSellBuys().size(), 1);
		
		
		sellService.deleteSell(sell.getId());
		sellService.deleteSell(sell2.getId());
		
		firstBuy = buyService.getBuyById(firstBuy.getId());
		mat = materialService.getMaterialById(mat.getId());
		sellSizeAfter = ((List<Sell>) sellService.listAllSells()).size();
		materialAvailAfter = mat.getAvailable();
		firstBuySoldAfter = firstBuy.getSoldInt();
		assertEquals(sellSizeBefore, sellSizeAfter);
		assertEquals(materialAvailBefore, materialAvailAfter);
		assertEquals(firstBuySoldBefore, firstBuySoldAfter);
		
    }
    
    @Test
    @Transactional
    public void testSaveAndDeleteSell2() throws Exception {
    	
    	Material mat = null;
    	Product product = prodService.listAllProducts().iterator().next();
    	ArrayList<SellMaterial> sellMaterials = new ArrayList<SellMaterial>();
    	Sell sell = new Sell();
    	List<Material> listAllMaterials = (List<Material>) materialService.listAllMaterials();
    	
    	for (Material material : listAllMaterials) {
			if (material.getAvailable() > 1 && material.getBuys().size() > 1) {
				mat = material;
				SellMaterial e = new SellMaterial(sell, material);
				e.setQuantity(12);
				sellMaterials.add(e);
				break;
			}
		}
    	
    	Buy firstBuy = ((List<Buy>) buyService.listAllBuysByMaterialAndNotSold(mat)).get(0);
    	Buy secondBuy = ((List<Buy>) buyService.listAllBuysByMaterialAndNotSold(mat)).get(1);
    	int firstBuySoldBefore = firstBuy.getSoldInt();
    	int secondBuySoldBefore = secondBuy.getSoldInt();
    	
    	sell.setSellMaterials(sellMaterials);
    	sell.setAdditionalCosts(0.0);
    	sell.setPrice(10.0);
    	sell.setQuantity(1);
    	sell.setSoldAt( new Date(new java.util.Date().getTime()));
    	sell.setProduct(product);
    	mat = materialService.getMaterialById(mat.getId());
    	int sellSizeBefore = ((List<Sell>) sellService.listAllSells()).size();
    	int materialAvailBefore = mat.getAvailable();
		
    	sell = sellService.saveSell(sell);
		
    	mat = materialService.getMaterialById(mat.getId());
    	firstBuy = buyService.getBuyById(firstBuy.getId());
    	secondBuy = buyService.getBuyById(secondBuy.getId());
		int sellSizeAfter = ((List<Sell>) sellService.listAllSells()).size();
		int materialAvailAfter = mat.getAvailable();
		int firstBuySoldAfter = firstBuy.getSoldInt();
		int secondBuySoldAfter = secondBuy.getSoldInt();
		
		assertEquals(sellSizeBefore + 1, sellSizeAfter);
		assertEquals(materialAvailBefore - 12, materialAvailAfter);
		assertEquals(firstBuy.getQuantity().intValue(), firstBuySoldAfter);
		assertEquals(sell.getSellBuys().size(), 2);
		assertEquals(secondBuySoldBefore + firstBuySoldBefore - firstBuySoldAfter + 12, secondBuySoldAfter);
		
		sellService.deleteSell(sell.getId());
		
		firstBuy = buyService.getBuyById(firstBuy.getId());
		secondBuy = buyService.getBuyById(secondBuy.getId());
		mat = materialService.getMaterialById(mat.getId());
		sellSizeAfter = ((List<Sell>) sellService.listAllSells()).size();
		materialAvailAfter = mat.getAvailable();
		firstBuySoldAfter = firstBuy.getSoldInt();
		secondBuySoldAfter = secondBuy.getSoldInt();
		assertEquals(sellSizeBefore, sellSizeAfter);
		assertEquals(materialAvailBefore, materialAvailAfter);
		assertEquals(firstBuySoldBefore, firstBuySoldAfter);
		assertEquals(secondBuySoldBefore, secondBuySoldAfter);
		
    }

}