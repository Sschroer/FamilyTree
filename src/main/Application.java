package main;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * This is the code to run the FamilyTree Application
 * @author Stephen Schroer
 *
 */
public class Application {

	/**
	 * This is the main code for the application.
	 * @param args
	 */
	public static void main(String[] args) {

	}

	/**
	 * This method checks to see if dates inputted are the correct format
	 * @param date Date to check.
	 * @return true if format is yyyy-MM-dd. False if it is not.
	 */
	protected boolean isValidDate(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(false);
		try {
			sdf.parse(date);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}
}
