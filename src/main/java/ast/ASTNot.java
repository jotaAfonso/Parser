package ast;

import java.util.List;

import exceptions.TypingException;
import types.BoolType;
import types.IType;

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
	public List<ASTVar> getVars() {
		return this.node.getVars();
	}
	
	@Override
	public List<ASTId> getIds() {
		return this.node.getIds();
	}
}