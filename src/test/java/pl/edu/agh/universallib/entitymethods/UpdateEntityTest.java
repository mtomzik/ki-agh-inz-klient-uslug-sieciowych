package pl.edu.agh.universallib.entitymethods;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import pl.edu.agh.universallib.entity.MyDataHandler;
import pl.edu.agh.universallib.entity.example.PodcastMethods;
import pl.edu.agh.universallib.entity.exception.EntityMethodsException;
import pl.edu.agh.universallib.url.WebServiceType;

public class UpdateEntityTest {

	private PodcastMethods pm;
	private MyDataHandler dataHandler;
	
	@Before
	public void prepareEntity() throws EntityMethodsException {
		pm = new PodcastMethods(
				"http://localhost:8888/springrestdemo-0.0.1-SNAPSHOT/",
				WebServiceType.REST);
		dataHandler = new MyDataHandler();
		pm.deleteAll(dataHandler);
		pm.create("{\"title\":\"SomeTitle\",\"linkOnPodcastpedia\":\"http://google.com\",\"feed\":\"http://googlee.com\",\"description\":\"testDescription\",\"insertionDate\":1389295270000}", dataHandler);
		dataHandler = new MyDataHandler();
	}

	@Test
	public void test() throws EntityMethodsException {
		pm.update("{\"title\":\"SomeTitleAfterUpdate\"}",
				1, dataHandler);
		assertNull(dataHandler.getError());
		pm.get(1, dataHandler);
		assertTrue(dataHandler.getData().contains("SomeTitleAfterUpdate"));
	}

}
