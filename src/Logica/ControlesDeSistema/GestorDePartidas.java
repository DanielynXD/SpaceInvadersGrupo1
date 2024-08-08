package Logica.ControlesDeSistema;

import Presentacion.PanelDeJuegoData;
import java.io.IOException;
import java.io.*;

public class GestorDePartidas {

    public void guardarPartida(PanelDeJuegoData panelDeJuego, String nombre) {


        File archivo = new File("src/PartidasGuardadas/" + nombre + ".txt");
        FileOutputStream flujoDeSalida = null;
        try {
            flujoDeSalida = new FileOutputStream(archivo);

            ObjectOutputStream manejadorDeEscritura = new ObjectOutputStream(flujoDeSalida);

            manejadorDeEscritura.writeObject(panelDeJuego);

            manejadorDeEscritura.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public PanelDeJuegoData cargarPartida(String nombre) throws NoExisteLaPartidaGuardadaException{

        File archivo = new File("src/PartidasGuardadas/" + nombre + ".txt");
         if (archivo.exists()) {
             throw new NoExisteLaPartidaGuardadaException();
         }else {

             FileInputStream flujoDeEntrada = null;
             try {
                 flujoDeEntrada = new FileInputStream(archivo);
                 ObjectInputStream manejadorDeLectura = new ObjectInputStream(flujoDeEntrada);
                 PanelDeJuegoData panelDeJuegoData = (PanelDeJuegoData) manejadorDeLectura.readObject();
                 manejadorDeLectura.close();
                 return panelDeJuegoData;
             } catch (IOException | ClassNotFoundException e) {
                 System.out.println("EL ARCHIVO NO EXISTE");
                 return null;
             }
         }
    }

    /*
    public void guardarPartida(PanelDeJuego panelDeJuego, String nombre) {
    File archivo = new File("src/PartidasGuardadas/" + nombre + ".txt");
        FileInputStream flujoDeEntrada = null;
        try {
            flujoDeSalida = new FileOutputStream(archivo);

            ObjectOutputStream manejadorDeEscritura = new ObjectOutputStream(flujoDeSalida);

            manejadorDeEscritura.writeObject(panelDeJuego);

            manejadorDeEscritura.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    public PanelDeJuego cargarPartida(String nombre) {
    File archivo = new File("src/PartidasGuardadas/" + nombre + ".txt");
        FileInputStream flujoDeEntrada = null;
        try {
            flujoDeEntrada = new FileInputStream(archivo);
            ObjectInputStream manejadorDeLectura = new ObjectInputStream(flujoDeEntrada);
            PanelDeJuego persona = (PanelDeJuego) manejadorDeLectura.readObject();
            manejadorDeLectura.close();
            return persona;
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }



    }



    */



}
