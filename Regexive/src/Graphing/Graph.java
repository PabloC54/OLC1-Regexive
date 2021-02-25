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
import Structs.Tree;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

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


    /*
    
    digraph G {
    nodesep=0.4; //was 0.8
    ranksep=0.5;

    {node[style=invis,label=""]; cx_30;
    }
    {node[style=invis, label="", width=.1]; ocx_45; ocx_20;
    }

    {rank=same; 20; 45; cx_30}
    {rank=same; 10; 25; ocx_20}
    {rank=same; 40; 50; ocx_45}

    30 -> 20;
    30 -> 45;
    20 -> 10;
    20 -> 25;

    45 -> 40;
    45 -> 50;

} 
    
     */
    public void graphTree(Tree tree) throws IOException {

        Node root = tree.getRoot();

        file = new File(main_path+"\\nombre.dot");
        writer = new FileWriter(file);

        file.createNewFile();

        String h = " digraph G {\n"
            + "    nodesep=0.4; //was 0.8\n"
            + "    ranksep=0.5;\n"
            + "\n"
            + "    {node[style=invis,label=\"\"]; cx_30;\n"
            + "    }\n"
            + "    {node[style=invis, label=\"\", width=.1]; ocx_45; ocx_20;\n"
            + "    }\n"
            + "\n"
            + "    {rank=same; 20; 45; cx_30}\n"
            + "    {rank=same; 10; 25; ocx_20}\n"
            + "    {rank=same; 40; 50; ocx_45}\n"
            + "\n"
            + "    30 -> 20;\n"
            + "    30 -> 45;\n"
            + "    20 -> 10;\n"
            + "    20 -> 25;\n"
            + "\n"
            + "    45 -> 40;\n"
            + "    45 -> 50;\n"
            + "\n"
            + "} ";

        writer.write(h);
        
        Runtime.getRuntime().exec("dot -Tpng "+main_path+"\\nombre.dot -o "+main_path+"\\nombre.png");
        System.out.println("ahuevo");

        printNode(root);

        return;
    }

    private void printNode(Node node) {

        System.out.println(node.lexeme);

        if (node.left != null) {
            System.out.println("izq->");
            printNode(node.left);
        }

        if (node.right != null) {
            System.out.println("rgt->");
            printNode(node.right);
        }
        System.out.println("<-back");
    }
}
