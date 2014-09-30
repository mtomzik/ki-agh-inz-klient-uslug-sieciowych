package pl.edu.agh.universallib.entity;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

abstract public class Entity {

	ObjectMapper objectMapper = new ObjectMapper();
	
	public Entity mapEntityFromJson(String json) throws JsonParseException, JsonMappingException, IOException {
		return objectMapper.readValue(json, this.getClass());
	}
	
}
