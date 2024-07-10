import javax.swing.JFrame;

public class Ventana extends JFrame {

    public static final int ANCHO = 800, ALTO = 600;

    public Ventana (){
        setTitle("SpaceInvaders");
        setSize(ANCHO, ALTO);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        setVisible(true);
    }

    public static void main(String[] args) {
        new Ventana();
    }

}


