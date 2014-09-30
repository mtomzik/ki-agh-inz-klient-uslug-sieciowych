package pl.edu.agh.universallib.entity;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import pl.edu.agh.universallib.api.handler.DataHandler;
import pl.edu.agh.universallib.entity.example.PodcastMethods;
import pl.edu.agh.universallib.url.WebServiceType;

public class GetEntitiesTest {

	private PodcastMethods pm;

	@Before
	public void prepareEntity() throws EntityException {
		pm = new PodcastMethods(
				"http://localhost:8888/springrestdemo-0.0.1-SNAPSHOT",
				WebServiceType.REST);
		pm.deleteAll();
		pm.create("{\"title\":\"SomeTitle\",\"linkOnPodcastpedia\":\"http://google.com\",\"feed\":\"http://googlee.com\",\"description\":\"testDescription\"}");
		pm.create("{\"title\":\"Some2Title\",\"linkOnPodcastpedia\":\"http://google2.com\",\"feed\":\"http://googlee2.com\",\"description\":\"testDescription2\"}");
	}

	@Test
	public void getEntityTest() throws EntityException {
		DataHandler result = pm.getAll();
		assertNull(result.getError());
		System.out.println(result.getData());
		assertTrue(result.getData().contains("\"title\":\"SomeTitle\",\"linkOnPodcastpedia\":\"http://google.com\",\"feed\":\"http://googlee.com\",\"description\":\"testDescription\""));
		assertTrue(result.getData().contains("\"title\":\"Some2Title\",\"linkOnPodcastpedia\":\"http://google2.com\",\"feed\":\"http://googlee2.com\",\"description\":\"testDescription2\""));
	}
}
