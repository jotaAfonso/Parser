package main.validators;

import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;

import utils.CommonUtils;

import static utils.Constants.*;

public class FileParameterValidator implements IParameterValidator {

	@Override
	public void validate(String name, String value) throws ParameterException {
		Path pathToConfigDir = Paths.get(value);
		if(!exists(pathToConfigDir)) {
			throw new ParameterException(CommonUtils.replaceMsgOne(FILE_DOES_NOT_EXIST, name));
		}
		
		if(!Files.isRegularFile(pathToConfigDir, LinkOption.NOFOLLOW_LINKS)) {
			throw new ParameterException(CommonUtils.replaceMsgOne(IT_IS_NOT_A_FILE, name));
		}
	}
	
	private boolean exists(Path path) {
		return (Files.exists(path, LinkOption.NOFOLLOW_LINKS));
	}
}
