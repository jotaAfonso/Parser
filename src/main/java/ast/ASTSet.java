package ast;

import java.util.ArrayList;
import java.util.List;

import exceptions.TypingException;
import types.IType;
import types.SetType;

public class ASTSet implements ASTNode {

	public ASTSet() {
	}

	@Override
	public String toString() {
		return "[]";
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
		return SetType.singleton;
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