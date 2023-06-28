package utils;

public class Transition {

	private String fromState;
	private String toState;

	private String operation;
	private String participant;

	private boolean newParticipant;
	private boolean bothParticipant;

	public Transition(String from, String to, String op, String part) {
		this.fromState = UtilsParser.removeParenthesisFromString(from);
		this.toState = UtilsParser.removeParenthesisFromString(to);
		this.operation = op;
		setupPart(part);
	}

	private void setupPart(String part) {
		String partLocal = UtilsParser.removeParenthesisFromString(part);
		String[] elements = partLocal.split("[|]");

		// both old and new
		if (elements.length == 2) {
			if (elements[0].contains(":"))
				removeRoleFromPart(elements[0]);
			else if (elements[1].contains(":"))
				removeRoleFromPart(elements[1]);
			this.newParticipant = true;
			this.bothParticipant = true;
		} else {
			// new
			if (elements[0].contains(":")) {
				removeRoleFromPart(elements[0]);
				this.newParticipant = true;
			} else
				this.setParticipant(part);
		}
	}

	private void removeRoleFromPart(String partRole) {
		String part = partRole.split("[:]")[0];
		this.setParticipant(part);
	}

	public void setFromState(String state) {
		this.fromState = state;
	}

	public String getFromState() {
		return this.fromState;
	}

	public void setToState(String state) {
		this.toState = state;
	}

	public String getToState() {
		return this.toState;
	}

	public void setOperation(String op) {
		this.operation = op;
	}

	public String getOperation() {
		return this.operation;
	}

	public void setParticipant(String part) {
		this.participant = part;
	}

	public String getParticipant() {
		return this.participant;
	}

	public boolean isNewParticipant() {
		return newParticipant;
	}

	public void setNewParticipant(boolean newParticipant) {
		this.newParticipant = newParticipant;
	}

	public boolean isBothParticipant() {
		return bothParticipant;
	}

	public void setBothParticipant(boolean bothParticipant) {
		this.bothParticipant = bothParticipant;
	}
}
