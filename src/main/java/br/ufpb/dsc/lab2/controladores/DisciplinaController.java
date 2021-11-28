package br.ufpb.dsc.lab2.controladores;

import br.ufpb.dsc.lab2.dtos.*;
import br.ufpb.dsc.lab2.entidades.Disciplina;
import br.ufpb.dsc.lab2.servicos.DisciplinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/disciplinas")
public class DisciplinaController {

    private DisciplinaService disciplinaService;

    @Autowired
    private DisciplinaController(DisciplinaService disciplinaService) {
        this.disciplinaService = disciplinaService;
    }

    @GetMapping
    public ResponseEntity<List<DisciplinaDTO>> getDisciplinas() {
        return new ResponseEntity<>(disciplinaService.getDisciplinas(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Disciplina> getDisciplinaById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(disciplinaService.getDisciplinaById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/likes/{id}")
    public ResponseEntity<DisciplinaLikesDTO> adicionarCurtida(@PathVariable Long id) throws Exception {
        try {
            return new ResponseEntity<>(disciplinaService.adicionarCurtida(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/nota/{id}")
    public ResponseEntity<DisciplinaNotasDTO> adicionarNota(@PathVariable Long id, @RequestBody NotaDTO notaDTO) throws Exception {
        try {
            return new ResponseEntity<>(disciplinaService.adicionarNota(id, notaDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/comentarios/{id}")
    public ResponseEntity<DisciplinaComentarioDTO> adicionarComentario(@PathVariable Long id, @RequestBody ComentarioDTO comentarioDTO) throws Exception {
        try {

            return new ResponseEntity<>(disciplinaService.adicionarComentario(id, comentarioDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/ranking/likes")
    public ResponseEntity<List<Disciplina>> ranquearLikeDisciplinas() {
        return new ResponseEntity<>(disciplinaService.ranquearLikesDisciplina(), HttpStatus.OK);
    }

    @GetMapping("/ranking/notas")
    public ResponseEntity<List<Disciplina>> ranquearNotaDisciplinas() {
        return new ResponseEntity<>(disciplinaService.ranquearNotasDisciplina(), HttpStatus.OK);
    }
}