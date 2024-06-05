/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personajes;
/**
 *
 * @author jorge
 */
public abstract class Kgb extends Personaje {
    //Constructores
    
    public Kgb() {
        super();
    }

    public Kgb(String nombre, int turno, int idCeldaActual, char marca) {
        super(nombre, turno, idCeldaActual, marca);
    }
}
