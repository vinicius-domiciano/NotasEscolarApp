package br.com.myApp.MyApp.model.dto.aluno;

import br.com.myApp.MyApp.model.Aluno;
import br.com.myApp.MyApp.model.dto.turma.TurmaDefaultDTO;

import java.util.ArrayList;
import java.util.List;

public class AlunoAllDTO extends AlunoDefaultDTO{

    public AlunoAllDTO() {
    }

    public AlunoAllDTO(Aluno aluno) {
        this.setIdAluno(aluno.getIdAluno());
        this.setNome(aluno.getNome());
        this.setRa(aluno.getRa());
        this.setSenha(aluno.getSenha());
        this.notas = NotasDTO.convertNotasToDTO(aluno.getNotas());
        this.turma = new TurmaDefaultDTO(aluno.getTurma());
    }

    private List<NotasDTO> notas = new ArrayList<>();
    private TurmaDefaultDTO turma;

    public List<NotasDTO> getNotas() {
        return notas;
    }

    public void setNotas(List<NotasDTO> notas) {
        this.notas = notas;
    }

}
