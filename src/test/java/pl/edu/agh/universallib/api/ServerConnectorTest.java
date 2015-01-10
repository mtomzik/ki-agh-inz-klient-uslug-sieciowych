package pl.edu.agh.universallib.api;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import pl.edu.agh.universallib.api.exception.ProcessingException;
import pl.edu.agh.universallib.api.handler.TestDataHandler;
import pl.edu.agh.universallib.api.httpconnection.ConnectionType;
import pl.edu.agh.universallib.api.mediator.WebServiceDataMediator;
import pl.edu.agh.universallib.entity.example.Book;

public class ServerConnectorTest {

	private ServerConnector serverConnector;

	@Before
	public void setupServerConnector() {
		serverConnector = new ServerConnector("http://localhost:8888/springrestdemo-0.0.1-SNAPSHOT");
	}

	@Test(expected = ProcessingException.class)
	public void testProcessNonExistingUrl() throws Exception {
		serverConnector = new ServerConnector("http://somenonexistingwebservice.com");
		// given
		ApiCall apiCall = new ApiCall();
		apiCall.setCallDate(new Date());
		apiCall.setConnectionType(ConnectionType.GET);

		TestDataHandler dataHandler = new TestDataHandler();
		WebServiceDataMediator<Book> dataMediator = new WebServiceDataMediator<Book>(dataHandler, Book.class);

		// do
		serverConnector.process(apiCall, dataMediator);
		Thread.sleep(1000L);

		throw dataHandler.getException();
	}

}
