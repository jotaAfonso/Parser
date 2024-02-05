/* ParserTokenManager.java */
/* Generated By:JavaCC: Do not edit this line. ParserTokenManager.java */
package parser;
import data.*;
import java.util.*;
import validations.*;
import exceptions.*;
import utils.*;
import types.*;
import ast.*;

/** Token Manager. */
@SuppressWarnings("all")
public class ParserTokenManager implements ParserConstants {

  /** Debug output. */
  public  java.io.PrintStream debugStream = System.out;
  /** Set debug output. */
  public  void setDebugStream(java.io.PrintStream ds) { debugStream = ds; }
private final int jjStopStringLiteralDfa_0(int pos, long active0){
   switch (pos)
   {
      case 0:
         if ((active0 & 0x200000000L) != 0L)
            return 82;
         if ((active0 & 0xa0000L) != 0L)
            return 6;
         if ((active0 & 0x400000L) != 0L)
         {
            jjmatchedKind = 46;
            return 58;
         }
         if ((active0 & 0x100000000L) != 0L)
            return 83;
         if ((active0 & 0x800L) != 0L)
            return 68;
         if ((active0 & 0xc0L) != 0L)
         {
            jjmatchedKind = 46;
            return 12;
         }
         if ((active0 & 0x2001206030L) != 0L)
         {
            jjmatchedKind = 46;
            return 82;
         }
         return -1;
      case 1:
         if ((active0 & 0x1004010L) != 0L)
            return 82;
         if ((active0 & 0x40L) != 0L)
         {
            if (jjmatchedPos != 1)
            {
               jjmatchedKind = 46;
               jjmatchedPos = 1;
            }
            return 11;
         }
         if ((active0 & 0x20006020a0L) != 0L)
         {
            if (jjmatchedPos != 1)
            {
               jjmatchedKind = 46;
               jjmatchedPos = 1;
            }
            return 82;
         }
         return -1;
      case 2:
         if ((active0 & 0x2000002090L) != 0L)
            return 82;
         if ((active0 & 0x600060L) != 0L)
         {
            jjmatchedKind = 46;
            jjmatchedPos = 2;
            return 82;
         }
         return -1;
      case 3:
         if ((active0 & 0x200020L) != 0L)
            return 82;
         if ((active0 & 0x400040L) != 0L)
         {
            jjmatchedKind = 46;
            jjmatchedPos = 3;
            return 82;
         }
         return -1;
      case 4:
         if ((active0 & 0x400000L) != 0L)
            return 82;
         if ((active0 & 0x40L) != 0L)
         {
            jjmatchedKind = 46;
            jjmatchedPos = 4;
            return 82;
         }
         return -1;
      default :
         return -1;
   }
}
private final int jjStartNfa_0(int pos, long active0){
   return jjMoveNfa_0(jjStopStringLiteralDfa_0(pos, active0), pos + 1);
}
private int jjStopAtPos(int pos, int kind)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   return pos + 1;
}
private int jjMoveStringLiteralDfa0_0(){
   switch(curChar)
   {
      case 34:
         return jjStartNfaWithStates_0(0, 32, 83);
      case 40:
         return jjStopAtPos(0, 28);
      case 41:
         return jjStopAtPos(0, 29);
      case 42:
         return jjStopAtPos(0, 10);
      case 43:
         return jjStopAtPos(0, 8);
      case 44:
         return jjStopAtPos(0, 36);
      case 45:
         return jjStopAtPos(0, 9);
      case 47:
         return jjStartNfaWithStates_0(0, 11, 68);
      case 58:
         jjmatchedKind = 34;
         return jjMoveStringLiteralDfa1_0(0x4000000000L);
      case 59:
         return jjStopAtPos(0, 35);
      case 60:
         jjmatchedKind = 19;
         return jjMoveStringLiteralDfa1_0(0x20000L);
      case 61:
         return jjMoveStringLiteralDfa1_0(0x8000L);
      case 62:
         jjmatchedKind = 20;
         return jjMoveStringLiteralDfa1_0(0x40000L);
      case 70:
         return jjMoveStringLiteralDfa1_0(0x400000L);
      case 78:
         return jjMoveStringLiteralDfa1_0(0x2000L);
      case 79:
         return jjMoveStringLiteralDfa1_0(0x4000L);
      case 84:
         return jjMoveStringLiteralDfa1_0(0x200000L);
      case 91:
         return jjStopAtPos(0, 30);
      case 93:
         return jjStopAtPos(0, 31);
      case 95:
         return jjStartNfaWithStates_0(0, 33, 82);
      case 98:
         return jjMoveStringLiteralDfa1_0(0x20L);
      case 105:
         return jjMoveStringLiteralDfa1_0(0x1000010L);
      case 115:
         return jjMoveStringLiteralDfa1_0(0xc0L);
      case 118:
         return jjMoveStringLiteralDfa1_0(0x2000000000L);
      case 123:
         return jjStopAtPos(0, 26);
      case 124:
         return jjStopAtPos(0, 39);
      case 125:
         return jjStopAtPos(0, 27);
      default :
         return jjMoveNfa_0(0, 0);
   }
}
private int jjMoveStringLiteralDfa1_0(long active0){
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(0, active0);
      return 1;
   }
   switch(curChar)
   {
      case 61:
         if ((active0 & 0x8000L) != 0L)
            return jjStopAtPos(1, 15);
         else if ((active0 & 0x20000L) != 0L)
            return jjStopAtPos(1, 17);
         else if ((active0 & 0x40000L) != 0L)
            return jjStopAtPos(1, 18);
         else if ((active0 & 0x4000000000L) != 0L)
            return jjStopAtPos(1, 38);
         break;
      case 79:
         return jjMoveStringLiteralDfa2_0(active0, 0x2000L);
      case 82:
         if ((active0 & 0x4000L) != 0L)
            return jjStartNfaWithStates_0(1, 14, 82);
         break;
      case 97:
         return jjMoveStringLiteralDfa2_0(active0, 0x2000400000L);
      case 101:
         return jjMoveStringLiteralDfa2_0(active0, 0x80L);
      case 110:
         if ((active0 & 0x1000000L) != 0L)
         {
            jjmatchedKind = 24;
            jjmatchedPos = 1;
         }
         return jjMoveStringLiteralDfa2_0(active0, 0x10L);
      case 111:
         return jjMoveStringLiteralDfa2_0(active0, 0x20L);
      case 114:
         return jjMoveStringLiteralDfa2_0(active0, 0x200000L);
      case 116:
         return jjMoveStringLiteralDfa2_0(active0, 0x40L);
      default :
         break;
   }
   return jjStartNfa_0(0, active0);
}
private int jjMoveStringLiteralDfa2_0(long old0, long active0){
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(0, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(1, active0);
      return 2;
   }
   switch(curChar)
   {
      case 84:
         if ((active0 & 0x2000L) != 0L)
            return jjStartNfaWithStates_0(2, 13, 82);
         break;
      case 108:
         return jjMoveStringLiteralDfa3_0(active0, 0x400000L);
      case 111:
         return jjMoveStringLiteralDfa3_0(active0, 0x20L);
      case 114:
         if ((active0 & 0x2000000000L) != 0L)
            return jjStartNfaWithStates_0(2, 37, 82);
         return jjMoveStringLiteralDfa3_0(active0, 0x40L);
      case 116:
         if ((active0 & 0x10L) != 0L)
            return jjStartNfaWithStates_0(2, 4, 82);
         else if ((active0 & 0x80L) != 0L)
            return jjStartNfaWithStates_0(2, 7, 82);
         break;
      case 117:
         return jjMoveStringLiteralDfa3_0(active0, 0x200000L);
      default :
         break;
   }
   return jjStartNfa_0(1, active0);
}
private int jjMoveStringLiteralDfa3_0(long old0, long active0){
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(1, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(2, active0);
      return 3;
   }
   switch(curChar)
   {
      case 101:
         if ((active0 & 0x200000L) != 0L)
            return jjStartNfaWithStates_0(3, 21, 82);
         break;
      case 105:
         return jjMoveStringLiteralDfa4_0(active0, 0x40L);
      case 108:
         if ((active0 & 0x20L) != 0L)
            return jjStartNfaWithStates_0(3, 5, 82);
         break;
      case 115:
         return jjMoveStringLiteralDfa4_0(active0, 0x400000L);
      default :
         break;
   }
   return jjStartNfa_0(2, active0);
}
private int jjMoveStringLiteralDfa4_0(long old0, long active0){
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(2, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(3, active0);
      return 4;
   }
   switch(curChar)
   {
      case 101:
         if ((active0 & 0x400000L) != 0L)
            return jjStartNfaWithStates_0(4, 22, 82);
         break;
      case 110:
         return jjMoveStringLiteralDfa5_0(active0, 0x40L);
      default :
         break;
   }
   return jjStartNfa_0(3, active0);
}
private int jjMoveStringLiteralDfa5_0(long old0, long active0){
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(3, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(4, active0);
      return 5;
   }
   switch(curChar)
   {
      case 103:
         if ((active0 & 0x40L) != 0L)
            return jjStartNfaWithStates_0(5, 6, 82);
         break;
      default :
         break;
   }
   return jjStartNfa_0(4, active0);
}
private int jjStartNfaWithStates_0(int pos, int kind, int state)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) { return pos + 1; }
   return jjMoveNfa_0(state, pos + 1);
}
static final long[] jjbitVec0 = {
   0x0L, 0x0L, 0xffffffffffffffffL, 0xffffffffffffffffL
};
private int jjMoveNfa_0(int startState, int curPos)
{
   int startsAt = 0;
   jjnewStateCnt = 82;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 82:
               case 63:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 46)
                     kind = 46;
                  { jjCheckNAddTwoStates(62, 63); }
                  break;
               case 0:
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     if (kind > 43)
                        kind = 43;
                     { jjCheckNAdd(60); }
                  }
                  else if ((0x100002600L & l) != 0L)
                  {
                     if (kind > 1)
                        kind = 1;
                  }
                  else if (curChar == 47)
                     { jjAddStates(0, 1); }
                  else if (curChar == 34)
                     { jjCheckNAddTwoStates(65, 66); }
                  else if (curChar == 46)
                  {
                     if (kind > 40)
                        kind = 40;
                  }
                  else if (curChar == 60)
                     jjstateSet[jjnewStateCnt++] = 6;
                  else if (curChar == 33)
                     jjstateSet[jjnewStateCnt++] = 4;
                  else if (curChar == 38)
                  {
                     if (kind > 12)
                        kind = 12;
                  }
                  if (curChar == 13)
                     jjstateSet[jjnewStateCnt++] = 1;
                  break;
               case 11:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 46)
                     kind = 46;
                  { jjCheckNAddTwoStates(62, 63); }
                  break;
               case 83:
                  if ((0xffffffffffffdbffL & l) != 0L)
                     { jjCheckNAddTwoStates(65, 66); }
                  if (curChar == 34)
                  {
                     if (kind > 47)
                        kind = 47;
                  }
                  break;
               case 58:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 46)
                     kind = 46;
                  { jjCheckNAddTwoStates(62, 63); }
                  break;
               case 68:
                  if (curChar == 42)
                     { jjCheckNAddTwoStates(74, 75); }
                  else if (curChar == 47)
                     { jjCheckNAddStates(2, 4); }
                  break;
               case 12:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 46)
                     kind = 46;
                  { jjCheckNAddTwoStates(62, 63); }
                  break;
               case 1:
                  if (curChar == 10 && kind > 1)
                     kind = 1;
                  break;
               case 2:
                  if (curChar == 13)
                     jjstateSet[jjnewStateCnt++] = 1;
                  break;
               case 3:
                  if (curChar == 38 && kind > 12)
                     kind = 12;
                  break;
               case 4:
                  if (curChar == 61 && kind > 16)
                     kind = 16;
                  break;
               case 5:
                  if (curChar == 33)
                     jjstateSet[jjnewStateCnt++] = 4;
                  break;
               case 6:
                  if (curChar == 62 && kind > 16)
                     kind = 16;
                  break;
               case 7:
                  if (curChar == 60)
                     jjstateSet[jjnewStateCnt++] = 6;
                  break;
               case 35:
                  if (curChar == 46 && kind > 40)
                     kind = 40;
                  break;
               case 60:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 43)
                     kind = 43;
                  { jjCheckNAdd(60); }
                  break;
               case 64:
                  if (curChar == 34)
                     { jjCheckNAddTwoStates(65, 66); }
                  break;
               case 65:
                  if ((0xffffffffffffdbffL & l) != 0L)
                     { jjCheckNAddTwoStates(65, 66); }
                  break;
               case 66:
                  if (curChar == 34 && kind > 47)
                     kind = 47;
                  break;
               case 67:
                  if (curChar == 47)
                     { jjAddStates(0, 1); }
                  break;
               case 69:
                  if ((0xffffffffffffdbffL & l) != 0L)
                     { jjCheckNAddStates(2, 4); }
                  break;
               case 70:
                  if ((0x2400L & l) != 0L && kind > 2)
                     kind = 2;
                  break;
               case 71:
                  if (curChar == 10 && kind > 2)
                     kind = 2;
                  break;
               case 72:
                  if (curChar == 13)
                     jjstateSet[jjnewStateCnt++] = 71;
                  break;
               case 73:
                  if (curChar == 42)
                     { jjCheckNAddTwoStates(74, 75); }
                  break;
               case 74:
                  if ((0xffff7fffffffffffL & l) != 0L)
                     { jjCheckNAddTwoStates(74, 75); }
                  break;
               case 75:
                  if (curChar == 42)
                     jjstateSet[jjnewStateCnt++] = 76;
                  break;
               case 76:
                  if (curChar == 47 && kind > 3)
                     kind = 3;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 82:
               case 62:
                  if ((0x7fffffe07fffffeL & l) == 0L)
                     break;
                  if (kind > 46)
                     kind = 46;
                  { jjCheckNAddTwoStates(62, 63); }
                  break;
               case 0:
                  if ((0x7fffffe87fffffeL & l) != 0L)
                  {
                     if (kind > 46)
                        kind = 46;
                     { jjCheckNAddTwoStates(62, 63); }
                  }
                  if (curChar == 65)
                     { jjAddStates(5, 6); }
                  else if (curChar == 70)
                     jjstateSet[jjnewStateCnt++] = 58;
                  else if (curChar == 102)
                     jjstateSet[jjnewStateCnt++] = 52;
                  else if (curChar == 69)
                     jjstateSet[jjnewStateCnt++] = 46;
                  else if (curChar == 101)
                     jjstateSet[jjnewStateCnt++] = 40;
                  else if (curChar == 97)
                     jjstateSet[jjnewStateCnt++] = 33;
                  else if (curChar == 68)
                     jjstateSet[jjnewStateCnt++] = 30;
                  else if (curChar == 100)
                     jjstateSet[jjnewStateCnt++] = 24;
                  else if (curChar == 83)
                     jjstateSet[jjnewStateCnt++] = 18;
                  else if (curChar == 115)
                     jjstateSet[jjnewStateCnt++] = 12;
                  break;
               case 11:
                  if ((0x7fffffe07fffffeL & l) != 0L)
                  {
                     if (kind > 46)
                        kind = 46;
                     { jjCheckNAddTwoStates(62, 63); }
                  }
                  if (curChar == 97)
                     jjstateSet[jjnewStateCnt++] = 10;
                  break;
               case 83:
               case 65:
                  { jjCheckNAddTwoStates(65, 66); }
                  break;
               case 58:
                  if ((0x7fffffe07fffffeL & l) != 0L)
                  {
                     if (kind > 46)
                        kind = 46;
                     { jjCheckNAddTwoStates(62, 63); }
                  }
                  if (curChar == 79)
                     jjstateSet[jjnewStateCnt++] = 57;
                  break;
               case 12:
                  if ((0x7fffffe07fffffeL & l) != 0L)
                  {
                     if (kind > 46)
                        kind = 46;
                     { jjCheckNAddTwoStates(62, 63); }
                  }
                  if (curChar == 116)
                     jjstateSet[jjnewStateCnt++] = 11;
                  break;
               case 8:
                  if (curChar == 115 && kind > 23)
                     kind = 23;
                  break;
               case 9:
                  if (curChar == 116)
                     jjstateSet[jjnewStateCnt++] = 8;
                  break;
               case 10:
                  if (curChar == 114)
                     jjstateSet[jjnewStateCnt++] = 9;
                  break;
               case 13:
                  if (curChar == 115)
                     jjstateSet[jjnewStateCnt++] = 12;
                  break;
               case 14:
                  if (curChar == 83 && kind > 23)
                     kind = 23;
                  break;
               case 15:
                  if (curChar == 84)
                     jjstateSet[jjnewStateCnt++] = 14;
                  break;
               case 16:
                  if (curChar == 82)
                     jjstateSet[jjnewStateCnt++] = 15;
                  break;
               case 17:
                  if (curChar == 65)
                     jjstateSet[jjnewStateCnt++] = 16;
                  break;
               case 18:
                  if (curChar == 84)
                     jjstateSet[jjnewStateCnt++] = 17;
                  break;
               case 19:
                  if (curChar == 83)
                     jjstateSet[jjnewStateCnt++] = 18;
                  break;
               case 20:
                  if (curChar == 121 && kind > 23)
                     kind = 23;
                  break;
               case 21:
                  if (curChar == 111)
                     jjstateSet[jjnewStateCnt++] = 20;
                  break;
               case 22:
                  if (curChar == 108)
                     jjstateSet[jjnewStateCnt++] = 21;
                  break;
               case 23:
                  if (curChar == 112)
                     jjstateSet[jjnewStateCnt++] = 22;
                  break;
               case 24:
                  if (curChar == 101)
                     jjstateSet[jjnewStateCnt++] = 23;
                  break;
               case 25:
                  if (curChar == 100)
                     jjstateSet[jjnewStateCnt++] = 24;
                  break;
               case 26:
                  if (curChar == 89 && kind > 23)
                     kind = 23;
                  break;
               case 27:
                  if (curChar == 79)
                     jjstateSet[jjnewStateCnt++] = 26;
                  break;
               case 28:
                  if (curChar == 76)
                     jjstateSet[jjnewStateCnt++] = 27;
                  break;
               case 29:
                  if (curChar == 80)
                     jjstateSet[jjnewStateCnt++] = 28;
                  break;
               case 30:
                  if (curChar == 69)
                     jjstateSet[jjnewStateCnt++] = 29;
                  break;
               case 31:
                  if (curChar == 68)
                     jjstateSet[jjnewStateCnt++] = 30;
                  break;
               case 32:
                  if (curChar == 121 && kind > 25)
                     kind = 25;
                  break;
               case 33:
                  if (curChar == 110)
                     jjstateSet[jjnewStateCnt++] = 32;
                  break;
               case 34:
                  if (curChar == 97)
                     jjstateSet[jjnewStateCnt++] = 33;
                  break;
               case 36:
                  if (curChar == 115 && kind > 41)
                     kind = 41;
                  break;
               case 37:
                  if (curChar == 116)
                     jjstateSet[jjnewStateCnt++] = 36;
                  break;
               case 38:
                  if (curChar == 115)
                     jjstateSet[jjnewStateCnt++] = 37;
                  break;
               case 39:
                  if (curChar == 105)
                     jjstateSet[jjnewStateCnt++] = 38;
                  break;
               case 40:
                  if (curChar == 120)
                     jjstateSet[jjnewStateCnt++] = 39;
                  break;
               case 41:
                  if (curChar == 101)
                     jjstateSet[jjnewStateCnt++] = 40;
                  break;
               case 42:
                  if (curChar == 83 && kind > 41)
                     kind = 41;
                  break;
               case 43:
                  if (curChar == 84)
                     jjstateSet[jjnewStateCnt++] = 42;
                  break;
               case 44:
                  if (curChar == 83)
                     jjstateSet[jjnewStateCnt++] = 43;
                  break;
               case 45:
                  if (curChar == 73)
                     jjstateSet[jjnewStateCnt++] = 44;
                  break;
               case 46:
                  if (curChar == 88)
                     jjstateSet[jjnewStateCnt++] = 45;
                  break;
               case 47:
                  if (curChar == 69)
                     jjstateSet[jjnewStateCnt++] = 46;
                  break;
               case 48:
                  if (curChar == 108 && kind > 42)
                     kind = 42;
                  break;
               case 49:
                  if (curChar == 108)
                     jjstateSet[jjnewStateCnt++] = 48;
                  break;
               case 50:
                  if (curChar == 97)
                     jjstateSet[jjnewStateCnt++] = 49;
                  break;
               case 51:
                  if (curChar == 114)
                     jjstateSet[jjnewStateCnt++] = 50;
                  break;
               case 52:
                  if (curChar == 111)
                     jjstateSet[jjnewStateCnt++] = 51;
                  break;
               case 53:
                  if (curChar == 102)
                     jjstateSet[jjnewStateCnt++] = 52;
                  break;
               case 54:
                  if (curChar == 76 && kind > 42)
                     kind = 42;
                  break;
               case 55:
                  if (curChar == 76)
                     jjstateSet[jjnewStateCnt++] = 54;
                  break;
               case 56:
                  if (curChar == 65)
                     jjstateSet[jjnewStateCnt++] = 55;
                  break;
               case 57:
                  if (curChar == 82)
                     jjstateSet[jjnewStateCnt++] = 56;
                  break;
               case 59:
                  if (curChar == 70)
                     jjstateSet[jjnewStateCnt++] = 58;
                  break;
               case 61:
                  if ((0x7fffffe87fffffeL & l) == 0L)
                     break;
                  if (kind > 46)
                     kind = 46;
                  { jjCheckNAddTwoStates(62, 63); }
                  break;
               case 69:
                  { jjAddStates(2, 4); }
                  break;
               case 74:
                  { jjAddStates(7, 8); }
                  break;
               case 77:
                  if (curChar == 65)
                     { jjAddStates(5, 6); }
                  break;
               case 78:
                  if (curChar == 68 && kind > 12)
                     kind = 12;
                  break;
               case 79:
                  if (curChar == 78)
                     jjstateSet[jjnewStateCnt++] = 78;
                  break;
               case 80:
                  if (curChar == 89 && kind > 25)
                     kind = 25;
                  break;
               case 81:
                  if (curChar == 78)
                     jjstateSet[jjnewStateCnt++] = 80;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 83:
               case 65:
                  if ((jjbitVec0[i2] & l2) != 0L)
                     { jjCheckNAddTwoStates(65, 66); }
                  break;
               case 69:
                  if ((jjbitVec0[i2] & l2) != 0L)
                     { jjAddStates(2, 4); }
                  break;
               case 74:
                  if ((jjbitVec0[i2] & l2) != 0L)
                     { jjAddStates(7, 8); }
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStateCnt) == (startsAt = 82 - (jjnewStateCnt = startsAt)))
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { return curPos; }
   }
}

