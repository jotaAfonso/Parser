package ast;

import java.util.ArrayList;
import java.util.List;

import exceptions.TypingException;
import types.IType;
import types.IntType;

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
	public IType typeCheckPost() throws TypingException {
		return this.typeCheck();
	}

	@Override
	public IType typeCheckPre() throws TypingException {
		return this.typeCheck();
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
	public List<ASTVar> getVars() {
		return new ArrayList<ASTVar>();
	}
	
	@Override
	public List<ASTId> getIds() {
		return new ArrayList<ASTId>();
	}
}