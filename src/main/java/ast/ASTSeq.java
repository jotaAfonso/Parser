package ast;

import java.util.ArrayList;
import java.util.List;

import exceptions.TypingException;
import types.AssignType;
import types.BoolType;
import types.IType;
import types.SequenceType;

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
		IType lt = left.typeCheck();
		IType rt = right.typeCheck();
		
		if(lt == rt && lt == AssignType.singleton)
			return AssignType.singleton;
		if(lt == rt && lt == BoolType.singleton)
			return BoolType.singleton;
		
		return SequenceType.singleton; 
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