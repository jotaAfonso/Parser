/* Generated By:JavaCC: Do not edit this line. Parser.java */
package parser;
import data.*;
import java.util.*;
import validations.*;
import exceptions.*;
import utils.*;
import types.*;
import ast.*;

@SuppressWarnings("all")
public class Parser implements ParserConstants {

  final public int Start(Hashtable<String, Automaton> auto, ValidationChecks checks) throws ParseException, CustomException, TypingException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case UNDERSCORE:
      DeployTransition(auto,checks);
                                        {if (true) return 0;}
      break;
    case IDWN:
      NormalTransition(auto, checks);
                                           {if (true) return 0;}
      break;
    case 0:
      jj_consume_token(0);
                  {if (true) return 1;}
      break;
    default:
      jj_la1[0] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public void DeployTransition(Hashtable<String, Automaton> auto, ValidationChecks checks) throws ParseException, CustomException, TypingException {
        Token iS, eS, isES = null;
        String p;
        Transition t;
        Boolean eC = false;
        ASTNode postC = null, preC = null;
        Set <ASTVar> vars = null;
    iS = jj_consume_token(UNDERSCORE);
    jj_consume_token(LBRACKET);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case INT:
    case BOOL:
    case STRING:
    case MINUS:
    case AND:
    case NOT:
    case OR:
    case TRUE:
    case FALSE:
    case SLPAR:
    case EXISTS:
    case FORALL:
    case NUMBER:
    case IDWN:
    case STRING_LITERAL:
      preC = Assertion();
      break;
    default:
      jj_la1[1] = jj_gen;
      ;
    }
    jj_consume_token(RBRACKET);
    p = Participant();
    eC = ExternalCall();
    t = DeployAction(auto);
    jj_consume_token(LBRACKET);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case INT:
    case BOOL:
    case STRING:
    case MINUS:
    case AND:
    case NOT:
    case OR:
    case TRUE:
    case FALSE:
    case SLPAR:
    case EXISTS:
    case FORALL:
    case NUMBER:
    case IDWN:
    case STRING_LITERAL:
      postC = Assertion();
      break;
    default:
      jj_la1[2] = jj_gen;
      ;
    }
    jj_consume_token(RBRACKET);
    jj_consume_token(LBRACKET);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case INT:
    case BOOL:
    case STRING:
      vars = VariableDeclaration();
      break;
    default:
      jj_la1[3] = jj_gen;
      ;
    }
    jj_consume_token(RBRACKET);
    eS = jj_consume_token(IDWN);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case PLUS:
      isES = jj_consume_token(PLUS);
      break;
    default:
      jj_la1[4] = jj_gen;
      ;
    }
        GrammarLogic.addTransition(auto, checks, t, iS, eS, isES, eC, preC, postC, p, true, vars);
  }

  final public Set <ASTVar> VariableDeclaration() throws ParseException {
        Token t1, t2;
        Set <ASTVar> vars = new HashSet <ASTVar> ();
        IType ty1, ty2 = null;
    ty1 = Type();
    t1 = jj_consume_token(IDWN);
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case SEMICOLON:
        ;
        break;
      default:
        jj_la1[5] = jj_gen;
        break label_1;
      }
      jj_consume_token(SEMICOLON);
      ty2 = Type();
      t2 = jj_consume_token(IDWN);
                                                                             vars.add(new ASTVar(t2.image, ty2));
    }
          vars.add(new ASTVar(t1.image, ty1)); {if (true) return vars;}
    throw new Error("Missing return statement in function");
  }

  final public void NormalTransition(Hashtable<String, Automaton> auto, ValidationChecks checks) throws ParseException, CustomException, TypingException {
        Token iS, eS, isES = null;
        String p;
        Transition t;
        Boolean eC = false;
        ASTNode postC = null, preC = null;
    iS = jj_consume_token(IDWN);
    jj_consume_token(LBRACKET);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case INT:
    case BOOL:
    case STRING:
    case MINUS:
    case AND:
    case NOT:
    case OR:
    case TRUE:
    case FALSE:
    case SLPAR:
    case EXISTS:
    case FORALL:
    case NUMBER:
    case IDWN:
    case STRING_LITERAL:
      preC = Assertion();
      break;
    default:
      jj_la1[6] = jj_gen;
      ;
    }
    jj_consume_token(RBRACKET);
    p = ParticipantExtra();
    eC = ExternalCall();
    t = NormalAction(auto);
    jj_consume_token(LBRACKET);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case INT:
    case BOOL:
    case STRING:
    case MINUS:
    case AND:
    case NOT:
    case OR:
    case TRUE:
    case FALSE:
    case SLPAR:
    case EXISTS:
    case FORALL:
    case NUMBER:
    case IDWN:
    case STRING_LITERAL:
      postC = Assertion();
      break;
    default:
      jj_la1[7] = jj_gen;
      ;
    }
    jj_consume_token(RBRACKET);
    eS = jj_consume_token(IDWN);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case PLUS:
      isES = jj_consume_token(PLUS);
      break;
    default:
      jj_la1[8] = jj_gen;
      ;
    }
                GrammarLogic.addTransition(auto, checks, t, iS, eS, isES, eC, preC, postC, p, false, null);
  }

  final public boolean ExternalCall() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case GT:
      jj_consume_token(GT);
               {if (true) return false;}
      break;
    case LT:
      jj_consume_token(LT);
                 {if (true) return true;}
      break;
    default:
      jj_la1[9] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public void AnyParticipant() throws ParseException {
    jj_consume_token(ANY);
  }

  final public String Participant() throws ParseException {
        Token p1, p2 = null, p3 = null;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case ANY:
      AnyParticipant();
      break;
    default:
      jj_la1[10] = jj_gen;
      ;
    }
    p1 = jj_consume_token(IDWN);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case COLON:
      p2 = jj_consume_token(COLON);
      p3 = jj_consume_token(IDWN);
      break;
    default:
      jj_la1[11] = jj_gen;
      ;
    }
                if(p2 == null) { {if (true) return p1.image;} }
                else { {if (true) return p1.image.concat(p2.image).concat(p3.image);} }
    throw new Error("Missing return statement in function");
  }

  final public String ParticipantExtra() throws ParseException {
        String p1, p2;
        Token join;
        Set < String > parts = new HashSet < String > ();
    p1 = Participant();
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case MOREPART:
        ;
        break;
      default:
        jj_la1[12] = jj_gen;
        break label_2;
      }
      join = jj_consume_token(MOREPART);
      p2 = Participant();
                                                                   parts.add(join.image.concat(p2));
    }
          {if (true) return p1.concat(String.join("", parts));}
    throw new Error("Missing return statement in function");
  }

  final public Transition DeployAction(Hashtable<String, Automaton> auto) throws ParseException {
        Token c;
        List<Param> param = null;
    jj_consume_token(STARTLABEL);
    jj_consume_token(LPAR);
    c = jj_consume_token(IDWN);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case COMMA:
      jj_consume_token(COMMA);
      param = ParamList();
      break;
    default:
      jj_la1[13] = jj_gen;
      ;
    }
    jj_consume_token(RPAR);
          {if (true) return new Transition(c.image, "starts", ".", param);}
    throw new Error("Missing return statement in function");
  }

  final public Transition NormalAction(Hashtable<String, Automaton> auto) throws ParseException {
        Token c, aLabel;
        String aType;
        List<Param> param;
    c = jj_consume_token(IDWN);
    aType = OperationType();
    aLabel = jj_consume_token(IDWN);
    jj_consume_token(LPAR);
    param = ParamList();
    jj_consume_token(RPAR);
          {if (true) return new Transition(c.image, aLabel.image, aType, param);}
    throw new Error("Missing return statement in function");
  }

  final public String OperationType() throws ParseException {
        Token aType;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case MINUS:
      aType = jj_consume_token(MINUS);
      break;
    case DOT:
      aType = jj_consume_token(DOT);
      break;
    default:
      jj_la1[14] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
          {if (true) return aType.image;}
    throw new Error("Missing return statement in function");
  }

