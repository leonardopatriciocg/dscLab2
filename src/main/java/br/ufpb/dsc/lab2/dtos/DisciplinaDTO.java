package br.ufpb.dsc.lab2.dtos;

import br.ufpb.dsc.lab2.entidades.Disciplina;
import lombok.Data;

@Data
public class DisciplinaDTO {
    private Long id;
    private String nome;

    public DisciplinaDTO(Disciplina disciplina) {
        this.id = disciplina.getId();
        this.nome = disciplina.getNome();
    }
}