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
public class Bombero extends Operador {
    private int bateriaRefrigerador;
    //Contructores

    public Bombero() {
        super();
        bateriaRefrigerador=generico.Constantes.cinco;
    }

    public Bombero(String nombre, int turno, int idCeldaActual, char marca) {
        super(nombre, turno, idCeldaActual, marca);
        bateriaRefrigerador=generico.Constantes.cinco;
    }
    
    //Getter y Setter

    public int getBateriaRefrigerador() {
        return bateriaRefrigerador;
    }

    public void setBateriaRefrigerador(int bateriaRefrigerador) {
        this.bateriaRefrigerador = bateriaRefrigerador;
    }
    
    //Metodos
    //Acciones que realiza el bombero
    @Override
     public void realizarAcciones(PrintWriter flujo){
        if (comprobarPuertaSalida()==false) {
            if (bateriaRefrigerador == generico.Constantes.cero) {
                recargarRefrigerador(flujo);
            }
            else{
               mover(flujo);
               refrigerar();
            }
                        
        }            
     }

     //Metodo para poner la bateria del refrigerador a 5
     private void recargarRefrigerador(PrintWriter flujo){         
         if (bateriaRefrigerador == generico.Constantes.cero) {
             flujo.println(getNombre()+generico.Constantes.recargandoBateria);
         }
         bateriaRefrigerador = generico.Constantes.cinco;
         
     }
     
     //Metodo para refrigerar la celda en la que se encuentra el bombero
     private void refrigerar(){         
        CentralNuclear central = CentralNuclear.getInstancia();
        int origen = getIdCeldaActual();
        int filaOrigen = Utilidad.calcularFila(origen);
        int colOrigen = Utilidad.calcularcColumna(origen);        
        Celda celdaOrigen = central.getCelda(filaOrigen, colOrigen);
        celdaOrigen.setRefrigerada(true);
        bateriaRefrigerador= bateriaRefrigerador - generico.Constantes.uno;
     }
     //To String

    @Override
    public String toString() {
        return generico.Constantes.bomberoNombre+getNombre()+generico.Constantes.comaTurno+getTurno()+generico.Constantes.comaIdCeldaActual+getIdCeldaActual()+generico.Constantes.comaMarca+getMarca()+generico.Constantes.comaReconocido+getReconocido()+generico.Constantes.comaCargaRefrigerador+getBateriaRefrigerador()+generico.Constantes.llaveCerrada;
    }
     
     
}
