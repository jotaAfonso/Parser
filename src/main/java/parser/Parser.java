/* Generated By:JavaCC: Do not edit this line. Parser.java */
package parser;
import data.*;
import java.util.*;
import validations.*;
import exceptions.*;
import utils.*;
import types.*;
import ast.*;

public class Parser implements ParserConstants {

  final public int Start(Hashtable<String, Automaton> auto, ValidationChecks checks) throws ParseException, CustomException, TypingException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case UNDERSCORE:
      DeployTransition(auto,checks);
                {if (true) return 0;}
      break;
    case ID:
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
        ASTNode postC = null;
    iS = jj_consume_token(UNDERSCORE);
    jj_consume_token(LBRACKET);
    jj_consume_token(RBRACKET);
    p = Participant();
    eC = ExternalCall();
    t = DeployAction(auto);
    jj_consume_token(LBRACKET);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case MINUS:
    case TRUE:
    case FALSE:
    case LPAR:
    case NOT:
    case VAR:
    case QUOTATIONMARK:
    case Num:
    case ID:
      postC = Assertion();
      break;
    default:
      jj_la1[1] = jj_gen;
      ;
    }
    jj_consume_token(RBRACKET);
    eS = jj_consume_token(ID);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case PLUS:
      isES = jj_consume_token(PLUS);
      break;
    default:
      jj_la1[2] = jj_gen;
      ;
    }
        GrammarLogic.addTransition(auto, checks, t, iS, eS, isES, eC, null, postC, p, true);
  }

  final public void NormalTransition(Hashtable<String, Automaton> auto, ValidationChecks checks) throws ParseException, CustomException, TypingException {
        Token iS, eS, isES = null;
        String p;
        Transition t;
        Boolean eC = false;
        ASTNode postC = null, preC = null;
    iS = jj_consume_token(ID);
    jj_consume_token(LBRACKET);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case MINUS:
    case TRUE:
    case FALSE:
    case LPAR:
    case NOT:
    case VAR:
    case QUOTATIONMARK:
    case Num:
    case ID:
      preC = Assertion();
      break;
    default:
      jj_la1[3] = jj_gen;
      ;
    }
    jj_consume_token(RBRACKET);
    p = ParticipantExtra();
    eC = ExternalCall();
    t = NormalAction(auto);
    jj_consume_token(LBRACKET);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case MINUS:
    case TRUE:
    case FALSE:
    case LPAR:
    case NOT:
    case VAR:
    case QUOTATIONMARK:
    case Num:
    case ID:
      postC = Assertion();
      break;
    default:
      jj_la1[4] = jj_gen;
      ;
    }
    jj_consume_token(RBRACKET);
    eS = jj_consume_token(ID);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case PLUS:
      isES = jj_consume_token(PLUS);
      break;
    default:
      jj_la1[5] = jj_gen;
      ;
    }
                GrammarLogic.addTransition(auto, checks, t, iS, eS, isES, eC, preC, postC, p, false);
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
      jj_la1[6] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public String Participant() throws ParseException {
        Token p1, p2 = null, p3 = null;
    p1 = jj_consume_token(ID);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case COLON:
      p2 = jj_consume_token(COLON);
      p3 = jj_consume_token(ID);
      break;
    default:
      jj_la1[7] = jj_gen;
      ;
    }
                if(p2 == null)
                        {if (true) return p1.image;}
                else
                        {if (true) return p1.image.concat(p2.image).concat(p3.image);}
    throw new Error("Missing return statement in function");
  }

  final public String ParticipantExtra() throws ParseException {
        String p1, p2;
        Token join;
        Set < String > parts = new HashSet < String > ();
    p1 = Participant();
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case MOREPART:
        ;
        break;
      default:
        jj_la1[8] = jj_gen;
        break label_1;
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
    c = jj_consume_token(ID);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case COMMA:
      jj_consume_token(COMMA);
      param = ParamList();
      break;
    default:
      jj_la1[9] = jj_gen;
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
    c = jj_consume_token(ID);
    aType = OperationType();
    aLabel = jj_consume_token(ID);
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
      jj_la1[10] = jj_gen;
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
    case ID:
      x = jj_consume_token(ID);
      jj_consume_token(COLON);
      t = Type();
                                   l.add(new Param(x.image,t));
      label_2:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case COMMA:
          ;
          break;
        default:
          jj_la1[11] = jj_gen;
          break label_2;
        }
        jj_consume_token(COMMA);
        x = jj_consume_token(ID);
        jj_consume_token(COLON);
        t = Type();
                                           l.add(new Param(x.image,t));
      }
      break;
    default:
      jj_la1[12] = jj_gen;
      ;
    }
          {if (true) return l;}
    throw new Error("Missing return statement in function");
  }

  final public IType Type() throws ParseException {
        IType t;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case INT:
      jj_consume_token(INT);
            t = IntType.singleton;
      break;
    case BOOL:
      jj_consume_token(BOOL);
             t = BoolType.singleton;
      break;
    case STRING:
      jj_consume_token(STRING);
               t = StringType.singleton;
      break;
    case ID:
      jj_consume_token(ID);
      jj_consume_token(COLON);
      jj_consume_token(PTYPE);
                            t = ParticipantType.singleton;
      break;
    default:
      jj_la1[13] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
          {if (true) return t;}
    throw new Error("Missing return statement in function");
  }

