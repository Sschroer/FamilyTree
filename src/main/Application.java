package main;

import java.time.DateTimeException;
import java.time.LocalDate;

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
	 * Parses a date string in the format "YYYY-MM-DD" and returns a LocalDate object.
	 *
	 * @param date A string representing a date in the format "YYYY-MM-DD".
	 * @return A LocalDate object representing the parsed date, or null if the input is invalid.
	 */
	protected static LocalDate parseDate(String date) {
	    try {
	        String[] parts = date.split("-");
	        if (parts.length != 3) {
	            throw new IllegalArgumentException("Invalid date format. Expected YYYY-MM-DD.");
	        }

	        int year = Integer.parseInt(parts[0]);
	        int month = Integer.parseInt(parts[1]);
	        int day = Integer.parseInt(parts[2]);

	        // Input validation
	        LocalDate.of(year, month, day); // This will throw an exception if the date is invalid

	        return LocalDate.of(year, month, day);
	    } catch (DateTimeException | NumberFormatException e) {
	        // Handle invalid date format or values
	        System.out.println("Invalid date format or values. Please provide date in YYYY-MM-DD format.");
	        return null; 
	    }
	}

}
