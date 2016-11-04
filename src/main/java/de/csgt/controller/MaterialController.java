package de.csgt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.csgt.domain.Buy;
import de.csgt.domain.Color;
import de.csgt.domain.Material;
import de.csgt.domain.Search;
import de.csgt.service.BuyService;
import de.csgt.service.MaterialService;

@Controller
public class MaterialController {
	
	@Autowired
	private MaterialService materialService;
	
	@Autowired
	private BuyService buyService;

	@RequestMapping(value = "/materials", method = RequestMethod.GET)
    public String list(Model model, Pageable pageable) {
		PageRequest req = new PageRequest(pageable.getPageNumber(), 20, Sort.Direction.ASC, "name");
		Page<Material> pag = materialService.listAllMaterialsPage(req);
        model.addAttribute("page", pag);
        model.addAttribute("colors", Color.values());
        model.addAttribute("search", new Search());
        return "materials";
    }
	
	@RequestMapping(value = "/material/search", method = RequestMethod.POST)
    public String search(@ModelAttribute("search") Search search, Model model, Pageable pageable){
		PageRequest req = new PageRequest(pageable.getPageNumber(), 20, Sort.Direction.ASC, "name");
        model.addAttribute("page", materialService.listAllMaterialsPage(req, search));
        
        model.addAttribute("colors", Color.values());
        model.addAttribute("search", search);
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
			} else if (difference < 0) {
				throw new RuntimeException("Nur weniger als vorher!");
			}
		}
		materialService.saveMaterial(material);
		return "redirect:/material/" + material.getId();
	}
	
    @RequestMapping("material/{id}")
    public String showMaterial(@PathVariable Integer id, Model model){
        Material materialById = materialService.getMaterialById(id);
		model.addAttribute("material", materialById);
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
