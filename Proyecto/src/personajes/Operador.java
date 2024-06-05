/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personajes;

import generico.Celda;
import generico.CentralNuclear;
import generico.Utilidad;

/**
 *
 * @author jorge
 */
public abstract class Operador extends Personaje {
    private boolean reconocido;
    private boolean enPuertaDeSalida;
    
    //Constructores

    public Operador() {
        super();
        reconocido=false;
        enPuertaDeSalida = false;
    }

    public Operador(String nombre, int turno, int idCeldaActual, char marca) {
        super(nombre,turno,idCeldaActual,marca);
        reconocido=false;
        enPuertaDeSalida = false;
    }
    
    //Getter and Setter

    public boolean getReconocido() {
        return reconocido;
    }

    public void setReconocido(boolean reconocido) {
        this.reconocido = reconocido;
    }
    
    //To String

    @Override
    public String toString() {
        return generico.Constantes.operadorLlave + generico.Constantes.reconocidoIgual + reconocido + generico.Constantes.llaveCerrada;
    }

    //METODOS
    //Metodo para comprobar la si en la celda en la que se encuentra tiene puerta de salida o no
    public boolean comprobarPuertaSalida(){        
        boolean hayPuerta=false;
        CentralNuclear central = CentralNuclear.getInstancia();
        
        int origen = getIdCeldaActual();
        int filaOrigen = Utilidad.calcularFila(origen);
        int colOrigen = Utilidad.calcularcColumna(origen);
        Celda celdaOrigen = central.getCelda(filaOrigen, colOrigen);
        if (celdaOrigen.getPuerta() != null) {
            hayPuerta = true;
            enPuertaDeSalida = true;
        }
        return hayPuerta;
    }
}
