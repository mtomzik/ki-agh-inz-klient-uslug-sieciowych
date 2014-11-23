package pl.edu.agh.universallib.entitylist;

import pl.edu.agh.universallib.api.ApiCall;
import pl.edu.agh.universallib.api.handler.WebServiceDataHandler;
import pl.edu.agh.universallib.api.httpconnection.ConnectionType;
import pl.edu.agh.universallib.entity.EntityMethods;
import pl.edu.agh.universallib.entity.exception.EntityMethodsException;
import pl.edu.agh.universallib.url.WebServiceType;

public class EntityListMethods extends EntityMethods {
	private int currentIndex;

	private int pageLength;
	private boolean paginated;

	public EntityListMethods(String webServiceUrl, WebServiceType webServiceType) {
		super(webServiceUrl, webServiceType);
		this.paginated = false;
	}

	public EntityListMethods(String webServiceUrl, WebServiceType webServiceType, int pageLength) {
		super(webServiceUrl, webServiceType);
		this.pageLength = pageLength;
		this.currentIndex = 0;
		this.paginated = true;
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

	public void getAll(WebServiceDataHandler dataHandler) throws EntityMethodsException {
		ApiCall apiCall = prepareApiCall(getUrlPart(), ConnectionType.GET, null, null);
		processApiCall(apiCall, dataHandler);
	}

	public void deleteAll(WebServiceDataHandler dataHandler) throws EntityMethodsException {
		ApiCall apiCall = prepareApiCall(getUrlPart(), ConnectionType.DELETE, null, null);
		processApiCall(apiCall, dataHandler);
	}

}
