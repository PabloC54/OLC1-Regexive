cd "C:\Users\pablo\Documents\src\Universidad\-Compi1-Proyecto-1\Regexive\src\Analyzers"

javac JLex/Main.java
java JLex.Main lexical.lex
java -jar Cup/java-cup-11b.jar -parser Parser syntactic.cup 
pause