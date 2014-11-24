package pl.edu.agh.universallib.entitylist;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import pl.edu.agh.universallib.entity.Entity;
import pl.edu.agh.universallib.entity.example.BookList;
import pl.edu.agh.universallib.entity.exception.EntityException;
import pl.edu.agh.universallib.util.StreamToStringConverter;

public class MapBooksFromXmlTest {

	private String Xml;
	
	@Before
	public void setUp() throws IOException{
		Xml = StreamToStringConverter.convertStreamToString(MapBooksFromXmlTest.class.getClassLoader().getResourceAsStream("books.xml"));
	}
	
	@Test
	public void testMapEntities() throws EntityException {
		List<Entity> testResults = new BookList().mapEntities(Xml);
		assertTrue(testResults.size() == 4);
	}

}
