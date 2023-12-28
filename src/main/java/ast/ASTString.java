package ast;

import java.util.ArrayList;
import java.util.List;

import exceptions.TypingException;
import types.IType;
import types.StringType;

public class ASTString implements ASTNode {

	String val;

	public ASTString(String n) {
		val = n;
	}

	@Override
	public String toString() {
		return val;
	}
	
	@Override
	public IType typeCheckPost() throws TypingException {
		return this.typeCheck();
	}

	@Override
	public IType typeCheckPre() throws TypingException {
		return this.typeCheck();
	}

	@Override
	public IType typeCheck() throws TypingException {
		return StringType.singleton;
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