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
public class Puerta {
    private int idCelda;
    private boolean puerta;
    private int cerradura;
    
    //CONSTRUCTORES
    public Puerta() {
        idCelda=0;
        puerta=false;
        cerradura = 0;
    }

    public Puerta(int idCelda, int cerradura) {
        this.idCelda = idCelda;
        puerta = false;
        this.cerradura = cerradura;
    }
    
    //GETTER Y SETTER

    public int getIdCelda() {
        return idCelda;
    }

    public void setIdCelda(int idCelda) {
        this.idCelda = idCelda;
    }

    public boolean isAbierta() {
        return puerta;
    }

    public void setPuerta(boolean puerta) {
        this.puerta = puerta;
    }

    public int getCerradura() {
        return cerradura;
    }

    public void setCerradura(int cerradura) {
        this.cerradura = cerradura;
    }
    
    
    
    
}
