package pl.edu.agh.universallib.entity;

import java.util.Date;

import pl.edu.agh.universallib.api.ApiCall;
import pl.edu.agh.universallib.api.ServerConnector;
import pl.edu.agh.universallib.api.handler.DataHandler;
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

	public DataHandler getAll() throws EntityMethodsException {
		ApiCall apiCall = prepareApiCall(getUrlPart(), ConnectionType.GET, null, null);
		return processApiCall(apiCall);
	}

	public DataHandler get(long id) throws EntityMethodsException {
		ApiCall apiCall = prepareApiCall(getUrlPart() + separator
				+ getIdPrefix(), ConnectionType.GET, id, null);
		return processApiCall(apiCall);
	}

	public DataHandler create(String data) throws EntityMethodsException {
		ApiCall apiCall = prepareApiCall(getUrlPart(), ConnectionType.POST,
				null, data);
		return processApiCall(apiCall);
	}

	public DataHandler update(String data, long id) throws EntityMethodsException {
		ApiCall apiCall = prepareApiCall(getUrlPart() + separator + getIdPrefix(), ConnectionType.PUT, id,
				data);
		return processApiCall(apiCall);
	}

	public DataHandler delete(long id) throws EntityMethodsException {
		ApiCall apiCall = prepareApiCall(getUrlPart() + separator
				+ getIdPrefix(), ConnectionType.DELETE, id, null);
		return processApiCall(apiCall);
	}

	public DataHandler deleteAll() throws EntityMethodsException {
		ApiCall apiCall = prepareApiCall(getUrlPart(), ConnectionType.DELETE, null, null);
		return processApiCall(apiCall);
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
			throw new EntityMethodsException("idPrefix is undeclared or doesnt have a value");
		}
		return idPrefix;
	}

	public String getUrlPart() throws EntityMethodsException {
		String urlPart = null;
		try {
			urlPart = (String) this.getClass().getField("urlPart").get(this);
		} catch (Exception e) {
			throw new EntityMethodsException(e);
		}
		return urlPart;
	}

	private DataHandler processApiCall(ApiCall apiCall) {
		serverConnector.process(apiCall);
		return serverConnector.getDataHandler(apiCall);
	}
}
