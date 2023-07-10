package validations;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import static utils.Constants.*;

import visual.*;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import data.Automaton;
import utils.ValidationUtils;

public class ValidationChecks {

	private Graph graph;
	private boolean validFlag;
	
	private Set<String> contractsRegistered = new HashSet<String>();
	
	private FSMGraphGenerator visuals;
	
	public ValidationChecks() {
		validFlag = true;
		this.setVisuals(new FSMGraphGenerator());
	}

	public Graph getGraph() {
		return graph;
	}

	public void setGraph(Graph graph) {
		this.graph = graph;
	}
	
	public boolean isValidFlag() {
		return validFlag;
	}

	public void setValidFlag(boolean validFlag) {
		this.validFlag = validFlag;
	}

	public FSMGraphGenerator getVisuals() {
		return visuals;
	}

	public void setVisuals(FSMGraphGenerator visuals) {
		this.visuals = visuals;
	}

	public void validate(Hashtable<String, Automaton> auto, String outputF) {
		this.checkIfContractsWereStarted(auto.keySet());
//		for (Automaton c : d.getAutomataSet()) {
//			// checks if the end states exist in the transitions
//			this.checkEndStates(c);
//			// checks if a state has a path a valid path from the initial state
//			// and also checks if it has a valid path to at least one end state
//			this.checkValidityOfStates(c, d.getGraph());
//			// checks if all participants are registered
//			this.checkValidityOfParticipants(c);
//			// checks if a participant was previously registered
//			checkRegistrationOfParticipants(c, d.getGraph());
//		}
		
		
		if (this.isValidFlag()) {
			toFileAndImage(auto, outputF);
		}
	}

	public void checkIfContractsWereStarted(Set<String> autoIds) {
		Set<String> regContractSet = this.getContractsRegistered();
		if (autoIds.containsAll(regContractSet)) {
			Set<String> result = new HashSet<String>(regContractSet);
			for (String part : autoIds) {
				if (!result.add(part)) {
					result.remove(part);
				}
			}
			if (!result.isEmpty()) {
				ValidationUtils.printError(ERROR_CONTRACT, ERROR_CONTRACT_MESSAGE, ValidationUtils.setToList(result));
				this.setValidFlag(false);
			}
		}
	}

