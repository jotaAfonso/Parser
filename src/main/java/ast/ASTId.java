package ast;

import java.util.ArrayList;
import java.util.List;

import exceptions.TypingException;
import types.IType;

public class ASTId implements ASTNode {

	String id;
	IType type;
	
	public ASTId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return this.id;
	}
	
	public void setType(IType type) {
		this.type = type;
	}

	@Override
	public IType typeCheck() throws TypingException {
		return type;
	}
	
	@Override
	public String toString() {
		return id;
	}
	
	@Override
	public boolean checkIfItHasVar() {
		return false;
	}

	@Override
	public List<ASTVar> getVars() {
		return new ArrayList<ASTVar>();
	}
	
	@Override
	public List<ASTId> getIds() {
		List<ASTId> result = new ArrayList<ASTId>();
		result.add(this);		
		return result;
	}
}