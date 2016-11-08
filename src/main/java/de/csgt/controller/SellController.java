package de.csgt.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import net.coobird.thumbnailator.Thumbnails;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Base64Utils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import de.csgt.MaterialLoader;
import de.csgt.domain.Sell;
import de.csgt.domain.SellMaterial;
import de.csgt.service.MaterialService;
import de.csgt.service.ProductService;
import de.csgt.service.SellService;

@Controller
public class SellController {
	
	@Autowired
	private ProductService productService;
	@Autowired
	private MaterialService materialService;
	
	@Autowired
	private SellService sellService;
	
	private Logger log = Logger.getLogger(MaterialLoader.class);
	
    @RequestMapping(value = "sells", method = RequestMethod.GET)
    public String list(Model model){
    	model.addAttribute("sells", sellService.listAllSells());
        return "sells";
    }

	@RequestMapping("sell/new")
	public String newSell(Model model) {
		Sell sell = new Sell();
		model.addAttribute("sell", sell);
		model.addAttribute("products", productService.listAllProducts());
		model.addAttribute("materials", materialService.listAllMaterials());
		return "sellform";
	}
	
	@RequestMapping(value = "sell", method = RequestMethod.POST)
    public String checkPersonInfo(@Valid Sell sell, BindingResult bindingResult, Model model, HttpServletRequest req, MultipartFile file) {
		log.info("sell post:" + req.getRequestURL() + " " + req.getQueryString() + " ");
		log.info("sell post:" + sell.toString());
        if (bindingResult.hasErrors()) {
        	System.out.println("bindingResult:" + bindingResult.toString());
        	model.addAttribute("products", productService.listAllProducts());
        	model.addAttribute("sell", sell);
        	model.addAttribute("fields", bindingResult);
            return "sellform";
        }
        if (file != null) {
			String base64;
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			try {
				Thumbnails.of(file.getInputStream()).forceSize(200, 200).outputFormat("jpg").toOutputStream(out);
				base64 = Base64Utils.encodeToString(out.toByteArray());
				sell.setFoto(base64);
			} catch (IOException e) {
				System.out.println(e);
			}
		}
		Sell sellSaved = sellService.saveSell(sell);
        return "redirect:/sell/" + sellSaved.getId();
    }
	
    @RequestMapping("sell/{id}")
    public String showProduct(@PathVariable Long id, Model model){
        model.addAttribute("sell", sellService.getSellById(id));
        return "sellshow";
    }
    

	@RequestMapping("sell/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		
		model.addAttribute("products", productService.listAllProducts());
		model.addAttribute("materials", materialService.listAllMaterials());
		model.addAttribute("sell", sellService.getSellById(id));
		return "sellform";
	}

	@RequestMapping("sell/delete/{id}")
	public String delete(@PathVariable Long id) {
		sellService.deleteSell(id);
		return "redirect:/sells";
	}
	
	@RequestMapping(value="sell", params={"addRow"})
	public String addRow(final Sell sell, final BindingResult bindingResult, Model model) {
	    List<SellMaterial> sellMaterials = sell.getSellMaterials();
	    if (sellMaterials == null) {
	    	sell.setSellMaterials(new ArrayList<SellMaterial>());
	    }
	    sell.getSellMaterials().add(new SellMaterial(sell));
	    model.addAttribute("products", productService.listAllProducts());
		model.addAttribute("materials", materialService.listAllMaterials());
		model.addAttribute("sell", sell);
	    return "sellform";
	}

	
	@RequestMapping(value="sell", params={"removeRow"})
	public String removeRow(final Sell sell, final BindingResult bindingResult, Model model, final HttpServletRequest req) {
		int parameter = Integer.valueOf(req.getParameter("removeRow"));
		log.info("remove:" + parameter);
	    sell.getSellMaterials().remove((int) parameter);
	    model.addAttribute("products", productService.listAllProducts());
		model.addAttribute("materials", materialService.listAllMaterials());
		model.addAttribute("sell", sell);
	    return "sellform";
	}
}
