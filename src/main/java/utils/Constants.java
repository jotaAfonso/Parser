package utils;

public class Constants {

	private Constants() {
		
	}
	
	//	MESSAGES
	public static final String ERROR_NO_INPUT_FILE = "Requires at least the input file path.";
	public static final String POSSIBLE_ARGS = "Arguments available - <input_file_path> <output_file_name>";
	
	//	FILES
	public static final String PATH_TEST = "inputFiles/Examples/test.txt";
	public static final String DEFAULT_OUTPUT = "global_type";
	
	//	STATES
	public static final String PRE_INITIAL_STATE = "_";
	
	//	PARTICIPANTS
	public static final String COLON = ":";
	public static final String PART_MULTIPLE = "|";
	
	//	REPLASABLE STRINGS
	public static final String REPLACE_1 = "%s1";
	public static final String REPLACE_2 = "%s2";
		
	//	ERROR MESSAGES
	public static final String EXCEPTION_TRANS_PART_REGIST = "Participant %s1 is already being registered in the transition with the role %s2\n.";

	//	JSON AUTOMATA ELEMENTS
	public static final String JSON_ID = "id";
	public static final String JSON_INITIAL_STATE = "initialState";
	public static final String JSON_STATES = "states";
	public static final String JSON_END_STATES = "finalStates";
	public static final String JSON_TRANSITIONS = "transitions";
	public static final String JSON_INTERNAL_TRANSITIONS = "internalTransitions";
	public static final String JSON_ROLES = "roles";
	public static final String JSON_ROLE_PARTICIPANTS = "rPAssociation";

	//	JSON TRANSITION ELEMENTS
	public static final String JSON_FROM = "from";
	public static final String JSON_TO = "to";
	public static final String JSON_LABEL = "actionLabel";
	public static final String JSON_NEW_PART = "newParts";
	public static final String JSON_EXISTANT_PART = "existantParts";
	public static final String JSON_INPUT = "input";
	public static final String JSON_PRE_COND = "preCondition";
	public static final String JSON_POST_COND = "postCondition";
	public static final String JSON_EXTERNAL_CALL = "externalCall";

	//	VALIDATIONS
	private static final String ERROR = "ERROR";
	public static final String ERROR_CONTRACT = ERROR + " - CONTRACT";
	public static final String ERROR_CONTRACT_MESSAGE = "Contract %s1 is not deployed.\n";
	public static final String ERROR_ENDSTATES = ERROR + " - ENDSTATES";
	public static final String ERROR_ENDSTATES_MESSAGE = "End state %s is not a used state.\n";
	public static final String ERROR_PARTICIPANT = ERROR + " - PARTICIPANT";
	public static final String ERROR_PARTICIPANT_MESSAGE = "Participant %s is not registered.\n";
	public static final String PARTICIPANT_ALREADY_REGISTERED = "Participant %s1 is already registered.\n";
	
}
