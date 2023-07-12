package data;

import java.util.*;

import static utils.Constants.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import ast.ASTId;
import exceptions.CustomException;
import types.IType;
import utils.CommonUtils;
import utils.Param;

public class Transition {

	private String id;
	private String type;

	@JsonProperty(JSON_FROM)
	private String fromS;
	@JsonProperty(JSON_TO)
	private String toS;

	@JsonProperty(JSON_LABEL)
	private String actionLabel;
	@JsonProperty(JSON_NEW_PART)
	private Set<AssociationRP> newParts = new HashSet<AssociationRP>();
	@JsonProperty(JSON_EXISTANT_PART)
	private Set<AssociationRP> existantParts = new HashSet<AssociationRP>();

	@JsonProperty(JSON_INPUT)
	private String input;

	@JsonProperty(JSON_PRE_COND)
	private String preCondition;
	@JsonProperty(JSON_POST_COND)
	private String postCondition;

	@JsonProperty(JSON_EXTERNAL_CALL)
	private boolean externalCall;

	private List<Param> parameters = new ArrayList<Param>();
	
	private Map<String,IType> localVars = new HashMap<String,IType>();

	public Transition(String id, String label, String type, List<Param> param) {
		this.id = id;
		this.actionLabel = label;
		this.type = type;
		this.setParameters(param);
		if(param != null && !param.isEmpty()) {
			for(Param p : param) {
				localVars.put(p.getId(), p.getType());
			}
		}

		this.preCondition = "";
		this.postCondition = "";
	}

	public Transition(String type, String f, String t, String l, Set<AssociationRP> nP, Set<AssociationRP> eP, String i,
			String preC, String postC, boolean e) {
		this.type = type;

		this.fromS = f;
		this.toS = t;
		this.actionLabel = l;

		this.newParts = nP;
		this.existantParts = eP;

		this.input = i;
		this.preCondition = preC;
		this.postCondition = postC;
		this.externalCall = e;
	}

	@JsonIgnore
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@JsonIgnore
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFromS() {
		return fromS;
	}

	public void setFromS(String fromS) {
		this.fromS = fromS;
	}

	public String getToS() {
		return toS;
	}

	public void setToS(String toS) {
		this.toS = toS;
	}

	public String getActionLabel() {
		return actionLabel;
	}

	public void setActionLabel(String actionLabel) {
		this.actionLabel = actionLabel;
	}

	public Set<AssociationRP> getNewParts() {
		return newParts;
	}

	public void setNewParts(Set<AssociationRP> newParts) {
		this.newParts = newParts;
	}

	public Set<AssociationRP> getExistantParts() {
		return existantParts;
	}

	public void setExistantParts(Set<AssociationRP> existantParts) {
		this.existantParts = existantParts;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String getPreCondition() {
		return preCondition;
	}

	public void setPreCondition(String preCondition) {
		this.preCondition = preCondition;
	}

	public String getPostCondition() {
		return postCondition;
	}

	public void setPostCondition(String postCondition) {
		this.postCondition = postCondition;
	}

	public boolean isExternalCall() {
		return externalCall;
	}

	public void setExternalCall(boolean externalCall) {
		this.externalCall = externalCall;
	}

	public void addParticipant(String p) throws CustomException {
		String[] elements = p.split("\\|");
		for (String e : elements) {
			addParticipantEither(e);
		}
	}

	private void addParticipantEither(String p) throws CustomException {
		if (p.contains(COLON)) {
			String[] elements = p.split("\\:", 0);
			AssociationRP associationE = this.getNewParts().stream().filter(x -> x.getRole().equals(elements[1]))
					.findAny().orElse(null);
			if (associationE != null) { // Already Exists
				if (associationE.getParticipants().contains(elements[0])) { // Trying to register the same participant
																			// with the same role again
					String msg = CommonUtils.replaceInExceptionTwo(EXCEPTION_TRANS_PART_REGIST, elements[0],
							elements[1]);
					throw new CustomException(msg);
				} else // Create with that role
					associationE.getParticipants().add(elements[0]);
			} else
				this.getNewParts().add(new AssociationRP(elements[1], elements[0]));
		} else { // Existent Participants
			if (this.getExistantParts().isEmpty()) // Initialize it
				this.getExistantParts().add(new AssociationRP("", p));
			else
				this.getExistantParts().stream().findFirst().get().getParticipants().add(p);
		}
	}

	@JsonIgnore
	public List<Param> getParameters() {
		return parameters;
	}

	public void setParameters(List<Param> parameters) {
		if (parameters != null) {
			this.parameters = parameters;
			String input = "";
			for (Param p : parameters) {
				input = input.concat(", ");
				input = input.concat(p.getId()).concat(":").concat(p.getType().getType());
			}
			if (!input.isEmpty())
				input = input.substring(2);
			this.setInput(input);
		}
	}

	public Map<String,IType> getLocalVars() {
		return localVars;
	}

	public void setLocalVars(Map<String,IType> localVars) {
		this.localVars = localVars;
	}
	
	public void addLocalVars(List<ASTId> globalVars, Map<String, IType> mapGlobal) throws CustomException {
		for(ASTId v : globalVars)
			if(!this.getLocalVars().containsKey(v.getId()) && !mapGlobal.containsKey(v.getId()))
				this.getLocalVars().put(v.getId(), v.getType());
			else {
				String msg = CommonUtils.replaceInExceptionOne(EXCEPTION_VARIABLE_ALREADY_EXISTS_WITH_THIS_ID, v.getId());
				throw new CustomException(msg);
			}
	}
}
