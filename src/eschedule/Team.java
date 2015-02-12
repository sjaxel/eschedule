package eschedule;
import java.util.*;

public class Team {
	private String name;
	private String shortname;
	
	public Team(String shortname, String name) {
	this.name = name;
	this.shortname = shortname;
	}
	
	public String getName() {
		return name;
	}
	
	public String setName() {
		return name;
	}
	
	public String toString() {
		return shortname;
	}
}
