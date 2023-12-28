package ast;
import java.util.ArrayList;
import java.util.List;

import exceptions.TypingException;
import types.IType;
import types.IntType;
import types.StringType;

public class ASTAdd implements ASTNode {

	ASTNode left, right;

	public ASTAdd(ASTNode l, ASTNode r) {
		left = l;
		right = r;
	}

	@Override
	public String toString() {
		return left.toString() + " + " + right.toString();
	}
	
	@Override
	public IType typeCheck() throws TypingException {
		IType typeCheck = left.typeCheck();
		IType typeCheck2 = right.typeCheck();
		
		if(typeCheck == typeCheck2 && (typeCheck.equals(IntType.singleton) || typeCheck.equals(StringType.singleton)))
			return typeCheck;
		else
			throw new TypingException("Wrong types in add");
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
		return this.typeCheck();
	}

	@Override
	public IType typeCheckPre() throws TypingException {
		return this.typeCheck();
	}
}
