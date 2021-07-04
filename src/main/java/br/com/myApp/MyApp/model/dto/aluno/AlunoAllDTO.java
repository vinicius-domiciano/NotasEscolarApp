package br.com.myApp.MyApp.model.dto.aluno;

import br.com.myApp.MyApp.model.Aluno;
import br.com.myApp.MyApp.model.dto.notas.NotasDefaultDTO;
import br.com.myApp.MyApp.model.dto.turma.TurmaDefaultDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AlunoAllDTO extends AlunoDefaultDTO{

    public AlunoAllDTO() {
    }

    public AlunoAllDTO(Aluno aluno) {
        this.setIdAluno(aluno.getIdAluno());
        this.setNome(aluno.getNome());
        this.setRa(aluno.getRa());
        this.setSenha(aluno.getSenha());
        this.setSerie(aluno.getSerie());
        this.notas = NotasDefaultDTO.convertNotasToDTO(aluno.getNotas());
        if (Boolean.FALSE.equals(Objects.isNull(aluno.getTurma())))
            this.turma = new TurmaDefaultDTO(aluno.getTurma());
    }

    private List<NotasDefaultDTO> notas = new ArrayList<>();
    private TurmaDefaultDTO turma;

    public List<NotasDefaultDTO> getNotas() {
        return notas;
    }

    public void setNotas(List<NotasDefaultDTO> notas) {
        this.notas = notas;
    }

    public TurmaDefaultDTO getTurma() {
        return turma;
    }

    public void setTurma(TurmaDefaultDTO turma) {
        this.turma = turma;
    }
}
