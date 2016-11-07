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
import de.csgt.service.AssignmentService;
import de.csgt.service.BuyService;
import de.csgt.service.MaterialService;

@Controller
public class BuyController {
//	private Logger log = Logger.getLogger(BuyController.class);
	@Autowired
	private MaterialService materialService;
	
	@Autowired
	private BuyService buyService;
	@Autowired
	private AssignmentService assingmentService;
	
    @RequestMapping(value = "buys", method = RequestMethod.GET)
    public String list(Model model){
    	model.addAttribute("buys", buyService.listAllBuys());
        return "buys";
    }

	@RequestMapping("buy/new")
	public String newBuy(Model model) {
		Buy buy = new Buy();
		model.addAttribute("buy", buy);
		model.addAttribute("materials", materialService.listAllMaterials());
		model.addAttribute("assignments", assingmentService.listAllAssignmentsByNotClosed());
		return "buyform";
	}
	
	@RequestMapping(value = "buy", method = RequestMethod.POST)
    public String checkPersonInfo(@Valid Buy buy, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
//        	System.out.println("bindingResult:" + bindingResult.toString());
        	model.addAttribute("materials", materialService.listAllMaterials());
    		model.addAttribute("assignments", assingmentService.listAllAssignmentsByNotClosed());
        	model.addAttribute("buy", buy);
        	model.addAttribute("fields", bindingResult);
            return "buyform";
        }
        buy = buyService.saveBuy(buy);
        return "redirect:/buy/" + buy.getId();
    }
	
	@RequestMapping("buy/{id}")
    public String showProduct(@PathVariable Long id, Model model){
        model.addAttribute("buy", buyService.getBuyById(id));
        return "buyshow";
    }
    

	@RequestMapping("buy/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		Buy buyById = buyService.getBuyById(id);
		buyById.setTempQuantity(buyById.getQuantity());
		model.addAttribute("buy", buyById);
		model.addAttribute("materials", materialService.listAllMaterials());
		model.addAttribute("assignments", assingmentService.listAllAssignmentsByNotClosed());
		return "buyform";
	}

	@RequestMapping("buy/delete/{id}")
	public String delete(@PathVariable Long id) {
		buyService.deleteBuy(id);
		return "redirect:/buys";
	}

}
