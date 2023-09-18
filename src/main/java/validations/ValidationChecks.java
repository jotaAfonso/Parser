package validations;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.LinkedList;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import main.MainCLIParameters;
import utils.CommonUtils;

public class ValidationChecks {

	private Graph graph;
	private int counter = 0;

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

	public void validate(Hashtable<String, Automaton> auto, MainCLIParameters args) throws CustomException, InterruptedException {
		this.checkIfContractsWereStarted(auto.keySet());
		for (Automaton a : auto.values()) {
//			// checks if a state has a path a valid path from the initial state
//			// and also checks if it has a valid path to at least one end state
			this.checkValidityOfStates(a, this.getGraph());
//			// checks if all participants are registered
			this.checkValidityOfParticipants(a);
//			// checks if a participant was previously registered
//			this.checkRegistrationOfParticipants(a, this.getGraph());
		}

		toFileAndImage(auto, args);

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
				msg = CommonUtils.replaceMsgOne(msg, rL.get(0));
				throw new CustomException(msg);
			}
		}
	}

	private void toFileAndImage(Hashtable<String, Automaton> auto, MainCLIParameters args) throws InterruptedException {
		Path outputP;
		if (args.getOutputPath() == null) {
			outputP = args.getInputPath().getParent();
			outputP = outputP.resolve("global_type.json");
		} else
			outputP = args.getOutputPath();

		for (Automaton a : auto.values()) {
			ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
			try {
				mapper.writeValue(outputP.toFile(), a);
				if (args.isVisual())
					FSMGraphGenerator.generateGraph(outputP.toString());
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
			throw new CustomException(CommonUtils.replaceMsgOne(PARTICIPANT_ALREADY_REGISTERED, p));
		else
			this.getParticipantsRegistered().add(p);
	}

	private void checkValidityOfParticipants(Automaton a) throws CustomException {
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
				msg = CommonUtils.replaceMsgOne(msg, rL.get(0));
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
				for (String p : op.getExistentParts().getParticipants()) {
					if (!checkIfPathStatePartExists(a, g, initS, op.getFromS(), p)) {
						String msg = CommonUtils.replaceMsgTwo(ERROR_PARTICIPANT_WAS_NOT_REGISTERED_PREVIOUSLY, p,
								op.getActionLabel());
						throw new CustomException(msg);
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
			String msg = CommonUtils.replaceMsgOne(ERROR_NO_PATH_BETWEEN_TO_ENDSTATE, state);
			throw new CustomException(msg);
		}
	}

	private void checkPathFromBeginning(Graph graph, String start, String end) throws CustomException {
		boolean existsPath = checkIfPathExists(graph, start, end);
		if (existsPath) {
			return;
		}

		Pattern pattern = Pattern.compile("I(\\d+)");
		Matcher matcher = pattern.matcher(end);
		if (matcher.find()) {
			return;
		}

		String msg = CommonUtils.replaceMsgTwo(ERROR_NO_PATH_BETWEEN_STATES, start, end);
		throw new CustomException(msg);
	}

	private boolean checkIfPathExists(Graph graph, String start, String end) {
		LinkedList<String> visited = new LinkedList<String>();
		visited.add(start);
		return breadthFirstSearch(graph, visited, start, end, "", false, false, false);
	}

	private boolean checkIfPathStatePartExists(Automaton a, Graph graph, String start, String end, String part)
			throws CustomException {
		LinkedList<String> visited = new LinkedList<String>();
		visited.add(start);
		boolean result = true;
		Transition starter = a.getTransitions().stream().filter(x -> x.getFromS().equals("_")).findAny().get();
		for (AssociationRP nP : starter.getNewParts()) {
			if (!nP.getParticipants().contains(part)) {
				result = breadthFirstSearch(graph, visited, start, end, part, false, false, true);
			}
		}
		return result;
	}

	private boolean breadthFirstParticipantCondition(String participantsOfEdge, String participantTarget) {
		for (String pE : participantsOfEdge.split("[|]")) {
			if (pE.contains(":") && pE.split("[:]")[0].equals(participantTarget))
				return true;
		}
		return false;
	}

	private boolean breadthFirstSearch(Graph graph, LinkedList<String> visited, String start, String end, String part,
			boolean reachedEndFlag, boolean participantRegistered, boolean conditionFlag) {

		String currentNode = visited.getLast();
		LinkedList<String> adjacentNodes = graph.adjacentNodes(currentNode);
		HashMap<String, String> adjacentNodesParticipants = graph.adjacentNodesPart(currentNode);

		for (String node : adjacentNodes) {
			if (visited.contains(node)) {
				continue;
			}
			if (node.equals(end) && (!conditionFlag || (conditionFlag && participantRegistered))) {
				visited.add(node);
				reachedEndFlag = true;
				visited.removeLast();
				break;
			}
			if (conditionFlag && breadthFirstParticipantCondition(adjacentNodesParticipants.get(node), part))
				participantRegistered = true;
		}

		for (String node : adjacentNodes) {
			if (visited.contains(node) || node.equals(end)) {
				continue;
			}
			visited.addLast(node);
			reachedEndFlag = breadthFirstSearch(graph, visited, start, end, part, reachedEndFlag, participantRegistered,
					conditionFlag);
			visited.removeLast();
		}
		return reachedEndFlag;
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
}
