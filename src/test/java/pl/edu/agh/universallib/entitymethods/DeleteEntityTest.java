package pl.edu.agh.universallib.entitymethods;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import pl.edu.agh.universallib.api.handler.DataHandler;
import pl.edu.agh.universallib.entity.example.PodcastMethods;
import pl.edu.agh.universallib.entity.exception.EntityMethodsException;
import pl.edu.agh.universallib.url.WebServiceType;

public class DeleteEntityTest {

	private PodcastMethods pm;

	@Before
	public void prepareEntity() throws EntityMethodsException {
		pm = new PodcastMethods(
				"http://localhost:8888/springrestdemo-0.0.1-SNAPSHOT/",
				WebServiceType.REST);
		pm.deleteAll();
		pm.create("{\"title\":\"SomeTitle\",\"linkOnPodcastpedia\":\"http://google.com\",\"feed\":\"http://googlee.com\",\"description\":\"testDescription\",\"insertionDate\":1389295270000}");
	}

	@Test
	public void test() throws EntityMethodsException {
		DataHandler dh = pm.delete(1);
		assertNull(dh.getError());
		assertEquals("204",dh.getData());
		assertNull(pm.get(1).getData());
	}

}