/** Token literal values. */
public static final String[] jjstrLiteralImages = {
"", null, null, null, "\151\156\164", "\142\157\157\154", 
"\163\164\162\151\156\147", "\163\145\164", "\53", "\55", "\52", "\57", null, "\116\117\124", "\117\122", 
"\75\75", null, "\74\75", "\76\75", "\74", "\76", "\124\162\165\145", 
"\106\141\154\163\145", null, "\151\156", null, "\173", "\175", "\50", "\51", "\133", "\135", "\42", 
"\137", "\72", "\73", "\54", "\166\141\162", "\72\75", "\174", null, null, null, null, 
null, null, null, null, };
protected Token jjFillToken()
{
   final Token t;
   final String curTokenImage;
   final int beginLine;
   final int endLine;
   final int beginColumn;
   final int endColumn;
   String im = jjstrLiteralImages[jjmatchedKind];
   curTokenImage = (im == null) ? input_stream.GetImage() : im;
   beginLine = input_stream.getBeginLine();
   beginColumn = input_stream.getBeginColumn();
   endLine = input_stream.getEndLine();
   endColumn = input_stream.getEndColumn();
   t = Token.newToken(jjmatchedKind, curTokenImage);

   t.beginLine = beginLine;
   t.endLine = endLine;
   t.beginColumn = beginColumn;
   t.endColumn = endColumn;

   return t;
}
static final int[] jjnextStates = {
   68, 73, 69, 70, 72, 79, 81, 74, 75, 
};

