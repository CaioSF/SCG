package com.example.scg.resource;


import com.example.scg.controller.JogoController;
import com.example.scg.dominio.Jogo;
import com.example.scg.repository.JogoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/jogo")
public class JogoResource {
    @Autowired
    private JogoRepository jogoRepository;


    @GetMapping(value = "/jogos")
    public List<Jogo> list() {
        return jogoRepository.findAll();
    }

    @PostMapping("/create")
    public ResponseEntity<Jogo> create(@RequestBody Jogo jogo) {
        JogoController jogoController = new JogoController();
        if (!jogoController.isJogoValido(jogo)) {
            return new ResponseEntity("Dados do Jogo inválidos", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        jogo = jogoRepository.save(jogo);
        return new ResponseEntity(jogo, HttpStatus.OK);
    }

    @PutMapping("/edit")
    public ResponseEntity<Jogo> editar(@RequestBody Jogo jogo) {
        JogoController jogoController = new JogoController();
        if (!jogoController.isJogoValido(jogo)) {
            return new ResponseEntity("Nome do jogo inválido", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        jogo = jogoRepository.save(jogo);
        return new ResponseEntity(jogo, HttpStatus.OK);

    }


    @GetMapping("getById/{id}")
    public Optional<Jogo> getById(@PathVariable(value = "id") int id) {
        return jogoRepository.findById(id);
    }

    @GetMapping("/total")
    public Long getTotal() {
        return jogoRepository.count();
    }

    @DeleteMapping("/remove/{id}")
    public Jogo remove(@PathVariable(value = "id") int id) {
        Optional<Jogo> optionalJogo = jogoRepository.findById(id);
        jogoRepository.delete(optionalJogo.get());
        return optionalJogo.get();
    }

}
