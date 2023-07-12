package ast;

import java.util.ArrayList;
import java.util.List;

import types.IType;
import types.TypingException;

public class ASTSeq implements ASTNode {

	ASTNode left, right;
	IType type;

	public ASTSeq(ASTNode l, ASTNode r) {
		left = l;
		right = r;
		type = null;
	}

	@Override
	public String toString() {
		return left.toString() + " ; " + right.toString();
	}

	@Override
	public IType typeCheck() throws TypingException {
		left.typeCheck();
		System.out.println(left.typeCheck());
		System.out.println(right.typeCheck());
		System.out.println();
		return type = right.typeCheck();
	}

	@Override
	public boolean checkIfItHasVar() {
		return this.left.checkIfItHasVar() || this.right.checkIfItHasVar();
	}
	
	@Override
	public boolean checkIfItHasIds() {
		return this.left.checkIfItHasIds() || this.right.checkIfItHasIds();
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