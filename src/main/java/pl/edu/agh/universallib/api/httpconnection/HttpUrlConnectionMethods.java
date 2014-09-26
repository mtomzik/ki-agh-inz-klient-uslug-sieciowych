package pl.edu.agh.universallib.api.httpconnection;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

public class HttpUrlConnectionMethods {

	public static String getSingleData(String address) throws IOException {
		HttpURLConnection conn = null;
		conn = getConnectionToUrl(address);
		conn.setRequestMethod("GET");
		conn.connect();
		return getResponse(conn);
	}

	public static int postRecord(String address, String jsonString) throws IOException {
		HttpURLConnection conn = null;
		DataOutputStream wr = null;
		try {
			conn = getConnectionToUrl(address);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			wr = new DataOutputStream(conn.getOutputStream());
			wr.writeBytes(jsonString);
			wr.flush();
			return conn.getResponseCode();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
			if (wr != null){
				wr.close();
			}
		}
		return -1;
	}

	public static int putRecord(String address, String contentToPut) throws IOException {
		HttpURLConnection httpCon = null;
		OutputStreamWriter out = null;
		try {
			httpCon = getConnectionToUrl(address);
			httpCon.setRequestMethod("PUT");
			httpCon.setRequestProperty("Content-Type", "application/json");
			out = new OutputStreamWriter(
					httpCon.getOutputStream());
			out.write(contentToPut);
			return httpCon.getResponseCode();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (httpCon != null) {
				httpCon.disconnect();
			}
			if (out != null){
				out.close();
			}
		}
		return -1;
	}

	public static int deleteRecord(String address) {
		HttpURLConnection httpCon = null;
		try {
			httpCon = getConnectionToUrl(address);
			httpCon.setRequestMethod("DELETE");
			return httpCon.getResponseCode();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (httpCon != null) {
				httpCon.disconnect();
			}
		}
		return -1;
	}

	private static HttpURLConnection getConnectionToUrl(String address)
			throws IOException {
		URL url = null;
		url = new URL(address);
		HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
		httpCon.setDoOutput(true);
		return httpCon;
	}

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
