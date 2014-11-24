package api;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class BookSearchAPI {

	private String apiURL;
	private String apiKey;
	private String outputType;
	private String queryText;
	
	public BookSearchAPI(String apiURL, String apiKey, String outputType, String queryText) {
		this.apiURL = apiURL;
		this.apiKey = apiKey;
		this.outputType = outputType;
		this.queryText = queryText;
	}
	
	public String getResult(String keyword) throws IOException {

		String urlEncodedKeyword = URLEncoder.encode(keyword, "utf-8");
		String urlParameter = "apikey=" + apiKey + "&output=" + outputType
				+ "&" + queryText + "=" + urlEncodedKeyword;
		
		URL url = new URL(apiURL);		
		HttpURLConnection urlC = (HttpURLConnection)url.openConnection();
		
		urlC.setDoOutput(true);
		
		DataOutputStream dos = new DataOutputStream(urlC.getOutputStream());
		dos.writeBytes(urlParameter);
		dos.flush();
		dos.close();

		BufferedReader br = new BufferedReader(new InputStreamReader(urlC.getInputStream()));
		String inputLine = null;
		StringBuffer responseSB = new StringBuffer();
		
		while ( (inputLine = br.readLine()) != null ) {
			responseSB.append(inputLine);
		}
		br.close();
		
		return responseSB.toString();
	}
	
}
