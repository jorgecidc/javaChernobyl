/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personajes;

import generico.Celda;
import generico.CentralNuclear;
import generico.Llave;
import generico.Puerta;
import java.io.PrintWriter;
import java.util.HashSet;

/**
 *
 * @author jorge
 */
public class Cientifico extends Operador {
    HashSet<Llave> llavesEncima;
    //Constructores
    
    public Cientifico() {
        super();
        llavesEncima = new HashSet<>();
    }

    public Cientifico(String nombre, int turno, int idCeldaActual, char marca) {
        super(nombre, turno, idCeldaActual, marca);
        llavesEncima = new HashSet<>();
    }
    
    //Metodos
    //Acciones que debe realizar el Cientifico
    @Override
    public void realizarAcciones(PrintWriter flujo){
        if (comprobarPuertaSalida()==false) {
            mover(flujo);
            cogerLlave(flujo);
            abrirPuerta(flujo);
        }
    }
    // Metodos para el llavesEncima
    public void insertarLlave(Llave llaveAux){
        llavesEncima.add(llaveAux);
    }
    public void borrarLlave(Llave llaveAux){
        llavesEncima.remove(llaveAux);
    }
    public void cogerLlave(PrintWriter flujo){
        int idCeldaActual = getIdCeldaActual();
        int fila = generico.Utilidad.calcularFila(idCeldaActual);
        int columna = generico.Utilidad.calcularcColumna(idCeldaActual);
        Celda celdaActual = CentralNuclear.getInstancia().getCelda(fila, columna);
        if (celdaActual.getLlave() != null) {
            llavesEncima.add(celdaActual.getLlave());
            flujo.println(getNombre() + generico.Constantes.llaveRecogida+ getIdCeldaActual()+generico.Constantes.punto);
            celdaActual.setLlave(null);            
        }
    }
    public boolean abrirPuerta(PrintWriter flujo){
        boolean puertaAbierta=false;
        int idCeldaActual = getIdCeldaActual();
        int fila = generico.Utilidad.calcularFila(idCeldaActual);
        int columna = generico.Utilidad.calcularcColumna(idCeldaActual);
        Celda celdaActual = CentralNuclear.getInstancia().getCelda(fila, columna);
        if (celdaActual.getPuerta() != null) {
            Puerta puerta = celdaActual.getPuerta();
            for (Llave llave : llavesEncima) {
                if (puerta.getCerradura() == llave.getIdLlave()) {
                    flujo.println(getNombre() + generico.Constantes.puertaAbierta);
                    puertaAbierta = true;
                    puerta.setPuerta(true);
                }
            }
        }
        return puertaAbierta;
    }
    
    //To String

    @Override
    public String toString() {
        return generico.Constantes.cinetificoNombre+getNombre()+generico.Constantes.comaTurno+getTurno()+generico.Constantes.comaIdCeldaActual+getIdCeldaActual()+generico.Constantes.comaMarca+getMarca()+generico.Constantes.comaReconocido+getReconocido()+generico.Constantes.llaveCerrada;
    }
    

    
    
}
