package main;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Hashtable;

import parser.*;
import tests.ParserTests;
import tests.Tuple2;
import data.Automaton;
import exceptions.CustomException;
import exceptions.TypingException;
import validations.ValidationChecks;
import visual.FSMGraphGenerator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;

public class Main {

	final static MainCLIParameters mainArgs = new MainCLIParameters();

	public static void main(String[] args) throws ParseException, CustomException, TypingException, IOException, InterruptedException {
		JCommander jCommander = new JCommander(mainArgs);
		jCommander.setProgramName("parser");

		try {
			jCommander.parse(args);
			if(mainArgs.isVisualize())
				FSMGraphGenerator.generateGraph(mainArgs.getInputPath().toString());
			else
				argumentsGiven(mainArgs);
		} catch (ParameterException e) {
			System.out.println(e.getMessage());
			showUsage(jCommander);
		}

		if (mainArgs.isHelp()) {
			showUsage(jCommander);
		}
	}

	private static void showUsage(JCommander jCommander) {
		jCommander.usage();
		System.exit(0);
	}

	private static void argumentsGiven(MainCLIParameters args)
			throws ParseException, CustomException, TypingException, IOException, InterruptedException {
		
		if(args.isTest()) {
			ParserTests tests = new ParserTests();
			tests.testPositiveCases();
			tests.testNegativeCases();
		}
			
		
		try {
			BufferedReader objReader = Files.newBufferedReader(args.getInputPath());
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

			checks.validate(auto, args);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	public static Tuple2<String, Boolean> accept(String s, boolean isPath) throws FileNotFoundException {
		Parser parser;
		if (isPath) {
			BufferedReader objReader;

			objReader = new BufferedReader(new FileReader(s));

			parser = new Parser(objReader);
		} else {
			ByteArrayInputStream st = new ByteArrayInputStream(s.getBytes());
			parser = new Parser(st);
		}
		Hashtable<String, Automaton> auto = new Hashtable<String, Automaton>();
		ValidationChecks checks = new ValidationChecks();

		try {
			parser.Start(auto, checks);
			return new Tuple2<String, Boolean>("", true);
		} catch (TokenMgrError e) {
			return new Tuple2<String, Boolean>(e.getMessage(), false);
		} catch (ParseException e) {
			return new Tuple2<String, Boolean>(e.getMessage(), false);
		} catch (CustomException e) {
			return new Tuple2<String, Boolean>(e.getMessage(), false);
		} catch (TypingException e) {
			return new Tuple2<String, Boolean>(e.getMessage(), false);
		}

	}
}