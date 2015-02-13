package eschedule;

import java.text.*;
import java.util.*;
import java.io.IOException;
import java.net.MalformedURLException;

public class Test {

	public static void main(String[] args) throws MalformedURLException, IOException, ParseException {
		ArrayList<String> testList, testList2;
		Schedule s1 = new Schedule();

		testList = Tools.getLeaguepedia("2015_NA_LCS_Spring/Regular_Season");
		testList2 = Tools.getLeaguepedia("2015_EU_LCS_Spring/Regular_Season");
		SParse parse = new SParse(s1, testList, testList2);
		s1.unSpoil();
		//s1.hidePast();
		s1.sort();
		System.out.println(s1);
	}

}
