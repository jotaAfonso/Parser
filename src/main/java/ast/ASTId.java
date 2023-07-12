package ast;

import java.util.List;
import java.util.ArrayList;

import types.IType;
import types.TypingException;

public class ASTId implements ASTNode {

	String id;
	IType type;

	public ASTId(String id, IType type) {
		this.id = id;
		this.type = type;
	}
	
	public ASTId(String id) {
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
		return "var " + id;
	}
	
	@Override
	public boolean checkIfItHasVar() {
		return true;
	}
	
	@Override
	public boolean checkIfItHasIds() {
		return false;
	}

	@Override
	public List<ASTId> getVars() {
		List<ASTId> result = new ArrayList<ASTId>();
		result.add(this);
		return result;
	}
	
	@Override
	public List<ASTVar> getIds() {
		List<ASTVar> result = new ArrayList<ASTVar>();		
		return result;
	}
}
