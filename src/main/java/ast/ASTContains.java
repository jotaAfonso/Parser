package ast;

import java.util.ArrayList;
import java.util.List;

import exceptions.TypingException;
import types.*;
import types.IType;

public class ASTContains implements ASTNode {

	ASTNode left, right;
	IType type;

	public ASTContains(ASTNode l, ASTNode r) {
		left = l;
		right = r;
	}

	@Override
	public String toString() {
		return left.toString() + " in " + right.toString();
	}

	@Override
	public IType typeCheck() throws TypingException {
		IType typeCheck = left.typeCheck();
		IType typeCheck2 = right.typeCheck();

		if ((typeCheck.equals(BoolType.singleton) || typeCheck.equals(IntType.singleton)
				|| typeCheck.equals(ParticipantType.singleton) || typeCheck.equals(StringType.singleton))
				&& typeCheck2.equals(SetType.singleton))
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
