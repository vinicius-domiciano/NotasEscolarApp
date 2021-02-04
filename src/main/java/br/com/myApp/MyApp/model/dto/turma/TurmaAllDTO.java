package br.com.myApp.MyApp.model.dto.turma;

import br.com.myApp.MyApp.model.Turma;
import br.com.myApp.MyApp.model.dto.aluno.AlunoDefaultDTO;
import br.com.myApp.MyApp.model.dto.diciplina.DiciplinaDefaultDTO;

import java.util.ArrayList;
import java.util.List;

public class TurmaAllDTO extends TurmaDefaultDTO{

    private List<AlunoDefaultDTO> alunos = new ArrayList<>();
    private List<DiciplinaDefaultDTO> diciplinas = new ArrayList<>();

    /*Construtor*/
    public TurmaAllDTO() {}

    public TurmaAllDTO(Turma turma) {
        this.setIdTurma(turma.getIdTurma());
        this.setPeriodo(turma.getPeriodo());
        this.setSerie(turma.getSerie());
        this.setTurma(turma.getTurma());
        this.alunos = AlunoDefaultDTO.convertAlunoToDTO(turma.getAlunos());
        this.diciplinas = DiciplinaDefaultDTO.convertDiciplinaToDTO(turma.getDiciplinas());
    }

    /** GETTER E SETTER **/

    public List<AlunoDefaultDTO> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<AlunoDefaultDTO> alunos) {
        this.alunos = alunos;
    }

    public List<DiciplinaDefaultDTO> getDiciplinas() {
        return diciplinas;
    }

    public void setDiciplinas(List<DiciplinaDefaultDTO> diciplinas) {
        this.diciplinas = diciplinas;
    }
}
