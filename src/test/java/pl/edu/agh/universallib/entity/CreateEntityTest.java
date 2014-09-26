package pl.edu.agh.universallib.entity;

import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import pl.edu.agh.universallib.api.handler.DataHandler;
import pl.edu.agh.universallib.entity.example.PodcastMethods;
import pl.edu.agh.universallib.url.WebServiceType;

public class CreateEntityTest {

	private PodcastMethods pm;

	@Before
	public void prepareEntity(){
		pm = new PodcastMethods("http://localhost:8888/springrestdemo-0.0.1-SNAPSHOT/", WebServiceType.REST);
	}
	
	@Test
	public void test() throws EntityException {
		DataHandler handler = pm.create("{\"title\":\"SomeTitle1\",\"linkOnPodcastpedia\":\"http://goog332le1.com\",\"feed\":\"http://go1o2332gle.com\",\"description\":\"testDescription\",\"insertionDate\":1389295270000}");
		assertNull(handler.getError());
	}

}
