package ast;

import java.util.ArrayList;
import java.util.List;

import types.IType;
import types.IntType;
import types.TypingException;

public class ASTNum implements ASTNode {

	int val;

	public ASTNum(int n) {
		val = n;
	}

	@Override
	public String toString() {
		return String.valueOf(val);
	}

	@Override
	public IType typeCheck() throws TypingException {
		return IntType.singleton;
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