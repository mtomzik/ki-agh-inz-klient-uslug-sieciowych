package pl.edu.agh.universallib.entitylist;

import pl.edu.agh.universallib.api.ApiCall;
import pl.edu.agh.universallib.api.handler.WebServiceDataHandler;
import pl.edu.agh.universallib.api.httpconnection.ConnectionType;
import pl.edu.agh.universallib.entity.EntityMethods;
import pl.edu.agh.universallib.entity.exception.EntityMethodsException;
import pl.edu.agh.universallib.url.WebServiceType;

public class EntityListMethods<T extends EntityList> extends EntityMethods<T> {
	private int currentIndex;

	private int pageLength;
	private boolean paginated;

	private String currentIndexUrlPart;
	private String pageLengthUrlPart;

	public EntityListMethods(Class<T> cls, String webServiceUrl, WebServiceType webServiceType) {
		super(cls, webServiceUrl, webServiceType, 1);
		this.paginated = false;
	}

	public EntityListMethods(Class<T> cls, String webServiceUrl, WebServiceType webServiceType, String currIndexUrlPart, String pageLengthUrlPart,
			int nThreads) {
		super(cls, webServiceUrl, webServiceType, nThreads);
		this.paginated = true;
		this.currentIndex = 0;
		this.pageLength = 10;
		this.currentIndexUrlPart = currIndexUrlPart;
		this.pageLengthUrlPart = pageLengthUrlPart;
	}

	public EntityListMethods(Class<T> cls, String webServiceUrl, WebServiceType webServiceType, String currIndexUrlPart, String pageLengthUrlPart) {
		super(cls, webServiceUrl, webServiceType, 1);
		this.paginated = true;
		this.pageLength = 10;
		this.currentIndex = 0;
		this.currentIndexUrlPart = currIndexUrlPart;
		this.pageLengthUrlPart = pageLengthUrlPart;
	}

	public int getCurrentIndex() {
		return currentIndex;
	}

	public void setCurrentIndex(int currentIndex) {
		this.currentIndex = currentIndex;
	}

	public int getPageLength() {
		return pageLength;
	}

	public void setPageLength(int pageLength) {
		this.pageLength = pageLength;
	}

	public boolean isPaginated() {
		return paginated;
	}

	/**
	 * This method fetches all records from Webservice
	 * 
	 * @param dataHandler
	 *            dataHandler that will be called when a response is received or
	 *            an exception occurs
	 * @throws EntityMethodsException
	 */
	public void getAll(WebServiceDataHandler dataHandler) throws EntityMethodsException {
		ApiCall apiCall = prepareApiCall(getUrlPart(), ConnectionType.GET, null, null);
		processApiCall(apiCall, dataHandler);
	}

	/**
	 * This method fetches all records from current page and increments current
	 * position
	 * 
	 * @param dataHandler
	 *            dataHandler that will be called when a response is received or
	 *            an exception occurs
	 * @throws EntityMethodsException
	 */
	public void getPage(WebServiceDataHandler dataHandler) throws EntityMethodsException {
		ApiCall apiCall = prepareApiCall(getUrlPart(), ConnectionType.GET, null, null);
		String currentUrl = apiCall.getUrl();
		if (currentUrl.charAt(currentUrl.length() - 1) == '/') {
			currentUrl = currentUrl.substring(0, currentUrl.length() - 1);
		}
		String urlToSet = currentUrl + "?" + currentIndexUrlPart + "=" + currentIndex + "&" + pageLengthUrlPart + "=" + pageLength;
		apiCall.setUrl(urlToSet);
		processApiCall(apiCall, dataHandler);
		currentIndex += pageLength;
	}

	/**
	 * This method deletes all records
	 * 
	 * @param dataHandler
	 *            dataHandler that will be called when a response is received or
	 *            an exception occurs
	 * @throws EntityMethodsException
	 */
	public void deleteAll(WebServiceDataHandler dataHandler) throws EntityMethodsException {
		ApiCall apiCall = prepareApiCall(getUrlPart(), ConnectionType.DELETE, null, null);
		processApiCall(apiCall, dataHandler);
	}

}
