package pl.edu.agh.universallib.entitylist;

import java.util.List;

import pl.edu.agh.universallib.entity.Entity;

public class EntityList extends Entity {

	private List<Entity> entities;

	public List<Entity> getEntities() {
		return entities;
	}

	public void setEntities(List<Entity> entities) {
		this.entities = entities;
	}

}
