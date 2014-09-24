package pl.edu.agh.universallib.url;

import org.junit.Test;
import static org.junit.Assert.*;

import pl.edu.agh.universallib.api.exception.ProcessingException;
import pl.edu.agh.universallib.entity.example.Podcast;
import pl.edu.agh.universallib.entity.example.PodcastMethods;

public class GenerateWebServiceUrlTest {
	
	@Test
	public void generateUrlTest() throws ProcessingException{
		GenerateWebServiceUrl generator = new GenerateWebServiceUrl();
		String result = generator.generateUrl(new Podcast(), new PodcastMethods("http://localhost:8888/springrestdemo-0.0.1-SNAPSHOT", WebServiceType.REST));
		assertEquals("/podcasts/", result);
	}
}
