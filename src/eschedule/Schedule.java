package eschedule;

import java.util.*;

public class Schedule {
	private List<Match> matches;
	
	public Schedule() {
		matches = new ArrayList<>();
	}
	
	public void addMatch(Match... matchlist) {
		for (Match m : matchlist) {
			matches.add(m);
		}
	}
	
	public void sort() {
		Collections.sort(matches);
	}
	
	public String toString() {
		String str = "";
		for (Match m : matches) {
		    str += m.toString() + "\n";
		}
		return str;

	}
	}


