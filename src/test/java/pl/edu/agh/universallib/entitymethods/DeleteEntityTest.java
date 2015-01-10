package pl.edu.agh.universallib.entitymethods;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import pl.edu.agh.universallib.api.handler.PodcastDataHandler;
import pl.edu.agh.universallib.entity.example.PodcastListMethods;
import pl.edu.agh.universallib.entity.exception.EntityMethodsException;
import pl.edu.agh.universallib.url.WebServiceType;
import pl.edu.agh.universallib.util.PropertiesLoader;

public class DeleteEntityTest {

	private PodcastListMethods pm;
	private PodcastDataHandler dataHandler;

	@Before
	public void prepareEntity() throws EntityMethodsException {
		pm = new PodcastListMethods(
				PropertiesLoader.getWebServiceAddress(),
				WebServiceType.REST);
		dataHandler = new PodcastDataHandler();
		pm.deleteAll(dataHandler);
		pm.create(
				"{\"title\":\"SomeTitle\",\"linkOnPodcastpedia\":\"http://google.com\",\"feed\":\"http://googlee.com\",\"description\":\"testDescription\",\"insertionDate\":1389295270000}",
				dataHandler);
		dataHandler = new PodcastDataHandler();
	}

	@Test
	public void test() throws EntityMethodsException {
		pm.delete(1, dataHandler);
		assertNull(dataHandler.getError());
		assertEquals("204", dataHandler.getData());
		dataHandler = new PodcastDataHandler();
		pm.get(1, dataHandler);
		assertNull(dataHandler.getData());
	}

}
