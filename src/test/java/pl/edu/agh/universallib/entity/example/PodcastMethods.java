package pl.edu.agh.universallib.entity.example;

import java.util.List;

import pl.edu.agh.universallib.entity.Entity;
import pl.edu.agh.universallib.entity.EntityMethods;

public class PodcastMethods implements EntityMethods {
	
	public String idPrefix = "";
	public String urlPart = "podcasts";
	
	@Override
	public List<Entity> getAll() {
		return null;
	}

	@Override
	public Entity get(long id) {
		return null;
	}

	@Override
	public void create(Entity e) {
	}

	@Override
	public void update(Entity e) {
	}

	@Override
	public void delete(long id) {
	}

	@Override
	public String getIdPrefix() {
		return this.idPrefix;
	}

	@Override
	public String getUrlPart() {
		return this.urlPart;
	}
}
