import javax.swing.*;
import java.awt.*;

public class Escenario extends JFrame {

    public Escenario () {
        setTitle("Space Invaders");
        setSize(800, 600);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.BLACK);

    }

}