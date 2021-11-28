package br.ufpb.dsc.lab2.dtos;

import br.ufpb.dsc.lab2.entidades.Comentario;
import br.ufpb.dsc.lab2.entidades.Disciplina;
import lombok.Data;

import java.util.List;
@Data
public class DisciplinaComentarioDTO {
    private Long id;
    private String nome;
    private List<Comentario> comentarios;

    public DisciplinaComentarioDTO(Disciplina disciplina) {
        this.id = disciplina.getId();
        this.nome = disciplina.getNome();
        this.comentarios = disciplina.getComentarios();
    }
}