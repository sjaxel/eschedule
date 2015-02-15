package eschedule;

import java.util.*;

import eschedule.Match.Status;


public class Schedule {
	private List<Match> matches;
	
	public Schedule() {
		matches = new ArrayList<Match>();
	}
	
	public void addMatch(Match... matchlist) {
		for (Match m : matchlist) {
			matches.add(m);
		}
	}
	
	public List<Match> getMatch(int... uid) {
		List<Match> getMatches = new ArrayList<Match>();
		for (Match m : matches) {
			
			getMatches.add(m);
		}
		return getMatches;
	}
	
	public int getSize() {
		return matches.size();
	}
	
	public void unSpoil() {
		for (Match m : matches) {
			m.setVisible();
		}
	}
	
	public void showWeeks(int... weeks) {
		hideAll();
		for (Match m : matches) {
			for (int w : weeks) {
				if (m.getWeek() == w) {
					m.setVisible();
				}
			}
		}
	}
	
	public void sort() {
		Collections.sort(matches);
	}

	public void hidePast() {
		Calendar now = new ECalendar();
		for (Match m : matches) {
			if (m.getCal().compareTo(now) < 0)
				m.setHidden();
		}
	}
	
	public void hideAll() {
		for (Match m : matches) {
			m.setHidden();
		}
	}
		
	public String toString() {
		String str = "";
		for (Match m : matches) {
			if (m.getStatus() != Status.HIDDEN)
				str += m.toString() + "\n";
		}
		return str;

	}
	}


