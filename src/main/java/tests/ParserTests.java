package tests;

import static org.junit.Assert.*;
import static utils.Constants.*;
import java.io.FileNotFoundException;
import org.junit.Test;

import main.Main;
import parser.ParseException;

public class ParserTests {
	
	private void testCase(String expression, boolean isPath) throws ParseException, FileNotFoundException {
		assertTrue(Main.accept(expression, isPath));		
	}
	
	private void testNegativeCase(String expression, boolean isPath) throws ParseException, FileNotFoundException {
		assertFalse(Main.accept(expression, isPath));
	}
	
	@Test
	public void test01() throws Exception {
		testCase("_ {} o:O > starts(c, y:int) {var int x := y; var bool y1 := true; var int q := 9} S0", false);
		testCase("_ {} o:O > starts(c, y:int) {} S0", false);
		testCase("_ {} o:O > starts(c, y:string) {var string x := \"randomS\"; var string w := y} S0", false);
		testCase("S0 {} b > c.mO() {} S1", false);
		testCase("S0 {true && true} b > c.mO() {} S1", false);
		testCase("S0 {10 > 10 + 2 - 4 / 3 * 6} b > c.mO() {} S1", false);
		testCase(PATH_TEST, true);
	}
	
	@Test
	public void test02() throws Exception {
		testNegativeCase("_ {} o:O > starts(c, y:int) S0", false);	// missing post condition
		testNegativeCase("_ {} o:O > starts(y:int) {} S0", false);	// missing contract id
		testNegativeCase("_ {} o|b:B > starts(c, y:int) {} S0", false);	// participant, is not new
		testNegativeCase("_ {} o > starts(c, y:int) {} S0", false);	// participant, is not new
		testNegativeCase("_ o:O > starts(c, y:int) {} S0", false);	// missing preCondition
		testNegativeCase("S1 {} o:O > starts(c) {} S0", false);	// fromState should be empty
		testNegativeCase("_ {} o:O < starts(c) {} S0", false);	// not a call to other contract
		testNegativeCase("S0 {} o > .h() {} S5+", false);	// missing contract id in the action
		
		testNegativeCase("_ {} o:O > starts(c, y:string) {var string x := \"randomS\"; var string x := y} S0", false);	// varIds not unique 
		testNegativeCase("_ {true && true} o:O > starts(c, y:int) {} S0", false);	// start does not take preConditions
	}
}
