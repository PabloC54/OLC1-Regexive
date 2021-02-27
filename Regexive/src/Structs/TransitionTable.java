package Structs;

import java.util.ArrayList;

/**
 *
 * @author josef
 */
public class TransitionTable {

    public ArrayList<ArrayList> states = new ArrayList();
    public int cont;

    public TransitionTable(Node root, ArrayList tabla, ArrayList<Node> leaves) {
        ArrayList data = new ArrayList();
        data.add("S0");
        data.add(root.first);
        data.add(new ArrayList());
        data.add(false);

        states.add(data);
        this.cont = 1;

        for (int i = 0; i < states.size(); i++) {
            ArrayList state = states.get(i);
            ArrayList<Integer> elements = (ArrayList) state.get(1);

            // TODO  Aqui se encuentra el bug
            for (int leave : elements) {
                FollowTable sigTabla = new FollowTable();
                ArrayList lexemeNext = (ArrayList) sigTabla.next(leave, tabla).clone();

                boolean exists = false;
                String found = "";

                for (ArrayList e : states) {
                    if (e.get(1).equals(lexemeNext.get(1))) {
                        exists = true;
                        found = (String) e.get(0);
                        break;
                    }
                }

                if (!exists) {
                    Leave hojas = new Leave();
                    if (hojas.isAccept(leave, leaves)) {
                        state.set(3, true);
                    }
                    if (lexemeNext.get(0) == "") {
                        continue;
                    }

                    ArrayList nuevo = new ArrayList();
                    nuevo.add("S" + cont);
                    nuevo.add(lexemeNext.get(1));
                    nuevo.add(new ArrayList());
                    nuevo.add(false);

                    Transition trans = new Transition(state.get(0) + "", lexemeNext.get(0) + "", nuevo.get(0) + "");
                    ((ArrayList) state.get(2)).add(trans);

                    cont += 1;
                    states.add(nuevo);

                } else {
                    Leave hojas = new Leave();
                    if (hojas.isAccept(leave, leaves)) {
                        state.set(3, true);
                    }

                    boolean trans_exist = false;

                    for (Object trans : (ArrayList) state.get(2)) {
                        Transition t = (Transition) trans;
                        if (t.compare(state.get(0) + "", lexemeNext.get(0) + "")) {
                            trans_exist = true;
                            break;
                        }
                    }

                    if (!trans_exist) {
                        Transition trans = new Transition(state.get(0) + "", lexemeNext.get(0) + "", found + "");
                        ((ArrayList) state.get(2)).add(trans);
                    }
                }
            }
        }
    }
}
