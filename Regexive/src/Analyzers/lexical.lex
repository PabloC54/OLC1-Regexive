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
    private ArrayList<ArrayList<String>> Symbols = new ArrayList<>();
    private ArrayList<ArrayList<String>> Errors = new ArrayList<>();

    private Symbol symbol(int line, int column, String lexeme, String token, int s){
        ArrayList<String> v = new ArrayList<>();
        v.add(String.valueOf(line));
        v.add(String.valueOf(column));
        v.add(token);
        v.add(lexeme);

        Symbols.add(v);

        return new Symbol(s, line, column, yytext());
    }

    private void Error(int line, int column, String lexem){    
        ArrayList<String> v = new ArrayList<>();
        v.add(String.valueOf(line));
        v.add(String.valueOf(column));
        v.add(lexem);

        Errors.add(v);
    }

    public ArrayList getSymbols(){
        return Symbols;
    }

    public ArrayList getErrors(){
        return Errors;
    }
%}

%init{
    yyline = 1;
    yychar = 1;
%init}

L=[a-zA-ZñÑ]
D=[0-9]
A=[\x20-\x2F\x3A-\x40\x5B-\x60\x7B-\x7D]
blanco=[\040\t]
set=(({L}|{D}|{A})\040*(,\040*({L}|{D}|{A})\040*)+)|(({L}|{D}|{A})\040*~\040*({L}|{D}|{A}))
llaveA="{"
llaveB="}"
dospuntos=":"
puntocoma=";"
flecha=-\040*>
concat="."
disy="|"
cerr_kleene="*"
cerr_positiva="+"
cerr_bool="?"
especial=(\\n)|(\\')|(\\\")
porcentajes="%%"{blanco}*\n+{blanco}*"%%"
conj="conj"
id={L}({L}|{D}|"_")*
string=[\"'](((\\\")|(\\n)|(\\\'))|[^\\\"\n])+[\"']
comentario=//(.|{blanco})*
comentario_multilinea=<!({blanco}|\n|[^!>])*!>

%%

{comentario}                {yychar=0;}
{comentario_multilinea}     {yychar=0;}
\n                          {yychar=0;}
{blanco}                    {}

{llaveA}        {return symbol(yyline, yychar, yytext(), "llaveA", sym.llaveA);}
{set}           {return symbol(yyline, yychar, yytext(), "set", sym.set);}
{llaveB}        {return symbol(yyline, yychar, yytext(), "llaveB", sym.llaveB);}
{dospuntos}     {return symbol(yyline, yychar, yytext(), "dospuntos", sym.dospuntos);}
{puntocoma}     {return symbol(yyline, yychar, yytext(), "puntocoma", sym.puntocoma);}
{flecha}        {return symbol(yyline, yychar, yytext(), "flecha", sym.flecha);}
{concat}        {return symbol(yyline, yychar, yytext(), "concat", sym.concat);}
{disy}          {return symbol(yyline, yychar, yytext(), "disy", sym.disy);}
{cerr_kleene}   {return symbol(yyline, yychar, yytext(), "cerr_kleene", sym.cerr_kleene);}
{cerr_positiva} {return symbol(yyline, yychar, yytext(), "cerr_positiva", sym.cerr_positiva);}
{cerr_bool}     {return symbol(yyline, yychar, yytext(), "cerr_bool", sym.cerr_bool);}
{especial}      {return symbol(yyline, yychar, yytext(), "especial", sym.especial);}
{porcentajes}   {return symbol(yyline, yychar, yytext(), "porcentajes", sym.porcentajes);}
{conj}          {return symbol(yyline, yychar, yytext(), "conj", sym.conj);}
{L}             {return symbol(yyline, yychar, yytext(), "L", sym.L);}
{id}            {return symbol(yyline, yychar, yytext(), "id", sym.id);}
{string}        {return symbol(yyline, yychar, "\\"+yytext().substring(0, yytext().length()-1)+"\\\"", "string", sym.string);}


. {
  Error(yyline, yychar, yytext());
}
