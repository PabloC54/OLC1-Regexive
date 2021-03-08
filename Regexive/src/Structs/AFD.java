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

import static GUI.MainGUI.Data;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author pablo
 */
public class AFD {

    private State initial_state;
    private String message = new String();

    private char[] ascii = " !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNÑOPQRSTUVWXYZ[\\]^_`abcdefghijklmnñopqrstubwxyz{|}".toCharArray();

    public AFD() {
        this.initial_state = new State();
    }

    private String getToken(String lexeme) {

        String result = null;

        for (ArrayList<String> symbol : Data.get("symbols")) {
            if (symbol.get(3).equals(lexeme)) {
                result = symbol.get(2);
                break;
            }
        }

        return result;
    }

    private String getSet(String id) {

        String result = null;

        for (ArrayList<String> set : Data.get("sets")) {
            if (set.get(0).equals(id)) {
                result = set.get(1);
                break;
            }
        }

        return result;
    }

    public boolean Evaluate(String lexeme) {

        return Buscar(lexeme, initial_state);
    }

    private boolean Buscar(String lexeme, State state) {

        if (lexeme.isEmpty() && state.is_final_state) {
            return true;
        }

        for (Map.Entry<String, State> entry : state.next_states.entrySet()) {

            String transition = entry.getKey();
            State state_temp = entry.getValue();

            String token = getToken(transition);

            switch (token) {

                case "id":
                    String set_definition = getSet(transition);

                    if (set_definition == null) {
                        message = "No se definió un conjunto '" + transition + "'";
                        return false;
                    }

                    if (set_definition.contains("~")) {
                        int l = 0, r = 0;

                        for (int i = 0; i < ascii.length; i++) {
                            if (set_definition.charAt(0) == ascii[i]) {
                                l = i;
                            }
                            if (set_definition.charAt(2) == ascii[i]) {
                                r = i;
                            }
                        }

                        if (l > r) {
                            message = "Conjunto '" + transition + "' mal definido, '" + set_definition.charAt(0) + "' se encuentra después de '" + set_definition.charAt(2) + "'";
                            continue;
                        }

                        for (int i = l; i <= r; i++) {
                            if (lexeme.charAt(0) == ascii[i]) {
                                return Buscar(lexeme.substring(1, lexeme.length()), state_temp);
                            }
                        }

                    } else {
                        String[] list = set_definition.split(",");

                        for (int i = 0; i < list.length; i++) {
                            if (lexeme.substring(0, 1).equals(list[i])) {
                                return Buscar(lexeme.substring(1, lexeme.length()), state_temp);
                            }
                        }
                    }

                    continue;

                case "L":

                    if (lexeme.startsWith(transition)) {
                        return Buscar(lexeme.substring(transition.length(), lexeme.length()), state_temp);
                    }
                    continue;

                case "string":

                    if (lexeme.startsWith(transition.substring(2, transition.length() - 2))) {
                        return Buscar(lexeme.substring(transition.length() - 4, lexeme.length()), state_temp);
                    }
                    continue;

                case "especial":

                    if (lexeme.startsWith(transition)) {
                        return Buscar(lexeme.substring(transition.length(), lexeme.length()), state_temp);
                    }
                    continue;

                default:
                    message = "No se esperaba '" + lexeme.charAt(0) + "'";
            }

        }
        return false;
    }

    public void setInitialState(State state) {
        this.initial_state = state;
    }

    public String getMessage() {
        return message;
    }

    public State getInitialState() {
        return initial_state;
    }
}