	private void toFileAndImage(Hashtable<String, Automaton> auto, String outputF) {
		for (Automaton a : auto.values()) {
			ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
			try {
				mapper.writeValue(Paths.get(outputF.concat(".json")).toFile(), a);
				FSMGraphGenerator.generateGraph(outputF.concat(".json"));
				System.out.print("File Generated");
			} catch (StreamWriteException e) {
				e.printStackTrace();
			} catch (DatabindException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}	
	}

	public Set<String> getContractsRegistered() {
		return contractsRegistered;
	}

	public void setContractsRegistered(Set<String> contractsRegistered) {
		this.contractsRegistered = contractsRegistered;
	}
	
//	public void checkValidityOfParticipants(Automaton a) {
//		if (this.getFlag()) {
//			Set<String> regPartSet = a.getRegisteredParticipantsSet();
//			if (a.getParticipantsSet().containsAll(regPartSet)) {
//				Set<String> result = new HashSet<String>(regPartSet);
//				for (String part : a.getParticipantsSet()) {
//					if (!result.add(part)) {
//						result.remove(part);
//					}
//				}
//
//				if (!result.isEmpty()) {
//					UtilsParser.printError(ERROR_PARTICIPANT, ERROR_PARTICIPANT_MESSAGE, UtilsParser.setToList(result));
//					this.setFlag(false);
//				}
//			}
//		}
//	}
//
//	public void checkEndStates(Automaton a) {
//		if (this.getFlag()) {
//			if (!a.getStatesSet().containsAll(a.getEndStatesSet())) {
//				List<String> result = new ArrayList<String>();
//				for (String state : a.getEndStatesSet()) {
//					if (!a.getStatesSet().contains(state)) {
//						result.add(state);
//					}
//				}
//
//				if (!result.isEmpty()) {
//					UtilsParser.printError(ERROR_ENDSTATES, ERROR_ENDSTATES_MESSAGE, result);
//					this.setFlag(false);
//				}
//			}
//		}
//	}
//
//	private void checkValidityOfStates(Automaton a, Graph g) {
//		if (this.getFlag()) {
//			String initS = a.getInitialState();
//			List<String> endS = UtilsParser.setToList(a.getEndStatesSet());
//
//			for (String s : a.getStatesSet()) {
//				if (!s.equals(initS) && !endS.contains(s)) {
//					this.validationFlag = checkPath(g, s, endS);
//					this.validationFlag = checkIfPathExists(g, initS, s);
//				} else if (endS.contains(s)) {
//					this.validationFlag = checkIfPathExists(g, initS, s);
//				} else {
//					this.validationFlag = checkPath(g, s, endS);
//				}
//			}
//		}
//	}
//
//	private void checkRegistrationOfParticipants(Automaton a, Graph g) {
//		if (this.getFlag()) {
//			String initS = a.getInitialState();
//			List<String> endS = UtilsParser.setToList(a.getEndStatesSet());
//
//			for (Transition op : a.getOperationsSet()) {
//				if (!endS.contains(op.getFromState()) && !op.getParticipant().contains(":")) {
//					checkIfPathStatePartExists(a, g, initS, op.getToState(), op.getParticipant());
//				}
//			}
//		}
//	}
//
//	private boolean checkPath(Graph graph, String state, List<String> endS) {
//		for (String e : endS) {
//			if (!checkIfPathExists(graph, state, e)) {
//				return false;
//			}
//		}
//		return true;
//	}
//
//	private boolean checkIfPathStatePartExists(Automaton a, Graph graph, String start, String end, String part) {
//		LinkedList<String> visited = new LinkedList<String>();
//		visited.add(start);
//		boolean result = false;
//		Transition starter = a.getOperationsSet().stream().filter(x -> x.getFromState().equals("_")).findAny().get();
//		if (!starter.getParticipant().split("[:]")[0].equals(part))
//			result = breadthFirstParticipant(graph, visited, start, end, part, false);
//		return result;
//	}
//
//	private boolean checkIfPathExists(Graph graph, String start, String end) {
//		LinkedList<String> visited = new LinkedList<String>();
//		visited.add(start);
//		boolean flag = false;
//		boolean result = breadthFirst(graph, visited, start, end, flag);
//		if (!result) {
//			System.out.println("No path Exists between " + start + " and " + end);
//		}
//		return result;
//	}
//
//	private boolean breadthFirstParticipant(Graph graph, LinkedList<String> visited, String start, String end,
//			String part, boolean reachedEndFlag) {
//		String nodeC = visited.getLast();
//		LinkedList<String> nodes = graph.adjacentNodes(nodeC);
//		HashMap<String, String> map = graph.adjacentNodesPart(nodeC);
//
//		if (nodes.isEmpty())
//			this.setFoundRegistryOfRoleFlagP(false);
//
//		for (String node : nodes) {
//			if (visited.contains(node)) {
//				// loop
//				continue;
//			}
//			if (node.equals(end) && this.getFoundRegistryOfRoleFlag()) {
//				visited.add(node);
//				reachedEndFlag = true;
//				visited.removeLast();
//				break;
//			}
//			if (map.get(node).contains(":") && map.get(node).split("[:]")[0].equals(part)) {
//				this.setFoundRegistryOfRoleFlagP(true);
//			}
//		}
//
//		for (String node : nodes) {
//			if (visited.contains(node) || node.equals(end)) {
//				continue;
//			}
//			visited.addLast(node);
//
//			reachedEndFlag = breadthFirstParticipant(graph, visited, start, end, part, reachedEndFlag);
//			if (reachedEndFlag)
//				break;
//			visited.removeLast();
//		}
//
//		return reachedEndFlag;
//	}
//
//	private boolean breadthFirst(Graph graph, LinkedList<String> visited, String start, String end, boolean flag) {
//		LinkedList<String> nodes = graph.adjacentNodes(visited.getLast());
//
//		for (String node : nodes) {
//			if (visited.contains(node)) {
//				continue;
//			}
//			if (node.equals(end)) {
//				visited.add(node);
//				flag = true;
//				visited.removeLast();
//				break;
//			}
//		}
//
//		for (String node : nodes) {
//			if (visited.contains(node) || node.equals(end)) {
//				continue;
//			}
//			visited.addLast(node);
//			flag = breadthFirst(graph, visited, start, end, flag);
//			visited.removeLast();
//		}
//
//		return flag;
//	}
}
