package br.ufpb.dsc.lab2.servicos;

import br.ufpb.dsc.lab2.daos.ComentarioDAO;
import br.ufpb.dsc.lab2.daos.DisciplinaDAO;
import br.ufpb.dsc.lab2.dtos.*;
import br.ufpb.dsc.lab2.entidades.Comentario;
import br.ufpb.dsc.lab2.entidades.Disciplina;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DisciplinaService {

    private DisciplinaDAO repositorioDeDisciplinas;
    private ComentarioDAO repositorioDeComentarios;

    @Autowired
    public DisciplinaService(DisciplinaDAO repositorioDeDisciplinas, ComentarioDAO repositorioDeComentarios) {
        this.repositorioDeDisciplinas = repositorioDeDisciplinas;
        this.repositorioDeComentarios = repositorioDeComentarios;
    }

    public List<DisciplinaDTO> getDisciplinas(){
        List<Disciplina> disciplinas = repositorioDeDisciplinas.findAll();
        List<DisciplinaDTO> listaDisciplinas = disciplinas.stream().map(DisciplinaDTO::new).collect(Collectors.toList());
        return listaDisciplinas;
    }

    public Disciplina getDisciplinaById(Long id) throws Exception{
        Optional<Disciplina> disciplinaOptional = repositorioDeDisciplinas.findById(id);
        Disciplina disciplinaRecuperada = disciplinaOptional.get();

        if (disciplinaOptional.isEmpty()){
            throw new Exception();
        }

        return disciplinaRecuperada;
    }

    public DisciplinaLikesDTO adicionarCurtida(Long id) throws Exception {
        Disciplina disciplinaRecuperada = getDisciplinaById(id);

        int curtidas = disciplinaRecuperada.getCurtidas();
        disciplinaRecuperada.setCurtidas(curtidas+1);
        DisciplinaLikesDTO disciplinaLikesDTO = new DisciplinaLikesDTO(disciplinaRecuperada);
        repositorioDeDisciplinas.save(disciplinaRecuperada);

        return disciplinaLikesDTO;
    }

    public DisciplinaNotasDTO adicionarNota(Long id, NotaDTO notaDTO) throws Exception{

        Disciplina disciplina = getDisciplinaById(id);

        if (disciplina.getNota() == 0) disciplina.setNota(notaDTO.getNota());
        else disciplina.setNota((disciplina.getNota()+notaDTO.getNota())/2);
        repositorioDeDisciplinas.save(disciplina);

        return new DisciplinaNotasDTO(disciplina);
    }

    public DisciplinaComentarioDTO adicionarComentario(Long id, ComentarioDTO comentarioDTO) throws Exception{

        Disciplina disciplina = getDisciplinaById(id);

        Comentario comentario = new Comentario(comentarioDTO.getTexto());
        comentario.setDisciplina(disciplina);
        repositorioDeComentarios.save(comentario);
        disciplina.getComentarios().add(comentario);

        DisciplinaLikesDTO disciplinaLikesDTO = new DisciplinaLikesDTO(disciplina);
        repositorioDeDisciplinas.save(disciplina);

        return new DisciplinaComentarioDTO(disciplina);
    }

    public List<Disciplina> ranquearNotasDisciplina() {
        return this.repositorioDeDisciplinas.findByOrderByNota();
    }

    public List<Disciplina> ranquearLikesDisciplina() {
        return repositorioDeDisciplinas.findAll(Sort.by(Sort.Direction.DESC, "curtidas"));
    }

}