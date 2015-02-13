package eschedule;

import java.util.*;
import java.io.*;
import java.text.*;
import java.net.*;

public class Tools {
	/**
	 * Tool for getting a UTC gregorian calendar. (1)
	 * @return GregorianCalendar-object with UTC time zone.
	 */
	public static Calendar getCal() {
		TimeZone zone = TimeZone.getTimeZone("UTC");
		Calendar cal = new GregorianCalendar(zone);
		return cal;
	}
	
	/**
	*Returns a formatted SimpleDateFormat.	(1)
	*<p>
	*@see java.text.SimpleDateFormat
	*@return A SimpleDateFormat with the formating "yyyy-MM-dd HH:mm". (3)
	*@param zone A string containing the timezone of the SDF ex. "GMT+2".
	*/
	public static SimpleDateFormat getSDF(String zone) {
		SimpleDateFormat sdf;
		sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		sdf.setTimeZone(TimeZone.getTimeZone(zone));
		sdf.setLenient(false);
		return sdf;
	}

	
	/**
	 * Parameterless getSDF with formating "yyyy-MM-dd HH:mm".
	 * @return A SimpleDateFormat with the formating "yyyy-MM-dd HH:mm". (3)
	 */
	public static SimpleDateFormat getSDF() {
		return getSDF("UTC");
	}
	
	public static ArrayList<String> getLeaguepedia(String page) {
		ArrayList<String> tokens = new ArrayList<String>();
		String apiAdress = "http://lol.gamepedia.com/api.php?action=query&prop=revisions&rvprop=content&format=xmlfm&titles=";
		try {
		    URL url = new URL(apiAdress + page);
		    HttpURLConnection httpcon = (HttpURLConnection) url.openConnection(); 
		    httpcon.addRequestProperty("User-Agent", "League Schedule Parser - Contact: sjaxel@github");
		    BufferedReader reader = new BufferedReader(new InputStreamReader(httpcon.getInputStream()));
		    String line;
		    
		    while ((line = reader.readLine()) != null) {
		        if (line.indexOf("GameSchedule") > 0) {
		        	tokens.add(line);
		        }
		    }
		    reader.close();

		} catch (MalformedURLException e) {
		    System.out.println("Problems with URL");
		} catch (IOException e) {
			System.out.println("IOException: " + e);
		}
		return tokens;
	}
	
	public static Map<String, Team> teamDict() {
		Map<String, Team> dict = new HashMap<String, Team>();
		dict.put("c9", new Team("C9", "Cloud9"));
		dict.put("clg", new Team("CLG", "Counter Logic Gaming"));
		dict.put("gravity", new Team("GG", "Gravity Gaming"));
		dict.put("t8", new Team("T8", "Team 8"));
		dict.put("c", new Team("CST", "Team Coast"));
		dict.put("d", new Team("DIG", "Team Dignitas"));
		dict.put("tip", new Team("TIP", "Team Impulse"));
		dict.put("liquid", new Team("LIQ", "Team Liquid"));
		dict.put("tsm", new Team("TSM", "Team Solo Mid"));
		dict.put("wfx", new Team("WFX", "Winterfox"));
		dict.put("cw", new Team("CW", "Copenhagen Wolves"));
		dict.put("elements", new Team("EL", "Elements"));
		dict.put("fnatic", new Team("FNC", "Fnatic"));
		dict.put("gg", new Team("GMB", "Gambit Gaming"));
		dict.put("giants!", new Team("GIA", "GIANTS! Gaming"));
		dict.put("h2k", new Team("H2K", "H2K-Gaming"));
		dict.put("mym", new Team("MYM", "MeetYourMakers"));
		dict.put("sk", new Team("SK", "SK Gaming"));
		dict.put("roccat", new Team("ROC", "Team ROCCAT"));
		dict.put("uol", new Team("UOL", "Unicorns of Love"));
		return dict;
	}
}
