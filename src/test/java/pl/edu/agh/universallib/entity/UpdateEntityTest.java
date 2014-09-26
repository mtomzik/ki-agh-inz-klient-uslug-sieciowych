package pl.edu.agh.universallib.entity;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import pl.edu.agh.universallib.entity.example.PodcastMethods;
import pl.edu.agh.universallib.url.WebServiceType;

public class UpdateEntityTest {

	private PodcastMethods pm;

	@Before
	public void prepareEntity(){
		pm = new PodcastMethods("http://localhost:8888/springrestdemo-0.0.1-SNAPSHOT/", WebServiceType.REST);
	}
	
	@Test
	public void test() throws EntityException {
		assertNull(pm.update("{\"title\":\"SomeTitleAfterFourthChange\"}", 1).getError());
	}

}
