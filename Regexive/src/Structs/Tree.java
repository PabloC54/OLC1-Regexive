package Structs;

import Structs.NodeType.Types;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

/**
 *
 * @author josef
 */
public class Tree {

    Node root;

    private ArrayList<Node> leaves = new ArrayList();
    private ArrayList<ArrayList> table = new ArrayList();
    private Stack pile = new Stack();
    public String id;

    public Tree(ArrayList<String> expression) {
        id = expression.remove(0);
        expression.add(0, ".");
        expression.add("#");
        Collections.reverse(expression);

        int num_leave = 1;
        for (String str : expression) {
            if (!str.equals(".") && !str.equals("|") && !str.equals("*") && !str.equals("+") && !str.equals("?")) {
                num_leave += 1;
            }
        }

        for (String symbol : expression) {
            Node left_node, right_node, node;

            switch (symbol) {
                case "|":
                    left_node = (Node) pile.pop();
                    right_node = (Node) pile.pop();
                    pile.push(new Node(symbol, Types.DISYUNCTION, 0, left_node, right_node, leaves, table));
                    break;

                case ".":
                    left_node = (Node) pile.pop();
                    right_node = (Node) pile.pop();
                    pile.push(new Node(symbol, Types.CONCATENATION, 0, left_node, right_node, leaves, table));
                    break;

                case "*":
                    node = (Node) pile.pop();
                    pile.push(new Node(symbol, Types.KLEENE_LOCK, 0, node, null, leaves, table));
                    break;

                case "+":
                    node = (Node) pile.pop();
                    pile.push(new Node(symbol, Types.POSITIVE_LOCK, 0, node, null, leaves, table));
                    break;

                case "?":
                    node = (Node) pile.pop();
                    pile.push(new Node(symbol, Types.BOOLEAN_LOCK, 0, node, null, leaves, table));
                    break;

                default:
                    num_leave -= 1;
                    node = new Node(symbol, Types.LEAVE, num_leave, null, null, leaves, table);
                    pile.push(node);
                    leaves.add(node);
                    break;
            }
        }

        root = (Node) pile.pop();
        root.getNode();
        root.follow();
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
