package pl.edu.agh.universallib.api.httpconnection;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

/**
 *	Lowest level class in library, accessing the Webservice
 */
public class HttpUrlConnectionMethods {

	/**This method fetches a single result (response)
	 * 
	 * @param address Complete address to webservice on which GET is to be performed
	 * @return Response from Webservice
	 * @throws IOException
	 */
	public static String getSingleData(String address) throws IOException {
		HttpURLConnection conn = null;
		conn = getConnectionToUrl(address);
		conn.setRequestMethod("GET");
		conn.connect();
		String response = getResponse(conn);
		return response;
	}

	/**Method used to post record to webservice
	 * @param address Complete address to webservice on which POST is to be performed
	 * @param dataString String to be posted, either in JSON or XML format
	 * @return Http response code
	 * @throws IOException
	 */
	public static int postRecord(String address, String dataString) throws IOException {
		HttpURLConnection conn = null;
		DataOutputStream out = null;
		try {
			conn = getConnectionToUrl(address);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			out = new DataOutputStream(conn.getOutputStream());
			out.writeBytes(dataString);
			out.flush();
			return conn.getResponseCode();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
			if (out != null){
				out.close();
			}
		}
		return -1;
	}

	/**Method to update current records
	 * @param address Complete address to webservice on which PUT is to be performed
	 * @param contentToPut Updated content, in JSON format
	 * @return Http response code
	 * @throws IOException
	 */
	public static int putRecord(String address, String contentToPut) throws IOException {
		HttpURLConnection conn = null;
		DataOutputStream out = null;
		try {
			conn = getConnectionToUrl(address);
			conn.setRequestMethod("PUT");
			conn.setRequestProperty("Content-Type", "application/json");
			out = new DataOutputStream(
					conn.getOutputStream());
			out.writeBytes(contentToPut);
			return conn.getResponseCode();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
			if (out != null){
				out.close();
			}
		}
		return -1;
	}

	/**
	 * Method that deletes a record
	 * 
	 * @param address Complete address to webservice on which DELETE is to be performed
	 * @return Http status code
	 */
	public static int deleteRecord(String address) {
		HttpURLConnection conn = null;
		try {
			conn = getConnectionToUrl(address);
			conn.setRequestMethod("DELETE");
			return conn.getResponseCode();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
		return -1;
	}

	/**Method that gets connection to a given URL
	 * @param address Address to the URL
	 * @return Connection to the URL
	 * @throws IOException
	 */
	private static HttpURLConnection getConnectionToUrl(String address)
			throws IOException {
		URL url = null;
		url = new URL(address);
		HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
		httpCon.setDoOutput(true);
		return httpCon;
	}

	/**This method fetches response from a given URL
	 * @param conn connection to the URL
	 * @return Response as String
	 * @throws IOException
	 */
	private static String getResponse(HttpURLConnection conn)
			throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(
				conn.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		return response.toString();
	}
}
