package ast;

import java.util.ArrayList;
import java.util.List;

import exceptions.TypingException;
import types.BoolType;
import types.IType;

public class ASTWhile implements ASTNode {

	ASTNode cond, expr;
	IType type;

	public ASTWhile(ASTNode c, ASTNode e) {
		cond = c;
		expr = e;
		type = null;
	}

	@Override
	public IType typeCheck() throws TypingException {
		cond.typeCheck();
		expr.typeCheck();

		return type = BoolType.singleton;
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