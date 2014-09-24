package pl.edu.agh.universallib.entity;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import pl.edu.agh.universallib.entity.example.PodcastMethods;
import pl.edu.agh.universallib.url.WebServiceType;

public class GetEntityTest {

	private PodcastMethods pm;
	
	@Before
	public void prepareEntity(){
		pm = new PodcastMethods("http://localhost:8888/springrestdemo-0.0.1-SNAPSHOT", WebServiceType.REST);
	}
	
	@Test
	public void getEntityTest() throws EntityException{
		assertEquals("Quarks & Co - zum Mitnehmen", pm.get(1).getData().get("title"));
	}
}
