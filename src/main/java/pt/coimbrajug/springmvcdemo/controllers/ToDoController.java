package pt.coimbrajug.springmvcdemo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import pt.coimbrajug.springmvcdemo.models.ToDo;

@Controller
@RequestMapping("/todos")
public class ToDoController {

	@RequestMapping(method = RequestMethod.GET)
	public String index(String search, Model model) {
		model.addAttribute("tasks", ToDo.fetchAll(search));
		return "/todos/index";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public String show(@PathVariable int id, Model model) {
		ToDo task = new ToDo();
		task.setId(Integer.valueOf(id));
		task.fetch();
		model.addAttribute("task", task);
		return "/todos/show";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}", consumes = "application/json")
	public @ResponseBody ToDo getTaskInJSON(@PathVariable int id) {
 
		ToDo task = new ToDo();
		task.setId(Integer.valueOf(id));
		task.fetch();
		
		return task;
 
	}	
	
	@RequestMapping(method = RequestMethod.POST)
	public String create(ToDo task) {
		if (task != null) {
			task.save();
		}
		return "redirect:/todos/" + task.getId();
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}", consumes = "application/json")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void edit(@PathVariable int id, @RequestBody ToDo task) {	

		if (task != null) {
			task.setId(id);
			task.save();
		}

	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<String> delete(@PathVariable int id) {	

		ToDo taskToRemove = new ToDo();
		taskToRemove.setId(id);
		if (taskToRemove.delete()) {
			return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
		}
		return new  ResponseEntity<String>(HttpStatus.NOT_FOUND);

	}	
	
}
