package ast;

import java.util.List;

import exceptions.TypingException;

import java.util.ArrayList;

import types.IType;

public class ASTVar implements ASTNode {

	String id;
	IType type;

	public ASTVar(String id, IType type) {
		this.id = id;
		this.type = type;
	}
	
	public ASTVar(String id) {
		this.id = id;
	}
	
	public String getId() {
		return this.id;
	}
	
	public IType getType() {
		return this.type;
	}

	@Override
	public IType typeCheck() throws TypingException {
		return type;
	}
	
	@Override
	public String toString() {
		return "var " + type.getType() + " " + id;
	}
	
	@Override
	public boolean checkIfItHasVar() {
		return true;
	}

	@Override
	public List<ASTVar> getVars() {
		List<ASTVar> result = new ArrayList<ASTVar>();
		result.add(this);
		return result;
	}
	
	@Override
	public List<ASTId> getIds() {
		return new ArrayList<ASTId>();		
	}
}
