package de.csgt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.csgt.domain.Color;
import de.csgt.domain.Shop;
import de.csgt.service.ShopService;

@Controller
public class ShopController {
	
	@Autowired
	private ShopService shopService;
	
    @RequestMapping(value = "/shops", method = RequestMethod.GET)
    public String list(Model model){
        model.addAttribute("shops", shopService.listAllShops());
        return "shops";
    }

	@RequestMapping("shop/new")
	public String newShop(Model model) {
		model.addAttribute("shop", new Shop());
		model.addAttribute("colors", Color.values());
		return "shopform";
	}

	@RequestMapping(value = "shop", method = RequestMethod.POST)
	public String saveShop(Shop shop) {
		shopService.saveShop(shop);
		return "redirect:/shop/" + shop.getId();
	}
	
    @RequestMapping("shop/{id}")
    public String showShop(@PathVariable Long id, Model model){
        model.addAttribute("shop", shopService.getShopById(id));
        return "shopshow";
    }

	@RequestMapping("shop/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		System.out.println("" + id + " " + model.toString());
		model.addAttribute("shop", shopService.getShopById(id));
		model.addAttribute("colors", Color.values());
		return "shopform";
	}

	@RequestMapping("shop/delete/{id}")
	public String delete(@PathVariable Long id) {
		shopService.deleteShop(id);
		return "redirect:/shops";
	}
}
