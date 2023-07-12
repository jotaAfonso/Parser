
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Hashtable;

import static utils.Constants.*;

import parser.mainparser.*;
import types.TypingException;
import data.Automaton;
import exceptions.CustomException;
import validations.ValidationChecks;

public class Main {

	private static boolean testFlag = true;

	public static void main(String[] args) throws parser.mainparser.ParseException, parser.assertions.ParseException, CustomException, TypingException {
		if (args.length <= 2 && args.length > 0 || testFlag) {
			String outputF = DEFAULT_OUTPUT;
			String inputF = PATH_TEST;
			if (args.length > 1) {
				inputF = args[0];
				outputF = args[1];
			}

			argumentsGiven(inputF, outputF);
		} else {
			noArgumentsGiven();
		}
	}

	private static void noArgumentsGiven() {
		System.err.printf("%s\u005cn", ERROR_NO_INPUT_FILE);
		System.out.printf("%s\u005cn", POSSIBLE_ARGS);
	}

	private static void argumentsGiven(String inputf, String outputf) throws ParseException, CustomException, TypingException {
		try {
			BufferedReader objReader = new BufferedReader(new FileReader(PATH_TEST));
			Parser parser = new Parser(objReader);
			boolean endOfFile = false;

			Hashtable<String, Automaton> auto = new Hashtable<String, Automaton>();

			ValidationChecks checks = new ValidationChecks();

			while (!endOfFile) {
				switch (parser.Start(auto, checks)) {
				case 0:
					break;
				case 1:
					endOfFile = true;
					break;
				default:
					break;
				}
			}
			
			checks.validate(auto, outputf);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
}