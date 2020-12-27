package br.com.myApp.MyApp.model;

import br.com.myApp.MyApp.model.enumerations.SerieEnum;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "Diciplina")
@Table(name = "tbl_diciplina")
public class Diciplina {

    @Id
    @GeneratedValue(generator = "uuid4")
    @GenericGenerator(name = "UUID", strategy = "uuid4")
    @Type(type = "org.hibernate.type.UUIDCharType")
    @Column(name = "id_diciplina", columnDefinition = "CHAR(36)")
    private UUID idDiciplina;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    private SerieEnum serie;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_materia", foreignKey = @ForeignKey(name = "MATERIA_ID_FK"))
    private Materia materia;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_professor", foreignKey = @ForeignKey(name = "PROFESSOR_ID_FK"))
    private Professor professor;

    @ManyToMany(mappedBy = "diciplinas")
    private List<Turma> turmas = new ArrayList<>();

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

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public List<Turma> getTurmas() {
        return turmas;
    }

    public void setTurmas(List<Turma> turmas) {
        this.turmas = turmas;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
}