int curLexState = 0;
int defaultLexState = 0;
int jjnewStateCnt;
int jjround;
int jjmatchedPos;
int jjmatchedKind;

/** Get the next Token. */
public Token getNextToken() 
{
  Token matchedToken;
  int curPos = 0;

  EOFLoop :
  for (;;)
  {
   try
   {
      curChar = input_stream.BeginToken();
   }
   catch(Exception e)
   {
      jjmatchedKind = 0;
      jjmatchedPos = -1;
      matchedToken = jjFillToken();
      return matchedToken;
   }

   jjmatchedKind = 0x7fffffff;
   jjmatchedPos = 0;
   curPos = jjMoveStringLiteralDfa0_0();
   if (jjmatchedKind != 0x7fffffff)
   {
      if (jjmatchedPos + 1 < curPos)
         input_stream.backup(curPos - jjmatchedPos - 1);
      if ((jjtoToken[jjmatchedKind >> 6] & (1L << (jjmatchedKind & 077))) != 0L)
      {
         matchedToken = jjFillToken();
         return matchedToken;
      }
      else
      {
         continue EOFLoop;
      }
   }
   int error_line = input_stream.getEndLine();
   int error_column = input_stream.getEndColumn();
   String error_after = null;
   boolean EOFSeen = false;
   try { input_stream.readChar(); input_stream.backup(1); }
   catch (java.io.IOException e1) {
      EOFSeen = true;
      error_after = curPos <= 1 ? "" : input_stream.GetImage();
      if (curChar == '\n' || curChar == '\r') {
         error_line++;
         error_column = 0;
      }
      else
         error_column++;
   }
   if (!EOFSeen) {
      input_stream.backup(1);
      error_after = curPos <= 1 ? "" : input_stream.GetImage();
   }
   throw new TokenMgrError(EOFSeen, curLexState, error_line, error_column, error_after, curChar, TokenMgrError.LEXICAL_ERROR);
  }
}

