
//----------------------------------------------------
// The following code was generated by CUP v0.11b 20160615 (GIT 4ac7450)
//----------------------------------------------------

package Analyzers;

import java_cup.runtime.*;
import java.util.ArrayList;

/** CUP v0.11b 20160615 (GIT 4ac7450) generated parser.
  */
@SuppressWarnings({"rawtypes"})
public class Parser extends java_cup.runtime.lr_parser {

 public final Class getSymbolContainer() {
    return sym.class;
}

  /** Default constructor. */
  @Deprecated
  public Parser() {super();}

  /** Constructor which sets the default scanner. */
  @Deprecated
  public Parser(java_cup.runtime.Scanner s) {super(s);}

  /** Constructor which sets the default scanner. */
  public Parser(java_cup.runtime.Scanner s, java_cup.runtime.SymbolFactory sf) {super(s,sf);}

  /** Production table. */
  protected static final short _production_table[][] = 
    unpackFromStrings(new String[] {
    "\000\030\000\002\002\004\000\002\002\007\000\002\003" +
    "\004\000\002\003\004\000\002\003\002\000\002\004\004" +
    "\000\002\004\004\000\002\004\002\000\002\005\010\000" +
    "\002\010\003\000\002\010\003\000\002\010\003\000\002" +
    "\006\006\000\002\011\005\000\002\011\005\000\002\011" +
    "\004\000\002\011\004\000\002\011\004\000\002\011\003" +
    "\000\002\012\003\000\002\012\003\000\002\012\005\000" +
    "\002\012\003\000\002\007\006" });

  /** Access to production table. */
  public short[][] production_table() {return _production_table;}

  /** Parse-action table. */
  protected static final short[][] _action_table = 
    unpackFromStrings(new String[] {
    "\000\064\000\004\010\004\001\002\000\010\023\ufffd\024" +
    "\013\025\012\001\002\000\004\002\006\001\002\000\004" +
    "\002\001\001\002\000\010\023\ufffd\024\013\025\012\001" +
    "\002\000\004\023\053\001\002\000\010\023\ufffd\024\013" +
    "\025\012\001\002\000\004\014\024\001\002\000\004\012" +
    "\014\001\002\000\004\025\015\001\002\000\004\014\016" +
    "\001\002\000\010\005\021\006\017\007\020\001\002\000" +
    "\004\013\ufff7\001\002\000\004\013\ufff6\001\002\000\004" +
    "\013\ufff8\001\002\000\004\013\023\001\002\000\012\011" +
    "\ufff9\023\ufff9\024\ufff9\025\ufff9\001\002\000\024\004\034" +
    "\010\025\015\036\016\027\017\026\020\037\021\032\022" +
    "\030\026\031\001\002\000\004\025\050\001\002\000\024" +
    "\004\034\010\025\015\036\016\027\017\026\020\037\021" +
    "\032\022\030\026\031\001\002\000\024\004\034\010\025" +
    "\015\036\016\027\017\026\020\037\021\032\022\030\026" +
    "\031\001\002\000\026\004\uffeb\010\uffeb\013\uffeb\015\uffeb" +
    "\016\uffeb\017\uffeb\020\uffeb\021\uffeb\022\uffeb\026\uffeb\001" +
    "\002\000\026\004\uffed\010\uffed\013\uffed\015\uffed\016\uffed" +
    "\017\uffed\020\uffed\021\uffed\022\uffed\026\uffed\001\002\000" +
    "\024\004\034\010\025\015\036\016\027\017\026\020\037" +
    "\021\032\022\030\026\031\001\002\000\004\013\043\001" +
    "\002\000\026\004\uffee\010\uffee\013\uffee\015\uffee\016\uffee" +
    "\017\uffee\020\uffee\021\uffee\022\uffee\026\uffee\001\002\000" +
    "\026\004\uffef\010\uffef\013\uffef\015\uffef\016\uffef\017\uffef" +
    "\020\uffef\021\uffef\022\uffef\026\uffef\001\002\000\024\004" +
    "\034\010\025\015\036\016\027\017\026\020\037\021\032" +
    "\022\030\026\031\001\002\000\024\004\034\010\025\015" +
    "\036\016\027\017\026\020\037\021\032\022\030\026\031" +
    "\001\002\000\026\004\ufff1\010\ufff1\013\ufff1\015\ufff1\016" +
    "\ufff1\017\ufff1\020\ufff1\021\ufff1\022\ufff1\026\ufff1\001\002" +
    "\000\024\004\034\010\025\015\036\016\027\017\026\020" +
    "\037\021\032\022\030\026\031\001\002\000\026\004\ufff4" +
    "\010\ufff4\013\ufff4\015\ufff4\016\ufff4\017\ufff4\020\ufff4\021" +
    "\ufff4\022\ufff4\026\ufff4\001\002\000\010\023\ufff5\024\ufff5" +
    "\025\ufff5\001\002\000\026\004\ufff0\010\ufff0\013\ufff0\015" +
    "\ufff0\016\ufff0\017\ufff0\020\ufff0\021\ufff0\022\ufff0\026\ufff0" +
    "\001\002\000\024\004\034\010\025\015\036\016\027\017" +
    "\026\020\037\021\032\022\030\026\031\001\002\000\026" +
    "\004\ufff3\010\ufff3\013\ufff3\015\ufff3\016\ufff3\017\ufff3\020" +
    "\ufff3\021\ufff3\022\ufff3\026\ufff3\001\002\000\026\004\ufff2" +
    "\010\ufff2\013\ufff2\015\ufff2\016\ufff2\017\ufff2\020\ufff2\021" +
    "\ufff2\022\ufff2\026\ufff2\001\002\000\004\011\051\001\002" +
    "\000\026\004\uffec\010\uffec\013\uffec\015\uffec\016\uffec\017" +
    "\uffec\020\uffec\021\uffec\022\uffec\026\uffec\001\002\000\004" +
    "\023\uffff\001\002\000\010\011\ufffa\024\013\025\057\001" +
    "\002\000\010\011\ufffa\024\013\025\057\001\002\000\010" +
    "\011\ufffa\024\013\025\057\001\002\000\004\011\063\001" +
    "\002\000\004\012\060\001\002\000\004\026\061\001\002" +
    "\000\004\013\062\001\002\000\010\011\uffea\024\uffea\025" +
    "\uffea\001\002\000\004\002\000\001\002\000\004\011\ufffb" +
    "\001\002\000\004\011\ufffc\001\002\000\004\023\ufffe\001" +
    "\002" });