// ASSERTIONS
  final public ASTNode Assertion() throws ParseException {
        ASTNode e;
    e = Seq();
               {if (true) return e;}
    throw new Error("Missing return statement in function");
  }

  final public ASTNode Seq() throws ParseException {
        ASTNode e1,e2;
    e1 = Assign();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case SEQ:
      jj_consume_token(SEQ);
      e2 = Seq();
                        e1 = new ASTSeq(e1, e2);
      break;
    default:
      jj_la1[14] = jj_gen;
      ;
    }
    {if (true) return e1;}
    throw new Error("Missing return statement in function");
  }

  final public ASTNode Assign() throws ParseException {
        ASTNode e1,e2;
    e1 = Disjunction();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case VARASS:
      jj_consume_token(VARASS);
      e2 = Assign();
                        e1 = new ASTAssign(e1, e2);
      break;
    default:
      jj_la1[15] = jj_gen;
      ;
    }
    {if (true) return e1;}
    throw new Error("Missing return statement in function");
  }

  final public ASTNode Disjunction() throws ParseException {
        ASTNode e1,e2;
    e1 = Conjunction();
    label_3:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case OR:
        ;
        break;
      default:
        jj_la1[16] = jj_gen;
        break label_3;
      }
      jj_consume_token(OR);
      e2 = Conjunction();
                             e1 = new ASTOr(e1,e2);
    }
    {if (true) return e1;}
    throw new Error("Missing return statement in function");
  }

  final public ASTNode Conjunction() throws ParseException {
        ASTNode e1,e2;
    e1 = BooleanExp();
    label_4:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case AND:
        ;
        break;
      default:
        jj_la1[17] = jj_gen;
        break label_4;
      }
      jj_consume_token(AND);
      e2 = BooleanExp();
                              e1 = new ASTAnd(e1,e2);
    }
    {if (true) return e1;}
    throw new Error("Missing return statement in function");
  }

  final public ASTNode BooleanExp() throws ParseException {
        ASTNode e1,e2;
    e1 = Exp();
    label_5:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case EQ:
      case NEQ:
      case LEQ:
      case GEQ:
      case LT:
      case GT:
        ;
        break;
      default:
        jj_la1[18] = jj_gen;
        break label_5;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case EQ:
        jj_consume_token(EQ);
        e2 = BooleanExp();
                                   e1 = new ASTEq(e1,e2);
        break;
      case NEQ:
        jj_consume_token(NEQ);
        e2 = BooleanExp();
                                    e1 = new ASTNeq(e1,e2);
        break;
      case LT:
        jj_consume_token(LT);
        e2 = Exp();
                            e1 = new ASTLt(e1,e2);
        break;
      case GT:
        jj_consume_token(GT);
        e2 = Exp();
                            e1 = new ASTGt(e1,e2);
        break;
      case LEQ:
        jj_consume_token(LEQ);
        e2 = Exp();
                             e1 = new ASTLeq(e1,e2);
        break;
      case GEQ:
        jj_consume_token(GEQ);
        e2 = Exp();
                             e1 = new ASTGeq(e1,e2);
        break;
      default:
        jj_la1[19] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
           {if (true) return e1;}
    throw new Error("Missing return statement in function");
  }

  final public ASTNode Exp() throws ParseException {
  ASTNode e1, e2;
    e1 = Term();
    label_6:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case PLUS:
      case MINUS:
        ;
        break;
      default:
        jj_la1[20] = jj_gen;
        break label_6;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case PLUS:
        jj_consume_token(PLUS);
        e2 = Term();
                            e1 = new ASTAdd(e1,e2);
        break;
      case MINUS:
        jj_consume_token(MINUS);
        e2 = Term();
                             e1 = new ASTSub(e1,e2);
        break;
      default:
        jj_la1[21] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
       {if (true) return e1;}
    throw new Error("Missing return statement in function");
  }

  final public ASTNode Term() throws ParseException {
  ASTNode e1, e2;
    e1 = Unary();
    label_7:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case TIMES:
      case DIV:
        ;
        break;
      default:
        jj_la1[22] = jj_gen;
        break label_7;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case TIMES:
        jj_consume_token(TIMES);
        e2 = Unary();
                              e1 = new ASTMul(e1,e2);
        break;
      case DIV:
        jj_consume_token(DIV);
        e2 = Unary();
                            e1 = new ASTDiv(e1,e2);
        break;
      default:
        jj_la1[23] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
       {if (true) return e1;}
    throw new Error("Missing return statement in function");
  }

  final public ASTNode Unary() throws ParseException {
        ASTNode e1;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case TRUE:
    case FALSE:
    case LPAR:
    case VAR:
    case QUOTATIONMARK:
    case Num:
    case ID:
      e1 = Fact();
                   {if (true) return e1;}
      break;
    case MINUS:
      jj_consume_token(MINUS);
      e1 = Unary();
                              {if (true) return new ASTSub(new ASTNum(0),e1);}
      break;
    case NOT:
      jj_consume_token(NOT);
      e1 = Unary();
                          {if (true) return new ASTNot(e1);}
      break;
    default:
      jj_la1[24] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public ASTNode Fact() throws ParseException {
        Token x;
        ASTNode e1;
        IType t;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case Num:
      x = jj_consume_token(Num);
                    {if (true) return new ASTNum(Integer.parseInt(x.image));}
      break;
    case QUOTATIONMARK:
      jj_consume_token(QUOTATIONMARK);
      x = jj_consume_token(ID);
      jj_consume_token(QUOTATIONMARK);
                                                    {if (true) return new ASTString(x.image);}
      break;
    case LPAR:
      jj_consume_token(LPAR);
      e1 = Seq();
      jj_consume_token(RPAR);
                                   {if (true) return e1;}
      break;
    case TRUE:
      jj_consume_token(TRUE);
                   {if (true) return new ASTBool(true);}
      break;
    case FALSE:
      jj_consume_token(FALSE);
                    {if (true) return new ASTBool(false);}
      break;
    case VAR:
      jj_consume_token(VAR);
      t = Type();
      x = jj_consume_token(ID);
                                        {if (true) return new ASTVar(x.image, t);}
      break;
    case ID:
      x = jj_consume_token(ID);
                     {if (true) return new ASTId(x.image);}
      break;
    default:
      jj_la1[25] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  /** Generated Token Manager. */
  public ParserTokenManager token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private int jj_gen;
  final private int[] jj_la1 = new int[26];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
      jj_la1_init_0();
      jj_la1_init_1();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x1001,0xa0098200,0x100,0xa0098200,0xa0098200,0x100,0x18000000,0x2000,0x0,0x4000,0x200,0x4000,0x0,0xb0,0x40000000,0x0,0x200000,0x400000,0x1f800000,0x1f800000,0x300,0x300,0xc00,0xc00,0xa0098200,0x80098000,};
   }
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {0x40,0x62,0x0,0x62,0x62,0x0,0x0,0x0,0x4,0x0,0x10,0x0,0x40,0x40,0x0,0x1,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x62,0x62,};
   }

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
    for (int i = 0; i < 26; i++) jj_la1[i] = -1;
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
    for (int i = 0; i < 26; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public Parser(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new ParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 26; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 26; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public Parser(ParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 26; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(ParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 26; i++) jj_la1[i] = -1;
  }

  private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
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

  /** Generate ParseException. */
  public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[39];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 26; i++) {
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
    for (int i = 0; i < 39; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
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

 }
