package de.csgt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.csgt.domain.Assignment;
import de.csgt.service.AssignmentService;
import de.csgt.service.ShopService;

@Controller
public class AssignmentController {
	
	@Autowired
	private AssignmentService assignmentService;
	
	@Autowired
	private ShopService shopService;
	
    @RequestMapping(value = "/assignments", method = RequestMethod.GET)
    public String list(Model model){
        model.addAttribute("assignments", assignmentService.listAllAssignments());
        return "assignments";
    }

	@RequestMapping("assignment/new")
	public String newAssignment(Model model) {
		model.addAttribute("assignment", new Assignment());
		model.addAttribute("shops", shopService.listAllShops());
		return "assignmentform";
	}

	@RequestMapping(value = "assignment", method = RequestMethod.POST)
	public String saveAssignment(Assignment assignment) {
		assignmentService.saveAssignment(assignment);
		return "redirect:/assignment/" + assignment.getId();
	}
	
    @RequestMapping("assignment/{id}")
    public String showAssignment(@PathVariable Long id, Model model){
        model.addAttribute("assignment", assignmentService.getAssignmentById(id));
        return "assignmentshow";
    }

	@RequestMapping("assignment/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		model.addAttribute("assignment", assignmentService.getAssignmentById(id));
		model.addAttribute("shops", shopService.listAllShops());
		return "assignmentform";
	}

	@RequestMapping("assignment/delete/{id}")
	public String delete(@PathVariable Long id) {
		assignmentService.deleteAssignment(id);
		return "redirect:/assignments";
	}
}
