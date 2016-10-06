package de.csgt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.csgt.domain.Material;
import de.csgt.service.MaterialServiceInterface;

@Controller
public class MaterialController {
	
	@Autowired
	private MaterialServiceInterface materialService;
	
    @RequestMapping(value = "/materials", method = RequestMethod.GET)
    public String list(Model model){
        model.addAttribute("materials", materialService.listAllMaterials());
        return "materials";
    }

	@RequestMapping("material/new")
	public String newMaterial(Model model) {
		model.addAttribute("material", new Material());
		return "materialform";
	}

	@RequestMapping(value = "material", method = RequestMethod.POST)
	public String saveMaterial(Material material) {
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
		return "materialform";
	}

	@RequestMapping("material/delete/{id}")
	public String delete(@PathVariable Integer id) {
		materialService.deleteMaterial(id);
		return "redirect:/materials";
	}
}
