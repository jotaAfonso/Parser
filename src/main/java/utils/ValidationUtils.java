package utils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ValidationUtils {
 
	public static void printError(String title, String message, List<String> result) {
		System.err.println(title);
		for (String part : result) {
			System.err.printf(message, part);
		}
	}
	
	public static void printLoop(String x, Set<String> y) {
		System.out.println(x);
		for (String i : y) {
			System.out.println(i);
		}
		System.out.println();
	}
	
	public static List<String> setToList(Set<String> setValue) {
		return setValue.stream().collect(Collectors.toList());
	}
}
