package Main;


import GUI.MainGUI;

/**
 *
 * @author pablo
 */

public class Main {
    
    public static String main_path = System.getProperty("user.dir") + "\\src";

    public static MainGUI gui = new MainGUI();

    public static void main(String[] args) {
        gui.setVisible(true);
    }

}
