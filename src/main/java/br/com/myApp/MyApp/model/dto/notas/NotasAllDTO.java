package br.com.myApp.MyApp.model.dto.notas;

import br.com.myApp.MyApp.model.Notas;
import br.com.myApp.MyApp.model.dto.aluno.AlunoDefaultDTO;
import br.com.myApp.MyApp.model.dto.pontos.PontosDefaultDTO;

import java.util.ArrayList;
import java.util.List;

public class NotasAllDTO extends NotasDefaultDTO{

    private AlunoDefaultDTO aluno;
    private List<PontosDefaultDTO> pontos = new ArrayList<>();

    /*Construtor*/

    public NotasAllDTO() {
    }

    public NotasAllDTO(Notas nota) {
        this.setIdNota(nota.getIdNota());
        this.setAno(nota.getAno());
        this.setBimestre(nota.getBimestre());
        this.aluno = new AlunoDefaultDTO(nota.getAluno());
        this.pontos = PontosDefaultDTO.convertPontosToDTO(nota.getPontos());
    }

    /** GETTER E SETTER **/

    public AlunoDefaultDTO getAluno() {
        return aluno;
    }

    public void setAluno(AlunoDefaultDTO aluno) {
        this.aluno = aluno;
    }

    public List<PontosDefaultDTO> getPontos() {
        return pontos;
    }

    public void setPontos(List<PontosDefaultDTO> pontos) {
        this.pontos = pontos;
    }
}
