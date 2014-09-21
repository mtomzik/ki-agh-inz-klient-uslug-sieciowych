package pl.edu.agh.universallib.api.httpconnection;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUrlConnectionPutAndDeleteRecord {

	public HttpUrlConnectionPutAndDeleteRecord() {

	}

	public void putRecord(String address, String contentToPut) throws Exception {
		URL url = new URL(address);
		HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
		httpCon.setDoOutput(true);
		httpCon.setRequestMethod("PUT");
		OutputStreamWriter out = new OutputStreamWriter(
				httpCon.getOutputStream());
		out.write(contentToPut);
		System.out.println("chuj");
		out.close();
	}

	public void deleteRecord(String address, String contentToDelete)
			throws Exception {
		URL url = new URL(address);
		HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
		httpCon.setDoOutput(true);
		httpCon.setRequestProperty("Content-Type", contentToDelete);
		httpCon.setRequestMethod("DELETE");
		httpCon.connect();
	}

}
