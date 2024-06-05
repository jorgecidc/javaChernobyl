/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generico;

/**
 *
 * @author jorge
 */
public class Llave {
    private int idLlave;
    private int celdaActual;
    
   //Constructores.

    public Llave() {
        idLlave=0;
        celdaActual=0;
    }

    public Llave(int idLlave, int celdaActual) {
        this.idLlave = idLlave;
        this.celdaActual = celdaActual;
    }
    
    //Getter and Setter

    public int getIdLlave() {
        return idLlave;
    }

    public void setIdLlave(int idLlave) {
        this.idLlave = idLlave;
    }

    public int getCeldaActual() {
        return celdaActual;
    }

    public void setCeldaActual(int celdaActual) {
        this.celdaActual = celdaActual;
    }
    
    //To String

    @Override
    public String toString() {
        return Constantes.LlaveLlaveAbierta + Constantes.idLlaveConstante + idLlave + Constantes.idLlaveConstante + celdaActual + Constantes.llaveCerrada;
    }
    
    
    
}
