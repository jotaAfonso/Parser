package ast;

import java.util.ArrayList;
import java.util.List;

import exceptions.TypingException;
import types.BoolType;
import types.IType;
import types.IntType;

public class ASTLt implements ASTNode {

	ASTNode left, right;

	public ASTLt(ASTNode l, ASTNode r) {
		left = l;
		right = r;
	}

	@Override
	public String toString() {
		return left.toString() + " < " + right.toString();
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
		IType typeCheck = left.typeCheck();
		IType typeCheck2 = right.typeCheck();

		if (typeCheck == typeCheck2 && typeCheck == IntType.singleton)
			return BoolType.singleton;
		else
			throw new TypingException();
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