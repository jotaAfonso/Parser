package ast;

import java.util.ArrayList;
import java.util.List;

import exceptions.TypingException;
import types.AssignType;
import types.BoolType;
import types.IType;

public class ASTAnd implements ASTNode {

	ASTNode left, right;

	public ASTAnd(ASTNode l, ASTNode r) {
		left = l;
		right = r;
	}

	@Override
	public String toString() {
		return "AND(" + left.toString() + ", " + right.toString() + ")";
	}
	
	@Override
	public IType typeCheck() throws TypingException {
		IType typeCheck = left.typeCheck();
		IType typeCheck2 = right.typeCheck();
		
		if(typeCheck == typeCheck2 && typeCheck.equals(BoolType.singleton))
			return typeCheck;
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

	@Override
	public IType typeCheckPost() throws TypingException {
		IType typeCheck = left.typeCheck();
		IType typeCheck2 = right.typeCheck();
		
		if(typeCheck == typeCheck2 && typeCheck.equals(AssignType.singleton))
			return typeCheck;
		else
			throw new TypingException();
	}

	@Override
	public IType typeCheckPre() throws TypingException {
		return this.typeCheck();
	}
}