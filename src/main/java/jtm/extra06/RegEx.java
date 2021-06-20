package jtm.extra06;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegEx {

	public boolean isLuckyNumber(String input) {

		// #1 Remove all non digits from the input.
		// HINT: use negation pattern.

		String digitsOnly = input.replaceAll("[^0-9]", "");
		int sum = 0;
		for (int i = 0; i < digitsOnly.length(); i++) {
			sum += Integer.parseInt(digitsOnly.substring(i, i + 1));
		}
		return sum == 25;
	}

	/**
	 * This method finds Kenny or Kelly hiding in this list. "Kenny" or "Kelly" can be written with
	 * arbitrary number of "n"s or "l"s starting with two.
	 * 
	 * @param input
	 *            — input string
	 * @return — position of "Kenny" string starting with zero. If there are no
	 *         "Ken..ny" return -1.
	 */
	public int findKenny(String input) {
		String regexString = "Ke[nl]{2,}y";
		Pattern pattern = Pattern.compile(regexString);
		Matcher matcher = pattern.matcher(input);
		if (matcher.find()) {
			System.out.println(input + " " + matcher.start());
			return matcher.start();
		} else {
			return -1;
		}
	}

	/**
	 * THis method checks if input string is correct telephone number. Correct
	 * Riga phone number starts with 67 or 66 and is followed by 6 other digits.
	 * not obligate prefix +371
	 * 
	 * @param telephoneNumber
	 *            - number, needed to be checked.
	 * @return true if number is valid Riga city number.
	 */
	public boolean isGood(String telephoneNumber) {
		// #5 check with "matches" method if this number is valid.
		String validNumber = "(\\+371)*((67|66)[0-9]{6})";
		return telephoneNumber.matches(validNumber);
	}
}
