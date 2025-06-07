package com.beesion.ms.test; // <--- Este paquete puede ser diferente para tu clase de prueba, verifica la declaración de paquete de tu archivo.
//      Si no tienes uno, está en el paquete por defecto.

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

// ¡Añade esta línea para importar tu SudokuValidatorService!
import com.beesion.ms.test.service.impl.SudokuValidatorService; // <--- ¡AÑADE ESTA LÍNEA!

public class SudokuValidatorServiceTest {

    private SudokuValidatorService sudokuValidatorService;

    @BeforeEach
    void setUp() {
        sudokuValidatorService = new SudokuValidatorService();
    }

    @Test
    void testValidSudokuBoard() {
        char[][] board1 = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        boolean isValid = sudokuValidatorService.isValidSudoku(board1);
        assertTrue(isValid, "El Ejemplo 1 debería ser válido.");
    }

    @Test
    void testInvalidSudokuBoard() {
        char[][] board2 = {
                {'8', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        boolean isValid = sudokuValidatorService.isValidSudoku(board2);
        assertFalse(isValid, "El Ejemplo 2 debería ser inválido debido al '8' duplicado.");
    }
}