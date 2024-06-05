/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personajes;

import generico.Celda;
import generico.CentralNuclear;
import generico.Utilidad;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author jorge
 */
public abstract class Personaje {
    private String nombre;
    private int turno;
    private int idCeldaActual;
    private char marca;
    private ArrayList<Character> lRuta;

    
    //Constructores

    public Personaje() {
        nombre="";
        turno=0;
        idCeldaActual=0;
        marca=0;
        lRuta =new ArrayList <>();
    }

    public Personaje(String nombre, int turno, int idCeldaActual, char marca) {
        this.nombre = nombre;
        this.turno = turno;
        this.idCeldaActual = idCeldaActual;
        this.marca = marca;
        lRuta =new ArrayList <>();
    }
    
    //Getter and Setter

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTurno() {
        return turno;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }

    public int getIdCeldaActual() {
        return idCeldaActual;
    }

    public void setIdCeldaActual(int idCeldaActual) {
        this.idCeldaActual = idCeldaActual;
    }

    public char getMarca() {
        return marca;
    }

    public void setMarca(char marca) {
        this.marca = marca;
    }
    
    
    //Metodos
    
    public abstract void realizarAcciones(PrintWriter flujo);
    
    //Metodo para calcular la siguiente celda a la que dirigirse segun la ruta
    public int calcularSiguienteIdCelda(){       
        int siguienteCelda = idCeldaActual;
        char movimiento = lRuta.get(generico.Constantes.cero);
        if (movimiento == generico.Constantes.norte) {
            siguienteCelda -= generico.Constantes.ocho;
        }
        else if (movimiento == generico.Constantes.sur) {
            siguienteCelda += generico.Constantes.ocho;
        }
        else if (movimiento == generico.Constantes.este) {
            siguienteCelda += generico.Constantes.uno;
        }
        else if (movimiento == generico.Constantes.oeste) {
            siguienteCelda -= generico.Constantes.uno;
        }
        return siguienteCelda;    
    }
    
    //Metodo para cargar los movimientos en la ruta
    public void cargarMovimientos(char[] vector){
        for (char c : vector) {
            lRuta.add(c);
        }
    }
    
    //Metodo para borrar el primer movimiento de la ruta
    public void borrarPrimerMovimiento(){
        lRuta.remove(generico.Constantes.cero);
    }
    
    //Metodo para agregar un movimiento al final de la ruta
    public void agregarMovimientoAlFinal(char movimiento){
        lRuta.add(movimiento);
    }
    
    //Metodo para ver si la ruta esta vacia
    public boolean estaVacio(){
        return lRuta.isEmpty();
    }
    
    //Metodo para mover a los personajes
    public void mover(PrintWriter flujo){        
        CentralNuclear central = CentralNuclear.getInstancia();
        Celda celdaDestino;
        int origen = idCeldaActual;
        int destino = calcularSiguienteIdCelda();
        int filaDestino = Utilidad.calcularFila(destino);
        int colDestino = Utilidad.calcularcColumna(destino);
        int filaOrigen = Utilidad.calcularFila(origen);
        int colOrigen = Utilidad.calcularcColumna(origen);
        
        Celda celdaOrigen = central.getCelda(filaOrigen, colOrigen);
        
        boolean hayCamino = central.hayCamino(origen, destino);
        //Si existe camino entre las celdas agrego el personaje en la nueva celda y lo borro de la antigua
        if (hayCamino == true) {
            celdaOrigen.borrarPersonaje(getNombre());
            celdaDestino = central.getCelda(filaDestino, colDestino);
            celdaDestino.agregarPersonaje(this);
            idCeldaActual=destino;
        }
        //Si no existe camino lo muestro junto a la direccion que no puede moverse
        if (hayCamino == false){
            flujo.println(getNombre()+generico.Constantes.noPuedo+lRuta.get(generico.Constantes.cero)+generico.Constantes.cerrarParentesis);
        }
        
        borrarPrimerMovimiento();
    }
        
}
