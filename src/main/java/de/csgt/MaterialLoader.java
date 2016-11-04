package de.csgt;

import java.sql.Date;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import de.csgt.domain.Assignment;
import de.csgt.domain.Color;
import de.csgt.domain.Material;
import de.csgt.domain.Shop;
import de.csgt.service.AssignmentService;
import de.csgt.service.MaterialService;
import de.csgt.service.ShopService;

@Component
public class MaterialLoader implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
    private MaterialService materialService;
	
	@Autowired
    private ShopService shopService;

	@Autowired
	private AssignmentService assService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
    	
    	setupMaterials();
    	Shop murmelkiste = new Shop();
    	murmelkiste.setId(1L);
    	murmelkiste.setName("Murmelkiste");
    	murmelkiste.setCity("Berlin");
    	shopService.saveShop(murmelkiste);
    	
    	Shop emmalein = new Shop();
    	emmalein.setId(2L);
    	emmalein.setName("emmalein");
    	shopService.saveShop(emmalein);
    	
    	Assignment ass1 = new Assignment();
    	ass1.setId(1L);
    	ass1.setShop(murmelkiste);
    	Calendar cal = Calendar.getInstance();
    	cal.set(Calendar.YEAR, 2016);
    	cal.set(Calendar.MONTH, 0);
    	cal.set(Calendar.DAY_OF_MONTH, 26);
    	ass1.setOrderedAt(new Date(cal.getTimeInMillis()));
    	ass1.setOrderNumber("2016-0766");
    	assService.saveAssignment(ass1);
    	
    }


	private void setupMaterials() {
		int i = 1;
		Material mat = new Material(i, "Holzclip", "", Color.SEVERAL, 0.0, 0);
		materialService.saveMaterial(mat);
		i++;
		
		mat = new Material(i, "Holzperle", "", Color.BABY_BLUE, 10.0, 0);
		materialService.saveMaterial(mat);
		i++;
		
		mat = new Material(i, "Holzperle", "", Color.PURPLE, 10.0, 0);
		materialService.saveMaterial(mat);
		i++;
		
		mat = new Material(i, "Holzperle", "", Color.LEMON, 10.0, 0);
		materialService.saveMaterial(mat);
		i++;
		
		mat = new Material(i, "Holzperle", "", Color.TURQUOISE, 10.0, 0);
		materialService.saveMaterial(mat);
		i++;
		
		mat = new Material(i, "Holzperle", "", Color.PINK, 10.0, 0);
		materialService.saveMaterial(mat);
		i++;
		
		mat = new Material(i, "Holzperle", "", Color.MIDDLE_BLUE, 10.0, 0);
		materialService.saveMaterial(mat);
		i++;
		
		mat = new Material(i, "Holzperle", "", Color.DARK_PINK, 10.0, 0);
		materialService.saveMaterial(mat);
		i++;
		
		mat = new Material(i, "Holzperle", "", Color.WHITE, 10.0, 0);
		materialService.saveMaterial(mat);
		i++;
		
		mat = new Material(i, "Holzperle", "", Color.BLUE_PURPLE, 10.0, 0);
		materialService.saveMaterial(mat);
		i++;
		
		mat = new Material(i, "Holzperle", "", Color.LIGHT_TURQUOISE, 10.0, 0);
		materialService.saveMaterial(mat);
		i++;
		
		mat = new Material(i, "Holzperle", "", Color.PINK2, 10.0, 0);
		materialService.saveMaterial(mat);
		i++;
		
		mat = new Material(i, "Holzbuchstabe A-Z", "", Color.WOOD, 10.0, 0);
		materialService.saveMaterial(mat);
		i++;
		
		mat = new Material(i, "Holzlinse", "", Color.SEVERAL, 10.0, 0);
		materialService.saveMaterial(mat);
		i++;
		
		mat = new Material(i, "Motivperle Würfel", "", Color.PINK, 0.0, 0);
		materialService.saveMaterial(mat);
		i++;
		
		mat = new Material(i, "Motivperle Würfel", "", Color.BABY_BLUE, 0.0, 0);
		materialService.saveMaterial(mat);
		i++;
		
		mat = new Material(i, "Sicherheitsperle", "", Color.WHITE, 10.0, 0);
		materialService.saveMaterial(mat);
		i++;
		
		mat = new Material(i, "Motivperle Cupcake", "", Color.SKY_BLUE, 0.0, 0);
		materialService.saveMaterial(mat);
		i++;
		
		mat = new Material(i, "Motivperle Cupcake", "", Color.DARK_PINK, 0.0, 0);
		materialService.saveMaterial(mat);
		i++;
		
		mat = new Material(i, "Motivperle Eule", "", Color.PINK, 0.0, 0);
		materialService.saveMaterial(mat);
		i++;
		
		mat = new Material(i, "Motivperle Eule", "", Color.BABY_BLUE, 0.0, 0);
		materialService.saveMaterial(mat);
		i++;
		
		mat = new Material(i, "Motivscheibe Skull", "", Color.DARK_PINK, 0.0, 0);
		materialService.saveMaterial(mat);
		i++;
		
		mat = new Material(i, "Motivperle Apfel", "", Color.SEVERAL, 0.0, 0);
		materialService.saveMaterial(mat);
		i++;
		
		mat = new Material(i, "Motivscheibe BORN 2015", "", Color.BABY_BLUE, 0.0, 0);
		materialService.saveMaterial(mat);
		i++;
		
		mat = new Material(i, "Motivscheibe BORN 2015", "", Color.PINK, 0.0, 0);
		materialService.saveMaterial(mat);
		i++;
		
		mat = new Material(i, "Motivscheibe Love Mom Dad", "", Color.BABY_BLUE, 0.0, 0);
		materialService.saveMaterial(mat);
		i++;
		
		mat = new Material(i, "Motivscheibe Love Mom Dad", "", Color.WHITE, 0.0, 0);
		materialService.saveMaterial(mat);
		i++;
		
		mat = new Material(i, "Linsen Sternchen", "", Color.BABY_BLUE, 10.0, 0);
		materialService.saveMaterial(mat);
		i++;
		
		mat = new Material(i, "Linsen Sternchen", "", Color.WHITE, 10.0, 0);
		materialService.saveMaterial(mat);
		i++;
		
		mat = new Material(i, "Linsen Sternchen", "", Color.PINK, 10.0, 0);
		materialService.saveMaterial(mat);
		i++;
		
		mat = new Material(i, "Motivperle Pilz", "", Color.BABY_BLUE, 0.0, 0);
		materialService.saveMaterial(mat);
		i++;
		
		mat = new Material(i, "Holzperle", "", Color.PINK, 8.0, 0);
		materialService.saveMaterial(mat);
		i++;
		
		mat = new Material(i, "Holzperle", "", Color.WHITE, 8.0, 0);
		materialService.saveMaterial(mat);
		i++;
		
		mat = new Material(i, "Holzperle", "", Color.BABY_BLUE, 8.0, 0);
		materialService.saveMaterial(mat);
		i++;
		
		mat = new Material(i, "Blümchen", "", Color.SEVERAL, 8.0, 0);
		materialService.saveMaterial(mat);
		i++;
		
		mat = new Material(i, "Motivperle Stern", "", Color.SEVERAL, 0.0, 0);
		materialService.saveMaterial(mat);
		i++;
		
		mat = new Material(i, "Glöckchen", "", Color.WHITE, 0.0, 0);
		materialService.saveMaterial(mat);
		i++;
		
		mat = new Material(i, "Glöckchen", "", Color.PINK, 0.0, 0);
		materialService.saveMaterial(mat);
		i++;
		
		mat = new Material(i, "Glöckchen", "", Color.LIGHT_BLUE, 0.0, 0);
		materialService.saveMaterial(mat);
		i++;
		
		mat = new Material(i, "Holzlinse", "", Color.BLUE, 10.0, 0);
		materialService.saveMaterial(mat);
		i++;
		
		mat = new Material(i, "Holzlinse", "", Color.PINK, 10.0, 0);
		materialService.saveMaterial(mat);
		i++;
		
		mat = new Material(i, "Holzlinse", "", Color.SEVERAL, 10.0, 0);
		materialService.saveMaterial(mat);
		i++;
		
		mat = new Material(i, "Halbring", "", Color.SEVERAL, 10.0, 0);
		materialService.saveMaterial(mat);
		i++;
		
		mat = new Material(i, "Motivperle Glitzerblümchen", "", Color.SEVERAL, 10.0, 0);
		materialService.saveMaterial(mat);
		i++;
		
		mat = new Material(i, "Holzring Mini", "", Color.WHITE, 10.0, 0);
		materialService.saveMaterial(mat);
		i++;
		
		mat = new Material(i, "Holzring Mini", "", Color.MIDDLE_BLUE, 10.0, 0);
		materialService.saveMaterial(mat);
		i++;
		
		mat = new Material(i, "Greifling Gr S", "", Color.PINK, 10.0, 0);
		materialService.saveMaterial(mat);
		i++;
		
		mat = new Material(i, "Motivperle Herz Schnörkel", "", Color.SEVERAL, 10.0, 0);
		materialService.saveMaterial(mat);
		i++;
		
		mat = new Material(i, "Motivperle Herz Glitzerpunkte", "", Color.PINK, 10.0, 0);
		materialService.saveMaterial(mat);
		i++;
		
		mat = new Material(i, "Motivperle Herz Glitzerpunkte", "", Color.BABY_BLUE, 10.0, 0);
		materialService.saveMaterial(mat);
		i++;
		
		mat = new Material(i, "Motivperle Krönchen", "", Color.SEVERAL, 10.0, 0);
		materialService.saveMaterial(mat);
		i++;
		
		mat = new Material(i, "Motivperle Schmetterling", "", Color.PINK, 10.0, 0);
		materialService.saveMaterial(mat);
		i++;
		
		mat = new Material(i, "Motivperle Schmetterling", "", Color.PINK2, 10.0, 0);
		materialService.saveMaterial(mat);
		i++;
		
		mat = new Material(i, "Motivperle Igel", "", Color.BABY_BLUE, 10.0, 0);
		materialService.saveMaterial(mat);
		i++;
		
		mat = new Material(i, "Motivperle Pferd", "", Color.PURPLE, 10.0, 0);
		materialService.saveMaterial(mat);
		i++;
		
		mat = new Material(i, "Motivperle Pferd", "", Color.PINK, 10.0, 0);
		materialService.saveMaterial(mat);
		i++;
		
		mat = new Material(i, "Holzlinse", "", Color.WHITE, 10.0, 0);
		materialService.saveMaterial(mat);
		i++;
		
		mat = new Material(i, "Holzbuchstabe T", "", Color.WOOD, 10.0, 0);
		materialService.saveMaterial(mat);
		i++;
		
		mat = new Material(i, "Motivperle Blume", "", Color.PINK, 0.0, 0);
		materialService.saveMaterial(mat);
		i++;
		
		mat = new Material(i, "Motivperle Blume", "", Color.BABY_PINK, 0.0, 0);
		materialService.saveMaterial(mat);
		i++;
		
		mat = new Material(i, "Motivperle Blume", "", Color.SILVER, 0.0, 0);
		materialService.saveMaterial(mat);
		i++;
		
		mat = new Material(i, "Holzring Mini", "", Color.GREY, 0.0, 0);
		materialService.saveMaterial(mat);
		i++;
		
		mat = new Material(i, "Holzbuchstabe A-Z", "", Color.WHITE, 10.0, 0);
		materialService.saveMaterial(mat);
		i++;
		
		mat = new Material(i, "Holzbuchstabe Z", "", Color.WOOD, 10.0, 0);
		materialService.saveMaterial(mat);
		i++;
		
		mat = new Material(i, "Motivperle Herz Schnörkel", "", Color.GREY, 0.0, 0);
		materialService.saveMaterial(mat);
		i++;
		
		mat = new Material(i, "Holzlinse", "", Color.LIGHT_GREY, 10.0, 0);
		materialService.saveMaterial(mat);
		i++;
		
		mat = new Material(i, "Halbring", "", Color.PURPLE, 10.0, 0);
		materialService.saveMaterial(mat);
		i++;
		
		mat = new Material(i, "Halbring", "", Color.GREY, 10.0, 0);
		materialService.saveMaterial(mat);
		i++;
		
		mat = new Material(i, "Sicherheitsperle", "", Color.PURPLE, 10.0, 0);
		materialService.saveMaterial(mat);
		i++;
		
		mat = new Material(i, "Sicherheitsperle", "", Color.PINK, 10.0, 0);
		materialService.saveMaterial(mat);
		i++;
		
		mat = new Material(i, "Sicherheitsperle", "", Color.BABY_PINK, 10.0, 0);
		materialService.saveMaterial(mat);
		i++;
		
		mat = new Material(i, "Sicherheitsperle", "", Color.DARK_PINK, 10.0, 0);
		materialService.saveMaterial(mat);
		i++;
		
		mat = new Material(i, "Sicherheitsperle", "", Color.PINK2, 10.0, 0);
		materialService.saveMaterial(mat);
		i++;
		
		mat = new Material(i, "Motivperle Schmetterling", "", Color.BLUE_PURPLE, 10.0, 0);
		materialService.saveMaterial(mat);
		i++;
		
		mat = new Material(i, "Motivperle Schmetterling", "", Color.DARK_PINK, 10.0, 0);
		materialService.saveMaterial(mat);
		i++;
		
		mat = new Material(i, "Sicherheitsperle", "", Color.GREY, 10.0, 0);
		materialService.saveMaterial(mat);
		i++;
		
		mat = new Material(i, "Holzperle", "", Color.YELLOW_GREEN, 10.0, 0);
		materialService.saveMaterial(mat);
		i++;
		
		mat = new Material(i, "Holzperle", "", Color.SKY_BLUE, 10.0, 0);
		materialService.saveMaterial(mat);
		i++;
		
		mat = new Material(i, "Holzperle", "", Color.BABY_PINK, 10.0, 0);
		materialService.saveMaterial(mat);
		i++;
		
		mat = new Material(i, "Holzperle", "", Color.GREY, 10.0, 0);
		materialService.saveMaterial(mat);
		i++;
		
		mat = new Material(i, "Motivperle Herz Punkt", "", Color.PURPLE, 0.0, 0);
		materialService.saveMaterial(mat);
		i++;
		
		mat = new Material(i, "Motivperle Herz Punkt", "", Color.DARK_PINK, 0.0, 0);
		materialService.saveMaterial(mat);
		i++;
		
		mat = new Material(i, "Holzring Mini", "", Color.SEVERAL, 10.0, 0);
		materialService.saveMaterial(mat);
		i++;
		
		mat = new Material(i, "Motivperle Herz Schnörkel", "", Color.DARK_PINK, 10.0, 0);
		materialService.saveMaterial(mat);
		i++;
		
		mat = new Material(i, "Motivperle Eule", "", Color.DARK_PINK, 0.0, 0);
		materialService.saveMaterial(mat);
		i++;
		
		mat = new Material(i, "Motivperle Eule", "", Color.PURPLE, 0.0, 0);
		materialService.saveMaterial(mat);
		i++;
		
		mat = new Material(i, "Motivperle Herz Schnörkel", "", Color.PURPLE, 10.0, 0);
		materialService.saveMaterial(mat);
		i++;
		
		mat = new Material(i, "Motivperle Herz Glitzerpunkte", "", Color.WHITE, 10.0, 0);
		materialService.saveMaterial(mat);
		i++;
		
		mat = new Material(i, "Holzperle", "", Color.LIGHT_GREY, 10.0, 0);
		materialService.saveMaterial(mat);
		i++;
		
		mat = new Material(i, "Holzclip", "", Color.LIGHT_GREY, 0.0, 0);
		materialService.saveMaterial(mat);
		i++;
		
		mat = new Material(i, "Motivperle Herz Glitzerpunkte", "", Color.GREY, 10.0, 0);
		materialService.saveMaterial(mat);
		i++;
		
	}
}