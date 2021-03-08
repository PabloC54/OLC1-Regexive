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
    private ArrayList<String> expression = new ArrayList();
    private ArrayList<ArrayList> table = new ArrayList();
    private Stack pile = new Stack();
    public String id;

    public Tree(ArrayList<String> expression) {
        this.expression = new ArrayList<>(expression);
        id = this.expression.remove(0);
        this.expression.add(0, "#");
        this.expression.add(0, ".");
        Collections.reverse(this.expression);

        int num_leave = 1;

        for (String symbol : this.expression) {
            Node left_node, right_node, node;

            switch (symbol) {
                case "|":
                    left_node = (Node) pile.pop();
                    right_node = (Node) pile.pop();
                    pile.push(new Node(symbol, Types.DISYUNCTION, 0, right_node, left_node, leaves, table));
                    break;

                case ".":
                    left_node = (Node) pile.pop();
                    right_node = (Node) pile.pop();
                    pile.push(new Node(symbol, Types.CONCATENATION, 0, right_node, left_node, leaves, table));
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
                    node = new Node(symbol, Types.LEAVE, num_leave, null, null, leaves, table);
                    pile.push(node);
                    leaves.add(node);
                    num_leave += 1;
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