//	ASSERTIONS - PARAMETERS
  final public List<Param> ParamList() throws ParseException {
        List<Param> l = new LinkedList<Param>();
        Token x;
        IType t;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case INT:
    case BOOL:
    case STRING:
    case PARTICIPANT:
    case ARRAY:
      t = TypeParam();
      x = jj_consume_token(IDWN);
                                   l.add(new Param(x.image, t));
      label_3:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case COMMA:
          ;
          break;
        default:
          jj_la1[15] = jj_gen;
          break label_3;
        }
        jj_consume_token(COMMA);
        t = TypeParam();
        x = jj_consume_token(IDWN);
                                          l.add(new Param(x.image, t));
      }
      break;
    default:
      jj_la1[16] = jj_gen;
      ;
    }
          {if (true) return l;}
    throw new Error("Missing return statement in function");
  }

  final public IType TypeParam() throws ParseException {
        IType t;
        Token role;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case PARTICIPANT:
      jj_consume_token(PARTICIPANT);
      role = jj_consume_token(IDWN);
                                        {if (true) return new ParticipantType(role.image);}
      break;
    case ARRAY:
      jj_consume_token(ARRAY);
      t = Type();
                                {if (true) return new SetType(t);}
      break;
    case INT:
    case BOOL:
    case STRING:
      t = Type();
                   {if (true) return t;}
      break;
    default:
      jj_la1[17] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public IType Type() throws ParseException {
        IType t;
        Token role;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case INT:
      jj_consume_token(INT);
                  {if (true) return IntType.singleton;}
      break;
    case BOOL:
      jj_consume_token(BOOL);
               {if (true) return BoolType.singleton;}
      break;
    case STRING:
      jj_consume_token(STRING);
                     {if (true) return StringType.singleton;}
      break;
    default:
      jj_la1[18] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public ASTNode Assertion() throws ParseException {
        ASTNode n;
    n = LogicalExp();
          {if (true) return n;}
    throw new Error("Missing return statement in function");
  }

  final public ASTNode LogicalExp() throws ParseException {
        ASTNode n1, n2;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case INT:
    case BOOL:
    case STRING:
    case MINUS:
    case TRUE:
    case FALSE:
    case SLPAR:
    case EXISTS:
    case FORALL:
    case NUMBER:
    case IDWN:
    case STRING_LITERAL:
      n1 = BooleanExp();
      break;
    case OR:
      jj_consume_token(OR);
      jj_consume_token(LPAR);
      n1 = LogicalExp();
      jj_consume_token(COMMA);
      n2 = LogicalExp();
      jj_consume_token(RPAR);
                                                                           n1 = new ASTOr(n1, n2);
      break;
    case AND:
      jj_consume_token(AND);
      jj_consume_token(LPAR);
      n1 = LogicalExp();
      jj_consume_token(COMMA);
      n2 = LogicalExp();
      jj_consume_token(RPAR);
                                                                            n1 = new ASTAnd(n1, n2);
      break;
    case NOT:
      jj_consume_token(NOT);
      n1 = LogicalExp();
                                    {if (true) return new ASTNot(n1);}
      break;
    default:
      jj_la1[19] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
            {if (true) return n1;}
    throw new Error("Missing return statement in function");
  }

  final public ASTNode BooleanExp() throws ParseException {
        ASTNode n1, n2, n3;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case INT:
    case BOOL:
    case STRING:
    case MINUS:
    case TRUE:
    case FALSE:
    case SLPAR:
    case NUMBER:
    case IDWN:
    case STRING_LITERAL:
      n1 = ArithmeticExp();
      label_4:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case EQ:
        case NEQ:
        case LEQ:
        case GEQ:
        case LT:
        case GT:
        case CONTAINS:
        case VARASS:
          ;
          break;
        default:
          jj_la1[20] = jj_gen;
          break label_4;
        }
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case EQ:
          jj_consume_token(EQ);
          n2 = ArithmeticExp();
                                              {if (true) return new ASTEq(n1, n2);}
          break;
        case NEQ:
          jj_consume_token(NEQ);
          n2 = ArithmeticExp();
                                               {if (true) return new ASTNeq(n1, n2);}
          break;
        case LT:
          jj_consume_token(LT);
          n2 = ArithmeticExp();
                                              {if (true) return new ASTLt(n1, n2);}
          break;
        case GT:
          jj_consume_token(GT);
          n2 = ArithmeticExp();
                                              {if (true) return new ASTGt(n1, n2);}
          break;
        case LEQ:
          jj_consume_token(LEQ);
          n2 = ArithmeticExp();
                                               {if (true) return new ASTLeq(n1, n2);}
          break;
        case GEQ:
          jj_consume_token(GEQ);
          n2 = ArithmeticExp();
                                               {if (true) return new ASTGeq(n1, n2);}
          break;
        case VARASS:
          jj_consume_token(VARASS);
          n2 = ArithmeticExp();
                                                  {if (true) return new ASTAssign(n1, n2);}
          break;
        case CONTAINS:
          jj_consume_token(CONTAINS);
          n2 = ArithmeticExp();
                                                    {if (true) return new ASTContains(n1, n2);}
          break;
        default:
          jj_la1[21] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
      }
          {if (true) return n1;}
      break;
    case EXISTS:
      jj_consume_token(EXISTS);
      n1 = AtomicExp();
      n2 = AtomicExp();
      n3 = LogicalExp();
                                                                         {if (true) return new ASTExists(n1, n2, n3);}
      break;
    case FORALL:
      jj_consume_token(FORALL);
      n1 = AtomicExp();
      n2 = AtomicExp();
      n3 = LogicalExp();
                                                                         {if (true) return new ASTForAll(n1, n2, n3);}
      break;
    default:
      jj_la1[22] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public ASTNode ArithmeticExp() throws ParseException {
  ASTNode n1, n2;
    n1 = UnaryExp();
    label_5:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case PLUS:
      case MINUS:
      case TIMES:
      case DIV:
        ;
        break;
      default:
        jj_la1[23] = jj_gen;
        break label_5;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case PLUS:
        jj_consume_token(PLUS);
        n2 = UnaryExp();
                                   {if (true) return new ASTAdd(n1, n2);}
        break;
      case MINUS:
        jj_consume_token(MINUS);
        n2 = UnaryExp();
                                    {if (true) return new ASTSub(n1, n2);}
        break;
      case TIMES:
        jj_consume_token(TIMES);
        n2 = UnaryExp();
                                    {if (true) return new ASTMul(n1, n2);}
        break;
      case DIV:
        jj_consume_token(DIV);
        n2 = UnaryExp();
                                  {if (true) return new ASTDiv(n1, n2);}
        break;
      default:
        jj_la1[24] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
          {if (true) return n1;}
    throw new Error("Missing return statement in function");
  }

/*
	Atomic and negation
*/
  final public ASTNode UnaryExp() throws ParseException {
        ASTNode n1;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case INT:
    case BOOL:
    case STRING:
    case TRUE:
    case FALSE:
    case SLPAR:
    case NUMBER:
    case IDWN:
    case STRING_LITERAL:
      n1 = AtomicExp();
      break;
    case MINUS:
      jj_consume_token(MINUS);
      n1 = UnaryExp();
                                    {if (true) return new ASTSub(new ASTNum(0), n1);}
      break;
    default:
      jj_la1[25] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
          {if (true) return n1;}
    throw new Error("Missing return statement in function");
  }

  final public ASTNode AtomicExp() throws ParseException {
        Token t1, t2;
        ASTNode n1;
        IType ty1 = null;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case NUMBER:
      t1 = jj_consume_token(NUMBER);
                          {if (true) return new ASTNum(Integer.parseInt(t1.image));}
      break;
    case STRING_LITERAL:
      t1 = jj_consume_token(STRING_LITERAL);
                                  {if (true) return new ASTString(t1.image);}
      break;
    case SLPAR:
      jj_consume_token(SLPAR);
      jj_consume_token(SRPAR);
                            {if (true) return new ASTSet();}
      break;
    case TRUE:
      jj_consume_token(TRUE);
                     {if (true) return new ASTBool(true);}
      break;
    case FALSE:
      jj_consume_token(FALSE);
                      {if (true) return new ASTBool(false);}
      break;
    default:
      jj_la1[27] = jj_gen;
      if (jj_2_1(2)) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case INT:
        case BOOL:
        case STRING:
          ty1 = Type();
          break;
        default:
          jj_la1[26] = jj_gen;
          ;
        }
        t1 = jj_consume_token(IDWN);
                  if(ty1 == null)
                        { {if (true) return new ASTId(t1.image);} }
                  else
                        { {if (true) return new ASTVar(t1.image, ty1);} }
      } else {
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
    throw new Error("Missing return statement in function");
  }

  private boolean jj_2_1(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_1(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(0, xla); }
  }

  private boolean jj_3R_10() {
    if (jj_scan_token(STRING)) return true;
    return false;
  }

  private boolean jj_3R_8() {
    if (jj_scan_token(INT)) return true;
    return false;
  }

  private boolean jj_3R_7() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_8()) {
    jj_scanpos = xsp;
    if (jj_3R_9()) {
    jj_scanpos = xsp;
    if (jj_3R_10()) return true;
    }
    }
    return false;
  }

  private boolean jj_3R_9() {
    if (jj_scan_token(BOOL)) return true;
    return false;
  }

  private boolean jj_3R_6() {
    if (jj_3R_7()) return true;
    return false;
  }

  private boolean jj_3_1() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_6()) jj_scanpos = xsp;
    if (jj_scan_token(IDWN)) return true;
    return false;
  }

  /** Generated Token Manager. */
  public ParserTokenManager token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private Token jj_scanpos, jj_lastpos;
  private int jj_la;
  private int jj_gen;
  final private int[] jj_la1 = new int[28];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
      jj_la1_init_0();
      jj_la1_init_1();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x1,0x80c0e470,0x80c0e470,0x70,0x200,0x0,0x80c0e470,0x80c0e470,0x200,0x300000,0x4000000,0x0,0x0,0x0,0x400,0x0,0x1f0,0x1f0,0x70,0x80c0e470,0x23f0000,0x23f0000,0x80c00470,0x1e00,0x1e00,0x80c00470,0x70,0x80c00000,};
   }
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {0x8004,0x19c00,0x19c00,0x0,0x0,0x10,0x19c00,0x19c00,0x0,0x0,0x0,0x8,0x100,0x20,0x200,0x20,0x0,0x0,0x0,0x19c00,0x80,0x80,0x19c00,0x0,0x0,0x19000,0x0,0x11000,};
   }
  final private JJCalls[] jj_2_rtns = new JJCalls[1];
  private boolean jj_rescan = false;
  private int jj_gc = 0;

  /** Constructor with InputStream. */
  public Parser(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public Parser(java.io.InputStream stream, String encoding) {
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new ParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 28; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 28; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor. */
  public Parser(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new ParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 28; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 28; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor with generated Token Manager. */
  public Parser(ParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 28; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(ParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 28; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      if (++jj_gc > 100) {
        jj_gc = 0;
        for (int i = 0; i < jj_2_rtns.length; i++) {
          JJCalls c = jj_2_rtns[i];
          while (c != null) {
            if (c.gen < jj_gen) c.first = null;
            c = c.next;
          }
        }
      }
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }

  static private final class LookaheadSuccess extends java.lang.Error { }
  final private LookaheadSuccess jj_ls = new LookaheadSuccess();
  private boolean jj_scan_token(int kind) {
    if (jj_scanpos == jj_lastpos) {
      jj_la--;
      if (jj_scanpos.next == null) {
        jj_lastpos = jj_scanpos = jj_scanpos.next = token_source.getNextToken();
      } else {
        jj_lastpos = jj_scanpos = jj_scanpos.next;
      }
    } else {
      jj_scanpos = jj_scanpos.next;
    }
    if (jj_rescan) {
      int i = 0; Token tok = token;
      while (tok != null && tok != jj_scanpos) { i++; tok = tok.next; }
      if (tok != null) jj_add_error_token(kind, i);
    }
    if (jj_scanpos.kind != kind) return true;
    if (jj_la == 0 && jj_scanpos == jj_lastpos) throw jj_ls;
    return false;
  }


/** Get the next Token. */
  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  private int[] jj_expentry;
  private int jj_kind = -1;
  private int[] jj_lasttokens = new int[100];
  private int jj_endpos;

  private void jj_add_error_token(int kind, int pos) {
    if (pos >= 100) return;
    if (pos == jj_endpos + 1) {
      jj_lasttokens[jj_endpos++] = kind;
    } else if (jj_endpos != 0) {
      jj_expentry = new int[jj_endpos];
      for (int i = 0; i < jj_endpos; i++) {
        jj_expentry[i] = jj_lasttokens[i];
      }
      jj_entries_loop: for (java.util.Iterator<?> it = jj_expentries.iterator(); it.hasNext();) {
        int[] oldentry = (int[])(it.next());
        if (oldentry.length == jj_expentry.length) {
          for (int i = 0; i < jj_expentry.length; i++) {
            if (oldentry[i] != jj_expentry[i]) {
              continue jj_entries_loop;
            }
          }
          jj_expentries.add(jj_expentry);
          break jj_entries_loop;
        }
      }
      if (pos != 0) jj_lasttokens[(jj_endpos = pos) - 1] = kind;
    }
  }

  /** Generate ParseException. */
  public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[49];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 28; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
          if ((jj_la1_1[i] & (1<<j)) != 0) {
            la1tokens[32+j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 49; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    jj_endpos = 0;
    jj_rescan_token();
    jj_add_error_token(0, 0);
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  final public void enable_tracing() {
  }

  /** Disable tracing. */
  final public void disable_tracing() {
  }

  private void jj_rescan_token() {
    jj_rescan = true;
    for (int i = 0; i < 1; i++) {
    try {
      JJCalls p = jj_2_rtns[i];
      do {
        if (p.gen > jj_gen) {
          jj_la = p.arg; jj_lastpos = jj_scanpos = p.first;
          switch (i) {
            case 0: jj_3_1(); break;
          }
        }
        p = p.next;
      } while (p != null);
      } catch(LookaheadSuccess ls) { }
    }
    jj_rescan = false;
  }

  private void jj_save(int index, int xla) {
    JJCalls p = jj_2_rtns[index];
    while (p.gen > jj_gen) {
      if (p.next == null) { p = p.next = new JJCalls(); break; }
      p = p.next;
    }
    p.gen = jj_gen + xla - jj_la; p.first = token; p.arg = xla;
  }

  static final class JJCalls {
    int gen;
    Token first;
    int arg;
    JJCalls next;
  }

 }
