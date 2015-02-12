package eschedule;
import java.util.*;
import java.text.*;

public class Match implements Comparable<Match> {
	private static int id = 1;

	
	private int uid;
	private Calendar time;
	private Team t1;
	private Team t2;
	private int t1score;
	private int t2score;
	private Team winner;
	private String league;
	
	
	public Match(Team t1, Team t2, String date) throws ParseException  {
		time = Tools.getCal();
		time.setTime(Tools.getSDF().parse(date));
		uid = id; id++;
		this.t1 = t1;
		this.t2 = t2;
	}
	
	
	public Match() {
		time = Tools.getCal();
		uid = id; id++;
	}
	
	public Calendar getCal() {
		return time;
	}
	
	public Date getTime() {
		return time.getTime();
	}
	
	public void setDate(Date date){
		time.setTime(date);
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
		return league + " - " + Tools.getSDF().format(getTime()) + " - " + t1 + " vs. " + t2;
	}
}
