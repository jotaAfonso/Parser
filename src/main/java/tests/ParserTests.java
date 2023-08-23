package tests;

import static utils.Constants.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import main.Main;

public class ParserTests {

	ArrayList<Tuple2<String, Boolean>> positiveCases = new ArrayList<Tuple2<String, Boolean>>();
	ArrayList<Tuple2<String, Boolean>> negativeCases = new ArrayList<Tuple2<String, Boolean>>();
	
	private void testPositiveCase(String expression, boolean isPath) {
		Tuple2<String, Boolean> result = null;
		try {
			result = Main.accept(expression, isPath);
		} catch (FileNotFoundException e) {
			System.err.println("File " + expression + " Not Found.");
		}
		if (!result.getSecond()) {
			if(!isPath)
				System.err.println("The expression (" + expression + ") has the following errors:");
			else
				System.err.println("The File (" + expression + ") has the following errors:");	
			System.err.println(result.getFirst());
        }		
	}
	
	private static void testNegativeCase(String expression, boolean isPath) {
		Tuple2<String, Boolean> result = null;
		try {
			result = Main.accept(expression, isPath);
		} catch (FileNotFoundException e) {
			System.err.println("File " + expression + " Not Found.");
		}
		if (!result.getSecond()) {
			if(!isPath)
				System.err.println("The expression (" + expression + ") has the following errors:");
			else
				System.err.println("The File (" + expression + ") has the following errors:");	
			System.err.println(result.getFirst());
        }
	}
	
	public void addCases(ArrayList<Tuple2<String, Boolean>> cases, String expression, boolean isPath) {
		cases.add(new Tuple2<String, Boolean>(expression, isPath));
	}
	
	public void addPositiveCases() {
		addCases(positiveCases, "_ {} o:O > starts(c, y:int) {} S0", false);
		addCases(positiveCases, "_ {} o:O > starts(c, y:string) {string x := \"randomS\" && string w := y} S0", false);
		addCases(positiveCases, "S0 {} b > c.mO() {} S1", false);
		addCases(positiveCases, "S0 {true && true} b > c.mO() {} S1", false);
		addCases(positiveCases, "S0 {10 > 10 + 2 - 4 / 3 * 6} b > c.mO() {} S1", false);
		addCases(positiveCases, PATH_TEST, true);
	}
	
	public void addNegativeCases() {
		addCases(negativeCases, "_ {} o:O > starts(c, y:int) S0", false);	// missing post condition
		addCases(negativeCases, "_ {} o:O > starts(y:int) {} S0", false);	// missing contract id
		addCases(negativeCases, "_ {} o|b:B > starts(c, y:int) {} S0", false);	// participant, is not new
		addCases(negativeCases, "_ {} o > starts(c, y:int) {} S0", false);	// participant, is not new
		addCases(negativeCases, "_ o:O > starts(c, y:int) {} S0", false);	// missing preCondition
		addCases(negativeCases, "S1 {} o:O > starts(c) {} S0", false);	// fromState should be empty
	}
	
	public void testPositiveCases() {
		addPositiveCases();
		for(Tuple2<String, Boolean> c : this.positiveCases) 
			testPositiveCase(c.getFirst(), c.getSecond());
	}
	
	public void testNegativeCases() {
		addNegativeCases();
		for(Tuple2<String, Boolean> c : this.negativeCases) 
			testNegativeCase(c.getFirst(), c.getSecond());
	}
}
