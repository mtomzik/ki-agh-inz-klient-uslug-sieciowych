package pl.edu.agh.universallib.api.mediator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import pl.edu.agh.universallib.api.handler.TestDataHandler;
import pl.edu.agh.universallib.entity.example.Podcast;

public class WebServiceDataMediatorTest {

	TestDataHandler dataHandler;
	WebServiceDataMediator<Podcast> mediator;

	@Before
	public void setUp() {
		dataHandler = new TestDataHandler();
		mediator = new WebServiceDataMediator<Podcast>(dataHandler, Podcast.class);
	}

	@Test
	public void testProcessDataStatusCode() {
		mediator.processData("200", null);
		assertNull(dataHandler.getData());
		assertNull(dataHandler.getException());
	}

	@Test
	public void testProcessException() {
		Exception e = new IllegalStateException();
		mediator.processData(null, e);
		assertEquals(e, dataHandler.getException());
	}

	@Test
	public void testProcessEntity() {
		String jsonData = "{\"title\":\"SomeTitle\",\"linkOnPodcastpedia\":\"http://google.com\",\"feed\":\"http://googlee.com\",\"description\":\"testDescription\"}";
		mediator.processData(jsonData, null);
		assertNotNull(dataHandler.getData());
		assertNull(dataHandler.getException());
	}

	@Test
	public void testProcessEntityList() {
		String jsonData = "{\"title\":\"SomeTitle\",\"linkOnPodcastpedia\":\"http://google.com\",\"feed\":\"http://googlee.com\",\"description\":\"testDescription\"}";
		mediator.processData(jsonData, null);
		assertNotNull(dataHandler.getData());
		assertNull(dataHandler.getException());
	}
}
