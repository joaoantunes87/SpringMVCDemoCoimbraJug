package pt.coimbrajug.springmvcdemo.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class that represents the Resource ToDo
 * This class follows the pattern ActiveRecord. In this case the for simplicity's sake,
 * data will be stored in memory and not in a Database
 */
public class ToDo {

	/** The incremental id. */
	private static int incrementalId = 0;
	
	/** The tasks. */
	private static Map<Integer, ToDo> tasks = new HashMap<>(0);

	/** The id. */
	private Integer id;
	
	/** The description. */
	private String description;
	
	/** The assignee name. */
	private String assigneeName;

	/**
	 * Instantiates a new to do.
	 */
	public ToDo() {

	}

	/**
	 * Instantiates a new to do.
	 *
	 * @param id the id
	 * @param description the description
	 * @param assigneeName the assignee name
	 */
	public ToDo(int id, String description, String assigneeName) {
		this.id = Integer.valueOf(id);
		this.description = description;
		this.assigneeName = assigneeName;
	}

	/**
	 * Generate next id -- simulate a sequence
	 *
	 * @return the int
	 */
	private static int generateId() {
		return ++incrementalId;
	}

	/** ActiveRecord Operations **/
	
	/**
	 * Fetch all Tasks.
	 *
	 * @param description the description
	 * @return the list
	 */
	public static List<ToDo> fetchAll(String description) {
		Collection<ToDo> tasksCollection = tasks.values();
		if ( description != null && !description.isEmpty()) {
			List<ToDo> filteredTasks = new ArrayList<ToDo>(0);
			for (ToDo task : tasksCollection) {
				if ( task.getDescription().contains(description)) {
					filteredTasks.add(task);
				}
			}
			return filteredTasks;
			
		}
		return new ArrayList<ToDo>(tasks.values());
	}

	/**
	 * Fill the ToDo.this with the data fetch from memory
	 *
	 * @return the to do
	 */
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

	/**
	 * Save the Task. If the Task already exists update if not create a new one
	 *
	 * @return true, if successful
	 */
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
	
	/**
	 * Delete the Taks with this.id
	 *
	 * @return true, if successful
	 */
	public boolean delete() {
		
		if (this.getId() != null) {
			if (tasks.containsKey(this.getId())) {
				tasks.remove(this.getId());
				return true;
			}
		}
		
		return false;
		
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the assignee name.
	 *
	 * @return the assignee name
	 */
	public String getAssigneeName() {
		return assigneeName;
	}

	/**
	 * Sets the assignee name.
	 *
	 * @param assigneeName the new assignee name
	 */
	public void setAssigneeName(String assigneeName) {
		this.assigneeName = assigneeName;
	}

}
