package eschedule;

import java.util.HashMap;

public class TeamKeys extends HashMap<String, Team> {

	public TeamKeys() {
		super(1000);
	}	

	public TeamKeys(int size) {
		super(size);
	}

}
