package ast;

import java.util.ArrayList;
import java.util.List;

import types.BoolType;
import types.IType;
import types.TypingException;

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