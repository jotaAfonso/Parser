package data;

import java.util.*;

import static utils.Constants.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import ast.ASTVar;
import exceptions.CustomException;
import types.IType;
import types.ParticipantType;
import utils.CommonUtils;
import utils.Param;

/**
 * The Class Transition.
 */
public class Transition {

	/** The id. */
	private String id;

	/** The type. */
	private String type;

	/** The from S. */
	@JsonProperty(JSON_FROM)
	private String fromS;

	/** The to S. */
	@JsonProperty(JSON_TO)
	private String toS;

	/** The action label. */
	@JsonProperty(JSON_LABEL)
	private String actionLabel;

	/** The new parts. */
	private Set<AssociationRP> newParts = new HashSet<AssociationRP>();
	
	/** The new parts. */
	private Map<String, Set<String>> caller = new HashMap<String, Set<String>>();

	/** The existent parts. */
	private AssociationRP existentParts;

	/** The input. */
	@JsonProperty(JSON_INPUT)
	private String input;

	/** The pre condition. */
	@JsonProperty(JSON_PRE_COND)
	private String preCondition;

	/** The post condition. */
	@JsonProperty(JSON_POST_COND)
	private String postCondition;

	/** The internal. */
	@JsonProperty(JSON_INTERNAL)
	private boolean internal;

	/** The external call. */
	@JsonProperty(JSON_EXTERNAL_CALL)
	private boolean externalCall;

	/** The parameters. */
	private List<Param> parameters = new ArrayList<Param>();

	/** The local vars. */
	private Map<String, IType> localVars = new HashMap<String, IType>();

	/**
	 * Instantiates a new transition.
	 *
	 * @param id    - id
	 * @param label - label
	 * @param type  - type
	 * @param param - parameters
	 */
	public Transition(String id, String label, String type, List<Param> param) {
		this.id = id;
		this.actionLabel = label;
		this.type = type;
		this.input = "";
		this.setParameters(param);
		if (param != null && !param.isEmpty()) {
			for (Param p : param) {
				localVars.put(p.getId(), p.getType());
			}
		}
		this.existentParts = new AssociationRP("");
		this.preCondition = "";
		this.postCondition = "";
	}

	/**
	 * Instantiates a new transition.
	 *
	 * @param type  - type
	 * @param f     - from
	 * @param t     - to
	 * @param l     - label
	 * @param nP    - new participants
	 * @param eP    - existent participants
	 * @param i     - input
	 * @param preC  - precondition
	 * @param postC - post condition
	 * @param e     - external call
	 */
	public Transition(String type, String f, String t, String l, Set<AssociationRP> nP, AssociationRP eP, String i,
			String preC, String postC, boolean e) {
		this.type = type;

		this.fromS = f;
		this.toS = t;
		this.actionLabel = l;

		this.newParts = nP;
		this.existentParts = eP;

		this.input = i;
		this.preCondition = preC;
		this.postCondition = postC;
		this.externalCall = e;
	}

	/**
	 * Gets the ID.
	 *
	 * @return the id
	 */
	@JsonIgnore
	public String getId() {
		return id;
	}

	/**
	 * Sets the ID.
	 *
	 * @param id - new ID
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	@JsonIgnore
	public String getType() {
		return type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type - new type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Gets the from State.
	 *
	 * @return the from State
	 */
	public String getFromS() {
		return fromS;
	}

	/**
	 * Sets the from State.
	 *
	 * @param fromS - new from State
	 */
	public void setFromS(String fromS) {
		this.fromS = fromS;
	}

	/**
	 * Gets the to State.
	 *
	 * @return the to State
	 */
	public String getToS() {
		return toS;
	}

	/**
	 * Sets the to State.
	 *
	 * @param toS - new to State
	 */
	public void setToS(String toS) {
		this.toS = toS;
	}

	/**
	 * Gets the action label.
	 *
	 * @return the action label
	 */
	public String getActionLabel() {
		return actionLabel;
	}

	/**
	 * Sets the action label.
	 *
	 * @param actionLabel - new action label
	 */
	public void setActionLabel(String actionLabel) {
		this.actionLabel = actionLabel;
	}

	/**
	 * Gets the new parts.
	 *
	 * @return the new participants
	 */
	public Set<AssociationRP> getNewParts() {
		return newParts;
	}

	/**
	 * Sets the new parts.
	 *
	 * @param newParts - new participants
	 */
	public void setNewParts(Set<AssociationRP> newParts) {
		this.newParts = newParts;
	}

	/**
	 * Gets the existent parts.
	 *
	 * @return the existent parts
	 */
	@JsonIgnore
	public AssociationRP getExistentParts() {
		return existentParts;
	}

	/**
	 * Sets the existent parts.
	 *
	 * @param existentParts - new existent parts
	 */
	public void setExistentParts(AssociationRP existentParts) {
		this.existentParts = existentParts;
	}

	/**
	 * Gets the input.
	 *
	 * @return the input
	 */
	public String getInput() {
		return input;
	}

	/**
	 * Sets the input.
	 *
	 * @param input - new input
	 */
	public void setInput(String input) {
		this.input = input;
	}

