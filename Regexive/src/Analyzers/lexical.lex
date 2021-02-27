package Analyzers;
import java_cup.runtime.Symbol;
import java.util.ArrayList;

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

    private ArrayList<ArrayList<String>> Simbolos = new ArrayList<>();
    private ArrayList<ArrayList<String>> Errores = new ArrayList<>();

    Symbol Simbolo(int linea, int columna, String lexema, String token, int s){
        ArrayList<String> v = new ArrayList<>();
        v.add(String.valueOf(linea));
        v.add(String.valueOf(columna));
        v.add(token);
        v.add(lexema);

        Simbolos.add(v);

        return new Symbol(s, linea, columna, yytext());
    }

    void ErrorLexico(int linea, int columna, String lexema){    
        ArrayList<String> v = new ArrayList<>();
        v.add(String.valueOf(linea));
        v.add(String.valueOf(columna));
        v.add(lexema);

        Errores.add(v);
    }

    public ArrayList getSimbolos(){
        return Simbolos;
    }

    public ArrayList getErrores(){
        return Errores;
    }
%}

%init{
    yyline = 1;
    yychar = 0;
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
porcentajes="%%"{blanco}*\n+{blanco}*"%%"
conj="conj"
id={L}({L}|{D}|"_")*
string=\"(.|{blanco})*\"
comentario=//(.|{blanco})*\n
comentario_multilinea=<!(.|{blanco}|\n)*!>

%%

{comentario}                {yychar=0;}
{comentario_multilinea}     {yychar=0;}
\n                          {yychar=0;}
{blanco}                    {}

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


. {
  ErrorLexico(yyline, yychar, yytext());
}
