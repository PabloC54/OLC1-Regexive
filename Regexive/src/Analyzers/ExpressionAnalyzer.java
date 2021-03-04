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
package Analyzers;

import Structs.Node;
import Structs.Tree;
import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author pablo
 */
public class ExpressionAnalyzer {

    private ArrayList<ArrayList<String>> symbols;
    private ArrayList<ArrayList<String>> sets;
    private Tree tree;
    private Stack pile;

    public ExpressionAnalyzer() {
        this.symbols = null;
        this.sets = null;
        this.tree = null;
    }

    private char[] letters = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZabcdefghijklmnñopqrstubwxyz".toCharArray();
    private char[] digits = "0123456789".toCharArray();
    private char[] ascii = " !\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}".toCharArray();

    private String getToken(String lexeme) {

        String result = null;

        System.out.println("buscando token para '" + lexeme + "'");

        for (ArrayList<String> symbol : symbols) {
            System.out.println(symbol.get(3) + " : " + symbol.get(3).equals(lexeme));
            if (symbol.get(3).equals(lexeme)) {
                result = symbol.get(2);
                break;
            }
        }

        return result;
    }

    private String getSet(String id) {

        String result = null;

        for (ArrayList<String> set : sets) {
            if (set.get(0).equals(id)) {
                result = set.get(1);
                break;
            }
        }

        return result;
    }

    public void setData(ArrayList symbols, ArrayList sets, Tree tree) {
        this.symbols = symbols;
        this.sets = sets;
        this.tree = tree;
    }

    public boolean Analyze(String lexeme) {
        if (sets == null || tree == null) {
            return false;
        }

        pile = new Stack();
        for (int i = 1; i < lexeme.length() - 1; i++) {
            pile.add(lexeme.substring(i, i + 1));
        }
        System.out.println("analizando '" + lexeme + "'");
        System.out.println(pile);
        return Operate(tree.getRoot());
    }

    private boolean Operate(Node node) {

        switch (node.type) {
            case CONCATENATION:
                return Operate(node.left) && Operate(node.right);

            case DISYUNCTION:
                return Operate(node.left) || Operate(node.right);

            case KLEENE_LOCK:
                while (Operate(node.left)) {
                }

                return true;

            case POSITIVE_LOCK:
                if (!Operate(node.left)) {
                    return false;
                }

                while (Operate(node.left)) {
                }

                return true;

            case BOOLEAN_LOCK:
                Operate(node.left);
                return true;

            default:
                String character = (String) pile.peek();

                switch (getToken(node.lexeme)) {

                    case "id":
                        String definition = getSet(character);

                        if (definition == null) {
                            return false;
                        }

                        if (definition.contains("~")) {

                            char[] list;
                            String token = getToken(definition);

                            if (token == "L_set") {
                                list = letters;
                            } else if (token == "D_set") {
                                list = digits;
                            } else {
                                list = ascii;
                            }

                            int l = 0, r = 0;

                            for (int i = 0; i < list.length; i++) {
                                if (definition.charAt(0) == list[i]) {
                                    l = i;
                                }
                                if (definition.charAt(2) == list[i]) {
                                    r = i;
                                }
                            }

                            if (l > r) {
                                return false;
                            }

                            for (int i = l; i <= r; i++) {
                                if (character.equals(letters[i])) {
                                    pile.pop();
                                    return true;
                                }
                            }

                        } else {
                            String[] list = definition.split(",");

                            for (int i = 0; i < list.length; i++) {
                                if (character.equals(list[i])) {
                                    pile.pop();
                                    return true;
                                }
                            }
                        }

                        return false;

                    case "L":

                        if (node.lexeme.equals(character)) {
                            pile.pop();
                            return true;
                        }

                        return false;

                    case "string":

                        for (int i = 0; i < node.lexeme.length(); i++) {
                            if (node.lexeme.charAt(i) != ((char) pile.get(i))) {
                                return false;
                            }
                        }

                        for (int i = 0; i < node.lexeme.length(); i++) {
                            pile.pop();
                        }

                        return true;

                    case "especial":

                        if (node.lexeme.equals(character)) {
                            pile.pop();
                            return true;
                        }

                        return false;

                    default:
                        System.out.println("no se reconoció ni mais");
                }
        }

        return false;
    }
}
