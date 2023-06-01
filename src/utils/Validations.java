package utils;

import java.util.*;
import java.util.Map.Entry;

public class Validations {

	private boolean validationFlag;

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
		this.checkValidaityOfContract(a);
		for (Contract c : a.getContractsSet()) {
			this.checkValidityOfParticipants(c);
			this.checkEndStates(c);
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

	public void checkValidaityOfContract(Automaton a) {
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

	public static Graph calculateShortestPathFromSource(Graph graph, Node source) {
		source.setDistance(0);

		Set<Node> settledNodes = new HashSet<Node>();
		Set<Node> unsettledNodes = new HashSet<Node>();

		unsettledNodes.add(source);

		while (unsettledNodes.size() != 0) {
			Node currentNode = getLowestDistanceNode(unsettledNodes);
			unsettledNodes.remove(currentNode);
			for (Entry<Node, Integer> adjacencyPair : currentNode.getAdjacentNodes().entrySet()) {
				Node adjacentNode = adjacencyPair.getKey();
				Integer edgeWeight = adjacencyPair.getValue();
				if (!settledNodes.contains(adjacentNode)) {
					calculateMinimumDistance(adjacentNode, edgeWeight, currentNode);
					unsettledNodes.add(adjacentNode);
				}
			}
			settledNodes.add(currentNode);
		}
		return graph;
	}

	private static Node getLowestDistanceNode(Set<Node> unsettledNodes) {
		Node lowestDistanceNode = null;
		int lowestDistance = Integer.MAX_VALUE;
		for (Node node : unsettledNodes) {
			int nodeDistance = node.getDistance();
			if (nodeDistance < lowestDistance) {
				lowestDistance = nodeDistance;
				lowestDistanceNode = node;
			}
		}
		return lowestDistanceNode;
	}

	private static void calculateMinimumDistance(Node evaluationNode, Integer edgeWeigh, Node sourceNode) {
		Integer sourceDistance = sourceNode.getDistance();
		if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {
			evaluationNode.setDistance(sourceDistance + edgeWeigh);
			LinkedList<Node> shortestPath = new LinkedList<Node>(sourceNode.getShortestPath());
			shortestPath.add(sourceNode);
			evaluationNode.setShortestPath(shortestPath);
		}
	}
}
