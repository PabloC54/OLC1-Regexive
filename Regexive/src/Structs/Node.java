
package Structs;

import java.util.ArrayList;
import Structs.type.Types;

/**
 *
 * @author josef
 */
public class Node {
    
    public ArrayList<Integer> first;
    public ArrayList<Integer> last;
    public boolean anullable;

    public String lexeme;
    Types type;
    int number;

    boolean accept;

    public Node left;
    public Node right;

    ArrayList<Node> leaves;
    ArrayList<ArrayList> table;

    public Node(String lexeme, Types type, int number, Node left, Node right, ArrayList<Node> leaves, ArrayList<ArrayList> table) {
        first = new ArrayList();
        last = new ArrayList();
        anullable = true;

        this.lexeme = lexeme;
        this.type = type;
        this.number = number;

        accept = "#".equals(this.lexeme);

        this.left = left;
        this.right = right;

        this.leaves = leaves;
        this.table = table;
    }

    public Node getNode() {
        Node leftNode = left instanceof Node ? left.getNode() : null;
        Node rightNode = right instanceof Node ? right.getNode() : null;

        if (null != type) {
            switch (type) {
                case HOJA:
                    anullable = false;
                    first.add(number);
                    last.add(number);
                    break;
                case AND:
                    if (leftNode instanceof Node && rightNode instanceof Node) {
                        anullable = leftNode.anullable && rightNode.anullable;

                        first.addAll(leftNode.first);
                        if (leftNode.anullable) {
                            first.addAll(( rightNode).first);
                        }

                        if (rightNode.anullable) {
                            last.addAll(leftNode.last);
                        }
                        last.addAll(rightNode.last);
                    }
                    break;
                case OR:
                    if (leftNode instanceof Node && rightNode instanceof Node) {
                        anullable = leftNode.anullable || rightNode.anullable;

                        first.addAll(leftNode.first);
                        first.addAll(rightNode.first);

                        last.addAll(leftNode.last);
                        last.addAll(rightNode.last);
                    }
                    break;
                case KLEENE:
                    if (leftNode instanceof Node) {
                        anullable = true;
                        first.addAll(leftNode.first);
                        last.addAll(leftNode.last);
                    }
                    break;
                case POSITIVA:
                    if (leftNode instanceof Node) {
                        anullable = false;
                        first.addAll(leftNode.first);
                        last.addAll(leftNode.last);
                    }
                    break;
                case BOOL:
                    if (leftNode instanceof Node) {
                        anullable = true;
                        first.addAll(leftNode.first);
                        last.addAll(leftNode.last);
                    }
                    break;
                default:
                    break;
            }
        }
        return this;
    }

    public Node follow() {
        Node leftFollow = left instanceof Node ? left.follow() : null;
        Node rightFollow = right instanceof Node ? right.follow() : null;

        if (null != type) {
            switch (type) {
                case AND:
                    for (int item : leftFollow.last) {
                        Leave hoja = new Leave();
                        Node nodo = hoja.getLeave(item, leaves);
                        FollowTable tabla = new FollowTable();
                        tabla.append(nodo.number, nodo.lexeme, rightFollow.first, table);
                    }
                    break;
                case KLEENE:
                    for (int item : leftFollow.last) {
                        Leave hoja = new Leave();
                        Node nodo = hoja.getLeave(item, leaves);
                        FollowTable tabla = new FollowTable();
                        tabla.append(nodo.number, nodo.lexeme, leftFollow.first, table);
                    }
                    break;
                case POSITIVA:
                    for (int item : leftFollow.last) {
                        Leave hoja = new Leave();
                        Node nodo = hoja.getLeave(item, leaves);
                        FollowTable tabla = new FollowTable();
                        tabla.append(nodo.number, nodo.lexeme, leftFollow.first, table);
                    }
                    break;
                case BOOL:
                    for (int item : leftFollow.last) {
                        Leave hoja = new Leave();
                        Node nodo = hoja.getLeave(item, leaves);
                        FollowTable tabla = new FollowTable();
                        tabla.append(nodo.number, nodo.lexeme, leftFollow.first, table);
                    }
                    break;
                default:
                    break;
            }
        }

        return this;
    }

}
