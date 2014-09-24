package pl.edu.agh.universallib.api.httpconnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUrlConnectionGetSingleRecord {

	public HttpUrlConnectionGetSingleRecord() {

	}

	public String getSingleData(String address) throws IOException {
		URL page = new URL(address);
		HttpURLConnection conn = (HttpURLConnection) page.openConnection();
		conn.setRequestMethod("GET");
		conn.connect();
		InputStreamReader in = new InputStreamReader(
				(InputStream) conn.getContent());
		BufferedReader buf = new BufferedReader(in);
		StringBuffer response = new StringBuffer();
		String line ;
		while ((line = buf.readLine()) != null) {
			response.append(line);
		}
		conn.disconnect();
		return response.toString();
	}

}
