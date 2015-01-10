package pl.edu.agh.universallib.api.runnable;

import org.junit.Before;
import org.junit.Test;

public class ServerConnectorRunnableTest {

	private ServerConnectorRunnable serverConnectorRunnable;

	@Before
	public void testServerConnectorRunnable() {
		serverConnectorRunnable = new ServerConnectorRunnable(null, null, null);
	}

	@Test(expected = NullPointerException.class)
	public void testNullServerConnector() {
		serverConnectorRunnable = new ServerConnectorRunnable(null, null, null);
		serverConnectorRunnable.run();
	}

	@Test
	public void testRun() {
		// TODO: mock
	}

}
