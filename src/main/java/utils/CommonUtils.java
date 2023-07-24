package utils;

import static utils.Constants.*;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import ast.ASTId;
import exceptions.CustomException;
import types.IType;

// TODO: Auto-generated Javadoc
/**
 * The Class CommonUtils.
 */
public class CommonUtils {
	
	/**
	 * Removes the two strings from label.
	 *
	 * @param label - label
	 * @param first - first
	 * @param second - second
	 * @return the revised label
	 */
	public static String removeTwoStringsFromLabel(String label, String first, String second) {
		label = label.replace(first, "");
		return label.replace(second, "");
	}
	
	/**
	 * Replaces two substrings in a message.
	 *
	 * @param msg - message being changed
	 * @param op1 - first new text of the message
	 * @param op2 - second new text of the message
	 * @return the revised message
	 */
	public static String replaceMsgTwo(String msg, String op1, String op2) {
		String result = msg.replace(REPLACE_1, op1);
		return result.replace(REPLACE_2, op2);
	}
	
	/**
	 * Replaces one substring in a message.
	 *
	 * @param msg - message being changed
	 * @param op1 - new text of the message
	 * @return the revised message
	 */
	public static String replaceMsgOne(String msg, String op1) {
		return msg.replace(REPLACE_1, op1);
	}
	
	/**
	 * Changes a set to a list.
	 *
	 * @param set - set value
	 * @return list with the same values of the set
	 */
	public static List<String> setToList(Set<String> set) {
		return set.stream().collect(Collectors.toList());
	}
	
	/**
	 * Adds the type to IDs.
	 *
	 * @param ids 	  - IDS
	 * @param globalV - global V
	 * @param localV  - local V
	 * @throws CustomException - custom exception
	 */
	public static void addTypeToIDs(List<ASTId> ids, Map<String,IType> globalV, Map<String,IType> localV) throws CustomException {
		for(ASTId id : ids) {
			if(globalV.containsKey(id.getId()))
				id.setType(globalV.get(id.getId()));
			else if(localV.containsKey(id.getId()))
				id.setType(localV.get(id.getId()));
			else {
				String msg = CommonUtils.replaceMsgOne(EXCEPTION_VARIABLE_WAS_NOT_INITIALIZE_CORRECTLY, id.getId());
				throw new CustomException(msg);
			}
		}			
	}
}