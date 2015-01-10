package pl.edu.agh.universallib.api.httpconnection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import pl.edu.agh.universallib.util.PropertiesLoader;

public class HttpConnectionTest {

	private static final String WS_ADDRESS = PropertiesLoader.getWebServiceAddress() + "podcasts";

	@Before
	public void initializeData() throws IOException {
		HttpUrlConnectionMethods.deleteRecord(WS_ADDRESS);
		HttpUrlConnectionMethods
				.postRecord(WS_ADDRESS,
						"{\"title\":\"SomeTitle\",\"linkOnPodcastpedia\":\"http://google.com\",\"feed\":\"http://googlee.com\",\"description\":\"testDescription\"}");
	}

	@Test
	public void getSingleRecordTest() throws IOException {
		String data = HttpUrlConnectionMethods.getSingleData(WS_ADDRESS + "/1");
		if (data.charAt(0) == '{')
			assertTrue(data
					.contains("\"title\":\"SomeTitle\",\"linkOnPodcastpedia\":\"http://google.com\",\"feed\":\"http://googlee.com\",\"description\":\"testDescription\""));
		else if (data.charAt(0) == '<') {
			assertTrue(data.contains("testDescription"));
			assertTrue(data.contains("http://google.com"));
			assertTrue(data.contains("http://googlee.com"));
			assertTrue(data.contains("SomeTitle"));
		} else
			fail();
	}

	@Test
	public void deleteRecordsTest() throws IOException {
		HttpUrlConnectionMethods.deleteRecord(WS_ADDRESS);
		assertEquals("[]", HttpUrlConnectionMethods.getSingleData(WS_ADDRESS));
	}

	@Test
	public void deleteRecordTest() throws IOException {
		HttpUrlConnectionMethods.deleteRecord(WS_ADDRESS + "/1");
		assertEquals("[]", HttpUrlConnectionMethods.getSingleData(WS_ADDRESS));
	}

	@Test
	public void putRecordTest() throws IOException {
		HttpUrlConnectionMethods.putRecord(WS_ADDRESS + "/1", "{\"title\":\"SomeTitleAfterUpdate\"}");
		String data = HttpUrlConnectionMethods.getSingleData(WS_ADDRESS + "/1");
		if (data.charAt(0) == '{')
			assertTrue(data
					.contains("\"title\":\"SomeTitleAfterUpdate\",\"linkOnPodcastpedia\":\"http://google.com\",\"feed\":\"http://googlee.com\",\"description\":\"testDescription\""));
		else if (data.charAt(0) == '<') {
			assertTrue(data.contains("testDescription"));
			assertTrue(data.contains("http://google.com"));
			assertTrue(data.contains("http://googlee.com"));
			assertTrue(data.contains("SomeTitleAfterUpdate"));
		} else
			fail();
	}
}
