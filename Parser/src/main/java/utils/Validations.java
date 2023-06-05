package utils;

import java.util.*;
import java.io.*;
import java.nio.file.Paths;

import com.fasterxml.jackson.databind.*;

public class Validations {

	private boolean validationFlag;
	private Graph graph;

	private static final String ERROR = "ERROR";
	private static final String ERROR_CONTRACT = ERROR + " - CONTRACT";
	private static final String ERROR_CONTRACT_MESSAGE = "Contract %s is not deployed.\n";
	private static final String ERROR_ENDSTATES = ERROR + " - ENDSTATES";
	private static final String ERROR_ENDSTATES_MESSAGE = "End state %s is not a used state.\n";
	private static final String ERROR_PARTICIPANT = ERROR + " - PARTICIPANT";
	private static final String ERROR_PARTICIPANT_MESSAGE = "Participant %s is not registered.\n";

	public Validations() {
		this.validationFlag = true;
	}

	public void setFlag(boolean value) {
		this.validationFlag = value;
	}

	public boolean getFlag() {
		return this.validationFlag;
	}

	public void validateParsedInput(Data d) throws IOException {
		this.checkValidaityOfContracts(d);
		for (Automaton c : d.getAutomataSet()) {
			this.checkValidityOfParticipants(c);
			this.checkEndStates(c);
			this.checkValidityOfStates(c, d.getGraph());
		}

		if (this.getFlag())
			for (Automaton c : d.getAutomataSet()) {
				// printAll(c);
				ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
				mapper.writeValue(Paths.get("Out.json").toFile(), c);
			}

	}

	public void printAll(Automaton a) {
		System.out.printf("Contract %s\n", a.getId());
		System.out.println("Init state:");
		System.out.println(a.getInitialState() + "\n");

		UtilsParser.printLoop("States:", a.getStatesSet());
		UtilsParser.printLoop("End States:", a.getEndStatesSet());
		UtilsParser.printLoop("Roles:", a.getRolesSet());
		UtilsParser.printLoop("Participants:", a.getRegisteredParticipantsSet());
		//UtilsParser.printLoop("Operations:", a.getOperationsSet());
		//UtilsParser.printLoop("Internal operations:", a.getInternalOperationsSet());
	}

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
					boolean t1 = checkPath(g, s, endS);
					boolean t2 = checkIfPathExists(g, initS, s);
				} else if (endS.contains(s)) {
					boolean t2 = checkIfPathExists(g, initS, s);
				} else {
					boolean t1 = checkPath(g, s, endS);
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

	public Graph getGraph() {
		return graph;
	}

	public void setGraph(Graph graph) {
		this.graph = graph;
	}
}
