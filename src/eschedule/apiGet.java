package eschedule;
import java.net.*;

import com.google.gson.*;
import java.io.*;

public class apiGet {
	private static final String BASEURL = "http://na.lolesports.com/api";
	//Request and open a connection.
	public static JsonArray getJson(String apiGet) throws MalformedURLException, IOException {
		URL url = new URL(BASEURL + apiGet);
		HttpURLConnection request = (HttpURLConnection) url.openConnection();
		request.addRequestProperty("User-Agent", "Mozilla/4.76");
		request.connect();

	    JsonParser jp = new JsonParser(); //from gson
	    JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent())); //convert the input stream to a json element
	    JsonArray rootobj = root.getAsJsonArray(); //may be an array, may be an object.
	    return rootobj;
	}
}