  /** Access to parse-action table. */
  public short[][] action_table() {return _action_table;}

  /** <code>reduce_goto</code> table. */
  protected static final short[][] _reduce_table = 
    unpackFromStrings(new String[] {
    "\000\064\000\004\002\004\001\001\000\010\003\007\005" +
    "\010\006\006\001\001\000\002\001\001\000\002\001\001" +
    "\000\010\003\065\005\010\006\006\001\001\000\002\001" +
    "\001\000\010\003\051\005\010\006\006\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\002\001" +
    "\001\000\004\010\021\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\002\001" +
    "\001\000\006\011\032\012\034\001\001\000\002\001\001" +
    "\000\006\011\046\012\034\001\001\000\006\011\044\012" +
    "\034\001\001\000\002\001\001\000\002\001\001\000\006" +
    "\011\043\012\034\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001\000\006\011\040\012\034\001\001" +
    "\000\006\011\037\012\034\001\001\000\002\001\001\000" +
    "\006\011\041\012\034\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\006\011\045\012\034\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\010\004\055\005" +
    "\053\007\054\001\001\000\010\004\064\005\053\007\054" +
    "\001\001\000\010\004\063\005\053\007\054\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001" });

  /** Access to <code>reduce_goto</code> table. */
  public short[][] reduce_table() {return _reduce_table;}

  /** Instance of action encapsulation class. */
  protected CUP$Parser$actions action_obj;

  /** Action encapsulation object initializer. */
  protected void init_actions()
    {
      action_obj = new CUP$Parser$actions(this);
    }

  /** Invoke a user supplied parse action. */
  public java_cup.runtime.Symbol do_action(
    int                        act_num,
    java_cup.runtime.lr_parser parser,
    java.util.Stack            stack,
    int                        top)
    throws java.lang.Exception
  {
    /* call code in generated class */
    return action_obj.CUP$Parser$do_action(act_num, parser, stack, top);
  }

