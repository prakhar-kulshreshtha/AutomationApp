package utilities;

import java.util.ArrayList;
import java.util.List;

import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

public class XmlUtils {

	public static void generateXmlSuites(List<String> selectedTestCases) {
		// TODO Auto-generated method stub
		List<XmlSuite> suites = new ArrayList<XmlSuite>();
		
		for(String testcase :selectedTestCases){
			
			switch (testcase){
			case "Chat test":
				System.out.println("Chat test");
				 XmlSuite suite = new XmlSuite();
				    suite.setName("ChatTestSuite");
				    List<XmlClass> classes = new ArrayList<XmlClass>();
				    classes.add(new XmlClass("testCases.SampleTest1"));

				    XmlTest test1 = new XmlTest(suite);
				    test1.setName("Sample test1");
				    test1.setXmlClasses(classes);

				    suites.add(suite);
				
				
				break;
			case "Explore Test cases":
				System.out.println("Explore Test cases");
				break;
			case "public channels Test cases":
				System.out.println("public channels Test cases");
				break;
			case "Contacts Test cases":
				System.out.println("Contacts Test cases");
				break;
			case "Settings Test cases":
				System.out.println("Settings Test cases");
				break;
			case "Chat session Test cases":
				System.out.println("Chat session Test cases");
				break;
			case "Group chat Test cases":
				System.out.println("Group chat Test cases");
				break;
			case "Voice call Test cases":
				System.out.println("Voice call Test cases");
				break;
			case "Video call Test cases":
				System.out.println("Video call Test cases");
				break;
		case "Sticker Test cases":
				System.out.println("Sticker Test cases");
				break;
							
			}
		}
		TestNG testNG = new TestNG();
	    testNG.setXmlSuites(suites);
	    testNG.run();
	    System.out.println("Test finished");
	}

}
