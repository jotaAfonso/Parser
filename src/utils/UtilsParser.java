package utils;

import java.util.*;
import java.util.stream.Collectors;

public class UtilsParser {

	public UtilsParser() {
	}

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

	public static String removeParenthesisFromString(String label) {
		return removeTwoStringsFromLabel(label, "(", ")");
	}

	public static String removeTwoStringsFromLabel(String label, String first, String second) {
		label = label.replace(first, "");
		return label.replace(second, "");
	}

	public static List<String> setToList(Set<String> setValue) {
		return setValue.stream().collect(Collectors.toList());
	}

	public static String getContractIdFromMethod(String method) {
		return UtilsParser.removeTwoStringsFromLabel(method, "starts(", ")");
	}
}
