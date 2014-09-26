package pl.edu.agh.universallib.api.httpconnection;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

public class HttpUrlConnectionMethods {

	public static String getSingleData(String address) throws IOException {
		HttpURLConnection conn = null;
		conn = getConnectionToUrl(address);
		conn.setRequestMethod("GET");
		conn.connect();
		String response = getResponse(conn);
		return response;
	}

	public static int postRecord(String address, String jsonString) throws IOException {
		HttpURLConnection conn = null;
		DataOutputStream out = null;
		try {
			conn = getConnectionToUrl(address);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			out = new DataOutputStream(conn.getOutputStream());
			out.writeBytes(jsonString);
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
			// TODO Auto-generated catch block
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

	public static int deleteRecord(String address) {
		HttpURLConnection conn = null;
		try {
			conn = getConnectionToUrl(address);
			conn.setRequestMethod("DELETE");
			return conn.getResponseCode();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.disconnect();
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
