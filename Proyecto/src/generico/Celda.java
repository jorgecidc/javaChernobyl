/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generico;
    import java.util.ArrayList;
    import personajes.*;
/**
 *
 * @author jcidc07
 */
public class Celda {
    /*********** Añade aquí los atributos de la clase Celda *************/
    private int idCelda;
    private ArrayList <Personaje> lPersonajes;
    private boolean refrigerada;
    private int escombros;
    private Puerta puerta;
    private Llave llave;

    public Celda() {
        idCelda=0;
        lPersonajes=new ArrayList <Personaje>();
        refrigerada=false;
        escombros=0;
        puerta=null;
        llave=null;
    }

    public Celda(int idCelda, ArrayList<Personaje> lPersonajes, boolean refrigerada, int escombros, Llave llave) {
        this.idCelda = idCelda;
        this.lPersonajes = lPersonajes;
        this.refrigerada = refrigerada;
        this.escombros = escombros;
        this.puerta = null;
        this.llave = null;
    }
    public Celda(int idCelda) {
        this.idCelda=idCelda;
        lPersonajes=new ArrayList <Personaje>();
        refrigerada=false;
        escombros=Constantes.cero;
        puerta=null;
        llave=null;
    }
    
    
    public int getIdCelda() {
        return idCelda;
    }

    public void setIdCelda(int idCelda) {
        this.idCelda = idCelda;
    }

    public ArrayList<Personaje> getlPersonajes() {
        return lPersonajes;
    }

    public void setlPersonajes(ArrayList<Personaje> lPersonajes) {
        this.lPersonajes = lPersonajes;
    }

    public boolean isRefrigerada() {
        return refrigerada;
    }

    public void setRefrigerada(boolean refrigerada) {
        this.refrigerada = refrigerada;
    }

    public int getEscombros() {
        return escombros;
    }

    public void setEscombros(int escombros) {
        this.escombros = escombros;
    }

    public Puerta getPuerta() {
        return puerta;
    }

    public void setPuerta(Puerta puerta) {
        this.puerta = puerta;
    }

    public Llave getLlave() {
        return llave;
    }

    public void setLlave(Llave llave) {
        this.llave = llave;
    }
    
    
    
    
    /*********** Añade aquí los métodos de la clase Celda *************/
    //Metodo para agregar personajes al ArrayList
    public void agregarPersonaje(Personaje personaje) {
        if (personaje != null) {
            lPersonajes.add(personaje);
        }
    }
    
    //Metodo para buscar un personaje dentro del ArrayList por su nombre
    public Personaje buscarPersonaje(String nombre) {       
        for (Personaje personaje : lPersonajes) {
            if (personaje.getNombre().equalsIgnoreCase(nombre)) {
                return personaje;
            }
        }
        return null;
    }
    
    //Metodo para borrar un personaje del ArrayList por su nombre
    public void borrarPersonaje(String nombre) {
        Personaje personaje = buscarPersonaje(nombre);
        if (personaje != null) {
            lPersonajes.remove(personaje);
        }
    }
    
    //Metodo que me retorna si el ArrayList esta vacio o no
    public boolean existeOperador() {
        for (Personaje personaje : lPersonajes) {
            if (personaje instanceof Operador) {
                return true;
            }
        }
        return false;
    }
    /***************************************************************************************/
    /***************************************************************************************/
    /****************************** MÉTODOS PARA PINTAR MAPA *******************************/
    /***************************************************************************************/
    /***************************************************************************************/
    @Override
    public String toString() {
        String interrogante = "?";
        if  (this.lPersonajes.size() > Constantes.cero){
            if  (this.lPersonajes.size() == Constantes.uno){
                //valueOf convierte a String el char recibido por parámetro
                return String.valueOf(this.lPersonajes.get(Constantes.cero).getMarca());
            }
            else{
                //valueOf convierte a String el int recibido por parámetro
                return String.valueOf(this.lPersonajes.size());
            }        
        }
        else if (getLlave() != null) {
            return interrogante; 
        }
        else{
            return Constantes.CERO;
        }
    }
    public Personaje retornarPersonaje(int pos){
        return lPersonajes.get(pos);
    }
    public int tamannoLista(){
        int tamanno;
        tamanno = lPersonajes.size();
        return tamanno;
    }
    
}
