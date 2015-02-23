package eschedule;

import java.util.*;
import java.util.Arrays;
import java.io.*;
import java.text.*;
import java.net.*;
import java.io.File;
import java.awt.image.*;
import javax.imageio.*;

public class Tools {
	/**
	 * Tool for getting a UTC Gregorian calendar. (1)
	 * 
	 * @return GregorianCalendar-object with UTC time zone.
	 */
	public static Calendar getCal() {
		TimeZone zone = TimeZone.getTimeZone("UTC");
		ECalendar cal = new ECalendar(zone);
		return cal;
	}

	/**
	 * Returns a formatted SimpleDateFormat. (1)
	 * <p>
	 *
	 * @see java.text.SimpleDateFormat
	 * @return A SimpleDateFormat with the formating "yyyy-MM-dd HH:mm". (3)
	 * @param zone
	 *            A string containing the timezone of the SDF ex. "GMT+2".
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
	 * 
	 * @return A SimpleDateFormat with the formating "yyyy-MM-dd HH:mm". (3)
	 */
	public static SimpleDateFormat getSDF() {
		return getSDF("UTC");
	}

	public static ArrayList<String> getCacheFile(String file) {
		ArrayList<String> tokens = new ArrayList<String>();
		BufferedReader reader;
		FileReader freader;
		String line;
		try {
			freader = new FileReader(new File(file));
			reader = new BufferedReader(freader);
			while ((line = reader.readLine()) != null) {
				tokens.add(line);
			}
			freader.close();
			reader.close();
		} catch (FileNotFoundException f) {
			//
		} catch (IOException i) {
			//
		}
		
		return tokens;
	}

	public static ArrayList<String> getLeaguepedia(String page) {
		ArrayList<String> tokens = new ArrayList<String>();
		String apiAdress = "http://lol.gamepedia.com/api.php?action=query&prop=revisions&rvprop=content&format=xml&redirects&titles=";
		try {
			URL url = new URL(apiAdress + page);
			HttpURLConnection httpcon = (HttpURLConnection) url
					.openConnection();
			httpcon.addRequestProperty("User-Agent",
					"Bot: League Schedule Parser - Contact: sjaxel@github");
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					httpcon.getInputStream()));
			String line;
			System.out.println("Writing file...");
			FileWriter fwriter = new FileWriter(new File("output.txt"));
			BufferedWriter writer = new BufferedWriter(fwriter);

			while (((line = reader.readLine()) != null)) {
				tokens.add(line);
				writer.write(line + "\n");
			}
			reader.close();
			writer.close();

		} catch (MalformedURLException e) {
			System.out.println("Problems with URL");
		} catch (IOException e) {
			System.out.println("IOException: " + e);
		}
		return tokens;
	}
	
	public static BufferedImage loadImage() {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("TeamSprite.png"));
			return img;
		} catch (IOException e) {
			System.out.println(e);
		}
		return img;
	}

	public static Map<String, Team> teamDict() {
		TeamKeys dict = new TeamKeys();
		TParse parse = new TParse(Tools.getCacheFile("teamdict.txt"), dict);
		return dict;
	}
}
