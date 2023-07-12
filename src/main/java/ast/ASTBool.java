package ast;

import java.util.ArrayList;
import java.util.List;

import types.BoolType;
import types.IType;
import types.TypingException;

public class ASTBool implements ASTNode {

	String val;

	public ASTBool(boolean n) {
		val = String.valueOf(n);
	}

	@Override
	public String toString() {
		return val;
	}

	@Override
	public IType typeCheck() throws TypingException {
		return BoolType.singleton;
	}
	
	@Override
	public boolean checkIfItHasVar() {
		return false;
	}
	
	@Override
	public boolean checkIfItHasIds() {
		return false;
	}
	
	@Override
	public List<ASTId> getVars() {
		List<ASTId> result = new ArrayList<ASTId>();
		return result;
	}
	
	@Override
	public List<ASTVar> getIds() {
		List<ASTVar> result = new ArrayList<ASTVar>();
		return result;
	}
}
