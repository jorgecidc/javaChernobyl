/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generico;

import ficheros.Escritura;
import ficheros.Lectura;
import personajes.Personaje;
import personajes.Oficial;
import personajes.Minero;
import generico.Llave;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import personajes.Cientifico;
import personajes.Bombero;
import personajes.Robot;
import personajes.Voluntario;

/**
 *
 * @author jorge
 */
public class Proyecto {
    public static void tratamientoPersonaje(Personaje personaje, PrintWriter flujo){
        CentralNuclear central = CentralNuclear.getInstancia();
        int turnoP = personaje.getTurno();
        if  (turnoP == central.getTurno()){
            personaje.realizarAcciones(flujo);
            personaje.setTurno(turnoP + 1);
        }
    }
public static void simulacion() throws IOException{
        Personaje personajeEnTratamiento;
        CentralNuclear central = CentralNuclear.getInstancia();
        
        //Abrimos flujos para escritura de fichero simulación
        String prefijo = "simulacionEntregaFinal";
        String extension = ".log";
        String nombreFichero = Escritura.generarNombreFichero(prefijo,extension);
        FileWriter fw = Escritura.abrirFlujoFW(nombreFichero);
        PrintWriter pw = Escritura.abrirFlujoPW(nombreFichero,fw);

        //ESCOMBROS
        central.inicializarEscombros();        
        
        pw.println(Constantes.TURNO+central.getTurno());
        central.pintarMatriz(pw);
        //SIMULACIÖN
        for(int i = 0;i < central.getTurnosNecesarios();i++){    
            central.setTurno(central.getTurno()+1);
            
            pw.println(Constantes.TURNO+central.getTurno());
            
            for(int j = 0;j < central.getSizeListaPersonajes();j++){
                personajeEnTratamiento = central.getPersonajeDeListaPorPosicion(j);
                tratamientoPersonaje(personajeEnTratamiento, pw);      
            }
            
            central.pintarMatriz(pw);
        }
        Escritura.cerrarFlujos(fw, pw);
        Escritura.escribirInformesEnFicheroLog();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {        
        boolean cargado;
        CentralNuclear central = CentralNuclear.getInstancia();
        cargado = Lectura.cargarDatosSimulacion(central);
        if  (cargado){
            simulacion();
            System.out.println(Constantes.MENSAJEOK);
        }
    }
}

        
