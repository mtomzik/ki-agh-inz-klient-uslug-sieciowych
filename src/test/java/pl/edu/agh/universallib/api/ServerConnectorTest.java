package pl.edu.agh.universallib.api;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import pl.edu.agh.universallib.api.httpconnection.ConnectionType;

public class ServerConnectorTest {

	private ServerConnector serverConnector;

	@Before
	public void setupServerConnector() {
		serverConnector = new ServerConnector(
				"http://localhost:8888/springrestdemo-0.0.1-SNAPSHOT");
	}

	@Test
	public void testProcess() {
		ApiCall apiCall = new ApiCall();
		apiCall.setCallDate(new Date());
		apiCall.setConnectionType(ConnectionType.GET);
		apiCall.setUrl("/podcasts/1");
		serverConnector.process(apiCall);
		System.out.println(serverConnector.getDataHandler(apiCall).getData());
		assertFalse(serverConnector.getDataHandler(apiCall).getData().isEmpty());
	}

}
