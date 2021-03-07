package Analyzers;

import java_cup.runtime.Symbol;
import java.util.ArrayList;

public class Scanner implements java_cup.runtime.Scanner {

    private final int YY_BUFFER_SIZE = 512;
    private final int YY_F = -1;
    private final int YY_NO_STATE = -1;
    private final int YY_NOT_ACCEPT = 0;
    private final int YY_END = 2;
    private final int YY_NO_ANCHOR = 4;
    private final int YY_BOL = 65536;
    private final int YY_EOF = 65537;

    private ArrayList<ArrayList<String>> Symbols = new ArrayList<>();
    private ArrayList<ArrayList<String>> Errors = new ArrayList<>();

    Symbol symbol(int line, int column, String lexeme, String token, int s) {
        ArrayList<String> v = new ArrayList<>();
        v.add(String.valueOf(line));
        v.add(String.valueOf(column));
        v.add(token);
        v.add(lexeme);
        Symbols.add(v);
        return new Symbol(s, line, column, yytext());
    }

    void Error(int line, int column, String lexem) {
        ArrayList<String> v = new ArrayList<>();
        v.add(String.valueOf(line));
        v.add(String.valueOf(column));
        v.add(lexem);
        Errors.add(v);
    }

    public ArrayList getSymbols() {
        return Symbols;
    }

    public ArrayList getErrors() {
        return Errors;
    }
    private java.io.BufferedReader yy_reader;
    private int yy_buffer_index;
    private int yy_buffer_read;
    private int yy_buffer_start;
    private int yy_buffer_end;
    private char yy_buffer[];
    private int yychar;
    private int yyline;
    private boolean yy_at_bol;
    private int yy_lexical_state;

    public Scanner(java.io.Reader reader) {
        this();
        if (null == reader) {
            throw (new Error("Error: Bad input stream initializer."));
        }
        yy_reader = new java.io.BufferedReader(reader);
    }

