/*
 * The MIT License
 *
 * Copyright 2021 pablo.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package Structs;

import java.util.ArrayList;

/**
 *
 * @author pablo
 */
public class AFND {

    private State initial_state;
    private ArrayList<Transition> transitions;

    private final String epsilon = "ε";

    public AFND(Tree tree) {
        this.initial_state = new State();

        transitions = Buscar(tree.getRoot().left);
        transitions.isEmpty();
    }

    private int num = 0;

    private int getNum() {
        num += 1;
        return num - 1;
    }

    private void setNum(int num) {
        this.num = num;
    }

    private ArrayList<Transition> Buscar(Node node) {

        ArrayList<Transition> t1 = new ArrayList<>(), t2 = new ArrayList<>(), t3 = new ArrayList<>();

        if (node.left != null) {
            t1 = Buscar(node.left);
        }

        if (node.right != null) {
            t2 = Buscar(node.right);
        }

        switch (node.type) {
            case CONCATENATION:
                int max = 0;
                for (Transition t : t1) {
                    if (t.getFinalState() > max) {
                        max = t.getFinalState();
                    }
                }

                for (Transition t : t2) {
                    t.setStates(t.getInitialState() + max, t.getFinalState() + max);
                }

                t3.addAll(t1);
                t3.addAll(t2);
                return t3;

            case DISYUNCTION:

                int min1 = 1000,
                 min2 = 1000,
                 max1 = 0,
                 max2 = 0;

                for (Transition t : t1) {
                    t.setStates(t.getInitialState() + 1, t.getFinalState() + 1);
                }
                for (Transition t : t1) {
                    if (t.getInitialState() < min1) {
                        min1 = t.getInitialState();
                    }
                    if (t.getFinalState() > max1) {
                        max1 = t.getFinalState();
                    }
                }

                t3.add(new Transition(min1 - 1, epsilon, min1));

                for (Transition t : t2) {
                    t.setStates(t.getInitialState() + max1 + 1, t.getFinalState() + max1 + 1);
                }
                for (Transition t : t2) {
                    if (t.getInitialState() < min2) {
                        min2 = t.getInitialState();
                    }
                    if (t.getFinalState() > max2) {
                        max2 = t.getFinalState();
                    }
                }

                t3.add(new Transition(min1 - 1, epsilon, min2));
                t3.add(new Transition(max1, epsilon, max2 + 1));
                t3.add(new Transition(max2, epsilon, max2 + 1));

                t3.addAll(t1);
                t3.addAll(t2);

                return t3;

            case KLEENE_LOCK:

                min1 = 1000;
                max1 = 0;

                for (Transition t : t1) {
                    if (t.getInitialState() < min1) {
                        min1 = t.getInitialState();
                    }
                    if (t.getFinalState() > max1) {
                        max1 = t.getFinalState();
                    }
                }

                t3.add(new Transition(min1, epsilon, min1 + 1));

                for (Transition t : t1) {
                    t.setStates(t.getInitialState() + 1, t.getFinalState() + 1);
                }

                t3.add(new Transition(max1 + 1, epsilon, max1 + 2));
                t3.add(new Transition(min1, epsilon, max1 + 2));
                t3.add(new Transition(max1 + 1, epsilon, min1 + 1));

                t3.addAll(t1);
                t3.addAll(t2);

                return t3;

            case POSITIVE_LOCK:

                min1 = 1000;
                max1 = 0;

                for (Transition t : t1) {
                    if (t.getInitialState() < min1) {
                        min1 = t.getInitialState();
                    }
                    if (t.getFinalState() > max1) {
                        max1 = t.getFinalState();
                    }
                }

                t3.add(new Transition(min1, epsilon, min1 + 1));

                for (Transition t : t1) {
                    t.setStates(t.getInitialState() + 1, t.getFinalState() + 1);
                }

                t3.add(new Transition(max1 + 1, epsilon, max1 + 2));
                t3.add(new Transition(max1 + 1, epsilon, min1 + 1));

                t3.addAll(t1);
                t3.addAll(t2);

                return t3;

            case BOOLEAN_LOCK:

                min1 = 1000;
                max1 = 0;

                for (Transition t : t1) {
                    if (t.getInitialState() < min1) {
                        min1 = t.getInitialState();
                    }
                    if (t.getFinalState() > max1) {
                        max1 = t.getFinalState();
                    }
                }

                t3.add(new Transition(min1, epsilon, min1 + 1));

                for (Transition t : t1) {
                    t.setStates(t.getInitialState() + 1, t.getFinalState() + 1);
                }

                t3.add(new Transition(max1 + 1, epsilon, max1 + 2));
                t3.add(new Transition(min1, epsilon, max1 + 2));

                t3.addAll(t1);
                t3.addAll(t2);

                return t3;

            default:
                Transition tran = new Transition(0, node.lexeme, 1);
                t3.add(tran);
                return t3;
        }
    }

    public State getInitialState() {
        return initial_state;
    }

    public ArrayList<Transition> getTransitions() {
        return transitions;
    }

    public String getLast() {
        int max = 0;
        for (Transition t : transitions) {
            if (t.getFinalState() > max) {
                max = t.getFinalState();
            }
        }

        return "S" + max;
    }
}
