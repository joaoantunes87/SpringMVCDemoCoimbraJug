package pt.coimbrajug.springmvcdemo.controllers;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pt.coimbrajug.springmvcdemo.models.ToDo;

@Controller
@RequestMapping("/todos")
public class ToDoController {

	@RequestMapping(method = RequestMethod.GET)
	public String index(Locale locale, Model model) {
		model.addAttribute("tasks", ToDo.fetchAll());
		return "/todos/index";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public String show(@PathVariable int id, Locale locale, Model model) {
		ToDo task = new ToDo();
		task.setId(Integer.valueOf(id));
		task.fetch();
		model.addAttribute("task", task);
		return "/todos/show";
	}	
	
	@RequestMapping(method = RequestMethod.POST)
	public String create(ToDo task, Locale locale, Model model) {
		if (task != null) {
			task.save();
		}
		return "redirect:/todos/" + task.getId();
	}
}
