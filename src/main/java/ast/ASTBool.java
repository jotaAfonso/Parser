package ast;

import java.util.ArrayList;
import java.util.List;

import exceptions.TypingException;
import types.BoolType;
import types.IType;

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
	public List<ASTVar> getVars() {
		return new ArrayList<ASTVar>();
	}
	
	@Override
	public List<ASTId> getIds() {
		return new ArrayList<ASTId>();		
	}
}
