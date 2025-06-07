package com.beesion.ms.test.service.impl;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

/**
 * Servicio para generar la secuencia de Fibonacci.
 * Aplica el Principio de Responsabilidad Única (SRP) al centrarse solo en la generación de la secuencia.
 */
@ApplicationScoped
public class FibonacciService {

    /**
     * Genera los primeros 'n' elementos de la secuencia de Fibonacci,
     * comenzando con dos números iniciales dados.
     *
     * @param initialSignature Un arreglo de 2 elementos que contiene los números iniciales (ej. [0, 1]).
     * Se espera que este arreglo no sea nulo y contenga exactamente 2 elementos.
     * @param n                Un entero no negativo que representa la cantidad de elementos de la secuencia a generar.
     * Si n es 0, se devuelve una lista vacía.
     * @return Una lista de enteros que representa los primeros 'n' elementos de la secuencia de Fibonacci.
     * @throws IllegalArgumentException Si initialSignature es nulo, no tiene 2 elementos, o si n es negativo.
     */
    public List<Long> generateFibonacciSequence(int[] initialSignature, int n) {
        // Validación de entradas aplicando el principio Fail-Fast
        if (initialSignature == null || initialSignature.length != 2) {
            throw new IllegalArgumentException("La firma inicial debe ser un arreglo de 2 elementos.");
        }
        if (n < 0) {
            throw new IllegalArgumentException("El número de elementos (n) no puede ser negativo.");
        }

        // Si n es 0, se devuelve una lista vacía como se especifica.
        if (n == 0) {
            return new ArrayList<>();
        }

        List<Long> fibonacciSequence = new ArrayList<>();

        long a = initialSignature[0];
        long b = initialSignature[1];

        // Manejo del caso base para n=1, donde solo se devuelve el primer elemento.
        if (n == 1) {
            fibonacciSequence.add(a);
            return fibonacciSequence;
        }

        // Añadir los dos números iniciales
        fibonacciSequence.add(a);
        fibonacciSequence.add(b);


        for (int i = 2; i < n; i++) {
            long next = a + b;
            fibonacciSequence.add(next);
            a = b; // Actualizar 'a' al valor anterior de 'b'
            b = next; // Actualizar 'b' al nuevo número generado
        }

        return fibonacciSequence;
    }
}