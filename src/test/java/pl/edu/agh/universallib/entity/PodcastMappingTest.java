package pl.edu.agh.universallib.entity;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.junit.Before;
import org.junit.Test;

import pl.edu.agh.universallib.entity.example.Podcast;
import pl.edu.agh.universallib.entity.example.PodcastListMethods;
import pl.edu.agh.universallib.entity.exception.EntityMethodsException;
import pl.edu.agh.universallib.url.WebServiceType;

public class PodcastMappingTest {

	private PodcastListMethods podcastMethods;
	private Podcast podcastExpectedEntity;

	@Before
	public void prepareEntity() throws EntityMethodsException {
		//TODO: konfigurator, connector? factory
		podcastMethods = new PodcastListMethods(
				"http://localhost:8888/springrestdemo-0.0.1-SNAPSHOT",
				WebServiceType.REST);
		podcastMethods.deleteAll(new MyDataHandler());
		podcastMethods.create("{\"title\":\"SomeTitle\",\"linkOnPodcastpedia\":\"http://google.com\",\"feed\":\"http://googlee.com\",\"description\":\"testDescription\"}", new MyDataHandler());
		podcastExpectedEntity = new Podcast();
		podcastExpectedEntity.setTitle("SomeTitle");
		podcastExpectedEntity.setLinkOnPodcastpedia("http://google.com");
		podcastExpectedEntity.setFeed("http://googlee.com");
		podcastExpectedEntity.setDescription("testDescription");
	}

	@Test
	public void mapEntityTest() throws EntityMethodsException, JsonParseException, JsonMappingException, IOException {
		MyDataHandler dataHandler = new MyDataHandler();
		podcastMethods.get(1, dataHandler);
		Podcast testPodcastEntity = new Podcast();
		testPodcastEntity = (Podcast) podcastExpectedEntity.mapEntity(dataHandler.getData());
		assertEquals(podcastExpectedEntity.getTitle(), testPodcastEntity.getTitle());
		assertEquals(podcastExpectedEntity.getLinkOnPodcastpedia(), testPodcastEntity.getLinkOnPodcastpedia());
		assertEquals(podcastExpectedEntity.getFeed(),testPodcastEntity.getFeed());
		assertEquals(podcastExpectedEntity.getDescription(),testPodcastEntity.getDescription());
	}
	
}
