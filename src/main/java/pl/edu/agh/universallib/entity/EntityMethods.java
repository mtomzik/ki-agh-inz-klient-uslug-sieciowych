package pl.edu.agh.universallib.entity;

import java.util.List;

public interface EntityMethods {
	public List<Entity> getAll();
	public Entity get(long id);
	public void create(Entity e);
	public void update(Entity e);
	public void delete(long id);
	
	public String getIdPrefix();
	public String getUrlPart();
}
