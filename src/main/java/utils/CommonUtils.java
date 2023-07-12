package utils;

import static utils.Constants.*;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import ast.ASTVar;
import exceptions.CustomException;
import types.IType;

public class CommonUtils {
	
	public static String removeTwoStringsFromLabel(String label, String first, String second) {
		label = label.replace(first, "");
		return label.replace(second, "");
	}
	
	public static String replaceInExceptionTwo(String main, String op1, String op2) {
		String result = main.replace(REPLACE_1, op1);
		return result.replace(REPLACE_2, op2);
	}
	
	public static String replaceInExceptionOne(String main, String op1) {
		return main.replace(REPLACE_1, op1);
	}
	
	public static List<String> setToList(Set<String> setValue) {
		return setValue.stream().collect(Collectors.toList());
	}
	
	public static void addTypeToIds(List<ASTVar> ids, Map<String,IType> globalV, Map<String,IType> localV) throws CustomException {
		for(ASTVar id : ids) {
			if(globalV.containsKey(id.getId()))
				id.setType(globalV.get(id.getId()));
			else if(localV.containsKey(id.getId()))
				id.setType(localV.get(id.getId()));
			else {
				String msg = CommonUtils.replaceInExceptionOne(EXCEPTION_VARIABLE_WAS_NOT_INITIALIZE_CORRECTLY, id.getId());
				throw new CustomException(msg);
			}
		}			
	}
}
