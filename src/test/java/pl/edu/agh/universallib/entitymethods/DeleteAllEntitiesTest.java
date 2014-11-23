package pl.edu.agh.universallib.entitymethods;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import pl.edu.agh.universallib.entity.MyDataHandler;
import pl.edu.agh.universallib.entity.example.PodcastListMethods;
import pl.edu.agh.universallib.entity.exception.EntityMethodsException;
import pl.edu.agh.universallib.url.WebServiceType;
import pl.edu.agh.universallib.util.PropertiesLoader;

public class DeleteAllEntitiesTest {

	private PodcastListMethods pm;
	private MyDataHandler dataHandler;

	@Before
	public void prepareEntity() {
		pm = new PodcastListMethods(
				PropertiesLoader.getWebServiceAddress(),
				WebServiceType.REST);
		dataHandler = new MyDataHandler();
	}

	@Test
	public void test() throws EntityMethodsException {
		pm.deleteAll(dataHandler);
		assertEquals("200", dataHandler.getData());
		assertNull(dataHandler.getError());
	}

}
