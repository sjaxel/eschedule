package eschedule;

import java.text.*;
import java.io.*;
import java.util.*;
import java.util.regex.*;

public class TParse {
	private ArrayList<String> tokens;
	private TeamKeys keyMap;
	private Pattern patternKey;
	private Pattern patternClose;
	private int lineNr = 0;
	private State status;
	private ArrayList<String> current;

	enum State {
		SEARCHING, PARSING;
	}

	/**
	 * Regurlar expression for matching closing lines of teams. Matches
	 * expression of the type [teamid] = {teamparam1 = value1, teamparam2 =
	 * value2}
	 */
	private static final String REGEXPCLOSE = "(?:\\['(.+?)'\\])"
			+ " .*? (?:\\{([^}]*.?)\\})";

	/**
	 * Matches running lines of team identifiers with the form: [alternative
	 * teamid] = 'teamid'
	 */
	private static final String REGEXPKEY = "(?:\\['(.+?)'\\])"
			+ " .*? (?:'(.+?)')";

	/**
	 * Constructor for TParse.
	 * 
	 * @param t
	 *            The token list containing lines parsed from leaguepedia
	 *            Module:Teamnames
	 * @param kmap
	 *            An empty TeamKeys<String, Match> hashMap that will be
	 *            populated.
	 */
	public TParse(ArrayList<String> t, TeamKeys kmap) {
		tokens = t;
		keyMap = kmap;
		status = State.SEARCHING;
		patternClose = Pattern.compile(REGEXPCLOSE, Pattern.COMMENTS);
		patternKey = Pattern.compile(REGEXPKEY, Pattern.COMMENTS);
		parseLines();

	}

	/**
	 * Function for starting the parse.
	 */
	void parseLines() {
		for (String line : tokens) {
			lineNr++; // For debugging
			parseLine(line);
		}
	}

	/**
	 * Main parser function.
	 * <p>
	 * Parses the teamid and it's alternatives then adds them to the
	 * {@link TeamString} map. The matcher works with a state variable to
	 * determine if it is parsing alternative teamid's or the closing line
	 * containing the team parameters. A closing line calls the parseTeam
	 * function.
	 * 
	 * @param line
	 *            A single line from the list parsed from Module:Teamnames.
	 */
	void parseLine(String line) {
		try {
			Matcher matcherKey = patternKey.matcher(line);
			Matcher matcherClose = patternClose.matcher(line);
			if (matcherClose.find()) {
				switch (status) {
				case SEARCHING:
					current = new ArrayList<String>();
					current.add(matcherClose.group(1));
					// System.out.println("State: Close/" + status + " Keys: " +
					// current);
					try {
						for (String s : current) {
							keyMap.put(s, teamParse(matcherClose.group(2)));
						}
						break;
					} catch (Exception e) {
						System.out.println(e);
						break;
					}

				case PARSING:
					current.add(matcherClose.group(1));
					// System.out.println("State: Close/" + status + " Keys: " +
					// current);
					try {
						for (String s : current) {
							keyMap.put(s, teamParse(matcherClose.group(2)));
						}
						status = State.SEARCHING;
						break;
					} catch (Exception e) {
						status = State.SEARCHING;
						System.out.println(e);
						break;
					}

				}
			} else if (matcherKey.find()) {
				switch (status) {
				case SEARCHING:
					current = new ArrayList<String>();
					current.add(matcherKey.group(1));
					// System.out.println("State: Key/" + status + " Keys: " +
					// current);
					status = State.PARSING;
					break;

				case PARSING:
					current.add(matcherKey.group(1));
					// System.out.println("State: Key/" + status + " Keys: " +
					// current);
					break;

				}
			}

		} catch (IllegalStateException e) {
			System.out.println("No match line: " + lineNr);
		}
	}

	/**
	 * Creates a team from a token of parameters.
	 * <p>
	 * 
	 * @param token
	 *            A token with id=value pairs separated by ",".
	 * @return A {@link Team} with the values from the token.
	 * @throws Exception
	 *             If the team isn't valid after parse an exception is thrown.
	 */
	Team teamParse(String token) throws Exception {
		Team t = new Team();
		Scanner scanner = new Scanner(token);
		scanner.useDelimiter(",");
		while (scanner.hasNext()) {
			if (scanner.hasNext(".*long.*")) {
				String s = SParse.getValue(scanner.next(), "post");
				String substring = s.trim().substring(1, s.length() - 1);
				t.setName(substring);

			} else if (scanner.hasNext(".*short.*")) {
				String s = SParse.getValue(scanner.next(), "post");
				String substring = s.trim().substring(1, s.length() - 1);
				t.setShortName(substring);
			} else {
				scanner.next();
			}
		}
		scanner.close();
		if (t.isValid()) {
			return t;
		} else {
			throw new Exception("TeamParseException: Team not valid.");
		}

	}

}
