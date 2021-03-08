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

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author pablo
 */
public class State {

    public String name;
    public boolean is_final_state;
    public Map<String, State> next_states = new HashMap<>();

    public State(String name, boolean is_final_state) {
        this.name = name;
        this.is_final_state = is_final_state;
    }

    public State() {
        this.name = "0";
        this.is_final_state = false;
    }

    public void setData(String name, boolean final_state) {
        this.name = name;
        this.is_final_state = final_state;
    }

    public String getName() {
        return name;
    }

    public void Next(String lexeme, State state) {
        next_states.put(lexeme, state);
    }

    public State Transition(String lexeme) {
        for (Map.Entry<String, State> entry : next_states.entrySet()) {
            if (entry.getKey().equals(lexeme)) {
                return entry.getValue();
            }
        }

        return null;
    }

    public State TransitionByName(String name) {

        if (next_states.isEmpty()) {
            return this;
        }

        for (Map.Entry<String, State> entry : next_states.entrySet()) {
            if (entry.getValue().name == name) {
                return entry.getValue();
            }
        }

        return null;
    }

}
