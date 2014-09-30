package pl.edu.agh.universallib.entitymethods;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import pl.edu.agh.universallib.entity.EntityException;
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
		pm.create("{\"title\":\"SomeTitle\",\"linkOnPodcastpedia\":\"http://google.com\",\"feed\":\"http://googlee.com\",\"description\":\"testDescription\"}");
	}

	@Test
	public void getEntityTest() throws EntityException {
		String result = pm.get(1).getData();
		assertTrue(result.contains("\"title\":\"SomeTitle\",\"linkOnPodcastpedia\":\"http://google.com\",\"feed\":\"http://googlee.com\",\"description\":\"testDescription\""));
	}
}
