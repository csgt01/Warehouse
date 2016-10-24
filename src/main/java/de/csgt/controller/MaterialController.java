package de.csgt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.csgt.domain.Buy;
import de.csgt.domain.Color;
import de.csgt.domain.Material;
import de.csgt.service.BuyServiceInterface;
import de.csgt.service.MaterialServiceInterface;

@Controller
public class MaterialController {
	
	@Autowired
	private MaterialServiceInterface materialService;
	
	@Autowired
	private BuyServiceInterface buyService;

	@RequestMapping(value = "/materials", method = RequestMethod.GET)
    public String list(Model model){
        model.addAttribute("materials", materialService.listAllMaterials());
        return "materials";
    }

	@RequestMapping("material/new")
	public String newMaterial(Model model) {
		model.addAttribute("material", new Material());
		model.addAttribute("colors", Color.values());
		return "materialform";
	}

	@RequestMapping(value = "material", method = RequestMethod.POST)
	public String saveMaterial(Material material) {
		Material mat = null;
		if (material.getId() != null) {
			mat = materialService.getMaterialById(material.getId());
		}
		if (mat != null && mat.getAvailable() != material.getAvailable()) {
			int difference = mat.getAvailable() - material.getAvailable();
			List<Buy> listAllBuysByMaterialAndNotSold = (List<Buy>) buyService.listAllBuysByMaterialAndNotSold(material);
			int tempDifference = difference;
			// in der form wurde weniger eingegeben!
			if (difference > 0) {
				for (Buy buy : listAllBuysByMaterialAndNotSold) {
					if (tempDifference == 0) {
						break;
					}
					int buyQuantity = buy.getQuantity() - buy.getSoldInt();
					if (buyQuantity > tempDifference) {
						buy.setSold(false);
						buy.setSoldInt(buy.getSoldInt() + tempDifference);
						buy = buyService.saveBuy(buy);
						break;
					} else if (buyQuantity == tempDifference) {
						buy.setSold(true);
						buy.setSoldInt(buy.getSoldInt() + tempDifference);
						buyService.saveBuy(buy);
						break;
					} else {
						int toSell = buy.getQuantity() - buy.getSoldInt();
						tempDifference = tempDifference - toSell;
						buy.setSold(true);
						buy.setSoldInt(buy.getQuantity());
						buyService.saveBuy(buy);
					}
				}
			}
		}
		materialService.saveMaterial(material);
		return "redirect:/material/" + material.getId();
	}
	
    @RequestMapping("material/{id}")
    public String showMaterial(@PathVariable Integer id, Model model){
        model.addAttribute("material", materialService.getMaterialById(id));
        return "materialshow";
    }

	@RequestMapping("material/edit/{id}")
	public String edit(@PathVariable Integer id, Model model) {
		
		model.addAttribute("material", materialService.getMaterialById(id));
		model.addAttribute("colors", Color.values());
		return "materialform";
	}

	@RequestMapping("material/delete/{id}")
	public String delete(@PathVariable Integer id) {
		materialService.deleteMaterial(id);
		return "redirect:/materials";
	}
}
