package pl.edu.agh.universallib.entitymethods;

import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import pl.edu.agh.universallib.api.handler.PodcastDataHandler;
import pl.edu.agh.universallib.entity.example.PodcastListMethods;
import pl.edu.agh.universallib.entity.exception.EntityMethodsException;
import pl.edu.agh.universallib.url.WebServiceType;
import pl.edu.agh.universallib.util.PropertiesLoader;

public class DeleteAllEntitiesTest {

	private PodcastListMethods pm;
	private PodcastDataHandler dataHandler;

	@Before
	public void prepareEntity() {
		pm = new PodcastListMethods(PropertiesLoader.getWebServiceAddress(), WebServiceType.REST);
		dataHandler = new PodcastDataHandler();
	}

	@Test
	public void test() throws EntityMethodsException, InterruptedException {
		pm.deleteAll(dataHandler);
		Thread.sleep(1000L);
		assertNull(dataHandler.getError());
	}

}
