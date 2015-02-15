package eschedule;
import java.util.*;

public class ECalendar extends GregorianCalendar {
	private int week;
	
	public ECalendar () {
		super();
	}
	public ECalendar (TimeZone zone) {
		super(zone);
	}
	
	public void setWeek(int w) {
		week = w;
	}
	
	public int getWeek() {
		return week;
	}
}
