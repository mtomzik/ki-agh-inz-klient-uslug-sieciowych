package pl.edu.agh.universallib.entity;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.junit.Before;
import org.junit.Test;

import pl.edu.agh.universallib.api.handler.PodcastDataHandler;
import pl.edu.agh.universallib.entity.example.Podcast;
import pl.edu.agh.universallib.entity.example.PodcastListMethods;
import pl.edu.agh.universallib.entity.example.PodcastMethods;
import pl.edu.agh.universallib.entity.exception.EntityMethodsException;
import pl.edu.agh.universallib.url.WebServiceType;
import pl.edu.agh.universallib.util.PropertiesLoader;

public class PodcastTestIntegration {

	private static final String ENTITY_JSON = "{\"title\":\"SomeTitle\",\"linkOnPodcastpedia\":\"http://google.com\",\"feed\":\"http://googlee.com\",\"description\":\"testDescription\"}";

	private PodcastListMethods podcastListMethods;
	private PodcastMethods podcastMethods;
	private Podcast podcastExpectedEntity;

	@Before
	public void prepareEntity() throws EntityMethodsException, InterruptedException {
		podcastListMethods = new PodcastListMethods(PropertiesLoader.getWebServiceAddress(), WebServiceType.REST);
		podcastListMethods.deleteAll(new PodcastDataHandler());
		Thread.sleep(500);
		podcastListMethods.create(ENTITY_JSON, new PodcastDataHandler());
		podcastMethods = new PodcastMethods(PropertiesLoader.getWebServiceAddress(), WebServiceType.REST);
		podcastExpectedEntity = new Podcast();
		podcastExpectedEntity.setTitle("SomeTitle");
		podcastExpectedEntity.setLinkOnPodcastpedia("http://google.com");
		podcastExpectedEntity.setFeed("http://googlee.com");
		podcastExpectedEntity.setDescription("testDescription");
	}

	@Test
	public void mapEntityTest() throws EntityMethodsException, JsonParseException, JsonMappingException, IOException, InterruptedException {
		PodcastDataHandler dataHandler = new PodcastDataHandler();
		podcastMethods.get(1, dataHandler);
		while (dataHandler.getResult() == null) {
			System.out.println("Waiting for result...");
			Thread.sleep(1000);
		}
		Podcast testPodcastEntity = dataHandler.getResult();
		assertEquals(podcastExpectedEntity.getTitle(), testPodcastEntity.getTitle());
		assertEquals(podcastExpectedEntity.getLinkOnPodcastpedia(), testPodcastEntity.getLinkOnPodcastpedia());
		assertEquals(podcastExpectedEntity.getFeed(), testPodcastEntity.getFeed());
		assertEquals(podcastExpectedEntity.getDescription(), testPodcastEntity.getDescription());
	}
}
