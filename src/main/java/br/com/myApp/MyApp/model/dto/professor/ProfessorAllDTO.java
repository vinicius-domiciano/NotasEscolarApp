package br.com.myApp.MyApp.model.dto.professor;

import br.com.myApp.MyApp.model.Professor;
import br.com.myApp.MyApp.model.dto.diciplina.DiciplinaDefaultDTO;
import br.com.myApp.MyApp.model.dto.materia.MateriaDefaultDTO;

import java.util.ArrayList;
import java.util.List;

public class ProfessorAllDTO extends ProfessorDefaultDTO{

    private List<MateriaDefaultDTO> materias = new ArrayList<>();
    private List<DiciplinaDefaultDTO> diciplinasAplicadas = new ArrayList<>();

    /*Contrutor*/

    public ProfessorAllDTO() {
    }

    public ProfessorAllDTO(Professor professor) {
        this.setIdProfessor(professor.getIdProfessor());
        this.setEmail(professor.getEmail());
        this.setNome(professor.getNome());
        this.setSenha(professor.getSenha());
        this.materias = MateriaDefaultDTO.convertMateriaToDTO(professor.getMaterias());
        this.diciplinasAplicadas = DiciplinaDefaultDTO.convertDiciplinaToDTO(professor.getDiciplinas());
    }

    /** GETTER E SETTER **/

    public List<MateriaDefaultDTO> getMaterias() {
        return materias;
    }

    public void setMaterias(List<MateriaDefaultDTO> materias) {
        this.materias = materias;
    }

    public List<DiciplinaDefaultDTO> getDiciplinasAplicadas() {
        return diciplinasAplicadas;
    }

    public void setDiciplinasAplicadas(List<DiciplinaDefaultDTO> diciplinasAplicadas) {
        this.diciplinasAplicadas = diciplinasAplicadas;
    }
}
