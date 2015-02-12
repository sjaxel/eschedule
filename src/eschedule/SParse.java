package eschedule;

import java.text.*;
import java.io.*;
import java.lang.*;
import java.net.MalformedURLException;
import java.nio.*;
import java.nio.file.*;
import java.nio.charset.*;
import java.util.*;
import java.util.regex.*;


/**
 * Parser class for reading match details from Leaguepedia and creating Match-objects
 * that are added to a schedule that is passed to the constructor of the class.
 * @author sjaxel
 *
 */
public class SParse {
	private Map<String, Team> dict = Tools.teamDict();
	private ArrayList<String> tokens;
	private Schedule sch;
	private final static Charset ENCODING = StandardCharsets.UTF_8;
	
	
	public SParse(ArrayList<String> tokens, Schedule s) throws IOException, ParseException {
	this.tokens = tokens;
	sch = s;
	System.out.println("Parse created");
	parseLines();
	}
	
	public void parseLines() throws IOException, ParseException {
		for (String s : tokens) {
			parseLine(s);
		}
	}
	/**
	 * Returns values of tokens "id=value"
	 * @param token Token formated "id=value" where pre and post are values.
	 * @param pos Takes a string "pre" or "post" on which value to return.
	 * @return A string with the value before or after the "=" in the token.
	 */
	String getValue(String token, String pos) {
		if (pos == "post")
			return token.substring((token.indexOf('='))+1).trim();
		else if (pos == "pre")
			return token.substring(0, (token.indexOf('='))).trim();
		else
			return null;
	}
	
	/**
	 * Main line-parser method. Takes a line with match details, creates a match, adds parsed 
	 * details and adds it to the schedule of the current SParse object.
	 * 
	 * @param line The string line containing match details with delimiters "|" and tokens "id=value".
	 * @throws ParseException If shit goes down.
	 */
	void parseLine(String line)  throws ParseException {
		Match match = new Match();
		Date time;
		String hour = null;
		String date = null;
		String zone = null;
		
		Scanner scanner  = new Scanner(line);
		scanner.useDelimiter("\\|");
		while (scanner.hasNext()) {
			if (scanner.hasNext("team1.*")) {
				String token = scanner.next();
				match.setTeam(1, dict.get(getValue(token, "post")));
			}
			else if (scanner.hasNext("team2.*")) {
				String token = scanner.next();
				match.setTeam(2, dict.get(getValue(token, "post")));
			}
			else if (scanner.hasNext("date.*")) {
				String token = scanner.next();
				date = getValue(token, "post");
			}
			else if (scanner.hasNext(".*\\d\\d:\\d\\d.*")) {
				String token = scanner.next();
				hour = getValue(token, "post");
				zone = getValue(token, "pre");
			}
			else if (scanner.hasNext(".*winner.*")) {
				String token = scanner.next();
				try {
					int value = Integer.parseInt(getValue(token, "post"));
					match.setWinner(value);
				}
				catch (NumberFormatException  e) {
					//This just states that there isn't a winner yet.
				}
			}			
			else if (scanner.hasNext(".*shownname.*")) {
				String token = scanner.next();
				match.setLeague((getValue(token, "post")));
			}
			else 
				scanner.next();
		}
		scanner.close();
		
		match.setDate(Tools.getSDF(zone).parse(date + " " + hour));
		sch.addMatch(match);
	}
	
	/**
	 * Get function for the filled schedule.
	 * @return The schedule that was passed to the SParse constructor.
	 */
	public Schedule getSched() {
		return sch;
	}
}
