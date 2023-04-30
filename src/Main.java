import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SD1 s = new SD1();
        s.setContentPane(s.sPanel);
        s.setTitle("Struktura Diskrete 1");
        s.setSize(700,350);
        s.setVisible(true);
        s.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
