package pl.edu.agh.universallib;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import pl.edu.agh.universallib.api.httpconnection.HttpUrlConnectionMethods;

public class HttpUrlGetSingleRecordTest {

	@Test
	public void getSingleRecordTest() throws IOException {
		String result = HttpUrlConnectionMethods.getSingleData("http://localhost:8888/springrestdemo-0.0.1-SNAPSHOT/podcasts/1");
		System.out.println(result);
		assertEquals("{\"id\":1,\"title\":\"Quarks & Co - zum Mitnehmen\",\"linkOnPodcastpedia\":\"http://www.podcastpedia.org/podcasts/1/Quarks-Co-zum-Mitnehmen\",\"feed\":\"http://podcast.wdr.de/quarks.xml\",\"description\":\"Quarks & Co: Das Wissenschaftsmagazin\",\"insertionDate\":1389295270000}", result);
	}

}
