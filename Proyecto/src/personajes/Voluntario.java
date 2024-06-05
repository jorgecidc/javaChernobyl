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
import personajes.Kgb;

/**
 *
 * @author jorge
 */
public class Voluntario extends Kgb {
    //Constructores
    
    public Voluntario() {
        super();
    }

    public Voluntario(String nombre, int turno, int idCeldaActual, char marca) {
        super(nombre, turno, idCeldaActual, marca);
    }
    
    //Metodos
    //Acciones que debe realizar el voluntario
    @Override
    public void realizarAcciones(PrintWriter flujo){
       mover(flujo);
       catalogar(flujo);
    }
    
    //Metodo para catalogar cuando un voluntario se encuentra con otro personaje en la misma celda
    public void catalogar(PrintWriter flujo){        
        CentralNuclear central = CentralNuclear.getInstancia();
        int columnaActual, filaActual;
        
        columnaActual = Utilidad.calcularcColumna(getIdCeldaActual());
        filaActual = Utilidad.calcularFila(getIdCeldaActual());
        Celda celdaActual = central.getCelda(filaActual, columnaActual);
        ArrayList<Personaje> listaPersonaje = celdaActual.getlPersonajes();
        Operador operadorAux;
        for (Personaje personaje : listaPersonaje) {
            if (personaje instanceof Operador ) {
                operadorAux = (Operador) personaje;
                if (operadorAux.getReconocido() != true) {
                    flujo.println(getNombre() + generico.Constantes.dosPuntos+operadorAux.getNombre()+ generico.Constantes.hashtag+operadorAux.getIdCeldaActual()+generico.Constantes.hashtag+operadorAux.getMarca());
                
                    operadorAux.setReconocido(true);
                }                
            }
        }
    }
    //To String
    

    @Override
    public String toString() {
        return generico.Constantes.voluntarioNombre+getNombre()+generico.Constantes.comaTurno+getTurno()+generico.Constantes.comaIdCeldaActual+getIdCeldaActual()+generico.Constantes.comaMarca+getMarca()+generico.Constantes.llaveCerrada;
    }
    
    
    
}
