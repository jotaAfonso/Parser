package data;

import java.util.Set;
import java.util.HashSet;

public class AssociationRP {

	private String role;
	private Set<String> participants = new HashSet<String>();
	
	public AssociationRP(String role, String part) {
		this.role = role;
		this.participants.add(part);
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Set<String> getParticipants() {
		return participants;
	}

	public void setParticipants(Set<String> participants) {
		this.participants = participants;
	}
}
