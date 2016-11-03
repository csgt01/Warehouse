package de.csgt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import de.csgt.domain.Color;
import de.csgt.domain.Material;
import de.csgt.domain.Shop;
import de.csgt.service.MaterialService;
import de.csgt.service.ShopService;

@Component
public class MaterialLoader implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
    private MaterialService materialService;
	
	@Autowired
    private ShopService shopService;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
    	
    	Shop shop = new Shop();
    	shop.setId(1L);
    	shop.setName("Murmelkiste");
    	
    	int i = 1;
    	Material mat = new Material(i, "Motivperle", "", Color.BLUE, 10.0, 0);
    	materialService.saveMaterial(mat);
    	i++;
    	
    	mat = new Material(i, "Motivperle", "", Color.BABY_BLUE, 10.0, 0);
    	materialService.saveMaterial(mat);
    	i++;
    	
    }
}