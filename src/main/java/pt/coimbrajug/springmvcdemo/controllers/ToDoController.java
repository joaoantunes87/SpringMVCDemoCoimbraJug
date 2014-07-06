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

/**
 * Controller to Prepare Data to Present information about ToDo resource
 *	Features
 * 	GET /todos - Web Page for all tasks
 *  POST /todos - To process data sent through Form in /todos
 *  GET /todos/{id} - Web Page for ToDo with specified id
 *  PUT /todos/{id} - Rest Api Request in JSON to edit the ToDo with specified id
 *  DELETE /todos/{id} - Rest Api Request to delete the ToDo with specified id
 */
@Controller
@RequestMapping("/todos")
public class ToDoController {

	/**
	 * Index.
	 *
	 * @param search the search
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String index(String search, Model model) {
		model.addAttribute("tasks", ToDo.fetchAll(search));
		return "/todos/index";
	}
	
	/**
	 * Show.
	 *
	 * @param id the id
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public String show(@PathVariable int id, Model model) {
		ToDo task = new ToDo();
		task.setId(Integer.valueOf(id));
		task.fetch();
		model.addAttribute("task", task);
		return "/todos/show";
	}
	
	/**
	 * Gets the task in json.
	 *
	 * @param id the id
	 * @return the task in json
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/{id}", consumes = "application/json")
	public @ResponseBody ToDo getTaskInJSON(@PathVariable int id) {
 
		ToDo task = new ToDo();
		task.setId(Integer.valueOf(id));
		task.fetch();
		
		return task;
 
	}	
	
	/**
	 * Creates the.
	 *
	 * @param task the task
	 * @return the string
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String create(ToDo task) {
		if (task != null) {
			task.save();
		}
		return "redirect:/todos/" + task.getId();
	}
	
	/**
	 * Edits the.
	 *
	 * @param id the id
	 * @param task the task
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}", consumes = "application/json")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void edit(@PathVariable int id, @RequestBody ToDo task) {	

		if (task != null) {
			task.setId(id);
			task.save();
		}

	}
	
	/**
	 * Delete.
	 *
	 * @param id the id
	 * @return the response entity
	 */
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
