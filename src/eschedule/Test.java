package eschedule;

import java.text.*;
import java.util.*;
import java.io.IOException;
import java.net.MalformedURLException;

import com.mongodb.MongoException;
import com.mongodb.MongoTimeoutException;

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
		MongoWrite writer = new MongoWrite("localhost", 27017);
		try {
			writer.insert("schedule15s", s1.getDBO());
		} catch (MongoTimeoutException e) {
			System.out.println(e);
		} catch (MongoException e) { 
			System.out.println(e);
		} finally {
			writer.close();
			System.out.println("MongoClient closed");
		}
		//s1.showWeeks(new int[] {5,6,7});
		//SView frame = new SView(s1);
		//frame.addMatches();
		//frame.display();


	}

}
