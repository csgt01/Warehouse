package de.csgt.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.csgt.domain.Buy;
import de.csgt.service.BuyServiceInterface;
import de.csgt.service.MaterialServiceInterface;

@Controller
public class BuyController {
	
	@Autowired
	private MaterialServiceInterface materialService;
	
	@Autowired
	private BuyServiceInterface buyService;
	
    @RequestMapping(value = "/buys", method = RequestMethod.GET)
    public String list(@PathVariable Integer materialId, Model model){
        
        return "buys";
    }

	@RequestMapping("buy/new")
	public String newBuy(Model model) {
		Buy buy = new Buy();
		model.addAttribute("buy", buy);
		model.addAttribute("materials", materialService.listAllMaterials());
		return "buyform";
	}
	
	@RequestMapping(value = "buy", method = RequestMethod.POST)
    public String checkPersonInfo(@Valid Buy buy, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
        	System.out.println(bindingResult.toString());
        	model.addAttribute("materials", materialService.listAllMaterials());
            return "buyform";
        }
        buyService.saveBuy(buy);
        return "redirect:/buy/" + buy.getId();
    }
	
    @RequestMapping("buy/{id}")
    public String showProduct(@PathVariable Long id, Model model){
        model.addAttribute("buy", buyService.getBuyById(id));
        return "buyshow";
    }
    

//	@RequestMapping("product/edit/{id}")
//	public String edit(@PathVariable Integer id, Model model) {
//		model.addAttribute("product", productService.getProductById(id));
//		return "productform";
//	}
//
//	@RequestMapping("product/delete/{id}")
//	public String delete(@PathVariable Integer id) {
//		productService.deleteProduct(id);
//		return "redirect:/products";
//	}
}
