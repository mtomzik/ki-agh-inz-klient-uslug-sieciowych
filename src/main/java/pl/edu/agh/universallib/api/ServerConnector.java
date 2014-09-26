package pl.edu.agh.universallib.api;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import pl.edu.agh.universallib.api.exception.ProcessingException;
import pl.edu.agh.universallib.api.handler.DataHandler;
import pl.edu.agh.universallib.api.httpconnection.ConnectionType;
import pl.edu.agh.universallib.api.httpconnection.HttpUrlConnectionGetSingleRecord;
import pl.edu.agh.universallib.api.httpconnection.HttpUrlConnectionPostRecord;

public class ServerConnector {

	 private final ConcurrentMap<ApiCall, DataHandler> apiCalls = new ConcurrentHashMap<ApiCall, DataHandler>();

	private String webServiceUrl;

	public ServerConnector(String webServiceUrl) {
		this.webServiceUrl = webServiceUrl;
	}

	public void process(ApiCall apiCall) {
		try {
			if (apiCall.getConnectionType().equals(ConnectionType.GET)) {
				HttpUrlConnectionGetSingleRecord httpGet = new HttpUrlConnectionGetSingleRecord();
				Map<String,String> map = new HashMap<String,String>();
				ObjectMapper mapper = new ObjectMapper();
				try {
					map = mapper.readValue(httpGet.getSingleData(webServiceUrl + apiCall.getUrl()), new TypeReference<HashMap<String,String>>(){});
					apiCalls.put(apiCall, new DataHandler(map,null));
				} catch (JsonParseException e) {
					e.printStackTrace();
					throw new ProcessingException("Couldn't parse JSON", e);
				} catch (JsonMappingException e) {
					e.printStackTrace();
					throw new ProcessingException("Couldn't map JSON", e);
				} catch (IOException e) {
					e.printStackTrace();
					throw new ProcessingException("IO Exception at processing call", e);
				}
			} else if (apiCall.getConnectionType().equals(ConnectionType.POST)){
				HttpUrlConnectionPostRecord httpPost = new HttpUrlConnectionPostRecord();
				try {
					httpPost.postRecord(webServiceUrl + apiCall.getUrl(), apiCall.getData());
					apiCalls.put(apiCall, new DataHandler(null, null));
				} catch (Exception e) {
					e.printStackTrace();
					throw new ProcessingException("Error POST with data " + apiCall.getData(), e);
				}
			}
		} catch (ProcessingException e) {
			apiCalls.put(apiCall, new DataHandler(null, e.getLocalizedMessage()));
		}
	}
	
	public DataHandler getDataHandler(ApiCall apiCall){
		return this.apiCalls.get(apiCall);
	}
}