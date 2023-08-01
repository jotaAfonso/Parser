package ast;

import java.util.ArrayList;
import java.util.List;

import exceptions.TypingException;
import types.AssignType;
import types.BoolType;
import types.IType;

public class ASTAssign implements ASTNode {
	
	ASTNode left, right;
	IType type;
	
	
	public ASTAssign(ASTNode l, ASTNode r){
		left = l;
		right = r;
	}

	@Override
	public IType typeCheck()
			throws TypingException {
		
		IType leftT = left.typeCheck();
		IType rightT = right.typeCheck();
		
		if(leftT == rightT)
			return BoolType.singleton;
		else
			throw new TypingException("Wrong types in assign");
	}
	
	@Override
	public String toString() {
		return left.toString() + " := " + right.toString();
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
