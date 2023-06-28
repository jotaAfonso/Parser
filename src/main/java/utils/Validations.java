package utils;

import java.util.*;
import java.io.*;
import java.nio.file.Paths;

import com.fasterxml.jackson.databind.*;

public class Validations {

	private boolean validationFlag;
	private boolean foundRegistryOfRoleFlag;

	private static final String ERROR = "ERROR";
	private static final String ERROR_CONTRACT = ERROR + " - CONTRACT";
	private static final String ERROR_CONTRACT_MESSAGE = "Contract %s is not deployed.\n";
	private static final String ERROR_ENDSTATES = ERROR + " - ENDSTATES";
	private static final String ERROR_ENDSTATES_MESSAGE = "End state %s is not a used state.\n";
	private static final String ERROR_PARTICIPANT = ERROR + " - PARTICIPANT";
	private static final String ERROR_PARTICIPANT_MESSAGE = "Participant %s is not registered.\n";

	public Validations() {
		this.validationFlag = true;
		this.foundRegistryOfRoleFlag = false;

	}

	public void setFlag(boolean value) {
		this.validationFlag = value;
	}

	public boolean getFlag() {
		return this.validationFlag;
	}

	public void setFoundRegistryOfRoleFlagP(boolean value) {
		this.foundRegistryOfRoleFlag = value;
	}

	public boolean getFoundRegistryOfRoleFlag() {
		return this.foundRegistryOfRoleFlag;
	}

	public void validateParsedInput(Data d, String outputFile) throws IOException {
		// checks if contract was deployed
		this.checkValidaityOfContracts(d);
		for (Automaton c : d.getAutomataSet()) {
			// checks if the end states exist in the transitions
			this.checkEndStates(c);
			// checks if a state has a path a valid path from the initial state
			// and also checks if it has a valid path to at least one end state
			this.checkValidityOfStates(c, d.getGraph());
			// checks if all participants are registered
			this.checkValidityOfParticipants(c);
			// checks if a participant was previously registered
			checkRegistrationOfParticipants(c, d.getGraph());
		}

		if (this.getFlag())
			for (Automaton c : d.getAutomataSet()) {
				// printAll(c);
				ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
				mapper.writeValue(Paths.get(outputFile.concat(".json")).toFile(), c);
			}

	}

	/*
	 * checks if a contract
	 * */
	public void checkValidaityOfContracts(Data d) {
		Set<String> regContractSet = d.getRegisteredContractsSet();
		if (d.getAutomataIdSet().containsAll(regContractSet)) {
			Set<String> result = new HashSet<String>(regContractSet);
			for (String part : d.getAutomataIdSet()) {
				if (!result.add(part)) {
					result.remove(part);
				}
			}
			if (!result.isEmpty()) {
				UtilsParser.printError(ERROR_CONTRACT, ERROR_CONTRACT_MESSAGE, UtilsParser.setToList(result));
				this.setFlag(false);
			}
		}
	}

	public void checkValidityOfParticipants(Automaton a) {
		if (this.getFlag()) {
			Set<String> regPartSet = a.getRegisteredParticipantsSet();
			if (a.getParticipantsSet().containsAll(regPartSet)) {
				Set<String> result = new HashSet<String>(regPartSet);
				for (String part : a.getParticipantsSet()) {
					if (!result.add(part)) {
						result.remove(part);
					}
				}

				if (!result.isEmpty()) {
					UtilsParser.printError(ERROR_PARTICIPANT, ERROR_PARTICIPANT_MESSAGE, UtilsParser.setToList(result));
					this.setFlag(false);
				}
			}
		}
	}

	public void checkEndStates(Automaton a) {
		if (this.getFlag()) {
			if (!a.getStatesSet().containsAll(a.getEndStatesSet())) {
				List<String> result = new ArrayList<String>();
				for (String state : a.getEndStatesSet()) {
					if (!a.getStatesSet().contains(state)) {
						result.add(state);
					}
				}

				if (!result.isEmpty()) {
					UtilsParser.printError(ERROR_ENDSTATES, ERROR_ENDSTATES_MESSAGE, result);
					this.setFlag(false);
				}
			}
		}
	}

