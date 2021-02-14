parser code
{;

    /* 
    comentario
    */

    public void syntax_error(Symbol s){
        System.out.println("# Error sintáctico en "+ s.left +", "+ s.right +". No se esperaba "+ s.value)
    }

    public void unrecovered_syntax_error(Symbol s){
        System.out.println("# Error sintáctico irrecuperable en "+ s.left +", "+ s.right +". No se esperaba "+ s.value)
    }


;}


terminal String PTCOMA;


non terminal S;
non terminal C;

//precedencia de menos a mas

precedence left MAS, MENOS;
precedence left MULT, DIV;



start with S;


expresion ::=
| parA expresion parB
| expresion:a MAS expresion:b { RESULT = a + b }
| expresion MENOS expresion
| expresion MULT expresion
| expresion DIV expresion
| ENTERO
| DECIMAL;





