package pl.edu.agh.universallib.entity;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import pl.edu.agh.universallib.entity.example.PodcastMethods;
import pl.edu.agh.universallib.url.WebServiceType;

public class GetEntityTest {

	private PodcastMethods pm;

	@Before
	public void prepareEntity() throws EntityException {
		pm = new PodcastMethods(
				"http://localhost:8888/springrestdemo-0.0.1-SNAPSHOT",
				WebServiceType.REST);
		pm.deleteAll();
		pm.create("{\"title\":\"SomeTitle\",\"linkOnPodcastpedia\":\"http://google.com\",\"feed\":\"http://googlee.com\",\"description\":\"testDescription\",\"insertionDate\":1389295270000}");
	}

	@Test
	public void getEntityTest() throws EntityException {
		assertEquals("SomeTitle", pm.get(1).getData().get("title"));
	}
}
