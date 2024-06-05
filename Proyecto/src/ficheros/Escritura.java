/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ficheros;

import generico.CentralNuclear;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import personajes.Minero;
import personajes.Robot;

public class Escritura {

    public static String generarNombreFichero(String prefijo,String extension) {
        int nombreMes = LocalDate.now().getMonthValue();
        int dia = LocalDate.now().getDayOfMonth();
        String nombreFichero = (prefijo+"_"+dia+nombreMes+extension);
        return nombreFichero;
    }

    public static FileWriter abrirFlujoFW(String nombreFicheroLog) throws IOException {
        return new FileWriter(nombreFicheroLog);
    }

    public static PrintWriter abrirFlujoPW(String nombreFicheroLog, FileWriter fw) throws IOException {
        return new PrintWriter(new BufferedWriter(new FileWriter(nombreFicheroLog)));
    }

    public static void cerrarFlujos(FileWriter fw, PrintWriter pw) throws IOException {
        if (fw != null) {
            fw.close();
        }
        if (pw != null) {
            pw.close();
        }
    }

    public static void escribirInformesEnFicheroLog() throws IOException {
        String prefijo = "informes";
        String extension = ".log";
        String nombreFichero = generarNombreFichero(prefijo, extension);
        FileWriter fw = abrirFlujoFW(nombreFichero);
        PrintWriter pw = abrirFlujoPW(nombreFichero,fw);
        CentralNuclear central = CentralNuclear.getInstancia();
        //CELDAS REFRIGERADAS
        central.mostrarCeldasRefrigeradas(pw);
        //KILOS MINEROS
        for (int i = 0; i < central.getlPersonajes().size(); i++) {
            if (central.getlPersonajes().get(i) instanceof Minero) {
                Minero mineroaux = (Minero) central.getlPersonajes().get(i);
                mineroaux.mostrarEscombrosRecogidos(pw);
            }
        }
        //VISITADAS POR ROBOTS
        pw.println(generico.Constantes.INFORMEROBOTS);
        for (int i = 0; i < central.getlPersonajes().size(); i++) {
            if (central.getlPersonajes().get(i) instanceof Robot) {
                Robot robotaux = (Robot) central.getlPersonajes().get(i);
                robotaux.mostrarCeldasVisitadas(pw);                
            }
        }
        cerrarFlujos(fw,pw);
    }

}
