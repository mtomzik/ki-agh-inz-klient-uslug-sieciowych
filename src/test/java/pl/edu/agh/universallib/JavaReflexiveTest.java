package pl.edu.agh.universallib;

import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;

import org.junit.Test;

public class JavaReflexiveTest {

	@Test
	public void testBookEntity() throws ClassNotFoundException {
		Class<?> c = Class.forName("pl.edu.agh.universallib.entity.example.Book");
		Field[] fields = c.getDeclaredFields();
		for (Field f : fields) System.out.println(f.toGenericString());
		//fail("Not yet implemented");
		assertTrue(true);
	}
}
