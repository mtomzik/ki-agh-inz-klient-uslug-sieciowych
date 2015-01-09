package pl.edu.agh.universallib.entity;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import pl.edu.agh.universallib.api.ApiCall;
import pl.edu.agh.universallib.api.ServerConnector;
import pl.edu.agh.universallib.api.handler.WebServiceDataHandler;
import pl.edu.agh.universallib.api.httpconnection.ConnectionType;
import pl.edu.agh.universallib.api.mediator.WebServiceDataMediator;
import pl.edu.agh.universallib.api.runnable.ServerConnectorRunnable;
import pl.edu.agh.universallib.entity.exception.EntityMethodsException;
import pl.edu.agh.universallib.url.WebServiceType;

/** Method that provides all CRUD operations on Entity
 *
 * @param <T> Type of returned Entities
 */
public abstract class EntityMethods<T extends Entity> {
	private final ServerConnector serverConnector;
	private final String separator;
	private final Executor executor;
	
	private final Class<T> cls;
	
	public EntityMethods(Class<T> cls, String serviceUrl, WebServiceType webServiceType) {
		if (!serviceUrl.substring(serviceUrl.length() - 1).equals("/")) {
			serviceUrl = serviceUrl + "/";
		}
		this.serverConnector = new ServerConnector(serviceUrl);
		this.separator = webServiceType.equals(WebServiceType.REST) ? "/" : "?";
		this.executor = Executors.newFixedThreadPool(1);
		this.cls = cls;
	}

	public EntityMethods(Class<T> cls, String serviceUrl, WebServiceType webServiceType, int nThreads) {
		if (!serviceUrl.substring(serviceUrl.length() - 1).equals("/")) {
			serviceUrl = serviceUrl + "/";
		}
		this.cls = cls;
		this.serverConnector = new ServerConnector(serviceUrl);
		this.separator = webServiceType.equals(WebServiceType.REST) ? "/" : "?";
		this.executor = Executors.newFixedThreadPool(nThreads);
	}

	/**This method fetches a record of given id
	 * @param id The id of the record
	 * @param dataHandler DataHandler that is invoked when a response is returned or an exception is thrown
	 * @throws EntityMethodsException
	 */
	public void get(long id, WebServiceDataHandler dataHandler) throws EntityMethodsException {
		ApiCall apiCall = prepareApiCall(getUrlPart() + separator
				+ getIdPrefix(), ConnectionType.GET, id, null);
		processApiCall(apiCall, dataHandler);
	}

	/**This method creates a record
	 * @param data Data in JSON format - representation of object to create
	 * @param dataHandler DataHandler that is invoked when a response is returned or an exception is thrown
	 * @throws EntityMethodsException
	 */
	public void create(String data, WebServiceDataHandler dataHandler) throws EntityMethodsException {
		ApiCall apiCall = prepareApiCall(getUrlPart(), ConnectionType.POST,
				null, data);
		processApiCall(apiCall, dataHandler);
	}
	
	/**Method that creates a record
	 * @param data Entity that will be serialized 
	 * @param dataHandler DataHandler that is invoked when a response is returned or an exception is thrown
	 * @throws EntityMethodsException
	 */
	public void create(Entity data, WebServiceDataHandler dataHandler) throws EntityMethodsException{
		try {
			create(data.mapToJson(false), dataHandler);
		} catch (JsonGenerationException e) {
			throw new EntityMethodsException("Problem with generating Json");
		} catch (JsonMappingException e) {
			throw new EntityMethodsException("Problem with mapping Entity to Json");
		} catch (IOException e) {
			throw new EntityMethodsException();
		}
	}

	/**This method updates a record
	 * @param data JSON with fields to update
	 * @param id ID of record being updated
	 * @param dataHandler DataHandler that is invoked when a response is returned or an exception is thrown
	 * @throws EntityMethodsException
	 */
	public void update(String data, long id, WebServiceDataHandler dataHandler) throws EntityMethodsException {
		ApiCall apiCall = prepareApiCall(getUrlPart() + separator + getIdPrefix(), ConnectionType.PUT, id,
				data);
		processApiCall(apiCall, dataHandler);
	}

	/**This method deletes a record
	 * @param id ID of a record to delete
	 * @param dataHandler DataHandler that is invoked when a response is returned or an exception is thrown
	 * @throws EntityMethodsException
	 */
	public void delete(long id, WebServiceDataHandler dataHandler) throws EntityMethodsException {
		ApiCall apiCall = prepareApiCall(getUrlPart() + separator
				+ getIdPrefix(), ConnectionType.DELETE, id, null);
		processApiCall(apiCall, dataHandler);
	}

	/** Method that prepares a call to the webservice based on current configuration
	 * @param url Url generated to be added after Webservice address 
	 * @param connectionType The type of the connection
	 * @param id Id of the record, nullable
	 * @param data Data when inserting or updating
	 * @return Prepared ApiCall object
	 */
	protected ApiCall prepareApiCall(String url, ConnectionType connectionType,
			Long id, String data) {
		ApiCall apiCall = new ApiCall();
		apiCall.setCallDate(new Date());
		apiCall.setConnectionType(connectionType);
		apiCall.setUrl(url + (id == null ? "" : id));
		apiCall.setData(data);
		return apiCall;
	}

	/**Method that checks for idPrefix field in current class
	 * @return IdPrefix as String
	 * @throws EntityMethodsException if idPrefix field is undeclared or doesn't have a value
	 */
	public String getIdPrefix() throws EntityMethodsException {
		String idPrefix = null;
		try {
			idPrefix = (String) this.getClass().getField("idPrefix").get(this);
		} catch (Exception e) {
			throw new EntityMethodsException("idPrefix is undeclared or doesn't have a value", e);
		}
		return idPrefix;
	}

	/**Method that checks for urlPart field in current class
	 * @return UrlPart as String
	 * @throws EntityMethodsException if urlPart field is undeclared or doesn't have a value
	 */
	public String getUrlPart() throws EntityMethodsException {
		String urlPart = null;
		try {
			urlPart = (String) this.getClass().getField("urlPart").get(this);
		} catch (Exception e) {
			throw new EntityMethodsException("urlPart is undeclared or doesn't have a value", e);
		}
		return urlPart;
	}
	
	/**Method that creates a mediator, a runnable object and delegates it to Executor to be executed
	 * @param apiCall Previously prepared ApiCall
	 * @param dataHandler A handler that will be invoked when a response is received or an exception occurs
	 */
	protected void processApiCall(ApiCall apiCall, WebServiceDataHandler dataHandler){
		WebServiceDataMediator<T> mediator = new WebServiceDataMediator<T>(dataHandler, cls);
		ServerConnectorRunnable runnable = new ServerConnectorRunnable(serverConnector, mediator, apiCall);
		executor.execute(runnable);
	}
}
