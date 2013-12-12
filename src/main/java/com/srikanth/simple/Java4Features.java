package com.srikanth.simple;

import java.io.IOException;
import java.util.prefs.Preferences;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Useful Links:-
 * http://www.studytonight.com/java/chained-exception-in-java.php
 * http://www.tutorialspoint.com/ipv4/
 * http://www.vogella.com/articles/JavaRegularExpressions/article.html
 * http://www.vogella.com/articles/JavaPreferences/article.html
 * @author Srikanth
 *
 */
public class Java4Features {
	Logger logger = LoggerFactory.getLogger(Java4Features.class);
	private Preferences prefs;
	public void showChainedExceptions(int a, int b) {
		
		if(b == 0) {
			ArithmeticException aException = new ArithmeticException("Top Level Exception");
			aException.initCause(new IOException("Caused Exception"));
			throw aException;
		} else {
			logger.info("showChainedExceptions: " + a/b);
		}
	}
	public void setPreference() {
		prefs = Preferences.userRoot().node(this.getClass().getName());
		String id1 = "Test1";
		String id2 = "Test2";
		String id3 = "Test3";
		// First we will get the values. Define a boolean value
	    logger.info("" + prefs.getBoolean(id1, true));
	    // Define a string with default "Hello World
	    logger.info("" + prefs.get(id2, "Hello World"));
	    // Define a integer with default 50
	    logger.info("" + prefs.getInt(id3, 50));
	    // now set the values
	    prefs.putBoolean(id1, false);
	    prefs.put(id2, "Hello Europa");
	    prefs.putInt(id3, 45);
	    // Delete the preference settings for the first value
	    prefs.remove(id1);
	}
}
