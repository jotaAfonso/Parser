package utils;

import java.util.*;
import java.util.stream.Collectors;

public class Data {

	private Set<String> contractsRegistered = new HashSet<String>();
	private Hashtable<String, Automaton> automata = new Hashtable<String, Automaton>();
	
	private List<Transition> transitions = new ArrayList<Transition>();
	private Graph graph;
	
	public Data() {
		setGraph(new Graph());
	}

	/** adds a registered contract to the automaton, used for checking */
	public void addRegisteredContract(String contract) {
		// TODO: check if this split is needed
		String[] elements = contract.split("[.]");
		String cId = UtilsParser.removeTwoStringsFromLabel(elements[0], "starts(", ")");
		this.contractsRegistered.add(cId);
	}

	public Set<String> getRegisteredContractsSet() {
		return this.contractsRegistered;
	}

	/** adds a contract to the automaton */
	public void addContract(String contractLabel) {
		String cId = UtilsParser.removeTwoStringsFromLabel(contractLabel, "starts(", ")");
		this.automata.put(cId,new Automaton(cId));
	}
	
	public Automaton getContractById(String idValue) {
		String idLocal = UtilsParser.removeTwoStringsFromLabel(idValue, "starts(", ")");
		return this.automata.get(idLocal);
	}

	public Set<Automaton> getAutomataSet() {
		return this.automata.values().stream().collect(Collectors.toSet());
	}

	public Set<String> getAutomataIdSet() {
		return this.automata.keySet();
	}

	public Graph getGraph() {
		return graph;
	}

	public void setGraph(Graph graph) {
		this.graph = graph;
	}

	public List<Transition> getTransitions() {
		return transitions;
	}

	public void setTransitions(List<Transition> transitions) {
		this.transitions = transitions;
	}

	public void addTransitions(Transition transition) {
		this.transitions.add(transition);
	}
}