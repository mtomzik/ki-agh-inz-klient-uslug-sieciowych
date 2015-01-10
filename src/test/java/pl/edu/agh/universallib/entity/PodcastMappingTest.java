package pl.edu.agh.universallib.entity;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import javax.xml.bind.JAXBException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.junit.Before;
import org.junit.Test;

import pl.edu.agh.universallib.entity.example.Podcast;
import pl.edu.agh.universallib.entity.exception.EntityMethodsException;

public class PodcastMappingTest {

	private static final String ENTITY_JSON = "{\"title\":\"SomeTitle\",\"linkOnPodcastpedia\":\"http://google.com\",\"feed\":\"http://googlee.com\",\"description\":\"testDescription\"}";

	private Podcast podcastExpectedEntity;

	@Before
	public void prepareEntity() throws EntityMethodsException, InterruptedException {
		podcastExpectedEntity = new Podcast();
		podcastExpectedEntity.setTitle("SomeTitle");
		podcastExpectedEntity.setLinkOnPodcastpedia("http://google.com");
		podcastExpectedEntity.setFeed("http://googlee.com");
		podcastExpectedEntity.setDescription("testDescription");
	}

	@Test
	public void mapEntityWithNullsToJson() throws JsonGenerationException, JsonMappingException, IOException {
		String expected = "{\"id\":null,\"title\":\"SomeTitle\",\"linkOnPodcastpedia\":\"http://google.com\",\"feed\":\"http://googlee.com\",\"description\":\"testDescription\",\"insertionDate\":null}";
		assertEquals(expected, podcastExpectedEntity.mapToJson(true));
	}

	@Test
	public void mapEntityWithoutNullsToJson() throws JsonGenerationException, JsonMappingException, IOException {
		assertEquals(ENTITY_JSON, podcastExpectedEntity.mapToJson(false));
	}

	@Test
	public void mapEntityWithNullsToXml() throws JAXBException {
		String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><Podcast><description>testDescription</description><feed>http://googlee.com</feed><linkOnPodcastpedia>http://google.com</linkOnPodcastpedia><title>SomeTitle</title></Podcast>";
		assertEquals(expected, podcastExpectedEntity.mapToXml());
	}

	@Test
	public void mapEntityFromXml() throws JAXBException {
		String entityAsXml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><Podcast><description>testDescription</description><feed>http://googlee.com</feed><linkOnPodcastpedia>http://google.com</linkOnPodcastpedia><title>SomeTitle</title></Podcast>";
		Podcast testPodcastEntity = new Podcast();

		testPodcastEntity = (Podcast) testPodcastEntity.mapEntityFromXml(entityAsXml);

		assertEquals(podcastExpectedEntity.getTitle(), testPodcastEntity.getTitle());
		assertEquals(podcastExpectedEntity.getLinkOnPodcastpedia(), testPodcastEntity.getLinkOnPodcastpedia());
		assertEquals(podcastExpectedEntity.getFeed(), testPodcastEntity.getFeed());
		assertEquals(podcastExpectedEntity.getDescription(), testPodcastEntity.getDescription());
	}

	@Test
	public void mapEntityFromJson() throws JsonParseException, JsonMappingException, IOException {
		Podcast testPodcastEntity = new Podcast();
		testPodcastEntity = (Podcast) testPodcastEntity.mapEntityFromJson(ENTITY_JSON);

		assertEquals(podcastExpectedEntity.getTitle(), testPodcastEntity.getTitle());
		assertEquals(podcastExpectedEntity.getLinkOnPodcastpedia(), testPodcastEntity.getLinkOnPodcastpedia());
		assertEquals(podcastExpectedEntity.getFeed(), testPodcastEntity.getFeed());
		assertEquals(podcastExpectedEntity.getDescription(), testPodcastEntity.getDescription());
	}
}
