package ParserPackage;

import java.util.*;
import java.util.stream.Collectors;

public class Automaton {

	String initialState;
	HashSet<String> states = new HashSet<String>();
	HashSet<String> endStates = new HashSet<String>();
	
	HashSet<String> roles = new HashSet<String>();
	// TODO: change so that a participant can have more roles
	Hashtable<String, String> participants = new Hashtable<String, String>();
	HashSet<Contract> contracts = new HashSet<Contract>();
	// TODO: need to be associated to a contract?
	HashSet<String> internalOperations = new HashSet<String>();

	public Automaton() {
	}
	
	public void printAll() {
		System.out.print("Init state:\n");
		System.out.print(this.getInitialState() + "\n\\n");

		printLoop("States", this.getStatesList());
		printLoop("Roles", this.getRolesList());
		printLoop("Participants", this.getParticipantsList());
		printLoop("Contracts", this.getContractsList());
		printLoop("operations", this.getOperations());
		printLoop("internal operations", this.getInternalOperations());
	}

	// INTERNAL OPERATIONS
	public void addInternalOperations(String internalLabel) {
		this.internalOperations.add(removeParenthesis(internalLabel));
	}

	public List<String> getInternalOperations() {
		return this.internalOperations.stream().collect(Collectors.toList());
	}

	// PARTICIPANTS
	public void addParticipant(String participant, String role) {
		this.participants.put(participant, role);
	}

	public List<String> getParticipantsList() {
		return this.participants.keySet().stream().collect(Collectors.toList());
	}

	// CONTRACTS
	public void addContract(String contractLabel) {
		this.contracts.add(new Contract(cleanStartOperation(contractLabel)));
	}

	public List<String> getContractsList() {
		return this.contracts.stream().map(x -> x.getIdContract()).collect(Collectors.toList());
	}

	// OPERATIONS
	public void addOperation(String label) {
		String[] elements = label.split("[.]");
		this.contracts.stream().filter(x -> x.getIdContract().equals(elements[0]))
				.forEach(x -> x.addOperation(elements[1]));
	}
	
	public List<String> getOperations() {
		return this.contracts.stream().map(Contract::getOperations).flatMap(Collection::stream).collect(Collectors.toList());
	}

	public List<String> getOperationsOfaContract(String idContract) {
		return this.contracts.stream().filter(x -> x.getIdContract().equals(idContract)).toList().get(0)
				.getOperations();
	}

	// INITIAL STATE
	public void setInitialState(String stateLabel) {
		this.initialState = removeParenthesis(stateLabel);
	}

	public String getInitialState() {
		return this.initialState;
	}

	// STATES
	public void addState(String stateLabel) {
		this.states.add(removeParenthesis(stateLabel));
	}

	public List<String> getStatesList() {
		return this.states.stream().collect(Collectors.toList());
	}

	// END STATES
	public void addEndStates(String endStateLabel) {
		this.endStates.add(endStateLabel);
	}

	public List<String> getEndStatesList() {
		return this.endStates.stream().collect(Collectors.toList());
	}

	// ROLES
	public void addRole(String roleLabel) {
		this.roles.add(roleLabel);
	}

	public List<String> getRolesList() {
		return this.roles.stream().collect(Collectors.toList());
	}

	// UTIL
	private void printLoop(String x, List<String> y) {
		System.out.print(x + ":\n");
		for (String i : y) {
			System.out.print(i + "\n");
		}
		System.out.print("\n");
	}

	// removes clutter - used in state
	private String removeParenthesis(String label) {
		return replaceTwoCharSequence(label, "(", ")");
	}

	// gets the contract id
	private String cleanStartOperation(String label) {
		return replaceTwoCharSequence(label, "starts(", ")");
	}

	private String replaceTwoCharSequence(String label, String first, String second) {
		label = label.replace(first, "");
		return label.replace(second, "");
	}

}
