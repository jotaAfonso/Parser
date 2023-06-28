# Parser

Tested with Java 8

use Jar to run 

Arguments available - <input_file_path> <output_file_name>

<output_file_name> not required

examples:

java -jar .\JavaParserGlobalType.jar "inputFiles/Examples/simpleMarketPlace.txt" "global_output"
java -jar .\JavaParserGlobalType.jar "inputFiles/Examples/simpleMarketPlace.txt"

Grammar of parser:

exepression:
(S1) part c.operation (S2)
- where S1 is the initial state of the action;
- where S2 is the end state of the action;
- where part is the participant that invokes the action; 
- where c.operation is the contract_id and action label;

rules:
contracts need to be deployed/started by the following expression:
(_) o:O starts(c) (S0);
- the participant of this expression needs its type -> o:O meaning participant:RoleType

the participant section of an expression can either just be a registered participant, a new participant or both;
(part) || (part:Role1) || (part1|part2:Role1)
new participants need to be registered before calling an action.

need to indicate what are the end states by using the following expression:
ENDSTATES - c - {(S5)};
ENDSTATES - <contract_id> - {(eState_1), (eState_2)}

validations of the parser:
- checks if the contract was deployed/started;
- checks if the end states exist in the actions provided;
- checks if a state has a valid path from the initial state
and also checks if if it has a valid path to at least one end state.
- checks if all participants are registered;
- checks if a participant can call the action (checks if a participant was previously registred).