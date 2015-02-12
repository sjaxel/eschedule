package eschedule;

import java.text.*;
import java.util.*;
import com.google.gson.*;
import java.io.IOException;
import java.net.MalformedURLException;

public class Test {

	public static void main(String[] args) throws MalformedURLException, IOException, ParseException {
		ArrayList<String> testList;
		Schedule s1 = new Schedule();

		testList = Tools.getAPI("http://lol.gamepedia.com/api.php?action=query&prop=revisions&rvprop=content&format=xmlfm&titles=2015_NA_LCS_Spring/Regular_Season");
		SParse parse = new SParse(testList, s1);
		s1.sort();
		System.out.println(s1);
	}

}
