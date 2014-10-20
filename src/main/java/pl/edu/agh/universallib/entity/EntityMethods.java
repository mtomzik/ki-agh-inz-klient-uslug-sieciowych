package pl.edu.agh.universallib.entity;

import java.util.Date;

import pl.edu.agh.universallib.api.ApiCall;
import pl.edu.agh.universallib.api.ServerConnector;
import pl.edu.agh.universallib.api.handler.WebServiceDataHandler;
import pl.edu.agh.universallib.api.httpconnection.ConnectionType;
import pl.edu.agh.universallib.entity.exception.EntityMethodsException;
import pl.edu.agh.universallib.url.WebServiceType;

public abstract class EntityMethods {
	private final ServerConnector serverConnector;
	private final String separator;

	public EntityMethods(String serviceUrl, WebServiceType webServiceType) {
		if (!serviceUrl.substring(serviceUrl.length() - 1).equals("/")) {
			serviceUrl = serviceUrl + "/";
		}
		this.serverConnector = new ServerConnector(serviceUrl);
		this.separator = webServiceType.equals(WebServiceType.REST) ? "/" : "?";
	}

	public void getAll(WebServiceDataHandler dataHandler) throws EntityMethodsException {
		ApiCall apiCall = prepareApiCall(getUrlPart(), ConnectionType.GET, null, null);
		processApiCall(apiCall, dataHandler);
	}

	public void get(long id, WebServiceDataHandler dataHandler) throws EntityMethodsException {
		ApiCall apiCall = prepareApiCall(getUrlPart() + separator
				+ getIdPrefix(), ConnectionType.GET, id, null);
		processApiCall(apiCall, dataHandler);
	}

	public void create(String data, WebServiceDataHandler dataHandler) throws EntityMethodsException {
		ApiCall apiCall = prepareApiCall(getUrlPart(), ConnectionType.POST,
				null, data);
		processApiCall(apiCall, dataHandler);
	}

	public void update(String data, long id, WebServiceDataHandler dataHandler) throws EntityMethodsException {
		ApiCall apiCall = prepareApiCall(getUrlPart() + separator + getIdPrefix(), ConnectionType.PUT, id,
				data);
		processApiCall(apiCall, dataHandler);
	}

	public void delete(long id, WebServiceDataHandler dataHandler) throws EntityMethodsException {
		ApiCall apiCall = prepareApiCall(getUrlPart() + separator
				+ getIdPrefix(), ConnectionType.DELETE, id, null);
		processApiCall(apiCall, dataHandler);
	}

	public void deleteAll(WebServiceDataHandler dataHandler) throws EntityMethodsException {
		ApiCall apiCall = prepareApiCall(getUrlPart(), ConnectionType.DELETE, null, null);
		processApiCall(apiCall, dataHandler);
	}

	private ApiCall prepareApiCall(String url, ConnectionType connectionType,
			Long id, String data) {
		ApiCall apiCall = new ApiCall();
		apiCall.setCallDate(new Date());
		apiCall.setConnectionType(connectionType);
		apiCall.setUrl(url + (id == null ? "" : id));
		apiCall.setData(data);
		return apiCall;
	}

	public String getIdPrefix() throws EntityMethodsException {
		String idPrefix = null;
		try {
			idPrefix = (String) this.getClass().getField("idPrefix").get(this);
		} catch (Exception e) {
			throw new EntityMethodsException("idPrefix is undeclared or doesnt have a value", e);
		}
		return idPrefix;
	}

	public String getUrlPart() throws EntityMethodsException {
		String urlPart = null;
		try {
			urlPart = (String) this.getClass().getField("urlPart").get(this);
		} catch (Exception e) {
			throw new EntityMethodsException("urlPart is undeclared or doesn't have a value", e);
		}
		return urlPart;
	}

	private void processApiCall(ApiCall apiCall, WebServiceDataHandler dataHandler) {
		serverConnector.process(apiCall, dataHandler);
	}
}
