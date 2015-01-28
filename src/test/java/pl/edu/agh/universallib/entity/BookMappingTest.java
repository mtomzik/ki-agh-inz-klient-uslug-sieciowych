package pl.edu.agh.universallib.entity;

import java.io.IOException;

import javax.xml.bind.JAXBException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.junit.Before;
import org.junit.Test;

import pl.edu.agh.universallib.entity.example.Book;

public class BookMappingTest {
	
	private Book book;
	
	@Before
	public void init(){
		book = new Book();
		book.setPrice(25.99F);
		book.setTitle("Cos do czytania");
		book.setYear(2015);
	}
	
	@Test
	public void testMapToJsonWithNull() throws JsonGenerationException, JsonMappingException, IOException{
		System.out.println(book.mapToJson(true));
	}
	
	@Test
	public void testMapToJsonWithoutNull() throws JsonGenerationException, JsonMappingException, IOException{
		System.out.println(book.mapToJson(false));
	}
	
	@Test
	public void testMapToXml() throws JAXBException {
		System.out.println(book.mapToXml());
		Book testBook = new Book();
		testBook.mapEntity("{\"title\":\"Cos do czytania\",\"year\":2015,\"price\":25.99}");
	}
}
