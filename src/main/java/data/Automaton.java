package data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import ast.ASTVar;
import exceptions.CustomException;
import types.IType;
import utils.CommonUtils;

import static utils.Constants.*;

/**
 * The Class Automaton.
 */
public class Automaton {

	/** The id of the contract. */
	@JsonProperty(JSON_ID)
	private String id;

	/** The initial State. */
	@JsonProperty(JSON_INITIAL_STATE)
	private String initialS;

	/** All of the states. */
	@JsonProperty(JSON_STATES)
	private Set<String> states = new HashSet<String>();

	/** The end State. */
	@JsonProperty(JSON_END_STATES)
	private Set<String> endS = new HashSet<String>();

	/** The transitions. */
	@JsonProperty(JSON_TRANSITIONS)
	private Set<Transition> transitions = new HashSet<Transition>();

	/** The roles. */
	@JsonProperty(JSON_ROLES)
	private Set<String> roles = new HashSet<String>();

	/** The role participant, association of one role to multiple participants. */
	@JsonProperty(JSON_ROLE_PARTICIPANTS)
	private List<AssociationRP> roleParticipant = new ArrayList<AssociationRP>();

	/** The global variables. */
	private Map<String, IType> globalVars = new HashMap<String, IType>();

	/**
	 * Instantiates a new automaton.
	 *
	 * @param id - id
	 */
	public Automaton(String id) {
		this.id = id;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id - new id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets the initial State.
	 *
	 * @return the initial State
	 */
	public String getInitialS() {
		return initialS;
	}

	/**
	 * Sets the initial State.
	 *
	 * @param initialS - new initial State
	 */
	public void setInitialS(String initialS) {
		this.initialS = initialS;
	}

	/**
	 * Gets the states.
	 *
	 * @return the states
	 */
	public Set<String> getStates() {
		return states;
	}

	/**
	 * Sets the states.
	 *
	 * @param states - new states
	 */
	public void setStates(Set<String> states) {
		this.states = states;
	}

	/**
	 * Gets the end States.
	 *
	 * @return the end States
	 */
	public Set<String> getEndS() {
		return endS;
	}

	/**
	 * Sets the end State.
	 *
	 * @param endS - new end States
	 */
	public void setEndS(Set<String> endS) {
		this.endS = endS;
	}

	/**
	 * Gets the transitions.
	 *
	 * @return the transitions
	 */
	public Set<Transition> getTransitions() {
		return transitions;
	}

	/**
	 * Sets the transitions.
	 *
	 * @param transitions - new transitions
	 */
	public void setTransitions(Set<Transition> transitions) {
		this.transitions = transitions;
	}

	/**
	 * Gets the roles.
	 *
	 * @return the roles
	 */
	public Set<String> getRoles() {
		return roles;
	}

	/**
	 * Sets the roles.
	 *
	 * @param roles - new roles
	 */
	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

	/**
	 * Gets the role participant.
	 *
	 * @return the role participant
	 */
	public List<AssociationRP> getRoleParticipant() {
		return roleParticipant;
	}

	/**
	 * Sets the role participant.
	 *
	 * @param roleParticipant - new role participant
	 */
	public void setRoleParticipant(List<AssociationRP> roleParticipant) {
		this.roleParticipant = roleParticipant;
	}
	
	/**
	 * Gets the global variables.
	 *
	 * @return the global variables
	 */
	@JsonIgnore
	public Map<String, IType> getGlobalVars() {
		return globalVars;
	}

	/**
	 * Sets the global variables.
	 *
	 * @param globalVars - global variables
	 */
	public void setGlobalVars(Map<String, IType> globalVars) {
		this.globalVars = globalVars;
	}

	/**
	 * Adds the transition.
	 *
	 * @param t - transition to add
	 */
	public void addTransition(Transition t) {
		if (t.getType().equals("-"))
			t.setInternal(true);
		this.getTransitions().add(t);
	}

	/**
	 * Adds the both states of a transition.
	 *
	 * @param fromS - initial State of the transition
	 * @param toS   - end State of a transition
	 * @param isEnd - flag to know if to State belongs to the end States
	 */
	public void addBothStates(String fromS, String toS, boolean isEnd) {
		if (fromS.equals(PRE_INITIAL_STATE)) {
			this.setInitialS(toS);
		} else {
			this.getStates().add(fromS);
		}

		this.getStates().add(toS);
		if (isEnd)
			this.getEndS().add(toS);
	}

	/**
	 * Adds the associations between role and participant. The participant parameter
	 * can contain multiple participants, hence the split by [|]. Since this method
	 * only manages general associations, we only need to treat new participants.
	 *
	 * @param p        - participant
	 * @param isDeploy - flag to know if the method is called in the deploy action
	 * @throws CustomException - custom exception, the deploy action only accepts
	 *                         new Participants
	 */
	public void addRoleParticipant(String p, boolean isDeploy) throws CustomException {
		String[] elements = p.split("\\|");
		for (String e : elements) {
			if (e.contains(COLON)) {
				String[] individual = e.split("\\:", 0);
				addRoles(individual[1]);
				addAssociations(individual[0], individual[1]);
			} else if (!e.contains(COLON) && isDeploy) {
				throw new CustomException(EXCEPTION_START_PARTICIPANT);
			}
		}
	}

	/**
	 * Adds the role to the set of roles.
	 *
	 * @param role - role
	 */
	private void addRoles(String role) {
		if (!this.getRoles().contains(role))
			this.getRoles().add(role);
	}

	/**
	 * Complementary method to addRoleParticipant to add associations (assoc)
	 * between role and participants. Takes the respective role and participant and
	 * sees if there is an assoc entry. Assuming that it is there, it checks if the
	 * participants set already has the participant; if it has it throws an
	 * exception, if not it adds it to the set. If there is no assoc entry then it
	 * creates one with the role and participant.
	 *
	 * @param r - role
	 * @param p - participant
	 * @throws CustomException - custom exception, tries to register a participant
	 *                         that is already registered
	 */
	public void addAssociations(String p, String r) throws CustomException {
		AssociationRP associationE = this.getRoleParticipant().stream().filter(x -> x.getRole().equals(r)).findAny()
				.orElse(null);
		if (associationE != null) {
			if (associationE.getParticipants().contains(p))
				throw new CustomException(CommonUtils.replaceMsgTwo(EXCEPTION_TRANS_PART_REGIST, p, r));
			else
				associationE.getParticipants().add(p);
		} else
			this.getRoleParticipant().add(new AssociationRP(r, p));
	}

	/**
	 * Adds the global variables. Checks if the variable's IDs are unique among them
	 * and the parameters.
	 *
	 * @param globalVars - global variables
	 * @param localVars  - local variables
	 * @throws CustomException - custom exception, variables have unique IDS between
	 *                         each other and also between the parameters IDS
	 */
	public void addGlobalVars(List<ASTVar> globalVars, Map<String, IType> localVars) throws CustomException {
		for (ASTVar v : globalVars)
			if (!this.getGlobalVars().containsKey(v.getId()) && !localVars.containsKey(v.getId()))
				this.getGlobalVars().put(v.getId(), v.getType());
			else {
				String msg = CommonUtils.replaceMsgOne(EXCEPTION_VARIABLE_ALREADY_EXISTS_WITH_THIS_ID,
						v.getId());
				throw new CustomException(msg);
			}
	}
}
