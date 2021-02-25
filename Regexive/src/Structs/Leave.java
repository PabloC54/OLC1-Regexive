
package Structs;

import java.util.ArrayList;

/**
 *
 * @author josef
 */
public class Leave {
    
    public void addLeave(Node nodo, ArrayList<Node> leaves){
        leaves.add(nodo);
    }
    
    public Node getLeave(int numLeave, ArrayList<Node> leaves){
        for (Node item : leaves) {
            if(item.number == numLeave) return item;
        }
        return null;
    }
    
    public boolean isAccept(int numLeave, ArrayList<Node> leaves){
        for (Node item : leaves) {
            if(item.number == numLeave) return item.accept;
        }
        return false;
    }
}
