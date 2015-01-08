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
import pl.edu.agh.universallib.entity.example.PodcastListMethods;
import pl.edu.agh.universallib.entity.example.PodcastMethods;
import pl.edu.agh.universallib.entity.exception.EntityMethodsException;
import pl.edu.agh.universallib.url.WebServiceType;
import pl.edu.agh.universallib.util.PropertiesLoader;

public class PodcastMappingTest {

	private static final String ENTITY_JSON = "{\"title\":\"SomeTitle\",\"linkOnPodcastpedia\":\"http://google.com\",\"feed\":\"http://googlee.com\",\"description\":\"testDescription\"}";
	
	private PodcastListMethods podcastListMethods;
	private PodcastMethods podcastMethods;
	private Podcast podcastExpectedEntity;

	@Before
	public void prepareEntity() throws EntityMethodsException, InterruptedException {
		podcastListMethods = new PodcastListMethods(
				PropertiesLoader.getWebServiceAddress(),
				WebServiceType.REST);
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
		while (dataHandler.getResult() == null){
			System.out.println("Waiting for result...");
			Thread.sleep(1000);
		}
		Podcast testPodcastEntity = dataHandler.getResult();
		assertEquals(podcastExpectedEntity.getTitle(), testPodcastEntity.getTitle());
		assertEquals(podcastExpectedEntity.getLinkOnPodcastpedia(), testPodcastEntity.getLinkOnPodcastpedia());
		assertEquals(podcastExpectedEntity.getFeed(),testPodcastEntity.getFeed());
		assertEquals(podcastExpectedEntity.getDescription(),testPodcastEntity.getDescription());
	}
	
	@Test
	public void mapEntityWithNullsToJsonTest() throws JsonGenerationException, JsonMappingException, IOException{
		String expected = "{\"id\":null,\"title\":\"SomeTitle\",\"linkOnPodcastpedia\":\"http://google.com\",\"feed\":\"http://googlee.com\",\"description\":\"testDescription\",\"insertionDate\":null}";
		assertEquals(expected, podcastExpectedEntity.mapToJson(true));
	}
	
	@Test
	public void mapEntityWithoutNullsToJsonTest() throws JsonGenerationException, JsonMappingException, IOException{
		assertEquals(ENTITY_JSON, podcastExpectedEntity.mapToJson(false));
	}
	
	@Test
	public void mapEntityWithNullsToXmlTest() throws JAXBException{
		String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><Podcast><description>testDescription</description><feed>http://googlee.com</feed><linkOnPodcastpedia>http://google.com</linkOnPodcastpedia><title>SomeTitle</title></Podcast>";
		assertEquals(expected, podcastExpectedEntity.mapToXml());
	}
}