  /** Indicates start state. */
  public int start_state() {return 0;}
  /** Indicates start production. */
  public int start_production() {return 0;}

  /** <code>EOF</code> Symbol index. */
  public int EOF_sym() {return 0;}

  /** <code>error</code> Symbol index. */
  public int error_sym() {return 1;}



  /*
    ESTRUCTURAS PARA EXPRESIONES REGULARES (�?RBOLES), 
  */

    private ArrayList<ArrayList<String>> Conjuntos = new ArrayList<>();
    private ArrayList<ArrayList<String>> Expresiones = new ArrayList<>();
    private ArrayList<ArrayList<String>> Comparaciones = new ArrayList<>();
    private ArrayList<ArrayList<String>> Errores = new ArrayList<>();
        
    private ArrayList<String> expresion = new ArrayList<>();

    public ArrayList getConjuntos(){
        return Conjuntos;
    }  

    public ArrayList getExpresiones(){
        return Expresiones;
    }  

    public ArrayList getComparaciones(){
        return Comparaciones;
    }  

    public ArrayList getErrores(){
        return Errores;
    }   

    private void Conjunto(String id, String definicion){
        ArrayList<String> v = new ArrayList<>();
        v.add(id);
        v.add(definicion);
        Conjuntos.add(v);  
    }

    private void Expresion(String id){
        expresion.add(0, id);
        Expresiones.add(expresion);
        expresion = new ArrayList<>();
    }

    private void Termino(String operacion){
        expresion.add(0, operacion);
    }

    private void Termino(String a, String b){
        expresion.add(0, a);
        expresion.add(0, b);
    }

    private void Comparacion(String expresion, String string){
        ArrayList<String> v = new ArrayList<>();
        v.add(expresion);
        v.add(string);
        Comparaciones.add(v);
    }

    public void syntax_error(Symbol s){ 
        ArrayList<String> v = new ArrayList<>();
        v.add(String.valueOf(s.left));
        v.add(String.valueOf(s.right));
        v.add(String.valueOf(s.value));
        Errores.add(v);
    } 

    public void unrecovered_syntax_error(Symbol s) throws java.lang.Exception{ 
        System.out.println("Error síntactico irrecuperable en la Línea " + 
        (s.left)+ " Columna "+s.right+". Componente " + s.value + 
        " no reconocido."); 
    }


/** Cup generated class to encapsulate user supplied action code.*/
@SuppressWarnings({"rawtypes", "unchecked", "unused"})
class CUP$Parser$actions {
  private final Parser parser;

  /** Constructor */
  CUP$Parser$actions(Parser parser) {
    this.parser = parser;
  }

