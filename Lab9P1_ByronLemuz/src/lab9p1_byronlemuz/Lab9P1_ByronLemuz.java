  /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lab9p1_byronlemuz;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
/**
 *
 * @author lesly
 */
public class Lab9P1_ByronLemuz {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Scanner lea = new Scanner(System.in);

        System.out.print("Ingrese el número de clientes a generar: ");
        int numClientes = lea.nextInt();
        System.out.print("Ingrese el número de escritorios disponibles: ");
        int numVentanillas = lea.nextInt();
        System.out.print("Tiempo total: ");
        int tiempoTotal = lea.nextInt();

        ArrayList<Integer> colaPrioridad = new ArrayList<>();
        ArrayList<Integer> colaRegular = new ArrayList<>();
        Ventanilla ventanilla = new Ventanilla(numVentanillas);

        Random random = new Random();

        // Generar clientes aleatorios y agregarlos a las colas correspondientes
        for (int i = 0; i < numClientes; i++) {
            int tiempoConsulta = random.nextInt(5) + 1; // Generar tiempo aleatorio entre 1 y 5
            boolean esPrioridad = random.nextBoolean(); // Generar booleano aleatorio
            if (esPrioridad) {
                colaPrioridad.add(tiempoConsulta);
            } else {
                colaRegular.add(tiempoConsulta);
            }
        }

        // Correr simulación
        for (int i = 0; i < tiempoTotal; i++) {
            System.out.println("Iteración #" + (i + 1));
            ventanilla.actualizarVentanillas();
            while (ventanilla.encontrarVentanillaDisponible() != -1) { // Mientras haya ventanillas disponibles
                int indiceVentanilla = ventanilla.encontrarVentanillaDisponible();
                if (!colaPrioridad.isEmpty()) { // Atender cliente de cola de prioridad si hay alguno
                    int tiempoConsulta = colaPrioridad.remove(0);
                    ventanilla.agregarClientePreferencial(tiempoConsulta, indiceVentanilla);
                } else if (!colaRegular.isEmpty()) { // Atender cliente de cola regular si hay alguno
                    int tiempoConsulta = colaRegular.remove(0);
                    ventanilla.agregarClienteRegular(tiempoConsulta, indiceVentanilla);
                } else { // No hay clientes para atender, terminar la simulación
                    break;
                }
            }
            System.out.println(ventanilla.toString()); // Imprimir estado de las ventanillas
            if (colaPrioridad.isEmpty() && colaRegular.isEmpty()) { // No hay más clientes, terminar simulación
                break;
            }
        }

        lea.close();
    }
}

    
    

