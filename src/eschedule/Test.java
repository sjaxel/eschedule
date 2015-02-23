package eschedule;

import java.text.*;
import java.util.*;
import javax.swing.*;
import java.io.IOException;
import java.net.MalformedURLException;

public class Test {

	public static void main(String[] args) throws MalformedURLException,
			IOException, ParseException {
			
		ArrayList<String> testList1, testList2, testList3, testCache;
		Schedule s1 = new Schedule();

		testList1 = Tools.getLeaguepedia("2015_NA_LCS_Spring/Regular_Season");
		testList2 = Tools.getLeaguepedia("2015_EU_LCS_Spring/Regular_Season");
		//testList3 = Tools.getLeaguepedia("2015_LCK_Spring/Round_Robin");
		//testCache = Tools.getCacheFile("Test");
		SParse parse = new SParse(s1, testList1, testList2);
		s1.sort(); s1.unSpoil();
		s1.showWeeks(new int[] {5,6,7});
		SView frame = new SView(s1);
		frame.addMatches();
		frame.display();


	}

}
