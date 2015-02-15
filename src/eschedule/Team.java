package eschedule;
import java.util.*;

/**
 * Class for storing information about a team.
 * @author sjaxel
 *
 */
public class Team {
	private String name;
	private String shortname;
	private int sprite;
	
	public Team(String shortname, String name) {
	this.name = name;
	this.shortname = shortname;
	}
	
	public Team() {
	}
	
	public String getName() {
		return name;
	}
	
	public String getShortName() {
		return shortname;
	}	
	
	public void setName(String s) {
		name = s;
	}
	
	public void setShortName(String sname) {
		shortname = sname;
	}
	
	public void setSprite(int i) {
		sprite = i;
	}
	
	public boolean isValid() {
		if ((name != null) && (shortname != null)) {
			return true;
		}
		return false;
	}
	
	public String toString() {
		return shortname;
	}
}
