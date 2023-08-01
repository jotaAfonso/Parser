package ast;

import java.util.ArrayList;
import java.util.List;

import exceptions.TypingException;
import types.BoolType;
import types.IType;

public class ASTForAll implements ASTNode {

	ASTNode left, right;
	IType type;
	
	public ASTForAll(ASTNode e1, ASTNode e2) {
		left = e1;
		right = e2;
	}

	@Override
	public String toString() {
		return left.toString() + " ∀ " + right.toString();
	}

	@Override
	public IType typeCheck() throws TypingException {
		//IType typeCheck = left.typeCheck();
		//IType typeCheck2 = right.typeCheck();

		return type = BoolType.singleton;
	}

	@Override
	public boolean checkIfItHasVar() {
		return this.left.checkIfItHasVar() || this.right.checkIfItHasVar();
	}	
	
	@Override
	public List<ASTVar> getVars() {
		List<ASTVar> ll = this.left.getVars();
		List<ASTVar> rl = this.right.getVars();
		List<ASTVar> result = new ArrayList<ASTVar>();

		result.addAll(ll);
		result.addAll(rl);
		
		return result;
	}
	
	@Override
	public List<ASTId> getIds() {
		List<ASTId> ll = this.left.getIds();
		List<ASTId> rl = this.right.getIds();
		List<ASTId> result = new ArrayList<ASTId>();

		result.addAll(ll);
		result.addAll(rl);
		
		return result;
	}

}
