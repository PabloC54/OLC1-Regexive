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

import Structs.Node;
import Structs.Transition;
import Structs.TransitionTable;
import Structs.Tree;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

/**
 *
 * @author pablo
 */
public class Graph {

    String main_path;
    File file;
    FileWriter writer;

    public Graph(String main_path) {
        this.main_path = main_path;
    }

    public boolean graphTree(Tree tree, String folder, String id) throws IOException {
        try {
            String name = main_path + "\\ARBOLES_201901698\\" + folder;
            file = new File(name);
            file.mkdir();

            name += "\\" + id;
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
        } catch (Exception e) {
            return false;
        }
    }

    private String printNodes(Node node, String index) {

        String temp_str = "";

        if (node == null) {
            return temp_str;
        }

        temp_str += "node_" + index + " [fontsize=13 shape=box fontname = \"helvetica\" label=\"" + node.first + " " + node.lexeme + " " + node.last + "\n" + node.anullable + "\"];\n";

        String left_i = "l", right_i = "r";

        if (node.left != null) {
            temp_str += "node_" + index + " -> node_" + index + left_i + ";\n";
        }
        if (node.right != null) {
            temp_str += "node_" + index + " -> node_" + index + right_i + ";\n";
        }

        return temp_str + printNodes(node.left, index + left_i) + printNodes(node.right, index + right_i);
    }

    public boolean graphFollowTable(ArrayList<ArrayList> table, String folder, String id) throws IOException {
        try {
            String name = main_path + "\\SIGUIENTES_201901698\\" + folder;
            file = new File(name);
            file.mkdir();

            name += "\\" + id;
            file = new File(name + ".dot");
            file.createNewFile();
            writer = new FileWriter(file);

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
        } catch (Exception e) {
            return false;
        }
    }

    public boolean graphTransitionTable(Tree tree, String folder, String id) throws IOException {
        try {
            String name = main_path + "\\TRANSICIONES_201901698\\" + folder;
            file = new File(name);
            file.mkdir();

            name += "\\" + id;
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
                String temp = "[";
                for (Object tr : (ArrayList) state.get(2)) {
                    Transition t = (Transition) tr;
                    temp += t.toString() + ", ";
                }
                temp += "]";
                temp = temp.replace(", ]", "]");

                s += "<tr><td>" + state.get(0) + "  " + state.get(1) + "</td><td>" + temp + "</td><td>" + state.get(3) + "</td></tr>\n";
            }

            s += "</table>>];\n"
                + "} ";

            writer.write(s);
            writer.close();

            String command = "dot -Tpng " + name + ".dot -o " + name + ".png";
            Runtime.getRuntime().exec(command);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean graphAFND(String folder, String id) throws IOException {
        try {
            String name = main_path + "\\AFND_201901698\\" + folder;
            file = new File(name);
            file.mkdir();

            name += "\\" + id;
            file = new File(name + ".dot");
            file.createNewFile();
            writer = new FileWriter(file);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean graphAFD(String folder, String id) throws IOException {
        try {
            String name = main_path + "\\AFD_201901698\\" + folder;
            file = new File(name);
            file.mkdir();

            name += "\\" + id;
            file = new File(name + ".dot");
            file.createNewFile();
            writer = new FileWriter(file);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
