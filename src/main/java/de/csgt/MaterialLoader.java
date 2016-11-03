package de.csgt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import de.csgt.domain.Color;
import de.csgt.domain.Material;
import de.csgt.service.MaterialServiceInterface;

@Component
public class MaterialLoader implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
    private MaterialServiceInterface materialService;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
    	int i = 1;
    	Material mat = new Material(i, "Motivperle", "", Color.BLUE, 10.0, 0);
    	materialService.saveMaterial(mat);
    	i++;
    	
    	mat = new Material(i, "Motivperle", "", Color.BABY_BLUE, 10.0, 0);
    	materialService.saveMaterial(mat);
    	i++;
    	
    }
}