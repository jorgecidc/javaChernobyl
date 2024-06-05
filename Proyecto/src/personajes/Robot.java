/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personajes;

import generico.Celda;
import generico.CentralNuclear;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author jcidc07
 */
public class Robot extends Personaje{
    private ArrayList<Integer> idCeldasRegistradas;

    public Robot() {
        idCeldasRegistradas = new ArrayList<>();;
    }

    public Robot(String nombre, int turno, int idCeldaActual, char marca) {
        super(nombre, turno, idCeldaActual, marca);
        idCeldasRegistradas = new ArrayList<>();;
    }

    public Robot(String nombreRobot, int celdaActualRobot, String rutaRobot) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<Integer> getIdCeldasRegistradas() {
        return idCeldasRegistradas;
    }

    public void setIdCeldasRegistradas(ArrayList<Integer> idCeldasRegistradas) {
        this.idCeldasRegistradas = idCeldasRegistradas;
    }
    
    public void descifrarRuta(String ruta){
        char clave;
        for (int i = 0; i < ruta.length(); i++) {
            clave = ruta.charAt(i);
            if (clave == 'N' || clave == 'S'|| clave == 'E'|| clave == 'O') {
                agregarMovimientoAlFinal(clave);
            }            
        }
    }
    
    public void registrarMovimiento(int celdaAnterior){
        if (celdaAnterior == getIdCeldaActual()) {
            idCeldasRegistradas.add(-1);
        }
        else{
            idCeldasRegistradas.add(getIdCeldaActual());
        }
        
    }
    public void mostrarCeldasVisitadas(PrintWriter flujo){
        flujo.println(generico.Constantes.informe+getNombre());
        for (int i = 0; i < idCeldasRegistradas.size(); i++) {
            flujo.print(generico.Constantes.corcheteAbierto+idCeldasRegistradas.get(i)+generico.Constantes.corcheteCerrado);
        }
        flujo.print("\n");
    }
    public void escanear(PrintWriter flujo){
        int contador=0;
        int fila = generico.Utilidad.calcularFila(getIdCeldaActual());
        int columna = generico.Utilidad.calcularcColumna(getIdCeldaActual());
        CentralNuclear central = CentralNuclear.getInstancia();
        ArrayList <Celda> celdasAdyacentes = central.getAdyacencia(fila, columna);
        for (int i = 0; i < celdasAdyacentes.size(); i++) {
            Celda celdaAux = celdasAdyacentes.get(i);
            contador += celdaAux.getlPersonajes().size();
        }
        if (contador != 0) {
            flujo.println(getNombre()+ generico.Constantes.detectadas + contador + generico.Constantes.personasInfluencia);
        }      
    }
    @Override
     public void realizarAcciones(PrintWriter flujo){
         int celdaAnterior;
         celdaAnterior = getIdCeldaActual();
         mover(flujo);
         registrarMovimiento(celdaAnterior);
         escanear(flujo);
     }
    
    
}
