package Analyzers;

import java_cup.runtime.Symbol;
import java.util.Vector;

public class Scanner implements java_cup.runtime.Scanner {

    private final int YY_BUFFER_SIZE = 512;
    private final int YY_F = -1;
    private final int YY_NO_STATE = -1;
    private final int YY_NOT_ACCEPT = 0;
    private final int YY_START = 1;
    private final int YY_END = 2;
    private final int YY_NO_ANCHOR = 4;
    private final int YY_BOL = 65536;
    private final int YY_EOF = 65537;

    private Vector<Vector<String>> Simbolos = new Vector<>();
    private Vector<Vector<String>> Errores = new Vector<>();

    Symbol Simbolo(int linea, int columna, String lexema, String token, int s) {
        Vector<String> v = new Vector<>();
        v.add(String.valueOf(linea));
        v.add(String.valueOf(columna));
        v.add(token);
        v.add(lexema);
        Simbolos.add(v);
        return new Symbol(s, linea, columna, yytext());
    }

    void ErrorLexico(int linea, int columna, String lexema) {
        Vector<String> v = new Vector<>();
        v.add(String.valueOf(linea));
        v.add(String.valueOf(columna));
        v.add(lexema);
        Errores.add(v);
        System.out.println("[#Error Léxico] [" + linea + ", " + columna + "]  No se esperaba '" + lexema + "'.");
    }

    Vector getSimbolos() {
        return Simbolos;
    }

    Vector getErrores() {
        return Errores;
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
        /* 24 */ YY_NO_ANCHOR,
        /* 25 */ YY_NO_ANCHOR,
        /* 26 */ YY_NO_ANCHOR,
        /* 27 */ YY_NOT_ACCEPT,
        /* 28 */ YY_NO_ANCHOR,
        /* 29 */ YY_NO_ANCHOR,
        /* 30 */ YY_NO_ANCHOR,
        /* 31 */ YY_NO_ANCHOR,
        /* 32 */ YY_NO_ANCHOR,
        /* 33 */ YY_NO_ANCHOR,
        /* 34 */ YY_NOT_ACCEPT,
        /* 35 */ YY_NO_ANCHOR,
        /* 36 */ YY_NO_ANCHOR,
        /* 37 */ YY_NOT_ACCEPT,
        /* 38 */ YY_NO_ANCHOR,
        /* 39 */ YY_NOT_ACCEPT,
        /* 40 */ YY_NO_ANCHOR,
        /* 41 */ YY_NOT_ACCEPT,
        /* 42 */ YY_NO_ANCHOR,
        /* 43 */ YY_NOT_ACCEPT,
        /* 44 */ YY_NO_ANCHOR,
        /* 45 */ YY_NOT_ACCEPT,
        /* 46 */ YY_NO_ANCHOR,
        /* 47 */ YY_NOT_ACCEPT,
        /* 48 */ YY_NOT_ACCEPT,
        /* 49 */ YY_NOT_ACCEPT,
        /* 50 */ YY_NOT_ACCEPT,
        /* 51 */ YY_NOT_ACCEPT,
        /* 52 */ YY_NOT_ACCEPT,
        /* 53 */ YY_NOT_ACCEPT,
        /* 54 */ YY_NOT_ACCEPT,
        /* 55 */ YY_NO_ANCHOR,
        /* 56 */ YY_NO_ANCHOR,
        /* 57 */ YY_NO_ANCHOR,
        /* 58 */ YY_NO_ANCHOR,
        /* 59 */ YY_NOT_ACCEPT,
        /* 60 */ YY_NO_ANCHOR,
        /* 61 */ YY_NO_ANCHOR,
        /* 62 */ YY_NO_ANCHOR
    };
    private int yy_cmap[] = unpackFromString(1, 65538,
        "2:9,3,4,2:2,0,2:18,16,6,30,17:2,31,17,29,17:2,24,25,11,21,22,1,15:10,19,20,"
        + "5,17,7,26,17,18:2,32,18:6,34,18:3,28,33,18:11,17,27,17:2,35,17,18:2,32,18:6"
        + ",34,18:3,28,33,18:11,8,23,9,13,2:50,12,2:17,14,2:31,14,2:7988,10,2:57319,36"
        + ":2")[0];

    private int yy_rmap[] = unpackFromString(1, 63,
        "0,1,2:2,3:2,4,5,3:7,2,6,2:2,7,2:2,8,9,10,11,6,12,2,3,13,2:3,14,15,16,17,3,1"
        + "1,18,19,20,21,22,23,24,25,26,27,28,29,7,30,31,32,33,34,7,35,36,37,38")[0];