	/**
	 * Gets the precondition.
	 *
	 * @return - precondition
	 */
	public String getPreCondition() {
		return preCondition;
	}

	/**
	 * Sets the precondition.
	 *
	 * @param preCondition - new precondition
	 */
	public void setPreCondition(String preCondition) {
		this.preCondition = preCondition;
	}

	/**
	 * Gets the post condition.
	 *
	 * @return the post condition
	 */
	public String getPostCondition() {
		return postCondition;
	}

	/**
	 * Sets the post condition.
	 *
	 * @param postCondition - new post condition
	 */
	public void setPostCondition(String postCondition) {
		this.postCondition = postCondition;
	}

	/**
	 * Checks if is external call.
	 *
	 * @return true, if is external call
	 */
	public boolean isExternalCall() {
		return externalCall;
	}

	/**
	 * Sets the external call.
	 *
	 * @param externalCall - new external call
	 */
	public void setExternalCall(boolean externalCall) {
		this.externalCall = externalCall;
	}

	/**
	 * Checks if is internal.
	 *
	 * @return true, if is internal
	 */
	public boolean isInternal() {
		return internal;
	}

	/**
	 * Sets the internal.
	 *
	 * @param internal - new internal
	 */
	public void setInternal(boolean internal) {
		this.internal = internal;
	}

	/**
	 * Gets the parameters.
	 *
	 * @return the parameters
	 */
	@JsonIgnore
	public List<Param> getParameters() {
		return parameters;
	}

	/**
	 * Sets the parameters.
	 *
	 * @param parameters - new parameters
	 */
	public void setParameters(List<Param> parameters) {
		if (parameters != null) {
			this.parameters = parameters;
			String input = "";
			for (Param p : parameters) {
				String type = "";
				input = input.concat(", ");
				input = input.concat(p.getId()).concat(":");
				IType ltype = p.getType();
				if(ltype instanceof ParticipantType) {
					ParticipantType pType = (ParticipantType) ltype;
					type = pType.role;
				} else {
					type = ltype.getType();
				}
				input = input.concat(type);	
			}
			if (!input.isEmpty())
				input = input.substring(2);
			this.setInput(input);
		}
	}

	/**
	 * Gets the local variables.
	 *
	 * @return the local variables
	 */
	@JsonIgnore
	public Map<String, IType> getLocalVars() {
		return localVars;
	}

	/**
	 * Sets the local variables.
	 *
	 * @param localVars - local variables
	 */
	public void setLocalVars(Map<String, IType> localVars) {
		this.localVars = localVars;
	}

	/**
	 * Adds the participant.
	 *
	 * @param p the p
	 * @throws CustomException the custom exception
	 */
	public void addParticipant(String p) throws CustomException {
		String[] elements = p.split("\\|");
		for (String e : elements) {
			treatParticipant(e);
		}
	}

	/**
	 * Treats participant to add to either existent or new ones.
	 *
	 * @param p - participant
	 * @throws CustomException - custom exception, if it already exists a
	 *                         participant that is registered with the same role in
	 *                         the same transition it gives an exception.
	 */
	private void treatParticipant(String p) throws CustomException {
		if (p.contains(COLON)) {
			String[] elements = p.split("\\:", 0);
			AssociationRP associationE = this.getNewParts().stream().filter(x -> x.getRole().equals(elements[1]))
					.findAny().orElse(null);
			if (associationE != null) {
				if (associationE.getParticipants().contains(elements[0])) {
					String msg = CommonUtils.replaceMsgTwo(EXCEPTION_TRANS_PART_REGIST, elements[0], elements[1]);
					throw new CustomException(msg);
				} else
					associationE.getParticipants().add(elements[0]);
			} else {
				this.getNewParts().add(new AssociationRP(elements[1], elements[0]));
				Set<String> set = new HashSet<>(Arrays.asList(elements[1]));
				this.getCaller().put(elements[0], set);
			}
		} else {
			if (this.getExistentParts() == null) {
				this.getCaller().put(p, Collections.<String>emptySet());
				this.setExistentParts(new AssociationRP("", p));
			} else {
				this.getExistentParts().getParticipants().add(p);
				this.getCaller().put(p, Collections.<String>emptySet());
			}
		}
	}

	/**
	 * Adds the local variables.
	 *
	 * @param globalVars - global variables
	 * @param mapGlobal  - map global
	 * @throws CustomException - custom exception
	 */
	public void addLocalVars(List<ASTVar> globalVars, Map<String, IType> mapGlobal) throws CustomException {
		for (ASTVar v : globalVars)
			if (!this.getLocalVars().containsKey(v.getId()) && !mapGlobal.containsKey(v.getId()))
				this.getLocalVars().put(v.getId(), v.getType());
			else {
				String msg = CommonUtils.replaceMsgOne(EXCEPTION_VARIABLE_ALREADY_EXISTS_WITH_THIS_ID, v.getId());
				throw new CustomException(msg);
			}
	}

	public Map<String, Set<String>> getCaller() {
		return caller;
	}

	public void setCaller(Map<String, Set<String>> caller) {
		this.caller = caller;
	}
}