	private void checkValidityOfStates(Automaton a, Graph g) {
		if (this.getFlag()) {
			String initS = a.getInitialState();
			List<String> endS = UtilsParser.setToList(a.getEndStatesSet());

			for (String s : a.getStatesSet()) {
				if (!s.equals(initS) && !endS.contains(s)) {
					this.validationFlag = checkPath(g, s, endS);
					this.validationFlag = checkIfPathExists(g, initS, s);
				} else if (endS.contains(s)) {
					this.validationFlag = checkIfPathExists(g, initS, s);
				} else {
					this.validationFlag = checkPath(g, s, endS);
				}
			}
		}
	}

	private void checkRegistrationOfParticipants(Automaton a, Graph g) {
		if (this.getFlag()) {
			String initS = a.getInitialState();
			List<String> endS = UtilsParser.setToList(a.getEndStatesSet());

			for (Transition op : a.getOperationsSet()) {
				if (!endS.contains(op.getFromState()) && !op.getParticipant().contains(":")) {
					checkIfPathStatePartExists(a, g, initS, op.getToState(), op.getParticipant());
				}
			}
		}
	}

	private boolean checkPath(Graph graph, String state, List<String> endS) {
		for (String e : endS) {
			if (!checkIfPathExists(graph, state, e)) {
				return false;
			}
		}
		return true;
	}

	private boolean checkIfPathStatePartExists(Automaton a, Graph graph, String start, String end, String part) {
		LinkedList<String> visited = new LinkedList<String>();
		visited.add(start);
		boolean result = false;
		Transition starter = a.getOperationsSet().stream().filter(x -> x.getFromState().equals("_")).findAny().get();
		if (!starter.getParticipant().split("[:]")[0].equals(part))
			result = breadthFirstParticipant(graph, visited, start, end, part, false);
		return result;
	}

	private boolean checkIfPathExists(Graph graph, String start, String end) {
		LinkedList<String> visited = new LinkedList<String>();
		visited.add(start);
		boolean flag = false;
		boolean result = breadthFirst(graph, visited, start, end, flag);
		if (!result) {
			System.out.println("No path Exists between " + start + " and " + end);
		}
		return result;
	}

	private boolean breadthFirstParticipant(Graph graph, LinkedList<String> visited, String start, String end,
			String part, boolean reachedEndFlag) {
		String nodeC = visited.getLast();
		LinkedList<String> nodes = graph.adjacentNodes(nodeC);
		HashMap<String, String> map = graph.adjacentNodesPart(nodeC);

		if (nodes.isEmpty())
			this.setFoundRegistryOfRoleFlagP(false);

		for (String node : nodes) {
			if (visited.contains(node)) {
				// loop
				continue;
			}
			if (node.equals(end) && this.getFoundRegistryOfRoleFlag()) {
				visited.add(node);
				reachedEndFlag = true;
				visited.removeLast();
				break;
			}
			if (map.get(node).contains(":") && map.get(node).split("[:]")[0].equals(part)) {
				this.setFoundRegistryOfRoleFlagP(true);
			}
		}

		for (String node : nodes) {
			if (visited.contains(node) || node.equals(end)) {
				continue;
			}
			visited.addLast(node);

			reachedEndFlag = breadthFirstParticipant(graph, visited, start, end, part, reachedEndFlag);
			if (reachedEndFlag)
				break;
			visited.removeLast();
		}

		return reachedEndFlag;
	}

	private boolean breadthFirst(Graph graph, LinkedList<String> visited, String start, String end, boolean flag) {
		LinkedList<String> nodes = graph.adjacentNodes(visited.getLast());

		for (String node : nodes) {
			if (visited.contains(node)) {
				continue;
			}
			if (node.equals(end)) {
				visited.add(node);
				flag = true;
				visited.removeLast();
				break;
			}
		}

		for (String node : nodes) {
			if (visited.contains(node) || node.equals(end)) {
				continue;
			}
			visited.addLast(node);
			flag = breadthFirst(graph, visited, start, end, flag);
			visited.removeLast();
		}

		return flag;
	}
}
