package utils;

import java.util.Set;

public class Association {

	private String roleId;
	private Set<String> participants;

	public Association() {

	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public Set<String> getParticipants() {
		return participants;
	}

	public void setParticipants(Set<String> participants) {
		this.participants = participants;
	}

}
