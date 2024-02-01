package ast;

import java.util.ArrayList;
import java.util.List;

import exceptions.TypingException;
import types.BoolType;
import types.IType;

public class ASTBool implements ASTNode {

	String val;

	public ASTBool(boolean n) {
		String temp = String.valueOf(n);
		val = temp.substring(0, 1).toUpperCase() + temp.substring(1);
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

	@Override
	public IType typeCheckPost() throws TypingException {
		return this.typeCheck();
	}

	@Override
	public IType typeCheckPre() throws TypingException {
		return this.typeCheck();
	}
}