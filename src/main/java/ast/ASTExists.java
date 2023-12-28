package ast;

import java.util.ArrayList;
import java.util.List;

import exceptions.TypingException;
import types.BoolType;
import types.IType;
import types.IntType;
import types.ParticipantType;
import types.SetType;
import types.StringType;

public class ASTExists implements ASTNode {

	ASTNode left, middle, right;
	IType type;

	public ASTExists(ASTNode l, ASTNode m, ASTNode r) {
		left = l;
		middle = m;
		right = r;
	}

	@Override
	public String toString() {
		return " ∃ " + left.toString() + " " + middle.toString() + " " + right.toString();
	}

	@Override
	public IType typeCheck() throws TypingException {
		IType typeCheck = left.typeCheck();
		IType typeCheck2 = middle.typeCheck();
		IType typeCheck3 = right.typeCheck();

		if ((typeCheck.equals(BoolType.singleton) || typeCheck.equals(IntType.singleton)
				|| typeCheck.equals(ParticipantType.singleton) || typeCheck.equals(StringType.singleton))
				&& typeCheck2.equals(SetType.singleton) && typeCheck3.equals(BoolType.singleton))
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
		List<ASTVar> ml = this.middle.getVars();
		List<ASTVar> rl = this.right.getVars();
		List<ASTVar> result = new ArrayList<ASTVar>();

		result.addAll(ll);
		result.addAll(ml);
		result.addAll(rl);
		
		return result;
	}
	
	@Override
	public List<ASTId> getIds() {
		List<ASTId> ll = this.left.getIds();
		List<ASTId> ml = this.middle.getIds();
		List<ASTId> rl = this.right.getIds();
		List<ASTId> result = new ArrayList<ASTId>();

		result.addAll(ll);
		result.addAll(ml);
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
