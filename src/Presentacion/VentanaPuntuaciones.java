package Presentacion;

import Logica.Puntaje.ComparadorDePuntajes;
import Logica.Puntaje.Puntaje;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.util.Objects;

public class VentanaPuntuaciones extends JFrame{
    private Pintor pintor;
    private List<Puntaje> puntuaciones;

    public VentanaPuntuaciones(){
        pintor = new Pintor(this);
        puntuaciones = leerPuntuaciones("/Puntuaciones/PuntuacionesMejoresJugadores");

        setTitle("Puntuaciones");
        setBounds(100, 100, 500, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(pintor);
        setVisible(true);
        setResizable(false);

    }

    public List<Puntaje> leerPuntuaciones(String Puntuaciones) {
        List<Puntaje> puntuaciones = new ArrayList<>();
        try (BufferedReader lineaDePuntuaciones = new BufferedReader(new InputStreamReader(Objects.requireNonNull(getClass().getResourceAsStream(Puntuaciones))))) {
            String linea;
            while ((linea = lineaDePuntuaciones.readLine()) != null) {

                try {
                    String[] datos = linea.split(" ");
                    if (datos.length < 2) {
                        System.out.println("Linea invalida (faltan datos): " + linea);
                        continue;
                    }
                    String nombre = datos[0];
                    int puntaje = Integer.parseInt(datos[1]);
                    //long puntaje = Long.parseLong(datos[1]); PARA VALORES MUY ALTOS
                    puntuaciones.add(new Puntaje(nombre, puntaje));
                } catch (NumberFormatException e) {
                    System.out.println("Linea invalida (formato de numero): " + linea);
                }

                /*if (datos.length == 2 && !datos[1].isEmpty()) {
                    String nombre = datos[0];
                    int puntaje = Integer.parseInt(datos[1]);
                    //long puntaje = Long.parseLong(datos[1]); PARA VALORES MUY ALTOS
                    puntuaciones.add(new Puntaje(nombre, puntaje));
                } else {
                    System.out.println("Línea inválida: " + linea);
                }*/

            }
            puntuaciones.sort(new ComparadorDePuntajes());
        } catch (IOException e) {
            System.out.println("NO EXISTE EL ARCHIVO");;
        }
        return puntuaciones;
    }

    public List<Puntaje> getPuntuaciones() {
        return puntuaciones;
    }

}

