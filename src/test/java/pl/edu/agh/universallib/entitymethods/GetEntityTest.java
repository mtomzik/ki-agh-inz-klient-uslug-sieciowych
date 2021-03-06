package pl.edu.agh.universallib.entitymethods;

import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import pl.edu.agh.universallib.api.handler.PodcastDataHandler;
import pl.edu.agh.universallib.entity.example.PodcastListMethods;
import pl.edu.agh.universallib.entity.exception.EntityMethodsException;
import pl.edu.agh.universallib.url.WebServiceType;
import pl.edu.agh.universallib.util.PropertiesLoader;

public class GetEntityTest {

	private PodcastListMethods pm;
	private PodcastDataHandler dataHandler;

	@Before
	public void prepareEntity() throws EntityMethodsException {
		pm = new PodcastListMethods(PropertiesLoader.getWebServiceAddress(), WebServiceType.REST);
		dataHandler = new PodcastDataHandler();
		pm.deleteAll(dataHandler);
		pm.create(
				"{\"title\":\"SomeTitle\",\"linkOnPodcastpedia\":\"http://google.com\",\"feed\":\"http://googlee.com\",\"description\":\"testDescription\"}",
				dataHandler);
		dataHandler = new PodcastDataHandler();
	}

	@Test
	public void getEntityTest() throws EntityMethodsException, InterruptedException {
		pm.get(1, dataHandler);
		System.out.println("This should be printed first");
		while (dataHandler.getResult() == null && dataHandler.getError() == null) {
			System.out.println("Waiting for response...");
			Thread.sleep(1000);
		}
		assertNull(dataHandler.getError());
	}
}
