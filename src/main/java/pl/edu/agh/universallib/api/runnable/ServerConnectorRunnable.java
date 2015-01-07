package pl.edu.agh.universallib.api.runnable;

import pl.edu.agh.universallib.api.ApiCall;
import pl.edu.agh.universallib.api.ServerConnector;
import pl.edu.agh.universallib.api.handler.WebServiceDataHandler;

public class ServerConnectorRunnable implements Runnable {

	private ServerConnector serverConnector;
	private WebServiceDataHandler dataHandler;
	private ApiCall apiCall;
	
	public ServerConnectorRunnable(ServerConnector serverConnector, WebServiceDataHandler dataHandler, ApiCall apiCall) {
		this.serverConnector = serverConnector;
		this.dataHandler = dataHandler;
		this.apiCall = apiCall;
	}

	@Override
	public void run() {
		serverConnector.process(apiCall, dataHandler);
	}

}
