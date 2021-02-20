package Analyzers;
import java_cup.runtime.Symbol;
import java.util.Vector;

%%
%class Scanner
%public
%line
%char
%unicode
%ignorecase
%cup
%type Symbol

%{

    Symbol Simbolo(int linea, int columna, String lexema, String token, int s){

    Vector<String> v = new Vector<>();
    v.add(String.valueOf(linea));
    v.add(String.valueOf(columna));
    v.add(token);
    v.add(lexema);

    Simbolos.add(v);

    return new Symbol(s, linea, columna, yytext());
  }

  void ErrorLexico(int linea, int columna, String lexema){
    
    Vector<String> v = new Vector<>();
    v.add(String.valueOf(linea));
    v.add(String.valueOf(columna));
    v.add(lexema);

    Errores.add(v);

    System.out.println("[#Error Léxico] ["+linea+", "+columna+"]  No se esperaba '"+lexema+"'.");
  }

    Vector getSimbolos(){
        return Simbolos;
    }

    Vector getErrores(){
        return Errores;
    }

%}


%init{
  yyline = 1;
  yychar = 1;
  Vector<Vector<String>> Simbolos = new Vector<>();
  Vector<Vector<String>> Errores = new Vector<>();
%init}

L=[a-zA-ZñÑ]
D=[0-9]
A=[\x20-\x2F\x3A-\x40\x5B-\x7D]
blanco=[\040\t]
L_set=({L}(,{L})+)|([a-zñ]~[a-zñ])|([A-ZÑ]~[A-ZÑ])
D_set=({D}(,{D})+)|({D}~{D})
A_set=({A}(,{A})+)|({A}~{A})
llaveA="{"
llaveB="}"
dospuntos=":"
puntocoma=";"
flecha="->"
concat="."
disy="|"
cerr_kleene="*"
cerr_positiva="+"
cerr_bool="?"
especial=(\\n)|(\\')|(\\\")
porcentajes="%%"
conj="conj"
id={L}({L}|{D}|"_")*
string=\"(.|{blanco})*\"
comentario=//(.|{blanco})*\n
comentario_multilinea=<!(.|{blanco}|\n)*!>

%%

{comentario}                {}
{comentario_multilinea}     {}

{llaveA}        {return Simbolo(yyline, yychar, yytext(), "llaveA", sym.llaveA);}
{llaveB}        {return Simbolo(yyline, yychar, yytext(), "llaveB", sym.llaveB);}
{L_set}         {return Simbolo(yyline, yychar, yytext(), "L_set", sym.L_set);}
{D_set}         {return Simbolo(yyline, yychar, yytext(), "D_set", sym.D_set);}
{A_set}         {return Simbolo(yyline, yychar, yytext(), "A_set", sym.A_set);}
{dospuntos}     {return Simbolo(yyline, yychar, yytext(), "dospuntos", sym.dospuntos);}
{puntocoma}     {return Simbolo(yyline, yychar, yytext(), "puntocoma", sym.puntocoma);}
{flecha}        {return Simbolo(yyline, yychar, yytext(), "flecha", sym.flecha);}
{concat}        {return Simbolo(yyline, yychar, yytext(), "concat", sym.concat);}
{disy}          {return Simbolo(yyline, yychar, yytext(), "disy", sym.disy);}
{cerr_kleene}   {return Simbolo(yyline, yychar, yytext(), "cerr_kleene", sym.cerr_kleene);}
{cerr_positiva} {return Simbolo(yyline, yychar, yytext(), "cerr_positiva", sym.cerr_positiva);}
{cerr_bool}     {return Simbolo(yyline, yychar, yytext(), "cerr_bool", sym.cerr_bool);}
{especial}      {return Simbolo(yyline, yychar, yytext(), "especial", sym.especial);}
{porcentajes}   {return Simbolo(yyline, yychar, yytext(), "porcentajes", sym.porcentajes);}
{conj}          {return Simbolo(yyline, yychar, yytext(), "conj", sym.conj);}
{L}             {return Simbolo(yyline, yychar, yytext(), "L", sym.L);}
{id}            {return Simbolo(yyline, yychar, yytext(), "id", sym.id);}
{string}        {return Simbolo(yyline, yychar, yytext(), "string", sym.string);}

\n {yychar=1;}

{blanco}        {} 
{D}             {}

. {
  ErrorLexico(yyline, yychar, yytext());
}

