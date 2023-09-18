package main;

import static utils.Constants.DEFAULT_OUTPUT;

import exceptions.CustomException;
import exceptions.TypingException;
import parser.ParseException;
import visual.FSMGraphGenerator;

public class VisualMain {
	
	public static void main(String[] args) throws ParseException, CustomException, TypingException, InterruptedException {
		String filePath = DEFAULT_OUTPUT;
		
		if(args.length != 0)
			filePath = args[0];
		
		filePath = filePath + ".json";
		
		FSMGraphGenerator.generateGraph(filePath);
	}
}
