package br.ufpb.dsc.lab2.dtos;

import br.ufpb.dsc.lab2.entidades.Disciplina;
import lombok.Data;

@Data
public class DisciplinaLikesDTO {
    private Long id;
    private String nome;
    private int curtidas;

    public DisciplinaLikesDTO(Disciplina disciplina) {
        this.id = disciplina.getId();
        this.nome = disciplina.getNome();
        this.curtidas = disciplina.getCurtidas();
    }
}