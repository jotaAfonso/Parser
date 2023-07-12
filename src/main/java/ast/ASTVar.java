package ast;

import java.util.ArrayList;
import java.util.List;

import types.IType;
import types.TypingException;

public class ASTVar implements ASTNode {

	String id;
	IType type;
	
	public ASTVar(String id) {
		this.id = id;
	}
	
	public String getId() {
		return this.id;
	}
	
	public IType getType() {
		return this.type;
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
	public boolean checkIfItHasIds() {
		return true;
	}

	@Override
	public List<ASTId> getVars() {
		List<ASTId> result = new ArrayList<ASTId>();
		return result;
	}
	
	@Override
	public List<ASTVar> getIds() {
		List<ASTVar> result = new ArrayList<ASTVar>();
		result.add(this);
		return result;
	}
}