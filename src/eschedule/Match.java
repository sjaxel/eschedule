package eschedule;
import java.util.*;
import java.net.*;

public class Match implements Comparable<Match> {
	private static int id = 1;
    static enum Status {
        HIDDEN, VISIBLE, SPOILERS;
    }
	
    Status state;
	private int uid;
	private ECalendar time;
	private Team t1;
	private Team t2;
	private int t1score;
	private int t2score;
	private Team winner;
	private String league;
	private URL vod;
	
	/**
	 * Constructor for the Match-class. Creates an empty match with current time as
	 * and Status.SPOILERS as default.
	 */
	public Match() {
		state = Status.SPOILERS;
		uid = id; id++;
	}

	public boolean isValid() {
		if ((t1 != null) && (t2 != null) && (time != null))
			return true;
		else
			return false;		
	}
	
	/**
	 * Get the time for the match.
	 * @return A {@link Calendar} objects with the time of the match set.
	 */
	public Calendar getCal() {
		return time;
	}
	
	/**
	 * Get the time for the match.
	 * @return Returns a {@link Date} value with match time in milleseconds
	 * since the current epoch.
	 */
	public Date getTime() {
		return time.getTime();
	}

	public void initDate(Date date){
		time = (ECalendar) Tools.getCal();
		time.setTime(date);
	}

	public void setDate(Date date){
		time.setTime(date);
	}
	
	public void setWeek(int w) {
		time.setWeek(w);
	}
	
	public int getWeek() {
		return time.getWeek();
	}
	
	public void setVOD(String url) {
		if (url != null) {
		try {
			vod = new URL(url);
		} catch (MalformedURLException e) {
		}
		}
	}
	
	public URL getVOD() {
		if (vod != null) {
			return vod;
		}
		else {
			return null;
		}
	}
	
	public void setWinner(int t) {
		if (t > 2 || t < 0) {
			throw new IllegalArgumentException("Team index out of bounds (should be 1 or 2)");
		}
		if (t == 1)
			winner = t1;
		else
			winner = t2;	
	}
	
	public Team getWinner() {
		return winner;
	}
	
	public Status getStatus() {
		return state;
	}
	
	public void setSpoiler() {
		state = Status.SPOILERS;
	}
	
	public void setHidden() {
		state = Status.HIDDEN;
	}
	
	public void setVisible() {
		state = Status.VISIBLE;
	}
	
	public void setLeague(String league) {
		this.league = league;
	}
	
	public String getLeague() {
		return league;
	}
	
	public void setTeam(int t, Team team) {
		if (t > 2 || t < 0) {
			throw new IllegalArgumentException("Team index out of bounds (should be 1 or 2)");
		}
		if (t == 1)
			this.t1 = team;
		else
			this.t2 = team;
	}
	public Team getTeam(int t) {
		if (t > 2 || t < 0) {
			throw new IllegalArgumentException("Team index out of bounds (should be 1 or 2)");
		}
		if (t == 1)
			return t1;
		else
			return t2;
	}
	
	public int getUID() {
		return uid;
	}
	
	public String[] getTeamsToString() {
		String st1 = t1.getName();
		String st2 = t2.getName();
		return new String[] {st1, st2};
	}
	
	public int compareTo(Match m) {
		return time.getTime().compareTo(m.getTime());
	}
	
	public String toString() {
		if (state == Status.VISIBLE) {
			return "Week: " + Integer.toString(getWeek())
					+ " " + Tools.getSDF().format(getTime()) 
					+ " - " + t1 + " vs. " + t2;
		}
		else if (state == Status.SPOILERS) {
			return "Week: " + Integer.toString(getWeek())
					+ " " + Tools.getSDF().format(getTime()) 
					+ " - " + t1 + " vs. " + t2;
		}
		else {
			return "";
		}
	}
}
