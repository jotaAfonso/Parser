package parser;

import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import ast.ASTId;
import ast.ASTNode;
import ast.ASTVar;
import data.Automaton;
import data.Transition;
import exceptions.CustomException;
import exceptions.TypingException;
import types.AssignType;
import types.BoolType;
import types.ParticipantType;
import utils.CommonUtils;
import utils.Param;
import validations.ValidationChecks;

/**
 * The Class GrammarLogic.
 */
public class GrammarLogic {

	/**
	 * Adds a transition to the automaton. It treats the several aspects of the
	 * transition (external call, assertions and checks).
	 *
	 * @param auto       - auto
	 * @param checks     - checks
	 * @param t          - transition
	 * @param iS         - initial state of the transition
	 * @param eS         - final state of the transition
	 * @param isES       - flag to indicate if the final state of the transition is
	 *                   an end state
	 * @param eC         - external call
	 * @param preC       - precondition
	 * @param postC      - post condition
	 * @param p          - participant
	 * @param deployFlag - deploy flag
	 * @throws CustomException - custom exception
	 * @throws TypingException - typing exception
	 */
	public static void addTransition(Hashtable<String, Automaton> auto, ValidationChecks checks, Transition t, Token iS,
			Token eS, Token isES, boolean eC, ASTNode preC, ASTNode postC, String p, boolean deployFlag,
			Set<ASTVar> vars) throws CustomException, TypingException {
		// automaton
		Automaton a = getAutomataById(auto, t);
		// transition
		treatTransition(t, iS, eS, eC, p);
		// external call
		if (eC) {
			String internalS = "I" + checks.getCounter();
			t.setToS(internalS);
			checks.getGraph().addEdge(iS.image, internalS, p);
			treatExternalCall(a, checks, internalS, eS.image, "OK", t, p);
			treatExternalCall(a, checks, internalS, iS.image, "NOK", t, p);
			a.getStates().add(internalS);
			checks.setCounter(checks.getCounter() + 1);
		}
		// asserion_pre
		if (preC != null)
			treatAssertion(a, t, preC, false, null, true);

		// asserion_post
		if (postC != null || (vars != null && !vars.isEmpty()))
			treatAssertion(a, t, postC, deployFlag, vars, false);

		List<Param> lparams = t.getParameters().stream().filter(x -> x.getType() instanceof ParticipantType)
				.collect(Collectors.toList());
		// participants in parameters
		if (lparams != null && !lparams.isEmpty()) {
			for (Param pa : lparams) {
				ParticipantType type = (ParticipantType) pa.getType();
				a.addAssociations(pa.getId(), type.getRole());
			}
		}

		// automaton
		addDataToAutomaton(a, t, iS.image, eS.image, (isES != null), p, deployFlag);

		// graph
		if (!eC)
			checks.getGraph().addEdge(iS.image, eS.image, p);
		if (deployFlag)
			checks.getContractsRegistered().add(t.getId());
	}

	/**
	 * Treats an assertion.
	 *
	 * @param a          - automaton
	 * @param t          - transition
	 * @param condition  - assertion
	 * @param deployFlag - deploy flag
	 * @param vars
	 * @throws CustomException - custom exception
	 * @throws TypingException - typing exception
	 */
	private static void treatAssertion(Automaton a, Transition t, ASTNode condition, boolean deployFlag,
			Set<ASTVar> vars, boolean preCondition) throws CustomException, TypingException {
		if (deployFlag && !vars.isEmpty()) {
			a.addGlobalVars(vars.stream().collect(Collectors.toList()), t.getLocalVars());
			a.setStatesDeclaration(vars);
		}

		if (condition != null) {
			if (!deployFlag && condition.checkIfItHasVar())
				throw new CustomException("Variables are only declared in the deploy/start method.");

			if(preCondition)
				t.setPreCondition(condition.toString());
			else 
				t.setPostCondition(condition.toString());
			
			List<ASTId> idsV = condition.getIds();
			if (idsV != null && !idsV.isEmpty())
				CommonUtils.addTypeToIDs(idsV, a.getGlobalVars(), t.getLocalVars());

			if (preCondition) {
				if (!condition.typeCheckPre().equals(BoolType.singleton))
					throw new TypingException();
			} else {
				if (!condition.typeCheckPost().equals(AssignType.singleton))
					throw new TypingException("Post condition consists on conjunctions of assignments.");
			}
		}
	}

	/**
	 * Treats a transition.
	 *
	 * @param iS   - initial state of the transition
	 * @param eS   - final state of the transition
	 * @param isES - flag to indicate if the final state of the transition is an end
	 *             state
	 * @param eC   - external call
	 * @param p    - participant
	 * @throws CustomException the custom exception
	 */
	private static void treatTransition(Transition t, Token iS, Token eS, boolean eC, String p) throws CustomException {
		t.setFromS(iS.image);
		t.setToS(eS.image);
		t.addParticipant(p);
		t.setExternalCall(eC);
	}

	/**
	 * Treats an external call.
	 *
	 * @param a      - automaton
	 * @param checks - checks
	 * @param from   - from state
	 * @param to     - to state
	 * @param label  - action label
	 * @param t      - transition
	 * @param p      - participant
	 */
	private static void treatExternalCall(Automaton a, ValidationChecks checks, String from, String to, String label,
			Transition t, String p) {
		Transition newt = new Transition(".", from, to, label, t.getNewParticipants(), t.getExistentParts(), t.getInput(),
				t.getPreCondition(), t.getPostCondition(), false);
		a.addTransition(newt);
		checks.getGraph().addEdge(newt.getFromS(), newt.getToS(), p);
	}

	/**
	 * Gets the automaton by id.
	 *
	 * @param auto - auto
	 * @param t    - transition
	 * @return the automata by id
	 */
	private static Automaton getAutomataById(Hashtable<String, Automaton> auto, Transition t) {
		if (auto.get(t.getId()) == null)
			auto.put(t.getId(), new Automaton(t.getId()));
		return auto.get(t.getId());

	}

	/**
	 * Adds the data to automaton.
	 *
	 * @param a          - automaton
	 * @param t          - transition
	 * @param fromState  - from state
	 * @param toState    - to state
	 * @param isEnd      - is end
	 * @param p          - participant
	 * @param deployFlag - deploy flag
	 * @throws CustomException - custom exception
	 */
	private static void addDataToAutomaton(Automaton a, Transition t, String fromState, String toState, boolean isEnd,
			String p, boolean deployFlag) throws CustomException {
		a.addBothStates(fromState, toState, isEnd);
		a.addTransition(t);
		a.addRoleParticipant(p, deployFlag);
	}
}
