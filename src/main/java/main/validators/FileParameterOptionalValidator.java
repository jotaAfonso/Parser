package main.validators;

import static utils.Constants.IT_IS_NOT_A_FILE;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;

import utils.CommonUtils;

public class FileParameterOptionalValidator implements IParameterValidator {

	@Override
	public void validate(String name, String value) throws ParameterException {
		if(!value.contains(".json")) {
			throw new ParameterException(CommonUtils.replaceMsgOne(IT_IS_NOT_A_FILE, name));
		}
	}
}
