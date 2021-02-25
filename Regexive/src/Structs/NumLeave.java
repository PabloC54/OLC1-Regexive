
package Structs;

import java.util.ArrayList;

/**
 *
 * @author josef
 */
public final class NumLeave {

    public int content;

    public NumLeave(ArrayList<String> content) {
        int lenght = 0;

        for (String str : content) {
            if (str != "." && str != "|" && str != "*" && str != "+" && str != "?") {
                lenght += 1;
            }
        }

        this.content = lenght + 1;
    }

    public int getNum() {
        content -= 1;
        return content;
    }
}
