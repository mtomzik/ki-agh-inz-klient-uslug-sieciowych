package pl.edu.agh.universallib.entity;

import java.lang.reflect.Field;

abstract public class Entity {

	private Field[] fields;
	
	public Field[] getFields() {
		return fields;
	}

	public Entity(){
	}
}
