package pl.edu.agh.universallib.entitylist;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import pl.edu.agh.universallib.entity.Entity;
import pl.edu.agh.universallib.entity.example.PodcastList;
import pl.edu.agh.universallib.entity.exception.EntityException;

public class MapPodcastsFromJsonTest {

	private String json;
	private PodcastList podcastList;
	
	@Before
	public void setUp(){
		json = "[{\"id\":1,\"title\":\"SomeTitle\",\"linkOnPodcastpedia\":\"http://google.com\",\"feed\":\"http://googlee.com\",\"description\":\"testDescription\",\"insertionDate\":1416789815000},{\"id\":2,\"title\":\"Some2Title\",\"linkOnPodcastpedia\":\"http://google2.com\",\"feed\":\"http://googlee2.com\",\"description\":\"testDescription2\",\"insertionDate\":1416789815000}]";
		podcastList = new PodcastList();
	}
	
	@Test
	public void test() throws EntityException {
		List<Entity> testResults = podcastList.mapEntities(json);
		assertTrue(testResults.size() == 2);
	}

}
