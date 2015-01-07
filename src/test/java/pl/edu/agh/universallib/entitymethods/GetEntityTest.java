package pl.edu.agh.universallib.entitymethods;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import pl.edu.agh.universallib.api.handler.WebServiceDataHandler;
import pl.edu.agh.universallib.entity.MyDataHandler;
import pl.edu.agh.universallib.entity.example.PodcastListMethods;
import pl.edu.agh.universallib.entity.exception.EntityMethodsException;
import pl.edu.agh.universallib.url.WebServiceType;
import pl.edu.agh.universallib.util.PropertiesLoader;

public class GetEntityTest implements WebServiceDataHandler {

	private PodcastListMethods pm;
	private MyDataHandler dataHandler;
	
	@Before
	public void prepareEntity() throws EntityMethodsException {
		pm = new PodcastListMethods(
				PropertiesLoader.getWebServiceAddress(),
				WebServiceType.REST);
		dataHandler = new MyDataHandler();
		pm.deleteAll(dataHandler);
		pm.create("{\"title\":\"SomeTitle\",\"linkOnPodcastpedia\":\"http://google.com\",\"feed\":\"http://googlee.com\",\"description\":\"testDescription\"}", dataHandler);
		dataHandler = new MyDataHandler();
	}
	
	@Test
	public void getEntityTest() throws EntityMethodsException, InterruptedException {
		pm.get(1, dataHandler);
		System.out.println("This should be printed first");
		while(dataHandler.getData() == null && dataHandler.getError() == null){
			System.out.println("Waiting for response...");
			Thread.sleep(1000);
		}
		assertNull(dataHandler.getError());
		String data = dataHandler.getData();
		if (data.charAt(0) == '{')
			assertTrue(data.contains("\"title\":\"SomeTitle\",\"linkOnPodcastpedia\":\"http://google.com\",\"feed\":\"http://googlee.com\",\"description\":\"testDescription\""));
		else if (data.charAt(0) == '<'){
			assertTrue(data.contains("testDescription"));
			assertTrue(data.contains("http://google.com"));
			assertTrue(data.contains("http://googlee.com"));
			assertTrue(data.contains("SomeTitle"));			
		}
		else fail();

	}

	@Override
	public void processData(String data, Exception e) {
		if (e == null || data != null){
			System.out.println(data);
		}
		
	}
}
