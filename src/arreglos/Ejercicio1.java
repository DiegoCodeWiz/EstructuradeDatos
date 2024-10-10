/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package arreglos;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author Diego
 */
public class Ejercicio1 {
  public static void main(String[] args) {
        boolean salir = false;

        while (!salir) {
            String[] opciones = {"Generar 30 números aleatorios y realizar operaciones", 
                                 "Clasificación de puntajes de estudiantes", 
                                 "Salir"};
            String opcionSeleccionada = (String) JOptionPane.showInputDialog(null, 
                "Seleccione una opción", 
                "Menú Principal", 
                JOptionPane.QUESTION_MESSAGE, 
                null, 
                opciones, 
                opciones[0]);

            if (opcionSeleccionada == null || opcionSeleccionada.equals("Salir")) {
                salir = true;
                JOptionPane.showMessageDialog(null, "Saliendo del programa...");
            } else if (opcionSeleccionada.equals(opciones[0])) {
                OperacionesVector operaciones = new OperacionesVector();
                operaciones.mostrarResultados();
            } else if (opcionSeleccionada.equals(opciones[1])) {
                ClasificacionPuntajes puntajes = new ClasificacionPuntajes();
                puntajes.mostrarClasificacion();
            }
        }
    }
}

class OperacionesVector {

    private int[] vector;
    private int tamanio = 30;

    public OperacionesVector() {
        vector = new int[tamanio];
        Random random = new Random();
        for (int i = 0; i < tamanio; i++) {
            vector[i] = random.nextInt(30) + 1;  // Números entre 1 y 30
        }
        Arrays.sort(vector);  // Ordenar el vector
    }

    public int obtenerMaximo() {
        return Arrays.stream(vector).max().getAsInt();
    }

    public int obtenerMinimo() {
        return Arrays.stream(vector).min().getAsInt();
    }

    public double calcularMedia() {
        return Arrays.stream(vector).average().orElse(0);
    }

    public int calcularModa() {
        Map<Integer, Integer> frecuencias = new HashMap<>();
        for (int num : vector) {
            frecuencias.put(num, frecuencias.getOrDefault(num, 0) + 1);
        }
        int moda = vector[0];
        int maxFrecuencia = 0;
        for (Map.Entry<Integer, Integer> entry : frecuencias.entrySet()) {
            if (entry.getValue() > maxFrecuencia) {
                maxFrecuencia = entry.getValue();
                moda = entry.getKey();
            }
        }
        return moda;
    }

    public double calcularMediana() {
        if (tamanio % 2 == 0) {
            return (vector[tamanio / 2 - 1] + vector[tamanio / 2]) / 2.0;
        } else {
            return vector[tamanio / 2];
        }
    }

    public void calcularFrecuencias() {
        Map<Integer, Integer> frecuencias = new HashMap<>();
        for (int i = 1; i <= 10; i++) {
            frecuencias.put(i, 0);
        }
        for (int num : vector) {
            if (num >= 1 && num <= 10) {
                frecuencias.put(num, frecuencias.get(num) + 1);
            }
        }
        StringBuilder resultadoFrecuencias = new StringBuilder("Número | Cantidad | Porcentaje\n");
        for (int i = 1; i <= 10; i++) {
            int cantidad = frecuencias.get(i);
            double porcentaje = (double) cantidad / tamanio * 100;
            resultadoFrecuencias.append(String.format("%6d | %8d | %10.2f%%\n", i, cantidad, porcentaje));
        }
        JOptionPane.showMessageDialog(null, resultadoFrecuencias.toString(), "Frecuencias", JOptionPane.INFORMATION_MESSAGE);
    }

    public void mostrarResultados() {
        String resultado = "Vector generado y ordenado: " + Arrays.toString(vector) + "\n" +
                           "Número más grande: " + obtenerMaximo() + "\n" +
                           "Número más pequeño: " + obtenerMinimo() + "\n" +
                           "Media: " + calcularMedia() + "\n" +
                           "Moda: " + calcularModa() + "\n" +
                           "Mediana: " + calcularMediana();
        JOptionPane.showMessageDialog(null, resultado, "Resultados de Operaciones", JOptionPane.INFORMATION_MESSAGE);
        calcularFrecuencias();
    }
}

class ClasificacionPuntajes {

    private int[] puntajes;
    private int numEstudiantes;

    public ClasificacionPuntajes() {
        String input = JOptionPane.showInputDialog(null, "Ingrese la cantidad de estudiantes:");
        numEstudiantes = Integer.parseInt(input);
        puntajes = new int[numEstudiantes];
        Random random = new Random();
        for (int i = 0; i < numEstudiantes; i++) {
            puntajes[i] = random.nextInt(21);  // Puntajes entre 0 y 20
        }
    }

    public void mostrarClasificacion() {
        int deficientes = 0, regulares = 0, buenos = 0, excelentes = 0;

        for (int puntaje : puntajes) {
            if (puntaje >= 0 && puntaje <= 5) {
                deficientes++;
            } else if (puntaje >= 6 && puntaje <= 10) {
                regulares++;
            } else if (puntaje >= 11 && puntaje <= 15) {
                buenos++;
            } else if (puntaje >= 16 && puntaje <= 20) {
                excelentes++;
            }
        }

        String resultado = "Puntajes generados: " + Arrays.toString(puntajes) + "\n" +
                           "0-5 Deficientes: " + deficientes + "\n" +
                           "6-10 Regulares: " + regulares + "\n" +
                           "11-15 Buenos: " + buenos + "\n" +
                           "16-20 Excelentes: " + excelentes;
        JOptionPane.showMessageDialog(null, resultado, "Clasificación de Puntajes", JOptionPane.INFORMATION_MESSAGE);
    }
}
