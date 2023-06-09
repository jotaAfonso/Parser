package validations;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.LinkedList;
import java.util.Set;
import static utils.Constants.*;

import visual.*;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import data.AssociationRP;
import data.Automaton;
import data.Transition;
import exceptions.CustomException;
import utils.CommonUtils;

public class ValidationChecks {

	private Graph graph;
	private int counter = 0;
	private boolean foundRegistryOfRoleFlag = false;

	private Set<String> participantsAll = new HashSet<String>();
	private Set<String> participantsRegistered = new HashSet<String>();
	private Set<String> contractsRegistered = new HashSet<String>();

	private FSMGraphGenerator visuals;

	public ValidationChecks() {
		this.graph = new Graph();
		this.setVisuals(new FSMGraphGenerator());
	}

	public Graph getGraph() {
		return graph;
	}

	public void setGraph(Graph graph) {
		this.graph = graph;
	}

	public FSMGraphGenerator getVisuals() {
		return visuals;
	}

	public void setVisuals(FSMGraphGenerator visuals) {
		this.visuals = visuals;
	}

	// TODO: confirm for all contracts not just one
	public void validate(Hashtable<String, Automaton> auto, String outputF) throws CustomException {
		this.checkIfContractsWereStarted(auto.keySet());
		for (Automaton a : auto.values()) {
//			// checks if a state has a path a valid path from the initial state
//			// and also checks if it has a valid path to at least one end state
//			this.checkValidityOfStates(a, this.getGraph());
//			// checks if all participants are registered
			this.checkValidityOfParticipants(a);
//			// checks if a participant was previously registered
//			this.checkRegistrationOfParticipants(a, this.getGraph());
		}

		toFileAndImage(auto, outputF);

	}

