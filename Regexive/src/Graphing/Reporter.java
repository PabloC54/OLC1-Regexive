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
package Graphing;

import Structs.AFD;
import Structs.AFND;
import Structs.Node;
import Structs.State;
import Structs.Transition;
import Structs.TransitionTable;
import Structs.Tree;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author pablo
 */
public class Reporter {

    String main_path;
    File file;
    FileWriter writer;

    private int count;

    public Reporter(String main_path) {
        this.count = 0;
        this.main_path = main_path;
    }

    public boolean graphTree(Tree tree, String folder) throws IOException {
        try {
            String name = main_path + "\\Arboles\\" + folder;
            file = new File(name);
            file.mkdir();

            name += "\\" + tree.id;
            file = new File(name + ".dot");
            file.createNewFile();
            writer = new FileWriter(file);

            Node root = tree.getRoot();

            String s = " digraph G {\n"
                + "    nodesep=0.4;\n"
                + "    ranksep=0.5;\n\n"
                + printNodes(root, "")
                + "} ";

            writer.write(s);
            writer.close();

            String command = "dot -Tpng " + name + ".dot -o " + name + ".png";
            Runtime.getRuntime().exec(command);

//        Files.deleteIfExists(file.toPath());
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    private String printNodes(Node node, String index) {

        String temp_str = "";

        if (node == null) {
            return temp_str;
        }

        temp_str += "node_" + index + " [fontsize=13 fontname = \"helvetica\" label=\"" + node.first + " " + node.lexeme + " " + node.last + "\\n" + node.anullable + "\"];\n"
            + "";

        String left_i = "l", right_i = "r";

        if (node.left != null) {
            temp_str += "node_" + index + " -> node_" + index + left_i + ";\n";
        }
        if (node.right != null) {
            temp_str += "node_" + index + " -> node_" + index + right_i + ";\n";
        }

        return temp_str + printNodes(node.left, index + left_i) + printNodes(node.right, index + right_i);
    }

    public boolean graphFollowTable(Tree tree, String folder) throws IOException {
        try {
            String name = main_path + "\\Siguientes\\" + folder;
            file = new File(name);
            file.mkdir();

            name += "\\" + tree.id;
            file = new File(name + ".dot");
            file.createNewFile();
            writer = new FileWriter(file);

            ArrayList<ArrayList> table = tree.getTable();

            String s = " digraph G {\n"
                + " node [label=\"\\N\", fontsize=13 shape=plaintext fontname = \"helvetica\"];\n"
                + " Foo [label=<\n"
                + "<table border=\"0\" cellborder=\"1\" cellspacing=\"0\">\n"
                + "  <tr><td><i>Estado</i></td><td><i>Siguientes</i></td></tr>\n";

            for (ArrayList v : table) {
                s += "<tr><td align=\"left\">" + v.get(0) + "  " + v.get(1) + "</td><td>" + v.get(2) + "</td></tr>\n";
            }

            s += "</table>>];"
                + "} ";

            writer.write(s);
            writer.close();

            String command = "dot -Tpng " + name + ".dot -o " + name + ".png";
            Runtime.getRuntime().exec(command);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public boolean graphTransitionTable(Tree tree, String folder) throws IOException {
        try {
            String name = main_path + "\\Transiciones\\" + folder;
            file = new File(name);
            file.mkdir();

            name += "\\" + tree.id;
            file = new File(name + ".dot");
            file.createNewFile();
            writer = new FileWriter(file);

            TransitionTable tran = new TransitionTable(tree.getRoot(), tree.getTable(), tree.getLeaves());

            String s = " digraph G {\n"
                + " node [label=\"\\N\" fontsize=13 shape=plaintext fontname = \"helvetica\"];\n"
                + " Foo [label=<\n"
                + "<table border=\"0\" cellborder=\"1\" cellspacing=\"0\">\n"
                + "  <tr><td><i>Estado</i></td><td><i>Transición</i></td><td><i>Final</i></td></tr>\n"; // cambiar

            for (ArrayList<ArrayList> state : tran.states) {
                String temp = "";
                for (Object tr : (ArrayList) state.get(2)) {
                    Transition t = (Transition) tr;
                    temp += t.toString() + "\n";
                }

                s += "<tr><td>" + state.get(0) + "  " + state.get(1) + "</td><td>" + temp + "</td><td>" + state.get(3) + "</td></tr>\n";
            }

            s += "</table>>];\n"
                + "} ";

            writer.write(s);
            writer.close();

            String command = "dot -Tpng " + name + ".dot -o " + name + ".png";
            Runtime.getRuntime().exec(command);

            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public boolean graphAFND(Tree tree, String folder) throws IOException {
        try {
            String name = main_path + "\\AFND\\" + folder;
            file = new File(name);
            file.mkdir();

            name += "\\" + tree.id;
            file = new File(name + ".dot");
            file.createNewFile();
            writer = new FileWriter(file);

            AFND afnd = new AFND(tree);

            String s = " digraph G {\n"
                + "    node [shape=circle fontsize=13 fontname = \"helvetica\"];\n"
                + "    nodesep=0.4;\n"
                + "    ranksep=0.5;\n\n"
                + "    rankdir=LR;\n\n";

            for (Transition t : afnd.getTransitions()) {
                s += t.initialState + " -> " + t.finalState + " [label=\"" + t.transition + "\"];\n";
            }

            s += afnd.getLast() + " [shape=doublecircle];\n";

            s += "}";

            writer.write(s);
            writer.close();

            String command = "dot -Tpng " + name + ".dot -o " + name + ".png";
            Runtime.getRuntime().exec(command);
            return true;

        } catch (IOException e) {
            return false;
        }
    }

    public AFD graphAFD(Tree tree, String folder) throws IOException {

        AFD Afd = new AFD();

        Map<State, ArrayList<ArrayList<String>>> states = new HashMap<>();

        try {
            String name = main_path + "\\AFD\\" + folder;
            file = new File(name);
            file.mkdir();

            name += "\\" + tree.id;
            file = new File(name + ".dot");
            file.createNewFile();
            writer = new FileWriter(file);

            TransitionTable tran = new TransitionTable(tree.getRoot(), tree.getTable(), tree.getLeaves());

            String s = " digraph G {\n"
                + "    node [shape=circle fontsize=13 fontname = \"helvetica\"];\n"
                + "    nodesep=0.4;\n"
                + "    ranksep=0.5;\n\n"
                + "    rankdir=LR;\n\n";

            State initial_state = null;

            for (ArrayList state : tran.states) {
                String state_name = (String) state.get(0);
                boolean isfinal = (boolean) state.get(3);

                if (isfinal) {
                    s += state_name + " [shape=doublecircle];\n";
                }

                State state_temp = new State(state_name, isfinal);
                ArrayList<ArrayList<String>> state_list = new ArrayList<>();

                if (initial_state == null) {
                    initial_state = state_temp;
                }

                for (Object tr : (ArrayList) state.get(2)) {
                    Transition t = (Transition) tr;
                    s += state_name + " -> " + t.finalState + " [label=\"" + t.transition + "\"];\n";

                    ArrayList<String> list_temp = new ArrayList<>();
                    list_temp.add(t.transition);
                    list_temp.add(t.finalState);
                    state_list.add(list_temp);
                }

                states.put(state_temp, state_list);
            }
            s += "}";

            writer.write(s);
            writer.close();

            String command = "dot -Tpng " + name + ".dot -o " + name + ".png";
            Runtime.getRuntime().exec(command);

            for (Map.Entry<State, ArrayList<ArrayList<String>>> entry : states.entrySet()) {
                State state = entry.getKey();

                for (ArrayList<String> data : entry.getValue()) {

                    State next_state = null;

                    for (Map.Entry<State, ArrayList<ArrayList<String>>> state_temp : states.entrySet()) {
                        if (state_temp.getKey().getName().equals(data.get(1))) {
                            next_state = state_temp.getKey();
                        }
                    }

                    state.Next(data.get(0), next_state);
                }
            }

            Afd.setInitialState(initial_state);

            return Afd;
        } catch (IOException e) {
            return null;
        }
    }

    public boolean reportErrors(ArrayList<ArrayList<String>> lexical, ArrayList<ArrayList<String>> syntactic, String id) {
        try {
            file = new File(main_path + "\\Errores\\" + id + ".html");
            file.createNewFile();
            writer = new FileWriter(file);

            ArrayList<String> header = new ArrayList<>();
            header.add("Tipo");
            header.add("Línea");
            header.add("Descripción");
            header.add("Línea");
            header.add("Columna");

            String s = "<!DOCTYPE html>\n"
                + "\t<html>\n"
                + "\t\t<head>\n"
                + "\t\t\t<title>Errores</title>\n"
                + "\t\t\t<link rel='stylesheet' type='text/css' href='../../style.css'/>\n"
                + "\t\t</head>\n"
                + "\t\t<body>\n";

            s += "\t\t\t<table class='container'>\n"
                + "\t\t\t\t<thead>\n"
                + "\t\t\t\t\t<tr>\n";

            for (String h : header) {
                s += "\t\t\t\t\t\t<th><h1>" + h + "</h1></th>\n";
            }

            s += "\t\t\t\t\t</tr>\n"
                + "\t\t\t\t</thead>\n\n"
                + "\t\t\t\t<tbody>\n";

            int index = 1;
            if (!lexical.isEmpty()) {
                for (int i = 0; i < lexical.size(); i++) {
                    s += "\t\t\t\t\t<tr>\n"
                        + "\t\t\t\t\t\t<td>" + index + "</td>\n"
                        + "\t\t\t\t\t\t<td>Léxico</td>\n";

                    s += "\t\t\t\t\t\t<td>El lexema '" + lexical.get(i).get(2) + "' no pertenece al lenguaje</td>\n"
                        + "\t\t\t\t\t\t<td>" + lexical.get(i).get(0) + "</td>\n"
                        + "\t\t\t\t\t\t<td>" + lexical.get(i).get(1) + "</td>\n";

                    s += "\t\t\t\t\t</tr>\n\n";

                    index += 1;
                }
            }

            if (!syntactic.isEmpty()) {
                for (int i = 0; i < syntactic.size(); i++) {
                    s += "\t\t\t\t\t<tr>\n"
                        + "\t\t\t\t\t\t<td>" + index + "</td>\n"
                        + "\t\t\t\t\t\t<td>Sintáctico</td>\n";

                    s += "\t\t\t\t\t\t<td>No se esperaba '" + syntactic.get(i).get(2) + "'</td>\n"
                        + "\t\t\t\t\t\t<td>" + syntactic.get(i).get(0) + "</td>\n"
                        + "\t\t\t\t\t\t<td>" + syntactic.get(i).get(1) + "</td>\n";

                    s += "\t\t\t\t\t</tr>\n\n";

                    index += 1;
                }
            }

            s += "\t\t\t\t</tbody>\n"
                + "\t\t\t</table>\n"
                + "\t\t</body>\n"
                + "\t</html>";

            writer.write(s);
            writer.close();

            return true;

        } catch (IOException e) {
            System.out.println(e.getCause());
            return false;
        }
    }

}
