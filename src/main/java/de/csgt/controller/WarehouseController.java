package de.csgt.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import de.csgt.domain.Assignment;
import de.csgt.domain.Sell;
import de.csgt.domain.Statistic;
import de.csgt.service.AssignmentService;
import de.csgt.service.SellService;

@Controller
public class WarehouseController {
	
	@Autowired
	private AssignmentService assService;
	
	@Autowired
	private SellService sellService;
	
	@RequestMapping("/")
    String index(Model model) {
		Iterable<Assignment> listAllAssignments = assService.listAllAssignments();
		model.addAttribute("assignments", listAllAssignments);
		Iterable<Sell> listAllSells = sellService.listAllSells();
		model.addAttribute("sells", listAllSells);
		Statistic stat = new Statistic();
		Double in = 0.0;
		for (Sell sell : listAllSells) {
			in += (sell.getPrice()*sell.getQuantity());
		}
		Double out = 0.0;
		for (Assignment ass : listAllAssignments) {
			out += ass.getPrice();
		}
		stat.setIn(in);
		stat.setOut(out);
		List<Statistic> stats = new ArrayList<Statistic>();
		stats.add(stat);
		model.addAttribute("statistics", stats);
        return "index";
    }
}
