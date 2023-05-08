package com.example.scg.controller;

import com.example.scg.dominio.Jogo;



public class JogoController {

    public boolean isJogoValido(Jogo jogo) {
        return isNomeValido(jogo);
    }

    private boolean isNomeValido(Jogo jogo) {
        if ((jogo.getNome().isEmpty()) || jogo.getNome().length() < 2) {
            return false;
        }

        return true;
    }




}
