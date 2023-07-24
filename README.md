# Parser

This project is being developed with the intention of capturing the behaviour of Smart Contracts(SC).  
It also possess a visualizer to view the generated fsms.

## Prerequisites 
- Java 8  
- Javacc

## Usage
The application takes an input file, with the actions inside, and it will check if it generates a valid automaton. 

```
Options:
    -fsm, --fsm
      Displays the generated automaton as a fsm.
      Default: false
    -h, --help
      Displays help information.
      Default: false
  * -i, --inputFile
      Path to the source file.
    -o, --outputFile
      Path to the destination file. If not specified it will have the same 
      path as the input file.
```

### Examples:  
```
java -jar .\GlobalParser.jar -i "inputFiles/Examples/simpleMarketPlace.txt" 
java -jar .\GlobalParser.jar -i "inputFiles/Examples/simpleMarketPlace.txt" -o "output.path"
```

## Grammar of the parser:

## General expression of a Transition:
```
initialState preCodition participant action postCondition finalState ~+
```
### States (initial and final)
IDs are used to indicate their value.
```
S1 ... S2
```
If the plus sign is used at the end of the line, it indicates that the final state of the transition is an endState.
```
S1 ... S2+ -> S2 is an endState
```

### Participants
Are composed of the following type:
```
type of participantRole = 
  <string>:PType

type of participant =
     p                              (string, single existing/ already registered participant)
  || p : participantRole            (single new participant)
  || participant | participant      (multiple participants in the same action, parenthesis are not part of it)
```
Participants need to first be registered in the automaton in order to be called once again.

### Action:
Two distinct actions:
- deploy  (required to be indicated for each automaton stated) (type of deploy can only be normal). ex.:
```
o:O > starts(c)
```
- and not deploy actions, normal ones. It is composed of the contract id, the type (. or -) and the action label. ex.:
```
o > c.h()
```
An Action can also be of three types: 
- either a normal action ```(> cID.)```, which is observable by all participants.
- an internal action ```(> cID-)```, that indicates that the action is specific to a role and will not be projected onto other participant roles.
- an external action ```(< cID.)```, that represents an interaction with a different contract.

### Validations of the parser:
- checks if the contract was deployed/started 
- checks if a state has a valid path from the initial state and also checks if it has a valid path to at least one end state 
- checks if all participants are registered
- checks if a participant can call the action (checks if a participant was previously registred)

### Example
![image](https://github.com/jotaAfonso/Parser/assets/49497176/6f5927f3-18cb-451c-ba0f-73fd3490cab3)

