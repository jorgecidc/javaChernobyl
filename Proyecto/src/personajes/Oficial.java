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
public class Oficial extends Kgb {
    //Constructores
    
    public Oficial() {
        super();
    }

    public Oficial(String nombre, int turno, int idCeldaActual, char marca) {
        super(nombre, turno, idCeldaActual, marca);
    }
    
    //Metodos
    //Acciones que debe realizar el oficial
    @Override
    public void realizarAcciones(PrintWriter flujo){
        mover(flujo);
        catalogar(flujo);
        destruirLlave(flujo);
    }
    
    //Metodo para catalogar cuando coincida un oficial con otro personaje 
    public void catalogar(PrintWriter flujo){        
        CentralNuclear central = CentralNuclear.getInstancia();
        int columnaActual, filaActual;
        
        columnaActual = Utilidad.calcularcColumna(getIdCeldaActual());
        filaActual = Utilidad.calcularFila(getIdCeldaActual());
        Celda celdaActual = central.getCelda(filaActual, columnaActual);
        ArrayList<Personaje> listaPersonaje = celdaActual.getlPersonajes();
        Operador operadorAux;
        
        for (Personaje personaje : listaPersonaje) {
            if (personaje instanceof Operador) {
                operadorAux = (Operador) personaje;
                if (operadorAux instanceof Bombero && (operadorAux.getReconocido()!=true)) {
                    flujo.println(getNombre() + generico.Constantes.bomberoHashtag+operadorAux.getNombre()+ generico.Constantes.hashtag+operadorAux.getIdCeldaActual()+generico.Constantes.hashtag+operadorAux.getMarca());
                } else{
                    if (operadorAux instanceof Minero && (operadorAux.getReconocido()!=true)) {
                        flujo.println(getNombre() + generico.Constantes.mineroHashtag+operadorAux.getNombre()+ generico.Constantes.hashtag+operadorAux.getIdCeldaActual()+generico.Constantes.hashtag+operadorAux.getMarca());                  
                    } else{
                        if (operadorAux instanceof Cientifico && (operadorAux.getReconocido()!=true)) {
                           flujo.println(getNombre() + generico.Constantes.cinetificoHashtag+operadorAux.getNombre()+ generico.Constantes.hashtag+operadorAux.getIdCeldaActual()+generico.Constantes.hashtag+operadorAux.getMarca());
                        }
                    }
                }
                operadorAux.setReconocido(true);
            }
        }
    }
    public void destruirLlave(PrintWriter flujo){
        int idCeldaActual = getIdCeldaActual();
        int fila = generico.Utilidad.calcularFila(idCeldaActual);
        int columna = generico.Utilidad.calcularcColumna(idCeldaActual);
        Celda celdaActual = CentralNuclear.getInstancia().getCelda(fila, columna);
        if (celdaActual.getLlave() != null) {
            flujo.println(getNombre() +generico.Constantes.llaveDestruida+ getIdCeldaActual()+generico.Constantes.punto);
            celdaActual.setLlave(null);            
        }
    }
    //To String

    @Override
    public String toString() {
        return generico.Constantes.oficialNombre+getNombre()+generico.Constantes.comaTurno+getTurno()+generico.Constantes.comaIdCeldaActual+getIdCeldaActual()+generico.Constantes.comaMarca+getMarca()+generico.Constantes.llaveCerrada;
    }     
}
