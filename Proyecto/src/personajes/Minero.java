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
public class Minero extends Operador {

    private ArrayList<Integer> lEscombros = new ArrayList<Integer>();
    //Constructores
    public Minero() {
        super();
    }

    public Minero(String nombre, int turno, int idCeldaActual, char marca) {
        super(nombre, turno, idCeldaActual, marca);
    }
    
    //Metodos
    //Acciones que debe realizar el Minero
    @Override
    public void realizarAcciones(PrintWriter flujo){
        if (comprobarPuertaSalida()==false) {
            mover(flujo);
        }
        desescombrar(flujo);
    }
    
    //Metodo para desescombrar una celda y almacenar los kilso recogidos
    public void desescombrar(PrintWriter flujo) {
        CentralNuclear central = CentralNuclear.getInstancia();
        int origen = getIdCeldaActual();
        int filaOrigen = Utilidad.calcularFila(origen);
        int colOrigen = Utilidad.calcularcColumna(origen);
        Celda celdaOrigen = central.getCelda(filaOrigen, colOrigen);
        int escombros = celdaOrigen.getEscombros();        
        lEscombros.add(escombros);
        celdaOrigen.setEscombros(generico.Constantes.cero);
        int totalEscombros = generico.Constantes.cero;
        totalEscombros += escombros;
        int sumaEscombros = generico.Constantes.cero;
        for (int i = 0; i < lEscombros.size(); i++) {
            sumaEscombros = sumaEscombros + lEscombros.get(i);
        }
        if (totalEscombros > generico.Constantes.cero) {
            flujo.println(getNombre() + generico.Constantes.puntosLlevo + sumaEscombros + generico.Constantes.kilosDeEscombros);
        }
    }
    public void mostrarEscombrosRecogidos(PrintWriter flujo){
        flujo.println(generico.Constantes.INFORMEMINERO);
        int contador = 0;
        for (int i = 0; i < lEscombros.size(); i++) {
            contador += lEscombros.get(i);
        }
        flujo.println(getNombre()+generico.Constantes.escombrosRecogidos+contador);
    }
    
    //To String

    @Override
    public String toString() {
        return generico.Constantes.mineroNombre+getNombre()+generico.Constantes.comaTurno+getTurno()+generico.Constantes.comaIdCeldaActual+getIdCeldaActual()+generico.Constantes.comaMarca+getMarca()+generico.Constantes.comaReconocido+getReconocido()+generico.Constantes.llaveCerrada;
    }
    
    
    
}
