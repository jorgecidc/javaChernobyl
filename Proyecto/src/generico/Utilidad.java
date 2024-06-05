/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generico;

/**
 *
 * @author jcidc07
 */
public class Utilidad {
    //METODOS
    //Metodo para calcular la fila
    public static int calcularFila(int idCelda){
        int fila;
        fila = idCelda / Constantes.ocho;
        return fila;
    }
    
    //Metodo para calcular la columna
    public static int calcularcColumna(int idCelda){
        int columna;
        columna = idCelda % Constantes.ocho;
        return columna;
    }
}