  /** Method 0 with the actual generated action code for actions 0 to 300. */
  public final java_cup.runtime.Symbol CUP$Parser$do_action_part00000000(
    int                        CUP$Parser$act_num,
    java_cup.runtime.lr_parser CUP$Parser$parser,
    java.util.Stack            CUP$Parser$stack,
    int                        CUP$Parser$top)
    throws java.lang.Exception
    {
      /* Symbol object for return from actions */
      java_cup.runtime.Symbol CUP$Parser$result;

      /* select the action based on the action number */
      switch (CUP$Parser$act_num)
        {
          /*. . . . . . . . . . . . . . . . . . . .*/
          case 0: // $START ::= INI EOF 
            {
              Object RESULT =null;
		int start_valleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).left;
		int start_valright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).right;
		Object start_val = (Object)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-1)).value;
		RESULT = start_val;
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("$START",0, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          /* ACCEPT */
          CUP$Parser$parser.done_parsing();
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 1: // INI ::= llaveA S porcentajes C llaveB 
            {
              Object RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("INI",0, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-4)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 2: // S ::= DEFINICION_CONJUNTO S 
            {
              Object RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("S",1, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 3: // S ::= DEFINICION_EXPRESION_REGULAR S 
            {
              Object RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("S",1, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 4: // S ::= 
            {
              Object RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("S",1, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 5: // C ::= DEFINICION_CONJUNTO C 
            {
              Object RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("C",2, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 6: // C ::= COMPARACION C 
            {
              Object RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("C",2, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 7: // C ::= 
            {
              Object RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("C",2, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 8: // DEFINICION_CONJUNTO ::= conj dospuntos id flecha CONJUNTO puntocoma 
            {
              Object RESULT =null;
		int aleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-3)).left;
		int aright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-3)).right;
		String a = (String)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-3)).value;
		int bleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).left;
		int bright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).right;
		Object b = (Object)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-1)).value;
		Conjunto(a, (String) b);
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("DEFINICION_CONJUNTO",3, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-5)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 9: // CONJUNTO ::= L_set 
            {
              Object RESULT =null;
		int aleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int aright = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		String a = (String)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		RESULT=a;
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("CONJUNTO",6, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 10: // CONJUNTO ::= D_set 
            {
              Object RESULT =null;
		int aleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int aright = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		String a = (String)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		RESULT=a;
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("CONJUNTO",6, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 11: // CONJUNTO ::= A_set 
            {
              Object RESULT =null;
		int aleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int aright = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		String a = (String)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		RESULT=a;
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("CONJUNTO",6, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 12: // DEFINICION_EXPRESION_REGULAR ::= id flecha EXPRESION_REGULAR puntocoma 
            {
              Object RESULT =null;
		int aleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-3)).left;
		int aright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-3)).right;
		String a = (String)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-3)).value;
		Expresion(a);
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("DEFINICION_EXPRESION_REGULAR",4, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-3)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 13: // EXPRESION_REGULAR ::= concat EXPRESION_REGULAR EXPRESION_REGULAR 
            {
              Object RESULT =null;
		Termino(".");
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("EXPRESION_REGULAR",7, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 14: // EXPRESION_REGULAR ::= disy EXPRESION_REGULAR EXPRESION_REGULAR 
            {
              Object RESULT =null;
		Termino("|");
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("EXPRESION_REGULAR",7, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 15: // EXPRESION_REGULAR ::= cerr_kleene EXPRESION_REGULAR 
            {
              Object RESULT =null;
		Termino("*");
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("EXPRESION_REGULAR",7, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 16: // EXPRESION_REGULAR ::= cerr_positiva EXPRESION_REGULAR 
            {
              Object RESULT =null;
		Termino("+");
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("EXPRESION_REGULAR",7, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 17: // EXPRESION_REGULAR ::= cerr_bool EXPRESION_REGULAR 
            {
              Object RESULT =null;
		Termino("?");
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("EXPRESION_REGULAR",7, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 18: // EXPRESION_REGULAR ::= TERMINO 
            {
              Object RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("EXPRESION_REGULAR",7, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 19: // TERMINO ::= L 
            {
              Object RESULT =null;
		int aleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int aright = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		String a = (String)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		Termino(a);
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("TERMINO",8, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 20: // TERMINO ::= string 
            {
              Object RESULT =null;
		int aleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int aright = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		String a = (String)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		Termino(a);
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("TERMINO",8, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 21: // TERMINO ::= llaveA id llaveB 
            {
              Object RESULT =null;
		int aleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).left;
		int aright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).right;
		String a = (String)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-1)).value;
		Termino(a);
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("TERMINO",8, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 22: // TERMINO ::= especial 
            {
              Object RESULT =null;
		int aleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int aright = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		String a = (String)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		Termino(a);
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("TERMINO",8, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 23: // COMPARACION ::= id dospuntos string puntocoma 
            {
              Object RESULT =null;
		int aleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-3)).left;
		int aright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-3)).right;
		String a = (String)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-3)).value;
		int bleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).left;
		int bright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).right;
		String b = (String)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-1)).value;
		Comparacion(a, (String) b);
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("COMPARACION",5, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-3)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /* . . . . . .*/
          default:
            throw new Exception(
               "Invalid action number "+CUP$Parser$act_num+"found in internal parse table");

        }
    } /* end of method */

  /** Method splitting the generated action code into several parts. */
  public final java_cup.runtime.Symbol CUP$Parser$do_action(
    int                        CUP$Parser$act_num,
    java_cup.runtime.lr_parser CUP$Parser$parser,
    java.util.Stack            CUP$Parser$stack,
    int                        CUP$Parser$top)
    throws java.lang.Exception
    {
              return CUP$Parser$do_action_part00000000(
                               CUP$Parser$act_num,
                               CUP$Parser$parser,
                               CUP$Parser$stack,
                               CUP$Parser$top);
    }
}

}
