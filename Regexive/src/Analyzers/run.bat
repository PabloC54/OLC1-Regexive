cd "C:\Users\pablo\Documents\src\Universidad\-Compi1-Proyecto-1\Regexive\src\Analyzers"

del Parser.java
del Scanner.java
del sym.java

java JLex.Main lexical.lex
ren lexical.lex.java Scanner.java

java -jar Cup/java-cup-11b.jar -parser Parser syntactic.cup

pause