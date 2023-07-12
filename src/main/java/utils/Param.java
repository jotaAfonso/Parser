package utils;

import types.IType;

public class Param {
	
	String id;
	IType type;
	
	public Param(String id, IType type) {
		this.id = id;
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public IType getType() {
		return type;
	}

	public void setType(IType type) {
		this.type = type;
	}
	
	

}
