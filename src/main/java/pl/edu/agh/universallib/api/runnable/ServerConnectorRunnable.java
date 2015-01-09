package pl.edu.agh.universallib.api.runnable;

import pl.edu.agh.universallib.api.ApiCall;
import pl.edu.agh.universallib.api.ServerConnector;
import pl.edu.agh.universallib.api.mediator.WebServiceDataMediator;

/**
 * Class that can be delegated to a thread to execute.
 * run() causes ServerConnector passed in constructor to process
 */
public class ServerConnectorRunnable implements Runnable {

	private ServerConnector serverConnector;
	private WebServiceDataMediator<?> dataHandler;
	private ApiCall apiCall;
	
	public ServerConnectorRunnable(ServerConnector serverConnector, WebServiceDataMediator<?> dataHandler, ApiCall apiCall) {
		this.serverConnector = serverConnector;
		this.dataHandler = dataHandler;
		this.apiCall = apiCall;
	}

	@Override
	public void run() {
		serverConnector.process(apiCall, dataHandler);
	}

}
