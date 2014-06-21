package pt.coimbrajug.springmvcdemo.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.task.TaskExecutor;

public class ToDo {

	private static int incrementalId = 0;
	private static Map<Integer, ToDo> tasks = new HashMap<>(0);

	private Integer id;
	private String description;
	private String assigneeName;

	public ToDo() {

	}

	public ToDo(int id, String description, String assigneeName) {
		this.id = Integer.valueOf(id);
		this.description = description;
		this.assigneeName = assigneeName;
	}

	private static int generateId() {
		return ++incrementalId;
	}

	// ActiveRecord Operations
	public static List<ToDo> fetchAll() {
		return new ArrayList<ToDo>(tasks.values());
	}

	public ToDo fetch() {

		if (this.id != null) {
			ToDo task = tasks.get(this.id);
			if ( task != null) {
				this.setAssigneeName(task.getAssigneeName());
				this.setDescription(task.getDescription());
				return this;
			}
		}

		return null;

	}

	public boolean save() {

		// create new
		if (id == null) {
			this.id = generateId();
			tasks.put(this.id, this);
			return true;
			// update
		} else {

			ToDo task = tasks.get(this.id);
			if (task != null) {
				if (this.description != null && !this.description.isEmpty()) {
					task.setDescription(this.description);
				}

				if (this.assigneeName != null && !this.assigneeName.isEmpty()) {
					task.setAssigneeName(this.assigneeName);
				}

				return true;
			}

		}

		return false;
	}
	
	public boolean delete() {
		
		if (this.getId() != null) {
			if (tasks.containsKey(this.getId())) {
				tasks.remove(this.getId());
			}
		}
		
		return false;
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAssigneeName() {
		return assigneeName;
	}

	public void setAssigneeName(String assigneeName) {
		this.assigneeName = assigneeName;
	}

}
