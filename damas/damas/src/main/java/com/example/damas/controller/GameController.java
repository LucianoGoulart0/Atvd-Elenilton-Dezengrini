package com.example.damas.controller;

import com.example.damas.model.Board;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GameController {
    private Board board = new Board();  // Instância do tabuleiro
    private char currentPlayer = 'w';   // Define o jogador atual como 'w' (branco)

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("board", board.getBoard());  // Adiciona o tabuleiro ao modelo
        model.addAttribute("currentPlayer", currentPlayer);  // Adiciona o jogador atual ao modelo
        return "index";  // Retorna a página HTML chamada "index"
    }

    @PostMapping("/move")
    public String move(@RequestParam int fromRow, @RequestParam int fromCol, @RequestParam int toRow, @RequestParam int toCol, Model model) {
        if (board.isValidMove(fromRow, fromCol, toRow, toCol, currentPlayer)) {  // Verifica se o movimento é válido
            board.movePiece(fromRow, fromCol, toRow, toCol);  // Move a peça no tabuleiro
            currentPlayer = (currentPlayer == 'w' ? 'b' : 'w');  // Alterna o jogador atual
        }
        model.addAttribute("board", board.getBoard());  // Atualiza o tabuleiro no modelo
        model.addAttribute("currentPlayer", currentPlayer);  // Atualiza o jogador atual no modelo
        return "index";  // Retorna a página HTML chamada "index"
    }
}
