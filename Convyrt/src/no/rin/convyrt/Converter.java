/**
 * 
 */
package no.rin.convyrt;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The Converter takes care of converting amount between units
 * 
 * @author runar, alex
 *
 */
public class Converter {
	
	/*
	 * Convert a measure from a given unit and a given amount to a list of other measures
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<String> convert(String measure, String unit, float amount, String[] units) throws Exception {
		Converter c = new Converter();
		Method m = Converter.class.getMethod(measure, String.class, float.class, String[].class);
		return (ArrayList<String>) m.invoke(c, unit, amount, units);
	}
	
	/*
	 * Convert Distance from a given unit and a given amount to a list of amounts in other units
	 */
	public ArrayList<String> Distance(String from, float amount, String[] units) {
		from = findFrom(from);
		if (from.equals("meter")) {
			ArrayList<String> kaduvil = new ArrayList<String>();
			kaduvil.add("" + amount + " " + units[0] );
			kaduvil.add("" + amount / 1000 + " " + units[1] );
			kaduvil.add("" + amount * 39.37007874 + " " + units[2] );
			return kaduvil;
		} else {
			if (from.equals("inches")) {
				return Distance("meter", amount / 39.37007874f, units);
			} else {
				return Distance("meter", amount * 1000, units);
			}
		}
	}
	
	/*
	 * Convert Mass from a given unit and a given amount to a list of amounts in other units
	 */
	public ArrayList<String> Mass(String from, float amount, String[] units) {
		from = findFrom(from);
		if (from.equals("gram")) {
			ArrayList<String> kaduvil = new ArrayList<String>();
			kaduvil.add("" + amount + " " + units[0] );
			kaduvil.add("" + amount / 1000 + " " + units[1] );
			kaduvil.add("" + amount / 453.59237f + " " + units[2] );
			return kaduvil;
		} else {
			if (from.equals("pounds")) {
				return Mass("gram", amount * 453.59237f, units);
			} else {
				return Mass("gram", amount * 1000, units);
			}
		}
	}
	
	/*
	 * Convert Volume from a given unit and a given amount to a list of amounts in other units
	 */
	public ArrayList<String> Volume(String from, float amount, String[] units) {
		from = findFrom(from);
		if (from.equals("liter")) {
			ArrayList<String> kaduvil = new ArrayList<String>();
			kaduvil.add("" + amount + " " + units[0] );
			kaduvil.add("" + amount * 1000 + " " + units[1] );
			kaduvil.add("" + amount / 3.785411784f + " " + units[2] );
			return kaduvil;
		} else {
			if (from.equals("US gallon")) {
				return Volume("liter", amount * 3.785411784f, units);
			} else {
				return Volume("liter", amount / 1000, units);
			}
		}
	}
	
	/*
	 * Convert Area from a given unit and a given amount to a list of amounts in other units
	 */
	public ArrayList<String> Area(String from, float amount, String[] units) {
		ArrayList<String> kaduvil = new ArrayList<String>();
		kaduvil.add("Weird bugs here.");
		return kaduvil;
//		from = findFrom(from);
//		if (from.equals("square inch")) {
//			ArrayList<String> kaduvil = new ArrayList<String>();
//			kaduvil.add("" + amount + " square inch");
//			kaduvil.add("" + amount / (1.5500031f * Math.pow(10, 9)) + " square kilometer");
//			kaduvil.add("" + amount / 1550.0031f + " square meter");
//			return kaduvil;
//		} else {
//			if (from.equals("square meter")) {
//				return Area("square inch", (amount * 1550.0031f) );
//			} else {
//				float a = (float)(amount * (1.5500031f * Math.pow(10, 9)));
//				return Area("square inch", a);
//			}
//		}
	}
	
	/*
	 * Convert Temperature from a given unit and a given amount to a list of amounts in other units
	 */
	public ArrayList<String> Temperature(String from, float amount, String[] units) {
		from = findFrom(from);
		if (from.equals("Celcius")) {
			ArrayList<String> kaduvil = new ArrayList<String>();
			kaduvil.add("" + amount + " " + units[0] );
			kaduvil.add("" + (((amount*9)/5)+32) + " " + units[1] );
			kaduvil.add("" + (amount + 273.15f) + " " + units[2] );
			return kaduvil;
		} else {
			if (from.equals("Kelvin")) {
				return Temperature("Celcius", (amount-273.15f), units);
			} else {
				return Temperature("Celcius", (((amount-32)*5)/9), units);
			}
		}
	}
	
	/*
	 * Convert Time from a given unit and a given amount to a list of amounts in other units
	 */
	public ArrayList<String> Time(String from, float amount, String[] units) {
		from = findFrom(from);
		if (from.equals("hour")) {
			ArrayList<String> kaduvil = new ArrayList<String>();
			kaduvil.add("" + amount + " " + units[0] );
			kaduvil.add("" + amount*60 + " " + units[1] );
			kaduvil.add("" + amount*3600 + " " + units[2] );
			return kaduvil;
		} else {
			if (from.equals("second")) {
				return Time("hour", amount/3600, units);
			} else {
				return Time("hour", amount/60, units);
			}
		}
	}
	
	/*
	 * Find the unit name
	 */
	private String findFrom(String from) {
		Pattern UNIT = Pattern.compile("^\\w+.\\w+(?=\\ )"); // Match two words
		Matcher m = UNIT.matcher(from);
		while (m.find()) {
			from = m.group(0);
		}
		return from;
	}
}
