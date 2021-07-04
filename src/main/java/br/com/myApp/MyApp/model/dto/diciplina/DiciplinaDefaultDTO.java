package br.com.myApp.MyApp.model.dto.diciplina;

import br.com.myApp.MyApp.model.Diciplina;
import br.com.myApp.MyApp.model.Materia;
import br.com.myApp.MyApp.model.dto.materia.MateriaDefaultDTO;
import br.com.myApp.MyApp.model.dto.professor.ProfessorIdentifyDTO;
import br.com.myApp.MyApp.model.dto.turma.TurmaIdentifyDTO;
import br.com.myApp.MyApp.model.enumerations.SerieEnum;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class DiciplinaDefaultDTO {

    private UUID idDiciplina;
    private SerieEnum serie;

    @NotNull(message = "é necessario passar a materia")
    private MateriaDefaultDTO materia;

    @NotNull(message = "é necessario passar o professor")
    private ProfessorIdentifyDTO professorIdentify;

    @NotNull(message = "é necessario passar a turma")
    private List<TurmaIdentifyDTO> turmasIdentify;

    public DiciplinaDefaultDTO() {
    }

    public DiciplinaDefaultDTO(Diciplina diciplina) {
        this.idDiciplina = diciplina.getIdDiciplina();
        this.serie = diciplina.getSerie();
        this.materia = new MateriaDefaultDTO(diciplina.getMateria());
        this.professorIdentify = new ProfessorIdentifyDTO(diciplina.getProfessor());
        this.turmasIdentify = TurmaIdentifyDTO.convertTurmaToDTO(diciplina.getTurmas());
    }

    public UUID getIdDiciplina() {
        return idDiciplina;
    }

    public void setIdDiciplina(UUID idDiciplina) {
        this.idDiciplina = idDiciplina;
    }

    public SerieEnum getSerie() {
        return serie;
    }

    public void setSerie(SerieEnum serie) {
        this.serie = serie;
    }

    public MateriaDefaultDTO getMateria() {
        return materia;
    }

    public void setMateria(MateriaDefaultDTO materia) {
        this.materia = materia;
    }

    public ProfessorIdentifyDTO getProfessorIdentify() {
        return professorIdentify;
    }

    public void setProfessorIdentify(ProfessorIdentifyDTO professorIdentify) {
        this.professorIdentify = professorIdentify;
    }

    public List<TurmaIdentifyDTO> getTurmasIdentify() {
        return turmasIdentify;
    }

    public void setTurmasIdentify(List<TurmaIdentifyDTO> turmasIdentify) {
        this.turmasIdentify = turmasIdentify;
    }

    /*Metodos*/

    public static List<DiciplinaDefaultDTO> convertDiciplinaToDTO(List<Diciplina> diciplinas) {
        return diciplinas.stream().map(DiciplinaDefaultDTO::new).collect(Collectors.toList());
    }
}