    private int yy_nxt[][] = unpackFromString(39, 37,
        "-1,1,28,2,3,35,38:2,4,5,6,38,55,28,60,7,29,38,61,8,9,40,10,11,12,13,14,42,6"
        + "1,38,44,46,62,61:2,38,15,-1,27,-1:9,34,-1,37,-1:71,34,-1,37,-1:33,16,41,16,"
        + "43,16:2,-1:2,16,-1:9,16,-1:3,16:4,-1:12,48,-1,49,-1:33,16,-1,16,-1,16:2,-1:"
        + "2,16,-1:9,16,-1:3,16:4,-1:2,52:3,-1,52:25,19,52:5,-1:12,34,-1:36,41,-1:36,4"
        + "8,-1:26,39:5,54,39:29,-1:2,27:3,21,27:31,-1:11,16,-1,16,-1,16:2,-1:2,16,-1:"
        + "9,16,-1:3,16:2,26,16,-1:2,22,-1:3,22:5,-1,22,-1:4,22:20,-1:7,39,-1:4,34,-1,"
        + "37,-1:24,52:3,-1,52:6,53,52:18,19,52:5,-1:2,31,-1:3,31:5,-1,31,-1:4,31:20,-"
        + "1:8,17,-1:3,34,-1,37,-1:33,23,-1,23,-1,23,-1:3,23,-1:9,23,-1:3,23:3,-1:13,3"
        + "4,-1,37,-1:14,18:3,-1:16,32,-1:3,32,-1:3,32,-1:9,32,-1:3,32:3,-1:3,52:3,-1,"
        + "52:6,53,52,59,52:16,19,52:5,-1:13,32,-1,32,-1:3,32,-1:9,32,-1:3,32:3,-1:13,"
        + "34,-1,37,-1:17,20,-1:15,32,-1,32,-1,32,-1:3,32,-1:9,32,-1:3,32:3,-1:17,24,-"
        + "1:36,33,-1:22,22,-1:3,22:5,23,22,23,-1,23,-1,22:2,57,22:9,57,22:3,57:3,22,-"
        + "1:2,31,-1:3,31:5,32,31,32,-1,32,-1,31:2,32,31:9,32,31:3,32:3,31,-1:2,36,52:"
        + "2,-1,36:5,52,36,52:4,36:20,-1:2,39:5,54,25,39:28,-1:11,16,41,16,45,16:2,-1:"
        + "2,16,-1:9,16,-1:3,16:4,-1:11,16,-1,16,-1,16:2,-1:2,16,-1:9,30,-1:3,16:4,-1:"
        + "12,50,-1:26,58,52:2,-1,58:5,52,58,52:4,58:20,-1:11,16,41,16,47,16:2,-1:2,16"
        + ",-1:9,16,-1:3,16:4,-1:11,16,50,16,51,16:2,-1:2,16,-1:9,16,-1:3,16:4,-1:11,1"
        + "6,50,16,51,16:2,-1:2,16,-1:9,16,-1:3,16,56,16:2,-1");

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
                        case 1: {
                            ErrorLexico(yyline, yychar, yytext());
                        }
                        case -2:
                            break;
                        case 2: {
                        }
                        case -3:
                            break;
                        case 3: {
                            yychar = 1;
                        }
                        case -4:
                            break;
                        case 4: {
                            return Simbolo(yyline, yychar, yytext(), "llaveA", sym.llaveA);
                        }
                        case -5:
                            break;
                        case 5: {
                            return Simbolo(yyline, yychar, yytext(), "llaveB", sym.llaveB);
                        }
                        case -6:
                            break;
                        case 6: {
                            return Simbolo(yyline, yychar, yytext(), "L", sym.L);
                        }
                        case -7:
                            break;
                        case 7: {
                        }
                        case -8:
                            break;
                        case 8: {
                            return Simbolo(yyline, yychar, yytext(), "dospuntos", sym.dospuntos);
                        }
                        case -9:
                            break;
                        case 9: {
                            return Simbolo(yyline, yychar, yytext(), "puntocoma", sym.puntocoma);
                        }
                        case -10:
                            break;
                        case 10: {
                            return Simbolo(yyline, yychar, yytext(), "concat", sym.concat);
                        }
                        case -11:
                            break;
                        case 11: {
                            return Simbolo(yyline, yychar, yytext(), "disy", sym.disy);
                        }
                        case -12:
                            break;
                        case 12: {
                            return Simbolo(yyline, yychar, yytext(), "cerr_kleene", sym.cerr_kleene);
                        }
                        case -13:
                            break;
                        case 13: {
                            return Simbolo(yyline, yychar, yytext(), "cerr_positiva", sym.cerr_positiva);
                        }
                        case -14:
                            break;
                        case 14: {
                            return Simbolo(yyline, yychar, yytext(), "cerr_bool", sym.cerr_bool);
                        }
                        case -15:
                            break;
                        case 15:

                        case -16:
                            break;
                        case 16: {
                            return Simbolo(yyline, yychar, yytext(), "id", sym.id);
                        }
                        case -17:
                            break;
                        case 17: {
                            return Simbolo(yyline, yychar, yytext(), "flecha", sym.flecha);
                        }
                        case -18:
                            break;
                        case 18: {
                            return Simbolo(yyline, yychar, yytext(), "especial", sym.especial);
                        }
                        case -19:
                            break;
                        case 19: {
                            return Simbolo(yyline, yychar, yytext(), "string", sym.string);
                        }
                        case -20:
                            break;
                        case 20: {
                            return Simbolo(yyline, yychar, yytext(), "porcentajes", sym.porcentajes);
                        }
                        case -21:
                            break;
                        case 21: {
                            System.out.println(">>>> COMENTARIO <<<<");
                        }
                        case -22:
                            break;
                        case 22: {
                            return Simbolo(yyline, yychar, yytext(), "A_set", sym.A_set);
                        }
                        case -23:
                            break;
                        case 23: {
                            return Simbolo(yyline, yychar, yytext(), "L_set", sym.L_set);
                        }
                        case -24:
                            break;
                        case 24: {
                            return Simbolo(yyline, yychar, yytext(), "D_set", sym.D_set);
                        }
                        case -25:
                            break;
                        case 25: {
                            System.out.println(">>>> COMENTARIO MULTILINEA <<<<");
                        }
                        case -26:
                            break;
                        case 26: {
                            return Simbolo(yyline, yychar, yytext(), "conj", sym.conj);
                        }
                        case -27:
                            break;
                        case 28: {
                            ErrorLexico(yyline, yychar, yytext());
                        }
                        case -28:
                            break;
                        case 29: {
                        }
                        case -29:
                            break;
                        case 30: {
                            return Simbolo(yyline, yychar, yytext(), "id", sym.id);
                        }
                        case -30:
                            break;
                        case 31: {
                            return Simbolo(yyline, yychar, yytext(), "A_set", sym.A_set);
                        }
                        case -31:
                            break;
                        case 32: {
                            return Simbolo(yyline, yychar, yytext(), "L_set", sym.L_set);
                        }
                        case -32:
                            break;
                        case 33: {
                            return Simbolo(yyline, yychar, yytext(), "D_set", sym.D_set);
                        }
                        case -33:
                            break;
                        case 35: {
                            ErrorLexico(yyline, yychar, yytext());
                        }
                        case -34:
                            break;
                        case 36: {
                            return Simbolo(yyline, yychar, yytext(), "A_set", sym.A_set);
                        }
                        case -35:
                            break;
                        case 38: {
                            ErrorLexico(yyline, yychar, yytext());
                        }
                        case -36:
                            break;
                        case 40: {
                            ErrorLexico(yyline, yychar, yytext());
                        }
                        case -37:
                            break;
                        case 42: {
                            ErrorLexico(yyline, yychar, yytext());
                        }
                        case -38:
                            break;
                        case 44: {
                            ErrorLexico(yyline, yychar, yytext());
                        }
                        case -39:
                            break;
                        case 46: {
                            ErrorLexico(yyline, yychar, yytext());
                        }
                        case -40:
                            break;
                        case 55: {
                            return Simbolo(yyline, yychar, yytext(), "L", sym.L);
                        }
                        case -41:
                            break;
                        case 56: {
                            return Simbolo(yyline, yychar, yytext(), "id", sym.id);
                        }
                        case -42:
                            break;
                        case 57: {
                            return Simbolo(yyline, yychar, yytext(), "L_set", sym.L_set);
                        }
                        case -43:
                            break;
                        case 58: {
                            return Simbolo(yyline, yychar, yytext(), "A_set", sym.A_set);
                        }
                        case -44:
                            break;
                        case 60: {
                            return Simbolo(yyline, yychar, yytext(), "L", sym.L);
                        }
                        case -45:
                            break;
                        case 61: {
                            return Simbolo(yyline, yychar, yytext(), "L", sym.L);
                        }
                        case -46:
                            break;
                        case 62: {
                            return Simbolo(yyline, yychar, yytext(), "L", sym.L);
                        }
                        case -47:
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
