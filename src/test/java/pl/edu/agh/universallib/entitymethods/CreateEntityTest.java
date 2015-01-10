package pl.edu.agh.universallib.entitymethods;

import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import pl.edu.agh.universallib.api.handler.PodcastDataHandler;
import pl.edu.agh.universallib.entity.example.PodcastListMethods;
import pl.edu.agh.universallib.entity.exception.EntityMethodsException;
import pl.edu.agh.universallib.url.WebServiceType;
import pl.edu.agh.universallib.util.PropertiesLoader;

public class CreateEntityTest {

	private PodcastListMethods pm;

	private PodcastDataHandler dataHandler;

	@Before
	public void prepareEntity() throws EntityMethodsException {
		pm = new PodcastListMethods(PropertiesLoader.getWebServiceAddress(), WebServiceType.REST);
		dataHandler = new PodcastDataHandler();
		pm.deleteAll(dataHandler);
	}

	@Test
	public void testCreateWithJson() throws EntityMethodsException, InterruptedException {
		pm.create(
				"{\"title\":\"SomeTitle\",\"linkOnPodcastpedia\":\"http://google.com\",\"feed\":\"http://googlee.com\",\"description\":\"testDescription\"}",
				dataHandler);
		Thread.sleep(1000L);
		assertNull(dataHandler.getError());
	}

}
