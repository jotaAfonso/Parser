package ast;

import java.util.ArrayList;
import java.util.List;

import types.BoolType;
import types.IType;
import types.IntType;
import types.StringType;
import types.TypingException;

public class ASTNeq implements ASTNode {

	ASTNode left, right;

	public ASTNeq(ASTNode l, ASTNode r) {
		left = l;
		right = r;
	}

	@Override
	public String toString() {
		return left.toString() + " != " + right.toString();
	}

	@Override
	public IType typeCheck() throws TypingException {
		IType typeCheck = left.typeCheck();
		IType typeCheck2 = right.typeCheck();

		if (typeCheck == typeCheck2 && (typeCheck.equals(BoolType.singleton) || typeCheck.equals(IntType.singleton)
				|| typeCheck.equals(StringType.singleton)))
			return BoolType.singleton;
		else
			throw new TypingException();
	}
	
	@Override
	public boolean checkIfItHasVar() {
		return this.left.checkIfItHasVar() || this.right.checkIfItHasVar();
	}
	
	@Override
	public boolean checkIfItHasIds() {
		return this.left.checkIfItHasIds() || this.right.checkIfItHasIds();
	}

	public IType getType() {
		return BoolType.singleton;
	}
	
	@Override
	public List<ASTId> getVars() {
		List<ASTId> ll = this.left.getVars();
		List<ASTId> rl = this.right.getVars();
		List<ASTId> result = new ArrayList<ASTId>();

		result.addAll(ll);
		result.addAll(rl);
		
		return result;
	}
	
	@Override
	public List<ASTVar> getIds() {
		List<ASTVar> ll = this.left.getIds();
		List<ASTVar> rl = this.right.getIds();
		List<ASTVar> result = new ArrayList<ASTVar>();

		result.addAll(ll);
		result.addAll(rl);
		
		return result;
	}
}