package com.example.damas.model;

public class Board {
    private Piece[][] board;

    public Board() {
        board = new Piece[8][8];  // Cria um tabuleiro 8x8
        setupBoard();  // Inicializa o tabuleiro com peças
    }

    private void setupBoard() {
        for (int row = 0; row < 8; row++) {  // Loop pelas linhas do tabuleiro
            for (int col = 0; col < 8; col++) {  // Loop pelas colunas do tabuleiro
                if ((row + col) % 2 != 0) {  // Verifica se a posição é uma casa válida do tabuleiro
                    if (row < 3) {  // Se a linha for menor que 3, coloca uma peça 'b' (preta)
                        board[row][col] = new Piece('b');
                    } else if (row > 4) {  // Se a linha for maior que 4, coloca uma peça 'w' (branca)
                        board[row][col] = new Piece('w');
                    }
                }
            }
        }
    }

    public Piece getPiece(int row, int col) {
        return board[row][col];  // Retorna a peça na posição especificada
    }

    public void movePiece(int fromRow, int fromCol, int toRow, int toCol) {
        board[toRow][toCol] = board[fromRow][fromCol];  // Move a peça para a nova posição
        board[fromRow][fromCol] = null;  // Remove a peça da posição anterior
        
        // Condições para coroação da peça (tornar-se rei)
        if (toRow == 0 && board[toRow][toCol].getColor() == 'w') {
            board[toRow][toCol].makeKing();
        } else if (toRow == 7 && board[toRow][toCol].getColor() == 'b') {
            board[toRow][toCol].makeKing();
        }
    }

    public void removePiece(int row, int col) {
        board[row][col] = null;  // Remove uma peça do tabuleiro
    }

    public boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol, char currentPlayer) {
        Piece piece = getPiece(fromRow, fromCol);  // Obtém a peça que está sendo movida

        // Verifica se a peça existe e se pertence ao jogador atual
        if (piece == null || piece.getColor() != currentPlayer) {
            return false;
        }

        int rowDiff = toRow - fromRow;  // Diferença entre as linhas
        int colDiff = toCol - fromCol;  // Diferença entre as colunas

        // Se a peça não é um rei
        if (!piece.isKing()) {
            // Verifica movimento válido para peças normais
            if (currentPlayer == 'w' && rowDiff != -1) return false;  // Para o jogador 'w' (branco)
            if (currentPlayer == 'b' && rowDiff != 1) return false;  // Para o jogador 'b' (preto)
            if (Math.abs(colDiff) != 1 || getPiece(toRow, toCol) != null) return false;  // Verifica se é um movimento diagonal válido e se a casa final está vazia

            // Verifica captura de peça
            if (Math.abs(rowDiff) == 2 && Math.abs(colDiff) == 2) {
                int captureRow = fromRow + rowDiff / 2;
                int captureCol = fromCol + colDiff / 2;
                Piece capturedPiece = getPiece(captureRow, captureCol);
                if (capturedPiece == null || capturedPiece.getColor() == currentPlayer) return false;  // Verifica se há uma peça para capturar e se ela pertence ao jogador adversário
                removePiece(captureRow, captureCol);  // Remove a peça capturada do tabuleiro
            }
        }

        return true;  // Movimento válido
    }

    public Piece[][] getBoard() {
        return board;  // Retorna o tabuleiro completo
    }
}
