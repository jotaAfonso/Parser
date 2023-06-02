package utils;

import java.util.*;
import java.util.Map.Entry;

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

	public void validateParsedInput(Automaton a) {
		this.checkValidaityOfContracts(a);
		for (Contract c : a.getContractsSet()) {
			this.checkValidityOfParticipants(c);
			this.checkEndStates(c);
			this.checkValidityOfStates(c, a.getGraph());
		}

		if (this.getFlag())
			for (Contract c : a.getContractsSet())
				printAll(c);
	}

	public void printAll(Contract c) {
		System.out.printf("Contract %s\n", c.getIdContract());
		System.out.println("Init state:");
		System.out.println(c.getInitialState() + "\n");

		UtilsParser.printLoop("States:", c.getStatesSet());
		UtilsParser.printLoop("End States:", c.getEndStatesSet());
		UtilsParser.printLoop("Roles:", c.getRolesSet());
		UtilsParser.printLoop("Participants:", c.getRegisteredParticipantsSet());
		UtilsParser.printLoop("Operations:", c.getOperationsSet());
		UtilsParser.printLoop("Internal operations:", c.getInternalOperationsSet());
	}

	public void checkValidaityOfContracts(Automaton a) {
		Set<String> regContractSet = a.getRegisteredContractsSet();
		if (a.getContractsIdSet().containsAll(regContractSet)) {
			Set<String> result = new HashSet<String>(regContractSet);
			for (String part : a.getContractsIdSet()) {
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

	public void checkValidityOfParticipants(Contract c) {
		if (this.getFlag()) {
			Set<String> regPartSet = c.getRegisteredParticipantsSet();
			if (c.getParticipantsSet().containsAll(regPartSet)) {
				Set<String> result = new HashSet<String>(regPartSet);
				for (String part : c.getParticipantsSet()) {
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

	public void checkEndStates(Contract c) {
		if (this.getFlag()) {
			if (!c.getStatesSet().containsAll(c.getEndStatesSet())) {
				List<String> result = new ArrayList<String>();
				for (String state : c.getEndStatesSet()) {
					if (!c.getStatesSet().contains(state)) {
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

	private void checkValidityOfStates(Contract c, Graph g) {
		if (this.getFlag()) {
			String initS = c.getInitialState();
			List<String> endS = UtilsParser.setToList(c.getEndStatesSet());

			for (String s : c.getStatesSet()) {
				if (!s.equals(initS) && !endS.contains(s)) {
					boolean t1 = checkPath(g, s, endS);
					boolean t2 = checkIfPathExists(g, initS, s);
				} else if(endS.contains(s)) {
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
