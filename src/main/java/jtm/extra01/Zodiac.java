package jtm.extra01;

public class Zodiac {

	/**
	 * Determine the sign of the zodiac, use day and month.
	 * 
	 * @param day
	 * @param month
	 * @return zodiac
	 */

	//  Implement method which return zodiac sign names
	// As method parameter - day and month;
	// Look at wikipedia:
	// https://en.wikipedia.org/wiki/Zodiac#Table_of_dates
	// Tropical zodiac, to get appropriate date ranges for signs

	public static String getZodiac(int day, int month) {
		String zodiac = null;

		if      ((month == 12 && day >= 22 && day <= 31) || (month ==  1 && day >= 1 && day <= 20))
			zodiac = "Capricorn";
		else if ((month ==  1 && day >= 21 && day <= 31) || (month ==  2 && day >= 1 && day <= 19))
			zodiac = "Aquarius";
		else if ((month ==  2 && day >= 20 && day <= 29) || (month ==  3 && day >= 1 && day <= 20))
			zodiac = "Pisces";
		else if ((month ==  3 && day >= 21 && day <= 31) || (month ==  4 && day >= 1 && day <= 20))
			zodiac = "Aries";
		else if ((month ==  4 && day >= 21 && day <= 30) || (month ==  5 && day >= 1 && day <= 21))
			zodiac = "Taurus";
		else if ((month ==  5 && day >= 22 && day <= 31) || (month ==  6 && day >= 1 && day <= 21))
			zodiac = "Gemini";
		else if ((month ==  6 && day >= 22 && day <= 30) || (month ==  7 && day >= 1 && day <= 22))
			zodiac = "Cancer";
		else if ((month ==  7 && day >= 23 && day <= 31) || (month ==  8 && day >= 1 && day <= 22))
			zodiac = "Leo";
		else if ((month ==  8 && day >= 23 && day <= 31) || (month ==  9 && day >= 1 && day <= 23))
			zodiac = "Virgo";
		else if ((month ==  9 && day >= 23 && day <= 30) || (month == 10 && day >= 1 && day <= 23))
			zodiac = "Libra";
		else if ((month == 10 && day >= 24 && day <= 31) || (month == 11 && day >= 1 && day <= 22))
			zodiac = "Scorpio";
		else if ((month == 11 && day >= 23 && day <= 30) || (month == 12 && day >= 1 && day <= 21))
			zodiac = "Sagittarius";
		return zodiac;
	}

	public static void main(String[] args) {
		// HINT: you can use main method to test your getZodiac method with
		// different parameters
		System.out.println(getZodiac(21, 5));
		System.out.println(getZodiac(1, 6));
		System.out.println(getZodiac(18, 11));
	}

}
