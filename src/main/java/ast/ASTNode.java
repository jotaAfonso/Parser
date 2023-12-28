package ast;

import java.util.List;

import exceptions.TypingException;
import types.IType;

public interface ASTNode {

	IType typeCheck() throws TypingException;
	IType typeCheckPost() throws TypingException;
	IType typeCheckPre() throws TypingException;

	List<ASTVar> getVars();
	
	List<ASTId> getIds();
	
	boolean checkIfItHasVar();	
}

