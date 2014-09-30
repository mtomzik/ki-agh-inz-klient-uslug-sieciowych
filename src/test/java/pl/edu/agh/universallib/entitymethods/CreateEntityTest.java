package pl.edu.agh.universallib.entitymethods;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import pl.edu.agh.universallib.api.handler.DataHandler;
import pl.edu.agh.universallib.entity.example.PodcastMethods;
import pl.edu.agh.universallib.entity.exception.EntityMethodsException;
import pl.edu.agh.universallib.url.WebServiceType;

public class CreateEntityTest {

	private PodcastMethods pm;

	@Before
	public void prepareEntity() throws EntityMethodsException {
		pm = new PodcastMethods(
				"http://localhost:8888/springrestdemo-0.0.1-SNAPSHOT/",
				WebServiceType.REST);
		pm.deleteAll();
	}

	@Test
	public void test() throws EntityMethodsException {
		DataHandler handler = pm
				.create("{\"title\":\"SomeTitle\",\"linkOnPodcastpedia\":\"http://google.com\",\"feed\":\"http://googlee.com\",\"description\":\"testDescription\"}");
		assertNull(handler.getError());
		String data = pm.get(1).getData();
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
