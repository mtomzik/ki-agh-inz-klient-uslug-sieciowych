package pl.edu.agh.universallib.entitymethods;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import pl.edu.agh.universallib.entity.MyDataHandler;
import pl.edu.agh.universallib.entity.example.PodcastMethods;
import pl.edu.agh.universallib.entity.exception.EntityMethodsException;
import pl.edu.agh.universallib.url.WebServiceType;

public class GetEntitiesTest {

	private PodcastMethods pm;
	private MyDataHandler dataHandler;

	@Before
	public void prepareEntity() throws EntityMethodsException {
		pm = new PodcastMethods(
				"http://localhost:8888/springrestdemo-0.0.1-SNAPSHOT",
				WebServiceType.REST);
		dataHandler = new MyDataHandler();
		pm.deleteAll(dataHandler);
		pm.create("{\"title\":\"SomeTitle\",\"linkOnPodcastpedia\":\"http://google.com\",\"feed\":\"http://googlee.com\",\"description\":\"testDescription\"}", dataHandler);
		pm.create("{\"title\":\"Some2Title\",\"linkOnPodcastpedia\":\"http://google2.com\",\"feed\":\"http://googlee2.com\",\"description\":\"testDescription2\"}", dataHandler);
		dataHandler = new MyDataHandler();
	}

	@Test
	public void getEntityTest() throws EntityMethodsException {
		pm.getAll(dataHandler);
		assertNull(dataHandler.getError());
		assertTrue(dataHandler.getData().contains("\"title\":\"SomeTitle\",\"linkOnPodcastpedia\":\"http://google.com\",\"feed\":\"http://googlee.com\",\"description\":\"testDescription\""));
		assertTrue(dataHandler.getData().contains("\"title\":\"Some2Title\",\"linkOnPodcastpedia\":\"http://google2.com\",\"feed\":\"http://googlee2.com\",\"description\":\"testDescription2\""));
	}
}
