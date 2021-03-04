package Structs;

import java.util.ArrayList;
import Structs.NodeType.Types;

/**
 *
 * @author josef
 */
public class Node {

    public ArrayList<Integer> first;
    public ArrayList<Integer> last;
    public boolean anullable;

    public String lexeme;
    public Types type;
    int number;

    boolean accept;

    public Node left;
    public Node right;

    private ArrayList<Node> leaves;
    private ArrayList<ArrayList> table;

    public Node(String lexeme, Types type, int number, Node left, Node right, ArrayList<Node> leaves, ArrayList<ArrayList> table) {
        first = new ArrayList();
        last = new ArrayList();
        anullable = true;

        this.lexeme = lexeme;
        this.type = type;
        this.number = number;

        accept = this.lexeme.equals("#");

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
                case LEAVE:
                    anullable = false;
                    first.add(number);
                    last.add(number);
                    break;

                case CONCATENATION:
                    if (leftNode instanceof Node && rightNode instanceof Node) {
                        anullable = leftNode.anullable && rightNode.anullable;
                        first.addAll(leftNode.first);

                        if (leftNode.anullable) {
                            first.addAll((rightNode).first);
                        }
                        if (rightNode.anullable) {
                            last.addAll(leftNode.last);
                        }
                        last.addAll(rightNode.last);
                    }
                    break;

                case DISYUNCTION:
                    if (leftNode instanceof Node && rightNode instanceof Node) {
                        anullable = leftNode.anullable || rightNode.anullable;

                        first.addAll(leftNode.first);
                        first.addAll(rightNode.first);

                        last.addAll(leftNode.last);
                        last.addAll(rightNode.last);
                    }
                    break;

                case KLEENE_LOCK:
                    if (leftNode instanceof Node) {
                        anullable = true;
                        first.addAll(leftNode.first);
                        last.addAll(leftNode.last);
                    }
                    break;

                case POSITIVE_LOCK:
                    if (leftNode instanceof Node) {
                        anullable = false;
                        first.addAll(leftNode.first);
                        last.addAll(leftNode.last);
                    }
                    break;

                case BOOLEAN_LOCK:
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
                case CONCATENATION:
                    for (int item : leftFollow.last) {
                        Node node = getLeave(item, leaves);
                        FollowTable tabla = new FollowTable();
                        tabla.append(node.number, node.lexeme, rightFollow.first, table);
                    }
                    break;

                case KLEENE_LOCK:
                    for (int item : leftFollow.last) {
                        Node node = getLeave(item, leaves);
                        FollowTable tabla = new FollowTable();
                        tabla.append(node.number, node.lexeme, leftFollow.first, table);
                    }
                    break;

                case POSITIVE_LOCK:
                    for (int item : leftFollow.last) {
                        Node node = getLeave(item, leaves);
                        FollowTable tabla = new FollowTable();
                        tabla.append(node.number, node.lexeme, leftFollow.first, table);
                    }
                    break;

                case BOOLEAN_LOCK:
                    for (int item : leftFollow.last) {
                        Node node = getLeave(item, leaves);
                        FollowTable tabla = new FollowTable();
                        tabla.append(node.number, node.lexeme, leftFollow.first, table);
                    }
                    break;

                default:
                    break;
            }
        }

        return this;
    }

    private Node getLeave(int numLeave, ArrayList<Node> leaves) {
        for (Node item : leaves) {
            if (item.number == numLeave) {
                return item;
            }
        }
        return null;
    }
}
