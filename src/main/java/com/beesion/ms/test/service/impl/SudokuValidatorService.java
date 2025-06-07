package com.beesion.ms.test.service.impl;

import java.util.HashSet;
import java.util.Set;
import jakarta.enterprise.context.ApplicationScoped;
import com.beesion.ms.test.service.impl.SudokuValidatorService;

@ApplicationScoped
public class SudokuValidatorService {

    private static final int BOARD_SIZE = 9; // Tamaño del tablero de Sudoku
    private static final char EMPTY_CELL = '.'; // Carácter que representa una celda vacía

    /**
     * Valida si un tablero de Sudoku dado es válido según las reglas del Sudoku.
     *
     * @param board El tablero de Sudoku representado por una matriz de caracteres char[9][9].
     * Los números son '1'-'9' y las celdas vacías son '.'.
     * @return true si el tablero es un Sudoku válido, false en caso contrario.
     */
    public boolean isValidSudoku(char[][] board) {
        // Un tablero de Sudoku válido siempre es de 9x9
        if (board == null || board.length != BOARD_SIZE || board[0].length != BOARD_SIZE) {
            return false; // El tamaño del tablero no es 9x9
        }

        // 1. Validar filas
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (!isValidRow(board, i)) {
                return false;
            }
        }

        // 2. Validar columnas
        for (int j = 0; j < BOARD_SIZE; j++) {
            if (!isValidColumn(board, j)) {
                return false;
            }
        }

        // 3. Validar subcuadrículas de 3x3
        // Recorrer las subcuadrículas (de 0,0 a 2,2; de 0,3 a 2,5, etc.)
        for (int i = 0; i < BOARD_SIZE; i += 3) {
            for (int j = 0; j < BOARD_SIZE; j += 3) {
                if (!isValidSubgrid(board, i, j)) {
                    return false;
                }
            }
        }

        return true; // Si todas las validaciones pasan, el Sudoku es válido
    }

    /**
     * Valida si una fila específica del tablero contiene números válidos sin duplicados.
     *
     * @param board El tablero de Sudoku.
     * @param row El índice de la fila a validar.
     * @return true si la fila es válida, false en caso contrario.
     */
    private boolean isValidRow(char[][] board, int row) {
        Set<Character> seen = new HashSet<>(); // Conjunto para almacenar los números vistos en esta fila
        for (int j = 0; j < BOARD_SIZE; j++) {
            char current = board[row][j];
            if (current != EMPTY_CELL) {
                // Si ya hemos visto este número en esta fila, es un duplicado
                if (seen.contains(current)) {
                    return false;
                }
                // Si es un número válido (1-9), lo añadimos al conjunto
                if (current >= '1' && current <= '9') {
                    seen.add(current);
                } else {
                    return false; // Carácter inválido encontrado en la celda
                }
            }
        }
        return true;
    }

    /**
     * Valida si una columna específica del tablero contiene números válidos sin duplicados.
     *
     * @param board El tablero de Sudoku.
     * @param col El índice de la columna a validar.
     * @return true si la columna es válida, false en caso contrario.
     */
    private boolean isValidColumn(char[][] board, int col) {
        Set<Character> seen = new HashSet<>(); // Conjunto para almacenar los números vistos en esta columna
        for (int i = 0; i < BOARD_SIZE; i++) {
            char current = board[i][col];
            if (current != EMPTY_CELL) {
                // Si ya hemos visto este número en esta columna, es un duplicado
                if (seen.contains(current)) {
                    return false;
                }
                // Si es un número válido (1-9), lo añadimos al conjunto
                if (current >= '1' && current <= '9') {
                    seen.add(current);
                } else {
                    return false; // Carácter inválido encontrado en la celda
                }
            }
        }
        return true;
    }

    /**
     * Valida si una subcuadrícula de 3x3 del tablero contiene números válidos sin duplicados.
     *
     * @param board El tablero de Sudoku.
     * @param startRow El índice de la fila inicial de la subcuadrícula.
     * @param startCol El índice de la columna inicial de la subcuadrícula.
     * @return true si la subcuadrícula es válida, false en caso contrario.
     */
    private boolean isValidSubgrid(char[][] board, int startRow, int startCol) {
        Set<Character> seen = new HashSet<>(); // Conjunto para almacenar los números vistos en esta subcuadrícula
        // Recorrer las celdas dentro de la subcuadrícula 3x3
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                char current = board[startRow + i][startCol + j];
                if (current != EMPTY_CELL) {
                    // Si ya hemos visto este número en esta subcuadrícula, es un duplicado
                    if (seen.contains(current)) {
                        return false;
                    }
                    // Si es un número válido (1-9), lo añadimos al conjunto
                    if (current >= '1' && current <= '9') {
                        seen.add(current);
                    } else {
                        return false; // Carácter inválido encontrado en la celda
                    }
                }
            }
        }
        return true;
    }
}