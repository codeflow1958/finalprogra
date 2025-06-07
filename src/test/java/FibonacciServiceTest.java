package com.beesion.ms.test.service.impl; // Ajusta el paquete si tus tests están en otro lugar

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Clase de pruebas unitarias para FibonacciService.
 * Asegura que la generación de la secuencia de Fibonacci funcione correctamente
 * para varios casos de uso.
 */
public class FibonacciServiceTest {

    private FibonacciService fibonacciService;

    @BeforeEach
    void setUp() {
        fibonacciService = new FibonacciService();
    }

    /**
     * Prueba el caso de ejemplo 1: fibonacci([0, 1], 9).
     * Debería generar la secuencia estándar de Fibonacci.
     */
    @Test
    void testFibonacciExample1() {
        int[] initialSignature = {0, 1};
        int n = 9;
        List<Long> expected = Arrays.asList(0L, 1L, 1L, 2L, 3L, 5L, 8L, 13L, 21L);
        List<Long> actual = fibonacciService.generateFibonacciSequence(initialSignature, n);
        assertEquals(expected, actual, "La secuencia de Fibonacci para [0, 1], 9 debería ser correcta.");
    }

    /**
     * Prueba el caso de ejemplo 2: fibonacci([2, 3], 5).
     * Debería generar una secuencia de Fibonacci con números iniciales diferentes.
     */
    @Test
    void testFibonacciExample2() {
        int[] initialSignature = {2, 3};
        int n = 5;
        List<Long> expected = Arrays.asList(2L, 3L, 5L, 8L, 13L);
        List<Long> actual = fibonacciService.generateFibonacciSequence(initialSignature, n);
        assertEquals(expected, actual, "La secuencia de Fibonacci para [2, 3], 5 debería ser correcta.");
    }

    /**
     * Prueba el caso cuando n es 0.
     * Debería devolver una lista vacía.
     */
    @Test
    void testFibonacciNIsZero() {
        int[] initialSignature = {0, 1};
        int n = 0;
        List<Long> actual = fibonacciService.generateFibonacciSequence(initialSignature, n);
        assertTrue(actual.isEmpty(), "Cuando n es 0, la secuencia debe ser vacía.");
    }

    /**
     * Prueba el caso cuando n es 1.
     * Debería devolver solo el primer elemento de la firma inicial.
     */
    @Test
    void testFibonacciNIsOne() {
        int[] initialSignature = {5, 10};
        int n = 1;
        List<Long> expected = Arrays.asList(5L);
        List<Long> actual = fibonacciService.generateFibonacciSequence(initialSignature, n);
        assertEquals(expected, actual, "Cuando n es 1, la secuencia debe contener solo el primer elemento.");
    }

    /**
     * Prueba el caso cuando la firma inicial es nula.
     * Debería lanzar una IllegalArgumentException.
     */
    @Test
    void testFibonacciNullInitialSignature() {
        int[] initialSignature = null;
        int n = 5;
        assertThrows(IllegalArgumentException.class, () -> {
            fibonacciService.generateFibonacciSequence(initialSignature, n);
        }, "Debería lanzar IllegalArgumentException si la firma inicial es nula.");
    }

    /**
     * Prueba el caso cuando la firma inicial no tiene 2 elementos.
     * Debería lanzar una IllegalArgumentException.
     */
    @Test
    void testFibonacciInvalidInitialSignatureLength() {
        int[] initialSignature = {0}; // Solo un elemento
        int n = 5;
        assertThrows(IllegalArgumentException.class, () -> {
            fibonacciService.generateFibonacciSequence(initialSignature, n);
        }, "Debería lanzar IllegalArgumentException si la firma inicial no tiene 2 elementos.");

        int[] initialSignature2 = {0, 1, 2}; // Tres elementos
        assertThrows(IllegalArgumentException.class, () -> {
            fibonacciService.generateFibonacciSequence(initialSignature2, n);
        }, "Debería lanzar IllegalArgumentException si la firma inicial no tiene 2 elementos.");
    }

    /**
     * Prueba el caso cuando n es negativo.
     * Debería lanzar una IllegalArgumentException.
     */
    @Test
    void testFibonacciNegativeN() {
        int[] initialSignature = {0, 1};
        int n = -5;
        assertThrows(IllegalArgumentException.class, () -> {
            fibonacciService.generateFibonacciSequence(initialSignature, n);
        }, "Debería lanzar IllegalArgumentException si n es negativo.");
    }

    /**
     * Prueba con una secuencia larga para verificar el tipo Long.
     */
    @Test
    void testFibonacciLargeSequence() {
        int[] initialSignature = {0, 1};
        int n = 50; // Un número suficientemente grande para que los valores excedan int
        List<Long> actual = fibonacciService.generateFibonacciSequence(initialSignature, n);
        assertEquals(n, actual.size(), "El tamaño de la secuencia debe ser n.");
        // Opcional: verificar un valor específico al final si se conoce
        // assertEquals(12586269025L, actual.get(45), "El valor en la posición 45 debe ser correcto.");
        // (El valor 12586269025L es el 46º número de Fibonacci comenzando con 0,1)
    }
}