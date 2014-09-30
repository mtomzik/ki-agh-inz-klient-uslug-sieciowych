package pl.edu.agh.universallib.entitymethods;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import pl.edu.agh.universallib.api.handler.DataHandler;
import pl.edu.agh.universallib.entity.example.PodcastMethods;
import pl.edu.agh.universallib.entity.exception.EntityMethodsException;
import pl.edu.agh.universallib.url.WebServiceType;

public class DeleteAllEntitiesTest {

	private PodcastMethods pm;

	@Before
	public void prepareEntity() {
		pm = new PodcastMethods(
				"http://localhost:8888/springrestdemo-0.0.1-SNAPSHOT/",
				WebServiceType.REST);
	}

	@Test
	public void test() throws EntityMethodsException {
		DataHandler dataHandler = pm.deleteAll();
		assertEquals("200", dataHandler.getData());
		assertNull(dataHandler.getError());
	}

}
