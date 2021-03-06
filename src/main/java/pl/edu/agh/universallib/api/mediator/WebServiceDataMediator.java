package pl.edu.agh.universallib.api.mediator;

import pl.edu.agh.universallib.api.handler.WebServiceDataHandler;
import pl.edu.agh.universallib.entity.Entity;
import pl.edu.agh.universallib.entity.exception.EntityException;
import pl.edu.agh.universallib.entitylist.EntityList;

/** Class that serves as a mediator between ServerConnector and WebServiceDataHandler
 *
 * @param <T> The class that will be mapped from a response
 */
public class WebServiceDataMediator<T> {

	private final WebServiceDataHandler dataHandler;
	private Class<T> cls;

	public WebServiceDataMediator(WebServiceDataHandler dataHandler, Class<T> cls) {
		this.dataHandler = dataHandler;
		this.cls = cls;
	}

	/**Method processed a string response from webservice and maps it to Entity or EntityList of type T.
	 * If an exception occurs, dataHandler.processData is called with this exception as a parameter.
	 * @param data Response from Webservice
	 * @param e Exception if occured during http requests
	 */
	public void processData(String data, Exception e) {
		if (e != null) {
			dataHandler.processData(null, e);
		} else {
			try {
				if (data.length() == 3) {
					dataHandler.processData(null, null); // status http
				} else if (EntityList.class.isAssignableFrom(cls)) {
					EntityList entityList = (EntityList) cls.newInstance();
					entityList.setEntities(entityList.mapEntities(data));
					dataHandler.processData(entityList, e);
				} else if (Entity.class.isAssignableFrom(cls)) {
					Entity entity = (Entity) cls.newInstance();
					entity = entity.mapEntity(data);
					dataHandler.processData(entity, e);
				} else {

				}
			} catch (EntityException e1) {
				dataHandler.processData(null, e1);
			} catch (InstantiationException e1) {
				dataHandler.processData(null, e1);
			} catch (IllegalAccessException e1) {
				dataHandler.processData(null, e1);
			}
		}
	}
}
