/*
    Organizacion de Lenguajes y Compiladores 1 "A"
    José Puac
    Clase 4
    Método del Árbol
 */
package Structs;

import Structs.type.Types;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

/**
 *
 * @author josef
 */
public class Tree {

    Node root;

    ArrayList<Node> leaves = new ArrayList();
    ArrayList<ArrayList> table = new ArrayList();

    public Tree(ArrayList<String> expresion) {

        NumLeave numHoja = new NumLeave(expresion);
        Stack pila = new Stack();
        
        expresion.add(0, ".");
        expresion.add("#");

        Collections.reverse(expresion);

        expresion.forEach((character) -> {
            switch (character) {
                case "|":
                    Node lefto = (Node) pila.pop();
                    Node righto = (Node) pila.pop();

                    Node no = new Node(character, Types.OR, 0, lefto, righto, leaves, table);
                    pila.push(no);

                    break;
                case ".":
                    Node lefta = (Node) pila.pop();
                    Node righta = (Node) pila.pop();

                    Node na = new Node(character, Types.AND, 0, lefta, righta, leaves, table);
                    pila.push(na);

                    break;
                case "*":
                    Node onek = (Node) pila.pop();

                    Node nk = new Node(character, Types.KLEENE, 0, onek, null, leaves, table);
                    pila.push(nk);

                    break;
                case "+":
                    Node onep = (Node) pila.pop();

                    Node np = new Node(character, Types.POSITIVA, 0, onep, null, leaves, table);
                    pila.push(np);

                    break;
                case "?":
                    Node oneb = (Node) pila.pop();

                    Node nb = new Node(character, Types.BOOL, 0, oneb, null, leaves, table);
                    pila.push(nb);

                    break;
                default:
                    Node nd = new Node(character, Types.HOJA, numHoja.getNum(), null, null, leaves, table);
                    pila.push(nd);
                    Leave hoja = new Leave();
                    hoja.addLeave(nd, leaves);
                    break;
            }
        });
        this.root = (Node) pila.pop();
    }

    public Node getRoot() {
        return root;
    }
    
    public ArrayList<Node> getLeaves() {
        return leaves;
    }
    
    public ArrayList<ArrayList> getTable() {
        return table;
    }
}
