/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab9p1_byronlemuz;
import java.util.ArrayList;
/**
 *
 * @author lesly
 */
public class Ventanilla {
    
    private ArrayList<Integer> colaPrioridad;
    private ArrayList<Integer> colaRegular;
    private int[] ventanilla;
    private char[] clientesEnVentanilla;
    private int tamano;

    public Ventanilla(int tamano) {
        this.tamano = tamano;
        this.colaPrioridad = new ArrayList<Integer>();
        this.colaRegular = new ArrayList<Integer>();
        this.ventanilla = new int[tamano];
        this.clientesEnVentanilla = new char[tamano];

        for (int i = 0; i < tamano; i++) {
            this.ventanilla[i] = 0;
            this.clientesEnVentanilla[i] = ' ';
        }
    }

    public ArrayList<Integer> getColaPrioridad() {
        return colaPrioridad;
    }

    public void setColaPrioridad(ArrayList<Integer> colaPrioridad) {
        this.colaPrioridad = colaPrioridad;
    }

    public ArrayList<Integer> getColaRegular() {
        return colaRegular;
    }

    public void setColaRegular(ArrayList<Integer> colaRegular) {
        this.colaRegular = colaRegular;
    }

    public int[] getVentanilla() {
        return ventanilla;
    }

    public void setVentanilla(int[] ventanilla) {
        this.ventanilla = ventanilla;
    }

    public char[] getClientesEnVentanilla() {
        return clientesEnVentanilla;
    }

    public void setClientesEnVentanilla(char[] clientesEnVentanilla) {
        this.clientesEnVentanilla = clientesEnVentanilla;
    }

    public void agregarClienteRegular(int tiempo, int indiceVentanilla) {
        colaRegular.add(tiempo);
    }

    public void agregarClientePreferencial(int tiempo, int indiceVentanilla) {
        colaPrioridad.add(tiempo);
    }

    public int encontrarVentanillaDisponible() {
        for (int i = 0; i < ventanilla.length; i++) {
            if (ventanilla[i] == 0) {
                return i;
            }
        }
        return -1;
    }

    public void actualizarVentanillas() {
        for (int i = 0; i < ventanilla.length; i++) {
            if (ventanilla[i] > 0) {
                ventanilla[i]--;
                if (ventanilla[i] == 0) {
                    clientesEnVentanilla[i] = ' ';
                }
            }
        }
    }

    public void correrSimulacion(int tiempoTotal, int indiceventanilla, int indiceVentanilla) {
    int tiempoActual = 0;
    while (tiempoActual < tiempoTotal) {
        // Agregar clientes a las colas
        int numClientes = (int)(Math.random() * 4); // máximo 3 clientes por iteración
        for (int i = 0; i < numClientes; i++) {
            if (Math.random() < 0.3) { // 30% de probabilidad de ser cliente preferencial
                agregarClientePreferencial((int)(Math.random() * 5) + 1, indiceVentanilla); // tiempo entre 1 y 5 minutos
            } else {
                agregarClienteRegular((int)(Math.random() * 10) + 1, indiceventanilla); // tiempo entre 1 y 10 minutos
            }
        }
        
        // Asignar clientes a las ventanillas disponibles
        for (int i = 0; i < ventanilla.length; i++) {
            if (ventanilla[i] == 0) {
                int clienteIndex = (i);
                if (clienteIndex != -1) {
                    clientesEnVentanilla[i] = colaPrioridad.contains(clienteIndex) ? 'P' : 'R';
                    ventanilla[i] = colaPrioridad.contains(clienteIndex) ? colaPrioridad.get(clienteIndex) : colaRegular.get(clienteIndex - colaPrioridad.size());
                    if (colaPrioridad.contains(clienteIndex)) {
                        colaPrioridad.remove(clienteIndex);
                    } else {
                        colaRegular.remove(clienteIndex - colaPrioridad.size());
                    }
                }
            }
        }
        
        // Actualizar ventanillas
        actualizarVentanillas();
        
        tiempoActual++;
    }
}

    }
        

    