import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ventana extends JFrame implements ActionListener {

    public static final int ANCHO = 800, ALTO = 600;
    private JButton boton1;

    public Ventana (){
        setTitle("SpaceInvaders");
        setSize(ANCHO, ALTO);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);

        setLayout(null);

        boton1 = new JButton("Iniciar");
        boton1.setVisible(true);
        boton1.setBounds(350,200,100,50);
        boton1.addActionListener(this);
        this.add(boton1);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        abrirVentanaJuego();
    }

    private void abrirVentanaJuego() {
        new Escenario();
        this.dispose();
    }

}


