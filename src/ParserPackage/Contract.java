package ParserPackage;

import java.util.*;
import java.util.stream.Collectors;

public class Contract {

	String id;
	HashSet<String> operations = new HashSet<String>();

	public Contract(String id) {
		this.id = id;
	}

	public String getIdContract() {
		return this.id;
	}
	
	public void addOperation(String operationLabel) {
		this.operations.add(operationLabel);
	}

	public List<String> getOperations() {
		return this.operations.stream().collect(Collectors.toList());
	}
}
