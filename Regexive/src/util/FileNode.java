
package util;

import java.io.File;

/**
 *
 * @author Gilbert Le Blanc
 * https://stackoverflow.com/questions/23804675/list-files-and-directories-with-jtree-and-file-in-java
 */
public class FileNode {

    private File file;

    public FileNode(File file) {
        this.file = file;
    }

    @Override
    public String toString() {
        String name = file.getName();
        if (name.equals("")) {
            return file.getAbsolutePath();
        } else {
            return name;
        }
    }
}
