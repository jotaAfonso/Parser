package data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import exceptions.CustomException;
import utils.CommonUtils;

import static utils.Constants.*;

public class Automaton {
	
	@JsonProperty(JSON_ID)
	private String id;
	
	@JsonProperty(JSON_INITIAL_STATE)
	private String initialS;
	@JsonProperty(JSON_STATES)
	private Set<String> states = new HashSet<String>();
	@JsonProperty(JSON_END_STATES)
	private Set<String> endS = new HashSet<String>();

	@JsonProperty(JSON_TRANSITIONS)
	private Set<Transition> transitions = new HashSet<Transition>();
	@JsonProperty(JSON_INTERNAL_TRANSITIONS)
	private Set<Transition> internalTransitions = new HashSet<Transition>();

	@JsonProperty(JSON_ROLES)
	private Set<String> roles = new HashSet<String>();
	@JsonProperty(JSON_ROLE_PARTICIPANTS)
	private List<AssociationRP> roleParticipant = new ArrayList<AssociationRP>();
	
	public Automaton(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInitialS() {
		return initialS;
	}

	public void setInitialS(String initialS) {
		this.initialS = initialS;
	}

	public Set<String> getStates() {
		return states;
	}

	public void setStates(Set<String> states) {
		this.states = states;
	}

	public Set<String> getEndS() {
		return endS;
	}

	public void setEndS(Set<String> endS) {
		this.endS = endS;
	}

	public Set<Transition> getTransitions() {
		return transitions;
	}

	public void setTransitions(Set<Transition> transitions) {
		this.transitions = transitions;
	}

	public Set<Transition> getInternalTransitions() {
		return internalTransitions;
	}

	public void setInternalTransitions(Set<Transition> internalTransitions) {
		this.internalTransitions = internalTransitions;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

	public List<AssociationRP> getRoleParticipant() {
		return roleParticipant;
	}

	public void setRoleParticipant(List<AssociationRP> roleParticipant) {
		this.roleParticipant = roleParticipant;
	}
	
	public void addTransition(Transition t) {
		if(t.getType().equals("."))
			this.getTransitions().add(t);
		else
			this.getInternalTransitions().add(t);
	}
	
	public void addBothStates(String initS, String endS, boolean isEnd) {
		if(initS.equals(PRE_INITIAL_STATE)) {
			this.setInitialS(endS);
		} else {
			this.getStates().add(initS);
		}
		
		this.getStates().add(endS);
		if(isEnd)
			this.getEndS().add(endS);
	}
	
	public void addRoleParticipant(String p) throws CustomException {
		System.out.print("part - " + p + "\n");
		String[] elements = p.split("\\|");
		for(String e : elements) {
			if(e.contains(COLON)) {
				String[] individual = e.split("\\:", 0);
				addRoles(individual[1]);
				addAssociations(individual[0], individual[1]);
			}
		}
	}
	
	private void addRoles(String role) {
		if(!this.getRoles().contains(role))
			this.getRoles().add(role);
	}
	
	private void addAssociations(String p, String r) throws CustomException {
		AssociationRP associationE = this.getRoleParticipant().stream().filter(x -> x.getRole().equals(r)).findAny().orElse(null);
		if (associationE != null) {	//	Already Exists
			if(associationE.getParticipants().contains(p)) {	//	Trying to register the same participant with the same role again
				String msg = CommonUtils.replaceInExceptionTwo(EXCEPTION_TRANS_PART_REGIST, p, r);
				throw new CustomException(msg);
			}
			else	//	Create with that role
				associationE.getParticipants().add(p);
		} else 
			this.getRoleParticipant().add(new AssociationRP(r, p));
	}
}
