package data;

import java.util.Set;
import java.util.HashSet;
import static utils.Constants.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import exceptions.CustomException;
import utils.CommonUtils;

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

	public Transition(String id, String label, String type) {
		this.id = id;
		this.actionLabel = label;
		this.type = type;
		
		this.input = "";
		this.preCondition = "";
		this.postCondition = "";
	}

	public Transition(String type, String f, String t, String l, Set<AssociationRP> nP, Set<AssociationRP> eP,
			String i, String preC,String postC, boolean e) {
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
		for(String e : elements) {
			addParticipantEither(e);
		}
	}
	
	private void addParticipantEither(String p) throws CustomException {
		if(p.contains(COLON)) {
			String[] elements = p.split("\\:", 0);
			AssociationRP associationE = this.getNewParts().stream().filter(x -> x.getRole().equals(elements[1])).findAny().orElse(null);
			if (associationE != null) {	//	Already Exists
				if(associationE.getParticipants().contains(elements[0])) {	//	Trying to register the same participant with the same role again
					String msg = CommonUtils.replaceInExceptionTwo(EXCEPTION_TRANS_PART_REGIST, elements[0], elements[1]);
					throw new CustomException(msg);
				}
				else	//	Create with that role
					associationE.getParticipants().add(elements[0]);
			} else 
				this.getNewParts().add(new AssociationRP(elements[1], elements[0]));
		} else {	//	Existent Participants
			if(this.getExistantParts().isEmpty())	//	Initialize it
				this.getExistantParts().add(new AssociationRP("", p));
			else
				this.getExistantParts().stream().findFirst().get().getParticipants().add(p);
		}
	}
}
