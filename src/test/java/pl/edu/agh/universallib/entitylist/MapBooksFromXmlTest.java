package pl.edu.agh.universallib.entitylist;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import pl.edu.agh.universallib.entity.Entity;
import pl.edu.agh.universallib.entity.example.BookList;
import pl.edu.agh.universallib.entity.exception.EntityException;
import pl.edu.agh.universallib.util.StreamToStringConverter;

public class MapBooksFromXmlTest {

	@Test
	public void testMapEntities() throws EntityException, IOException {
		String xml = StreamToStringConverter.convertStreamToString(MapBooksFromXmlTest.class.getClassLoader().getResourceAsStream("books.xml"));

		List<Entity> testResults = new BookList().mapEntities(xml);
		assertTrue(testResults.size() == 4);
	}

	@Test(expected = EntityException.class)
	public void testMalformedXml() throws EntityException {
		String xml = "This is not an XML";
		new BookList().mapEntities(xml);
	}

}
