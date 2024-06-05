/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ficheros;
import generico.CentralNuclear;
import generico.Llave;
import generico.Puerta;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import personajes.Bombero;
import personajes.Cientifico;
import personajes.Minero;
import personajes.Oficial;
import personajes.Robot;
import personajes.Voluntario;

public class Lectura {
    
    private static String archivo = "inicio.txt";
    
    
    public Lectura(String archivo) {
        this.archivo = "inicio.txt";
    }

    public static List<String> leerArchivo() {
        List<String> lineas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.startsWith("--")) { // Ignorar comentarios
                    lineas.add(linea);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo de carga: " + e.getMessage());
        }
        return lineas;
    }

    public static boolean cargarDatosSimulacion(CentralNuclear central) {
        List<String> lineas = leerArchivo();
        boolean correcto = true;
        for (String linea : lineas) {
            String[] tokens = linea.split("#");
            String tipoObjeto = tokens[0];

            switch (tipoObjeto) {
                case "CENTRAL":
                    int turnosNecesarios = Integer.parseInt(tokens[1]);
                    central.setTurnosNecesarios(turnosNecesarios);
                    break;
                case "CIENTIFICO":
                    // Obtener los datos de configuración del Científico
                    // y crear un objeto Cientifico para agregarlo a la central
                    String nombreCientifico = tokens[1];
                    int turnoCientifico = Integer.parseInt(tokens[2]);
                    int celdaActualCientifico = Integer.parseInt(tokens[3]);
                    char marcaCientifico = tokens[4].charAt(0);
                    char[] rutaCientifico = new char[tokens[5].length()];
                    for (int i = 0; i < tokens[5].length(); i++) {
                        rutaCientifico[i] = tokens[5].charAt(i);
                    }
                    Cientifico cientifico = new Cientifico(nombreCientifico, turnoCientifico,
                    celdaActualCientifico, marcaCientifico);
                    central.insPersonaje(cientifico);
                    central.insPersonajeEnListaPersonajes(cientifico);
                    cientifico.cargarMovimientos(rutaCientifico);
                    break;
                case "MINERO":
                    // Obtener los datos de configuración del Minero
                    // y crear un objeto Minero para agregarlo a la central
                    String nombreMinero = tokens[1];
                    int turnoMinero = Integer.parseInt(tokens[2]);
                    int celdaActualMinero = Integer.parseInt(tokens[3]);
                    char marcaMinero = tokens[4].charAt(0);
                    char[] rutaMinero = new char[tokens[5].length()];
                    for (int i = 0; i < tokens[5].length(); i++) {
                        rutaMinero[i] = tokens[5].charAt(i);
                    }
                    Minero minero = new Minero(nombreMinero, turnoMinero, celdaActualMinero,
                    marcaMinero);
                    central.insPersonaje(minero);
                    central.insPersonajeEnListaPersonajes(minero);
                    minero.cargarMovimientos(rutaMinero);
                    break;
                case "BOMBERO":
                    // Obtener los datos de configuración del Bombero
                    // y crear un objeto Bombero para agregarlo a la central
                    String nombreBombero = tokens[1];
                    int turnoBombero = Integer.parseInt(tokens[2]);
                    int celdaActualBombero = Integer.parseInt(tokens[3]);
                    char marcaBombero = tokens[4].charAt(0);
                    char[] rutaBombero = new char[tokens[5].length()];
                    for (int i = 0; i < tokens[5].length(); i++) {
                        rutaBombero[i] = tokens[5].charAt(i);
                    }
                    Bombero bombero = new Bombero(nombreBombero, turnoBombero, celdaActualBombero,
                    marcaBombero);
                    central.insPersonaje(bombero);
                    central.insPersonajeEnListaPersonajes(bombero);
                    bombero.cargarMovimientos(rutaBombero);
                    break;
                case "OFICIAL":
                    // Obtener los datos de configuración del Oficial
                    // y crear un objeto Oficial para agregarlo a la lista de oficiales
                    String nombreOficial = tokens[1];
                    int turnoOficial = Integer.parseInt(tokens[2]);
                    int celdaActualOficial = Integer.parseInt(tokens[3]);
                    char marcaOficial = tokens[4].charAt(0);
                    char[] rutaOficial = new char[tokens[5].length()];
                    for (int i = 0; i < tokens[5].length(); i++) {
                        rutaOficial[i] = tokens[5].charAt(i);
                    }
                    Oficial oficial = new Oficial(nombreOficial, turnoOficial, celdaActualOficial,
                            marcaOficial);
                    central.insPersonaje(oficial);
                    central.insPersonajeEnListaPersonajes(oficial);
                    oficial.cargarMovimientos(rutaOficial);
                    break;
                case "VOLUNTARIO":
                    // Obtener los datos de configuración del Voluntario
                    // y crear un objeto Voluntario para agregarlo a la lista de voluntarios
                    String nombreVoluntario = tokens[1];
                    int turnoVoluntario = Integer.parseInt(tokens[2]);
                    int celdaActualVoluntario = Integer.parseInt(tokens[3]);
                    char marcaVoluntario = tokens[4].charAt(0);
                    char[] rutaVoluntario = new char[tokens[5].length()];
                    for (int i = 0; i < tokens[5].length(); i++) {
                        rutaVoluntario[i] = tokens[5].charAt(i);
                    }
                    Voluntario voluntario = new Voluntario(nombreVoluntario, turnoVoluntario, celdaActualVoluntario,
                            marcaVoluntario);
                    central.insPersonaje(voluntario);
                    central.insPersonajeEnListaPersonajes(voluntario);
                    voluntario.cargarMovimientos(rutaVoluntario);
                    break;
                case "ROBOT":
                    // Obtener los datos de configuración del Robot
                    // y crear un objeto Robot para agregarlo a la lista de robots
                    String nombreRobot = tokens[1];
                    int turno = Integer.parseInt(tokens[2]);
                    int celdaActual = Integer.parseInt(tokens[3]); 
                    char marca = tokens[4].charAt(0);
                    Robot robot = new Robot(nombreRobot, turno, celdaActual, marca);
                    String rutaCifrada = tokens[5];
                    ((Robot) robot).descifrarRuta(rutaCifrada);
                    central.insPersonaje(robot);
                    central.insPersonajeEnListaPersonajes(robot);
                    break;
                case "LLAVE":
                    // Obtener los datos de configuración de la Llave
                    // y crear un objeto Llave para agregarlo a la lista de llaves
                    int idLlave = Integer.parseInt(tokens[1]);
                    int idCeldaActualLlave = Integer.parseInt(tokens[2]);
                    Llave llave = new Llave(idLlave, idCeldaActualLlave);
                    central.insLlaveEnCelda(llave);
                    break;
                case "PUERTA":
                    // Obtener los datos de configuración de la Puerta
                    // y crear un objeto Puerta para agregarlo a la lista de puertas
                    int idCelda = Integer.parseInt(tokens[1]);
                    int llaveCerradura = Integer.parseInt(tokens[2]);
                    Puerta puerta = new Puerta(idCelda, llaveCerradura);
                    central.insPuertaSalida(puerta);
                    break;
                default:
                    System.err.println("Error: Objeto desconocido en el archivo de carga: " + tipoObjeto);
                    correcto = false;
                    break;
            }
        }
        return correcto;
    }
}