    public Scanner(java.io.InputStream instream) {
        this();
        if (null == instream) {
            throw (new Error("Error: Bad input stream initializer."));
        }
        yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(instream));
    }

    private Scanner() {
        yy_buffer = new char[YY_BUFFER_SIZE];
        yy_buffer_read = 0;
        yy_buffer_index = 0;
        yy_buffer_start = 0;
        yy_buffer_end = 0;
        yy_at_bol = true;
        yy_lexical_state = YYINITIAL;
        yyline = 1;
        yychar = 1;
    }

    private boolean yy_eof_done = false;
    private final int YYINITIAL = 0;
    private final int yy_state_dtrans[] = {
        0
    };

    private void yybegin(int state) {
        yy_lexical_state = state;
    }

    private int yy_advance()
        throws java.io.IOException {
        int next_read;
        int i;
        int j;

        if (yy_buffer_index < yy_buffer_read) {
            return yy_buffer[yy_buffer_index++];
        }

        if (0 != yy_buffer_start) {
            i = yy_buffer_start;
            j = 0;
            while (i < yy_buffer_read) {
                yy_buffer[j] = yy_buffer[i];
                ++i;
                ++j;
            }
            yy_buffer_end = yy_buffer_end - yy_buffer_start;
            yy_buffer_start = 0;
            yy_buffer_read = j;
            yy_buffer_index = j;
            next_read = yy_reader.read(yy_buffer,
                yy_buffer_read,
                yy_buffer.length - yy_buffer_read);
            if (-1 == next_read) {
                return YY_EOF;
            }
            yy_buffer_read = yy_buffer_read + next_read;
        }

        while (yy_buffer_index >= yy_buffer_read) {
            if (yy_buffer_index >= yy_buffer.length) {
                yy_buffer = yy_double(yy_buffer);
            }
            next_read = yy_reader.read(yy_buffer,
                yy_buffer_read,
                yy_buffer.length - yy_buffer_read);
            if (-1 == next_read) {
                return YY_EOF;
            }
            yy_buffer_read = yy_buffer_read + next_read;
        }
        return yy_buffer[yy_buffer_index++];
    }

    private void yy_move_end() {
        if (yy_buffer_end > yy_buffer_start
            && '\n' == yy_buffer[yy_buffer_end - 1]) {
            yy_buffer_end--;
        }
        if (yy_buffer_end > yy_buffer_start
            && '\r' == yy_buffer[yy_buffer_end - 1]) {
            yy_buffer_end--;
        }
    }
    private boolean yy_last_was_cr = false;

    private void yy_mark_start() {
        int i;
        for (i = yy_buffer_start; i < yy_buffer_index; ++i) {
            if ('\n' == yy_buffer[i] && !yy_last_was_cr) {
                ++yyline;
            }
            if ('\r' == yy_buffer[i]) {
                ++yyline;
                yy_last_was_cr = true;
            } else {
                yy_last_was_cr = false;
            }
        }
        yychar = yychar
            + yy_buffer_index - yy_buffer_start;
        yy_buffer_start = yy_buffer_index;
    }

    private void yy_mark_end() {
        yy_buffer_end = yy_buffer_index;
    }

    private void yy_to_mark() {
        yy_buffer_index = yy_buffer_end;
        yy_at_bol = (yy_buffer_end > yy_buffer_start)
            && ('\r' == yy_buffer[yy_buffer_end - 1]
            || '\n' == yy_buffer[yy_buffer_end - 1]
            || 2028/*LS*/ == yy_buffer[yy_buffer_end - 1]
            || 2029/*PS*/ == yy_buffer[yy_buffer_end - 1]);
    }

    private java.lang.String yytext() {
        return (new java.lang.String(yy_buffer,
            yy_buffer_start,
            yy_buffer_end - yy_buffer_start));
    }

    private int yylength() {
        return yy_buffer_end - yy_buffer_start;
    }

    private char[] yy_double(char buf[]) {
        int i;
        char newbuf[];
        newbuf = new char[2 * buf.length];
        for (i = 0; i < buf.length; ++i) {
            newbuf[i] = buf[i];
        }
        return newbuf;
    }
    private final int YY_E_INTERNAL = 0;
    private final int YY_E_MATCH = 1;
    private java.lang.String yy_error_string[] = {
        "Error: Internal error.\n",
        "Error: Unmatched input.\n"
    };

    private void yy_error(int code, boolean fatal) {
        java.lang.System.out.print(yy_error_string[code]);
        java.lang.System.out.flush();
        if (fatal) {
            throw new Error("Fatal Error.\n");
        }
    }

    private int[][] unpackFromString(int size1, int size2, String st) {
        int colonIndex = -1;
        String lengthString;
        int sequenceLength = 0;
        int sequenceInteger = 0;

        int commaIndex;
        String workString;

        int res[][] = new int[size1][size2];
        for (int i = 0; i < size1; i++) {
            for (int j = 0; j < size2; j++) {
                if (sequenceLength != 0) {
                    res[i][j] = sequenceInteger;
                    sequenceLength--;
                    continue;
                }
                commaIndex = st.indexOf(',');
                workString = (commaIndex == -1) ? st
                    : st.substring(0, commaIndex);
                st = st.substring(commaIndex + 1);
                colonIndex = workString.indexOf(':');
                if (colonIndex == -1) {
                    res[i][j] = Integer.parseInt(workString);
                    continue;
                }
                lengthString
                    = workString.substring(colonIndex + 1);
                sequenceLength = Integer.parseInt(lengthString);
                workString = workString.substring(0, colonIndex);
                sequenceInteger = Integer.parseInt(workString);
                res[i][j] = sequenceInteger;
                sequenceLength--;
            }
        }
        return res;
    }
    private int yy_acpt[] = {
        /* 0 */YY_NOT_ACCEPT,
        /* 1 */ YY_NO_ANCHOR,
        /* 2 */ YY_NO_ANCHOR,
        /* 3 */ YY_NO_ANCHOR,
        /* 4 */ YY_NO_ANCHOR,
        /* 5 */ YY_NO_ANCHOR,
        /* 6 */ YY_NO_ANCHOR,
        /* 7 */ YY_NO_ANCHOR,
        /* 8 */ YY_NO_ANCHOR,
        /* 9 */ YY_NO_ANCHOR,
        /* 10 */ YY_NO_ANCHOR,
        /* 11 */ YY_NO_ANCHOR,
        /* 12 */ YY_NO_ANCHOR,
        /* 13 */ YY_NO_ANCHOR,
        /* 14 */ YY_NO_ANCHOR,
        /* 15 */ YY_NO_ANCHOR,
        /* 16 */ YY_NO_ANCHOR,
        /* 17 */ YY_NO_ANCHOR,
        /* 18 */ YY_NO_ANCHOR,
        /* 19 */ YY_NO_ANCHOR,
        /* 20 */ YY_NO_ANCHOR,
        /* 21 */ YY_NO_ANCHOR,
        /* 22 */ YY_NO_ANCHOR,
        /* 23 */ YY_NO_ANCHOR,
        /* 24 */ YY_NOT_ACCEPT,
        /* 25 */ YY_NO_ANCHOR,
        /* 26 */ YY_NO_ANCHOR,
        /* 27 */ YY_NO_ANCHOR,
        /* 28 */ YY_NO_ANCHOR,
        /* 29 */ YY_NO_ANCHOR,
        /* 30 */ YY_NOT_ACCEPT,
        /* 31 */ YY_NO_ANCHOR,
        /* 32 */ YY_NO_ANCHOR,
        /* 33 */ YY_NOT_ACCEPT,
        /* 34 */ YY_NO_ANCHOR,
        /* 35 */ YY_NO_ANCHOR,
        /* 36 */ YY_NOT_ACCEPT,
        /* 37 */ YY_NO_ANCHOR,
        /* 38 */ YY_NO_ANCHOR,
        /* 39 */ YY_NOT_ACCEPT,
        /* 40 */ YY_NO_ANCHOR,
        /* 41 */ YY_NO_ANCHOR,
        /* 42 */ YY_NOT_ACCEPT,
        /* 43 */ YY_NO_ANCHOR,
        /* 44 */ YY_NO_ANCHOR,
        /* 45 */ YY_NOT_ACCEPT,
        /* 46 */ YY_NO_ANCHOR,
        /* 47 */ YY_NO_ANCHOR,
        /* 48 */ YY_NOT_ACCEPT,
        /* 49 */ YY_NO_ANCHOR,
        /* 50 */ YY_NOT_ACCEPT,
        /* 51 */ YY_NO_ANCHOR,
        /* 52 */ YY_NOT_ACCEPT,
        /* 53 */ YY_NOT_ACCEPT,
        /* 54 */ YY_NOT_ACCEPT,
        /* 55 */ YY_NOT_ACCEPT,
        /* 56 */ YY_NOT_ACCEPT,
        /* 57 */ YY_NO_ANCHOR,
        /* 58 */ YY_NOT_ACCEPT,
        /* 59 */ YY_NO_ANCHOR
    };
    private int yy_cmap[] = unpackFromString(1, 65538,
        "7:9,3,6,7:2,8,7:18,12,5,28,13:2,29,13,27,13:2,22,23,14,19,20,1,11:10,17,18,"
        + "4,13,2,24,13,10:2,30,10:6,32,10:3,26,31,10:11,13,25,13:2,11,13,10:2,30,10:6"
        + ",32,10:3,26,31,10:11,9,21,16,15,7:50,10,7:17,10,7:31,10,7:7988,10,7:57319,0"
        + ":2")[0];

    private int yy_rmap[] = unpackFromString(1, 60,
        "0,1,2,1:2,3,4,3:8,5,6,1:2,7,8,1,6,1,3:3,9,10,1,11,12,1,13,1,13,14,15,16,15,"
        + "17,18,8,19,20,21,22,8,23:2,24:2,25,26,27,28,29,30,31,32")[0];

    private int yy_nxt[][] = unpackFromString(33, 33,
        "1,2,25,3,31,25,4,34,-1,5,6,25,26,25:2,34,7,8,9,37,10,11,12,13,14,40,6,43:2,"
        + "46,59,6:2,-1:34,15,-1:10,24,-1,30,33,-1:29,24,-1,30,33,-1:27,16:2,24,-1,30,"
        + "33,-1:10,16,-1:3,16:3,-1,15:5,-1,15,-1,15:24,-1:10,16:2,-1:14,16,-1:3,16:3,"
        + "-1:12,19,-1,30,-1:19,42:5,-1,42:18,50,42,20,29,42:4,-1:10,16:2,-1:14,16,-1:"
        + "3,16:2,22,-1,19:2,-1,19:2,-1:3,19:3,28,19,28,-1,19:17,-1,19:2,-1,19:2,-1:3,"
        + "19:3,28,19:2,-1,19:17,-1:5,36,-1:6,24,-1,30,33,-1:18,32:2,-1,32:2,-1:3,32:3"
        + ",35,32:2,-1,32:17,-1,36,-1,36:2,53,36:27,-1:2,17,-1:9,39,-1,30,33,-1:18,42:"
        + "5,-1,42:5,38,42,45,42:10,50,42,20,29,42:4,-1:12,24,-1,30,33,-1:10,18:3,-1:5"
        + ",38:2,42,38:2,-1,42:2,38:3,41,38,41,42,38:9,44,38:2,19,38:4,-1,42:5,-1,42:5"
        + ",58,42,45,48,42:9,50,42:2,-1,42:4,-1:12,19,-1,30,-1:11,42:3,-1:5,38:2,42,38"
        + ":2,-1,42:2,38:3,41,38:2,42,38:9,44,38:2,19,38:4,-1:12,24,-1,30,33,-1:13,52,"
        + "-1:4,47:2,42,47:2,-1,42:2,47:3,49,47:2,42,47:9,51,47:2,32,47:4,-1:26,42:3,-"
        + "1:7,52,-1:2,54,-1:5,52,-1:22,21,-1:33,55,-1:2,54,-1:5,55,-1:16,56,-1:6,55,-"
        + "1:8,55,-1:16,56,-1:32,23,-1:13,16:2,-1:14,27,-1:3,16:3,-1,42:5,-1,42:5,58,4"
        + "2,45,48,42:9,50,42,20,29,42:4,-1:10,16:2,24,-1,30,33,-1:10,16,-1:3,16,57,16");

    public Symbol next_token()
        throws java.io.IOException {
        int yy_lookahead;
        int yy_anchor = YY_NO_ANCHOR;
        int yy_state = yy_state_dtrans[yy_lexical_state];
        int yy_next_state = YY_NO_STATE;
        int yy_last_accept_state = YY_NO_STATE;
        boolean yy_initial = true;
        int yy_this_accept;

        yy_mark_start();
        yy_this_accept = yy_acpt[yy_state];
        if (YY_NOT_ACCEPT != yy_this_accept) {
            yy_last_accept_state = yy_state;
            yy_mark_end();
        }
        while (true) {
            if (yy_initial && yy_at_bol) {
                yy_lookahead = YY_BOL;
            } else {
                yy_lookahead = yy_advance();
            }
            yy_next_state = YY_F;
            yy_next_state = yy_nxt[yy_rmap[yy_state]][yy_cmap[yy_lookahead]];
            if (YY_EOF == yy_lookahead && true == yy_initial) {
                return null;
            }
            if (YY_F != yy_next_state) {
                yy_state = yy_next_state;
                yy_initial = false;
                yy_this_accept = yy_acpt[yy_state];
                if (YY_NOT_ACCEPT != yy_this_accept) {
                    yy_last_accept_state = yy_state;
                    yy_mark_end();
                }
            } else {
                if (YY_NO_STATE == yy_last_accept_state) {
                    throw (new Error("Lexical Error: Unmatched Input."));
                } else {
                    yy_anchor = yy_acpt[yy_last_accept_state];
                    if (0 != (YY_END & yy_anchor)) {
                        yy_move_end();
                    }
                    yy_to_mark();
                    switch (yy_last_accept_state) {
                        case 1:

                        case -2:
                            break;
                        case 2: {
                            Error(yyline, yychar, yytext());
                        }
                        case -3:
                            break;
                        case 3: {
                        }
                        case -4:
                            break;
                        case 4: {
                            yychar = 0;
                        }
                        case -5:
                            break;
                        case 5: {
                            return symbol(yyline, yychar, yytext(), "llaveA", sym.llaveA);
                        }
                        case -6:
                            break;
                        case 6: {
                            return symbol(yyline, yychar, yytext(), "L", sym.L);
                        }
                        case -7:
                            break;
                        case 7: {
                            return symbol(yyline, yychar, yytext(), "llaveB", sym.llaveB);
                        }
                        case -8:
                            break;
                        case 8: {
                            return symbol(yyline, yychar, yytext(), "dospuntos", sym.dospuntos);
                        }
                        case -9:
                            break;
                        case 9: {
                            return symbol(yyline, yychar, yytext(), "puntocoma", sym.puntocoma);
                        }
                        case -10:
                            break;
                        case 10: {
                            return symbol(yyline, yychar, yytext(), "concat", sym.concat);
                        }
                        case -11:
                            break;
                        case 11: {
                            return symbol(yyline, yychar, yytext(), "disy", sym.disy);
                        }
                        case -12:
                            break;
                        case 12: {
                            return symbol(yyline, yychar, yytext(), "cerr_kleene", sym.cerr_kleene);
                        }
                        case -13:
                            break;
                        case 13: {
                            return symbol(yyline, yychar, yytext(), "cerr_positiva", sym.cerr_positiva);
                        }
                        case -14:
                            break;
                        case 14: {
                            return symbol(yyline, yychar, yytext(), "cerr_bool", sym.cerr_bool);
                        }
                        case -15:
                            break;
                        case 15: {
                            yychar = 0;
                        }
                        case -16:
                            break;
                        case 16: {
                            return symbol(yyline, yychar, yytext(), "id", sym.id);
                        }
                        case -17:
                            break;
                        case 17: {
                            return symbol(yyline, yychar, yytext(), "flecha", sym.flecha);
                        }
                        case -18:
                            break;
                        case 18: {
                            return symbol(yyline, yychar, yytext(), "especial", sym.especial);
                        }
                        case -19:
                            break;
                        case 19: {
                            return symbol(yyline, yychar, yytext(), "set", sym.set);
                        }
                        case -20:
                            break;
                        case 20: {
                            return symbol(yyline, yychar, "\\" + yytext().substring(0, yytext().length() - 1) + "\\\"", "string", sym.string);
                        }
                        case -21:
                            break;
                        case 21: {
                            yychar = 0;
                        }
                        case -22:
                            break;
                        case 22: {
                            return symbol(yyline, yychar, yytext(), "conj", sym.conj);
                        }
                        case -23:
                            break;
                        case 23: {
                            return symbol(yyline, yychar, yytext(), "porcentajes", sym.porcentajes);
                        }
                        case -24:
                            break;
                        case 25: {
                            Error(yyline, yychar, yytext());
                        }
                        case -25:
                            break;
                        case 26: {
                        }
                        case -26:
                            break;
                        case 27: {
                            return symbol(yyline, yychar, yytext(), "id", sym.id);
                        }
                        case -27:
                            break;
                        case 28: {
                            return symbol(yyline, yychar, yytext(), "set", sym.set);
                        }
                        case -28:
                            break;
                        case 29: {
                            return symbol(yyline, yychar, "\\" + yytext().substring(0, yytext().length() - 1) + "\\\"", "string", sym.string);
                        }
                        case -29:
                            break;
                        case 31: {
                            Error(yyline, yychar, yytext());
                        }
                        case -30:
                            break;
                        case 32: {
                            return symbol(yyline, yychar, yytext(), "set", sym.set);
                        }
                        case -31:
                            break;
                        case 34: {
                            Error(yyline, yychar, yytext());
                        }
                        case -32:
                            break;
                        case 35: {
                            return symbol(yyline, yychar, yytext(), "set", sym.set);
                        }
                        case -33:
                            break;
                        case 37: {
                            Error(yyline, yychar, yytext());
                        }
                        case -34:
                            break;
                        case 38: {
                            return symbol(yyline, yychar, yytext(), "set", sym.set);
                        }
                        case -35:
                            break;
                        case 40: {
                            Error(yyline, yychar, yytext());
                        }
                        case -36:
                            break;
                        case 41: {
                            return symbol(yyline, yychar, yytext(), "set", sym.set);
                        }
                        case -37:
                            break;
                        case 43: {
                            Error(yyline, yychar, yytext());
                        }
                        case -38:
                            break;
                        case 44: {
                            return symbol(yyline, yychar, yytext(), "set", sym.set);
                        }
                        case -39:
                            break;
                        case 46: {
                            Error(yyline, yychar, yytext());
                        }
                        case -40:
                            break;
                        case 47: {
                            return symbol(yyline, yychar, yytext(), "set", sym.set);
                        }
                        case -41:
                            break;
                        case 49: {
                            return symbol(yyline, yychar, yytext(), "set", sym.set);
                        }
                        case -42:
                            break;
                        case 51: {
                            return symbol(yyline, yychar, yytext(), "set", sym.set);
                        }
                        case -43:
                            break;
                        case 57: {
                            return symbol(yyline, yychar, yytext(), "id", sym.id);
                        }
                        case -44:
                            break;
                        case 59: {
                            return symbol(yyline, yychar, yytext(), "L", sym.L);
                        }
                        case -45:
                            break;
                        default:
                            yy_error(YY_E_INTERNAL, false);
                        case -1:
                    }
                    yy_initial = true;
                    yy_state = yy_state_dtrans[yy_lexical_state];
                    yy_next_state = YY_NO_STATE;
                    yy_last_accept_state = YY_NO_STATE;
                    yy_mark_start();
                    yy_this_accept = yy_acpt[yy_state];
                    if (YY_NOT_ACCEPT != yy_this_accept) {
                        yy_last_accept_state = yy_state;
                        yy_mark_end();
                    }
                }
            }
        }
    }
}