void SkipLexicalActions(Token matchedToken)
{
   switch(jjmatchedKind)
   {
      default :
         break;
   }
}
void MoreLexicalActions()
{
   jjimageLen += (lengthOfMatch = jjmatchedPos + 1);
   switch(jjmatchedKind)
   {
      default :
         break;
   }
}
void TokenLexicalActions(Token matchedToken)
{
   switch(jjmatchedKind)
   {
      default :
         break;
   }
}
private void jjCheckNAdd(int state)
{
   if (jjrounds[state] != jjround)
   {
      jjstateSet[jjnewStateCnt++] = state;
      jjrounds[state] = jjround;
   }
}
private void jjAddStates(int start, int end)
{
   do {
      jjstateSet[jjnewStateCnt++] = jjnextStates[start];
   } while (start++ != end);
}
private void jjCheckNAddTwoStates(int state1, int state2)
{
   jjCheckNAdd(state1);
   jjCheckNAdd(state2);
}

private void jjCheckNAddStates(int start, int end)
{
   do {
      jjCheckNAdd(jjnextStates[start]);
   } while (start++ != end);
}

    /** Constructor. */
    public ParserTokenManager(SimpleCharStream stream){

      if (SimpleCharStream.staticFlag)
            throw new Error("ERROR: Cannot use a static CharStream class with a non-static lexical analyzer.");

    input_stream = stream;
  }

  /** Constructor. */
  public ParserTokenManager (SimpleCharStream stream, int lexState){
    ReInit(stream);
    SwitchTo(lexState);
  }

  /** Reinitialise parser. */
  
  public void ReInit(SimpleCharStream stream)
  {


    jjmatchedPos =
    jjnewStateCnt =
    0;
    curLexState = defaultLexState;
    input_stream = stream;
    ReInitRounds();
  }

  private void ReInitRounds()
  {
    int i;
    jjround = 0x80000001;
    for (i = 82; i-- > 0;)
      jjrounds[i] = 0x80000000;
  }

  /** Reinitialise parser. */
  public void ReInit(SimpleCharStream stream, int lexState)
  
  {
    ReInit(stream);
    SwitchTo(lexState);
  }

  /** Switch to specified lex state. */
  public void SwitchTo(int lexState)
  {
    if (lexState >= 1 || lexState < 0)
      throw new TokenMgrError("Error: Ignoring invalid lexical state : " + lexState + ". State unchanged.", TokenMgrError.INVALID_LEXICAL_STATE);
    else
      curLexState = lexState;
  }


/** Lexer state names. */
public static final String[] lexStateNames = {
   "DEFAULT",
};

/** Lex State array. */
public static final int[] jjnewLexState = {
   -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
   -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
};
static final long[] jjtoToken = {
   0xcffffffffff1L, 
};
static final long[] jjtoSkip = {
   0xeL, 
};
static final long[] jjtoSpecial = {
   0x0L, 
};
static final long[] jjtoMore = {
   0x0L, 
};
    protected SimpleCharStream  input_stream;

    private final int[] jjrounds = new int[82];
    private final int[] jjstateSet = new int[2 * 82];
    private final StringBuilder jjimage = new StringBuilder();
    private StringBuilder image = jjimage;
    private int jjimageLen;
    private int lengthOfMatch;
    protected int curChar;
}
