package ast;

import java.util.ArrayList;
import java.util.List;

import types.BoolType;
import types.IType;
import types.TypingException;

public class ASTNot implements ASTNode {

	ASTNode node;

	public ASTNot(ASTNode n) {
		node = n;
	}

	@Override
	public String toString() {
		return "!" + node.toString();
	}

	@Override
	public IType typeCheck() throws TypingException {
		IType typeCheck = node.typeCheck();

		if (typeCheck == BoolType.singleton)
			return typeCheck;
		else
			throw new TypingException();
	}

	@Override
	public boolean checkIfItHasVar() {
		return this.node.checkIfItHasVar();
	}
	
	@Override
	public boolean checkIfItHasIds() {
		return this.node.checkIfItHasIds();
	}
	
	@Override
	public List<ASTId> getVars() {
		return this.node.getVars();
	}
	
	@Override
	public List<ASTVar> getIds() {
		List<ASTVar> result = new ArrayList<ASTVar>();	
		return result;
	}
}