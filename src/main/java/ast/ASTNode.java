package ast;

import java.util.List;

import types.IType;
import types.TypingException;

public interface ASTNode {
	
	IType typeCheck() throws TypingException;

	boolean checkIfItHasVar();	
	boolean checkIfItHasIds();	

	// THE TYPE IS SWITCH NEED TO SWITCH NAMES OF CLASSES
	List<ASTId> getVars();
	
	List<ASTVar> getIds();
}

