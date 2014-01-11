package com.srikanth.stax;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
/**
 * Stax is a PULL API and SAX is PUSH API Stax can do both XML reading and
 * writing. SAX can only do XML Reading and Stax can do sub parsing and
 * delegation. StAX has two different reader API's. One that looks most like
 * using an Iterator and one that looks most like using a ResultSet. These are
 * called the "iterator" and "cursor" readers. The iterator reader returns an
 * XML event object from it's nextEvent() calls. From this event object you can
 * see what type of event you had encountered (element, text, comment etc.).
 * This event element is immutable, and can be parsed around to other parts of
 * your application. You can also hang on to earlier event objects when
 * iterating to the next event. As you can see, this works very much like how
 * you use an ordinary Iterator when iterating over a collection. Here, you are
 * just iterating over XML events. Here's a sketch. 
 * The cursor reader does not return events from it's next() call. Rather this call moves the cursor to the
 * next "event" in the XML. You can then call methods directly on the cursor to
 * obtain more information about the current event. This is very similar to how
 * you iterate the records of a JDBC ResultSet, and call methods like
 * getString() or getLong() to get values from the current record pointed to by
 * the ResultSet.
 * So, one of the main differences is, that you can hang on to earlier XML event objects 
 * when using the iterator style API. You cannot do this when using the cursor style API.
 * Note: However, the cursor style API is said to be more memory-efficient than the iterator style API. 
 * So, if your application needs absolute top-performance, use the cursor style API. 
 * Useful Links:
 * https://today.java.net/pub/a/today/2006/07/20/introduction-to-stax.html http://tutorials.jenkov.com/java-xml/stax.html
 * http://tutorials.jenkov.com/java-xml/sax-vs-stax.html
 */

public class StaxCursorReadDemo {
    
    private static boolean bName;
    private static boolean bAge;
    private static boolean bGender;
    private static boolean bRole;

	public static void main(String[] args) {
		String fileName = "src/main/resources/employee.xml";
		List<Employee> empList = parseXML(fileName);
		for (Employee emp : empList) {
			System.out.println(emp.toString());
		}
	}
	/**
	 * @param fileName
	 * @return
	 */
	private static List<Employee> parseXML(String fileName) {
		List<Employee> empList = new ArrayList<>();
		Employee emp = null;
		XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
		try {
			XMLStreamReader xmlStreamReader = xmlInputFactory
					.createXMLStreamReader(new FileInputStream(fileName));
			int event = xmlStreamReader.getEventType();
			while (true) {
				switch (event) {
				case XMLStreamConstants.START_ELEMENT:
					if (xmlStreamReader.getLocalName().equals("Employee")) {
						emp = new Employee();
						emp.setId(Integer.parseInt(xmlStreamReader
								.getAttributeValue(0)));
					} else if (xmlStreamReader.getLocalName().equals("name")) {
						bName = true;
					} else if (xmlStreamReader.getLocalName().equals("age")) {
						bAge = true;
					} else if (xmlStreamReader.getLocalName().equals("role")) {
						bRole = true;
					} else if (xmlStreamReader.getLocalName().equals("gender")) {
						bGender = true;
					}
					break;
				case XMLStreamConstants.CHARACTERS:
					if (bName) {
						emp.setName(xmlStreamReader.getText());
						bName = false;
					} else if (bAge) {
						emp.setAge(Integer.parseInt(xmlStreamReader.getText()));
						bAge = false;
					} else if (bGender) {
						emp.setGender(xmlStreamReader.getText());
						bGender = false;
					} else if (bRole) {
						emp.setRole(xmlStreamReader.getText());
						bRole = false;
					}
					break;
				case XMLStreamConstants.END_ELEMENT:
					if (xmlStreamReader.getLocalName().equals("Employee")) {
						empList.add(emp);
					}
					break;
				}
				if (!xmlStreamReader.hasNext())
					break;

				event = xmlStreamReader.next();
			}
		} catch (FileNotFoundException | XMLStreamException e) {
			e.printStackTrace();
		}
		return empList;
	}
}