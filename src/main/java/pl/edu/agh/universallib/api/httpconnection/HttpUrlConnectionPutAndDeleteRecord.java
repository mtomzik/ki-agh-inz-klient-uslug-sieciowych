package pl.edu.agh.universallib.api.httpconnection;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class HttpUrlConnectionPutAndDeleteRecord {

	public HttpUrlConnectionPutAndDeleteRecord() {

	}

	public int putRecord(String address, String contentToPut) {
		URL url = null;
		try {
			url = new URL(address);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		HttpURLConnection httpCon = null;
		try {
			httpCon = (HttpURLConnection) url.openConnection();
			httpCon.setDoOutput(true);
			httpCon.setRequestMethod("PUT");
			httpCon.setRequestProperty("Content-Type", "application/json");
			OutputStreamWriter out = new OutputStreamWriter(
					httpCon.getOutputStream());
			out.write(contentToPut);
			out.close();
			return httpCon.getResponseCode();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

	public int deleteRecord(String address)
 {
		URL url = null;
		try {
			url = new URL(address);
		} catch (MalformedURLException e) {
			// FIXME: exception handling
		}
		HttpURLConnection httpCon = null;
		try {
			httpCon = (HttpURLConnection) url.openConnection();
			httpCon.setDoOutput(true);
			httpCon.setRequestMethod("DELETE");
			httpCon.connect();
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
}