	public void checkIfContractsWereStarted(Set<String> autoIds) throws CustomException {
		Set<String> regContractSet = this.getContractsRegistered();
		if (autoIds.containsAll(regContractSet)) {
			Set<String> result = new HashSet<String>(regContractSet);
			for (String part : autoIds) {
				if (!result.add(part)) {
					result.remove(part);
				}
			}
			if (!result.isEmpty()) {
				String msg = ERROR_CONTRACT.concat(" - ").concat(ERROR_CONTRACT_MESSAGE);
				List<String> rL = CommonUtils.setToList(result);
				msg = CommonUtils.replaceInExceptionOne(msg, rL.get(0));
				throw new CustomException(msg);
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

	public Set<String> getParticipantsRegistered() {
		return participantsRegistered;
	}

	public void setParticipantsRegistered(Set<String> contractsRegistered) {
		this.participantsRegistered = contractsRegistered;
	}

	public void addParticipantsRegistered(String p) throws CustomException {
		if (this.getParticipantsRegistered().contains(p))
			throw new CustomException(CommonUtils.replaceInExceptionOne(PARTICIPANT_ALREADY_REGISTERED, p));
		else
			this.getParticipantsRegistered().add(p);
	}

	public void checkValidityOfParticipants(Automaton a) throws CustomException {
		Set<String> regPartSet = this.getParticipantsRegistered();
		if (this.getParticipantsAll().containsAll(regPartSet)) {
			Set<String> result = new HashSet<String>(regPartSet);
			for (String part : this.getParticipantsAll()) {
				if (!result.add(part)) {
					result.remove(part);
				}
			}

			if (!result.isEmpty()) {
				String msg = ERROR_PARTICIPANT.concat(" - ").concat(ERROR_PARTICIPANT_MESSAGE);
				List<String> rL = CommonUtils.setToList(result);
				msg = CommonUtils.replaceInExceptionOne(msg, rL.get(0));
				throw new CustomException(msg);
			}
		}
	}

	private void checkValidityOfStates(Automaton a, Graph g) throws CustomException {
		String initS = a.getInitialS();
		List<String> endS = CommonUtils.setToList(a.getEndS());

		for (String s : a.getStates()) {
			if (!s.equals(initS) && !endS.contains(s)) {
				checkPathToEnd(g, s, endS);
				checkPathFromBeginning(g, initS, s);
			} else if (endS.contains(s))
				checkPathFromBeginning(g, initS, s);
			else
				checkPathToEnd(g, s, endS);
		}
	}

	private void checkRegistrationOfParticipants(Automaton a, Graph g) throws CustomException {
		String initS = a.getInitialS();
		List<String> endS = CommonUtils.setToList(a.getEndS());

		for (Transition op : a.getTransitions()) {
			if (!endS.contains(op.getFromS())) {
				for (AssociationRP nP : op.getExistantParts()) {
					for (String p : nP.getParticipants()) {
						if (!checkIfPathStatePartExists(a, g, initS, op.getToS(), p)) {
							String msg = CommonUtils.replaceInExceptionTwo(ERROR_PARTICIPANT_WAS_NOT_REGISTERED_PREVIOUSLY, p, op.getActionLabel());
							throw new CustomException(msg);
						}
					}
				}
			}
		}
	}

	private void checkPathToEnd(Graph graph, String state, List<String> endS) throws CustomException {
		boolean noPath = true;
		for (String e : endS) {
			if (checkIfPathExists(graph, state, e)) {
				noPath = false;
				break;
			}
		}
		if (noPath) {
			String msg = CommonUtils.replaceInExceptionOne(ERROR_NO_PATH_BETWEEN_TO_ENDSTATE, state);
			throw new CustomException(msg);
		}
	}

	private void checkPathFromBeginning(Graph graph, String start, String end) throws CustomException {
		boolean existsPath = checkIfPathExists(graph, start, end);
		if (!existsPath) {
			String msg = CommonUtils.replaceInExceptionTwo(ERROR_NO_PATH_BETWEEN_STATES, start, end);
			throw new CustomException(msg);
		}
	}

	private boolean checkIfPathExists(Graph graph, String start, String end) {
		LinkedList<String> visited = new LinkedList<String>();
		visited.add(start);
		return breadthFirst(graph, visited, start, end, false);
	}

	private boolean checkIfPathStatePartExists(Automaton a, Graph graph, String start, String end, String part)
			throws CustomException {
		LinkedList<String> visited = new LinkedList<String>();
		visited.add(start);
		boolean result = true;
		Transition starter = a.getTransitions().stream().filter(x -> x.getFromS().equals("_")).findAny().get();
		for (AssociationRP nP : starter.getNewParts()) {
			for (String p : nP.getParticipants()) {
				if (!p.equals(part)) {
					result = breadthFirstParticipant(graph, visited, start, end, part, false);
				}
			}
		}
		return result;
	}

//
	private boolean breadthFirstParticipant(Graph graph, LinkedList<String> visited, String start, String end,
			String part, boolean reachedEndFlag) {
		String nodeC = visited.getLast();
		LinkedList<String> nodes = graph.adjacentNodes(nodeC);
		HashMap<String, String> map = graph.adjacentNodesPart(nodeC);

		if (nodes.isEmpty())
			this.setFoundRegistryOfRoleFlag(false);

		for (String node : nodes) {
			if (visited.contains(node)) {
				// loop
				continue;
			}
			if (node.equals(end) && this.isFoundRegistryOfRoleFlag()) {
				visited.add(node);
				reachedEndFlag = true;
				visited.removeLast();
				break;
			}
			if (map.get(node).contains(":") && map.get(node).split("[:]")[0].equals(part)) {
				this.setFoundRegistryOfRoleFlag(true);
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

//
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

	public Set<String> getContractsRegistered() {
		return contractsRegistered;
	}

	public void setContractsRegistered(Set<String> contractsRegistered) {
		this.contractsRegistered = contractsRegistered;
	}

	public Set<String> getParticipantsAll() {
		return participantsAll;
	}

	public void setParticipantsAll(Set<String> participantsAll) {
		this.participantsAll = participantsAll;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public boolean isFoundRegistryOfRoleFlag() {
		return foundRegistryOfRoleFlag;
	}

	public void setFoundRegistryOfRoleFlag(boolean foundRegistryOfRoleFlag) {
		this.foundRegistryOfRoleFlag = foundRegistryOfRoleFlag;
	}
}
