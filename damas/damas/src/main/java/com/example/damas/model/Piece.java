package com.example.damas.model;

public class Piece {
    private boolean isKing;  // Indica se a peça é um rei
    private char color;      // Cor da peça ('b' para preto, 'w' para branco)

    public Piece(char color) {
        this.color = color;   // Define a cor da peça
        this.isKing = false;  // Inicialmente, a peça não é um rei
    }

    public boolean isKing() {
        return isKing;  // Retorna se a peça é um rei
    }

    public void makeKing() {
        this.isKing = true;  // Torna a peça um rei
    }

    public char getColor() {
        return color;  // Retorna a cor da peça
    }

    @Override
    public String toString() {
        // Representação da peça como uma string
        return color == 'b' ? (isKing ? "B" : "b") : (isKing ? "W" : "w");
        // Se a cor for 'b' (preto), retorna "B" se for rei, ou "b" se não for.
        // Se a cor for 'w' (branco), retorna "W" se for rei, ou "w" se não for.
    }
}
