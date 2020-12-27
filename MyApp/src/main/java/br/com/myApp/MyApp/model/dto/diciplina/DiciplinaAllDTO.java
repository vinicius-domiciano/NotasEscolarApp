package br.com.myApp.MyApp.model.dto.diciplina;

import br.com.myApp.MyApp.model.Diciplina;
import br.com.myApp.MyApp.model.dto.materia.MateriaDefaultDTO;
import br.com.myApp.MyApp.model.dto.professor.ProfessorDefaultDTO;
import br.com.myApp.MyApp.model.dto.turma.TurmaDefaultDTO;

import java.util.List;

public class DiciplinaAllDTO extends DiciplinaDefaultDTO{

    private ProfessorDefaultDTO professor;
    private MateriaDefaultDTO materia;
    private List<TurmaDefaultDTO> turmas;

    /*Construtor*/

    public DiciplinaAllDTO() {
    }

    public DiciplinaAllDTO(Diciplina diciplina) {
        this.setIdDiciplina(diciplina.getIdDiciplina());
        this.setSerie(diciplina.getSerie());
        this.materia = new MateriaDefaultDTO(diciplina.getMateria());
        this.professor = new ProfessorDefaultDTO(diciplina.getProfessor());
        this.turmas = TurmaDefaultDTO.convertTurmaToDTO(diciplina.getTurmas());
    }

    /** GETTER E SETTER **/

    public ProfessorDefaultDTO getProfessor() {
        return professor;
    }

    public void setProfessor(ProfessorDefaultDTO professor) {
        this.professor = professor;
    }

    public MateriaDefaultDTO getMateria() {
        return materia;
    }

    public void setMateria(MateriaDefaultDTO materia) {
        this.materia = materia;
    }

    public List<TurmaDefaultDTO> getTurmas() {
        return turmas;
    }

    public void setTurmas(List<TurmaDefaultDTO> turmas) {
        this.turmas = turmas;
    }
}
