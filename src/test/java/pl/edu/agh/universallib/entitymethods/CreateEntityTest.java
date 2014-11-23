package pl.edu.agh.universallib.entitymethods;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import pl.edu.agh.universallib.entity.MyDataHandler;
import pl.edu.agh.universallib.entity.example.PodcastListMethods;
import pl.edu.agh.universallib.entity.exception.EntityMethodsException;
import pl.edu.agh.universallib.url.WebServiceType;
import pl.edu.agh.universallib.util.PropertiesLoader;

public class CreateEntityTest {

	
	private PodcastListMethods pm;
	
	private MyDataHandler dataHandler;

	@Before
	public void prepareEntity() throws EntityMethodsException {
		pm = new PodcastListMethods(
				PropertiesLoader.getWebServiceAddress(),
				WebServiceType.REST);
		dataHandler = new MyDataHandler();
		pm.deleteAll(dataHandler);
	}

	@Test
	public void test() throws EntityMethodsException {
		pm.create("{\"title\":\"SomeTitle\",\"linkOnPodcastpedia\":\"http://google.com\",\"feed\":\"http://googlee.com\",\"description\":\"testDescription\"}", dataHandler);
		assertNull(dataHandler.getError());
		pm.get(1, dataHandler);
		String data = dataHandler.getData();
		if (data.charAt(0) == '{')
			assertTrue(data.contains("\"title\":\"SomeTitle\",\"linkOnPodcastpedia\":\"http://google.com\",\"feed\":\"http://googlee.com\",\"description\":\"testDescription\""));
		else if (data.charAt(0) == '<'){
			assertTrue(data.contains("testDescription"));
			assertTrue(data.contains("http://google.com"));
			assertTrue(data.contains("http://googlee.com"));
			assertTrue(data.contains("SomeTitle"));			
		}
		else fail();
		
		
	}

}
