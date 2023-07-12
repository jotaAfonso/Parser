package ast;

import java.util.List;

import types.BoolType;
import types.IType;
import types.TypingException;

// imply
public class ASTIf implements ASTNode {
	
	ASTNode e1, e2, e3;
	IType type;

	public ASTIf(ASTNode e1, ASTNode e2, ASTNode e3){
		this.e1 = e1;
		this.e2 = e2;
		this.e3 = e3;
	}
	

	@Override
	public IType typeCheck()
			throws TypingException {
		
		if(e1.typeCheck() != BoolType.singleton)
			throw new TypingException();
		
		type = e2.typeCheck();

		if(type == e3.typeCheck())
			return type;
		else
			throw new TypingException();
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

		return null;
	}
	
	@Override
	public List<ASTVar> getIds() {
		return null;
	}
}