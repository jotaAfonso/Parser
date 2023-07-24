package data;

import java.util.Set;
import java.util.HashSet;

/**
 * The Class AssociationRP.
 */
public class AssociationRP {

	/** The role. */
	private String role;
	
	/** The participants. */
	private Set<String> participants = new HashSet<String>();
	
	/**
	 * Instantiates a new association RP.
	 *
	 * @param role - role
	 */
	public AssociationRP(String role) {
		this.role = role;
	}
	
	/**
	 * Instantiates a new association RP.
	 *
	 * @param role - role
	 * @param part - participant
	 */
	public AssociationRP(String role, String part) {
		this.role = role;
		this.participants.add(part);
	}

	/**
	 * Gets the role.
	 *
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * Sets the role.
	 *
	 * @param role - new role
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * Gets the participants.
	 *
	 * @return the participants
	 */
	public Set<String> getParticipants() {
		return participants;
	}

	/**
	 * Sets the participants.
	 *
	 * @param participants - new participants
	 */
	public void setParticipants(Set<String> participants) {
		this.participants = participants;
	}
}
