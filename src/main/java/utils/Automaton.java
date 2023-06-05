package utils;

import java.util.*;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "id", "initialState", "states", "endStates", "operations",
	"internalOperations", "roles", "registeredParticipants" })
@JsonIgnoreProperties(value = { "participants" })
public class Automaton {

	@JsonProperty("id")
	private String id;

	@JsonProperty("initialState")
	private String initialState;
	@JsonProperty("states")
	private Set<String> states = new HashSet<String>();
	@JsonProperty("endStates")
	private Set<String> endStates = new HashSet<String>();

	@JsonProperty("operations")
	private Set<Transition> operations = new HashSet<Transition>();
	@JsonProperty("internalOperations")
	private Set<Transition> internalOperations = new HashSet<Transition>();

	@JsonProperty("roles")
	private Set<String> roles = new HashSet<String>();
	// TODO: change so that a participant can have more roles
	private Set<String> participants = new HashSet<String>();
	@JsonProperty("registeredParticipants")
	private Hashtable<String, String> registeredParticipants = new Hashtable<String, String>();

	public Automaton(String id) {
		this.id = id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/** returns the identification of the contract */
	public String getId() {
		return this.id;
	}

	public void setInitialState(String initialS) {
		this.initialState = UtilsParser.removeParenthesisFromString(initialS);
	}

	/** returns the initial state of the contract */
	public String getInitialState() {
		return this.initialState;
	}

	/** adds a state to the contract */
	public void addState(String stateLabel) {
		this.states.add(UtilsParser.removeParenthesisFromString(stateLabel));
	}

	@JsonIgnore
	/** returns the list of states of the contract */
	public Set<String> getStatesSet() {
		return this.states;
	}

	/** adds an end state to the contract */
	public void addEndState(String endStateLabel) {
		this.endStates.add(UtilsParser.removeParenthesisFromString(endStateLabel));
	}

	/** adds a list of end states to the contract */
	public void addEndStates(List<String> endStates) {
		this.endStates.addAll(
				endStates.stream().map(x -> UtilsParser.removeParenthesisFromString(x)).collect(Collectors.toList()));
	}

	@JsonIgnore
	/** returns the list of end states of the contract */
	public Set<String> getEndStatesSet() {
		return this.endStates;
	}

	/** adds an operation to the contract */
	public void addOperation(Transition operationLabel) {
		this.operations.add(operationLabel);
	}

	@JsonIgnore
	/** returns the list of operations of the contract */
	public Set<Transition> getOperationsSet() {
		return this.operations;
	}

	/** adds an internal operation to the contract */
	public void addInternalOperation(Transition internalLabel) {
		this.internalOperations.add(internalLabel);
	}

	@JsonIgnore
	/** returns the set of internal operations of the contract */
	public Set<Transition> getInternalOperationsSet() {
		return this.internalOperations;
	}

	// TODO: put it private and call it when new participants is called
	/** registers a role in the contract */
	public void addRole(String roleLabel) {
		this.roles.add(roleLabel);
	}

	@JsonIgnore
	/** returns the set of roles registered in the contract */
	public Set<String> getRolesSet() {
		return this.roles;
	}

	/** adds a participant, used for checking if a participant is registered */
	public void addParticipant(String participant) {
		this.participants.add(participant);
	}

	@JsonIgnore
	/** returns the set of participants involved the contract */
	public Set<String> getParticipantsSet() {
		return this.participants;
	}

	/** registers a participant in the contract */
	public void addRegisteredParticipant(String participant, String role) {
		this.registeredParticipants.put(participant, role);
	}

	public void addParticipantChoice(String part) {
		String partLocal = UtilsParser.removeParenthesisFromString(part);
		String[] elements = partLocal.split("[|]");
		if (elements.length == 2) {
			// TODO: method to avoid duplication
			if (elements[0].contains(":")) {
				String[] newPart = elements[0].split(":");
				this.addRegisteredParticipant(newPart[0], newPart[1]);
				this.addParticipant(elements[1]);
				this.addParticipant(newPart[0]);
				this.addRole(newPart[1]);
			} else {
				String[] newPart = elements[1].split(":");
				this.addRegisteredParticipant(newPart[0], newPart[1]);
				this.addParticipant(elements[0]);
				this.addParticipant(newPart[0]);
				this.addRole(newPart[1]);
			}
		} else {
			if (elements[0].contains(":")) {
				String[] newPart = elements[0].split("[:]");
				this.addRegisteredParticipant(newPart[0], newPart[1]);
				this.addParticipant(newPart[0]);
				this.addRole(newPart[1]);
			} else {
				this.addParticipant(elements[0]);
			}
		}
	}

	@JsonIgnore
	/** returns the set of participants registered in the contract */
	public Set<String> getRegisteredParticipantsSet() {
		return this.registeredParticipants.keySet();
	}
}