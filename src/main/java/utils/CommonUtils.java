package utils;

import static utils.Constants.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CommonUtils {
	
	public static String removeTwoStringsFromLabel(String label, String first, String second) {
		label = label.replace(first, "");
		return label.replace(second, "");
	}
	
	public static String replaceInExceptionTwo(String main, String op1, String op2) {
		String result = main.replace(REPLACE_1, op1);
		return result.replace(REPLACE_2, op2);
	}
	
	public static List<String> setToList(Set<String> setValue) {
		return setValue.stream().collect(Collectors.toList());
	}
}
