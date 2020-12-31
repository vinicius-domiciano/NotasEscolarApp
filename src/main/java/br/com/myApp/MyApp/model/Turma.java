package br.com.myApp.MyApp.model;

import br.com.myApp.MyApp.model.enumerations.PeriodoEnum;
import br.com.myApp.MyApp.model.enumerations.SerieEnum;
import br.com.myApp.MyApp.model.enumerations.TurmaEnum;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "Turma")
@Table(name = "tbl_turma")
public class Turma {

    @Id
    @GeneratedValue(generator = "uuid4")
    @GenericGenerator(name = "UUID", strategy = "uuid4")
    @Type(type = "org.hibernate.type.UUIDCharType")
    @Column(name = "id_turma", columnDefinition = "CHAR(36)")
    private UUID idTurma;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    private TurmaEnum turma;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    private SerieEnum serie;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    private PeriodoEnum periodo;

    //Relacionando com tabela Aluno
    @OneToMany(mappedBy = "turma", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Aluno> alunos = new ArrayList<>();

    //Relacionando com tabela diciplina
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Diciplina> diciplinas = new ArrayList<>();



    //Getter e Setters

    public UUID getIdTurma() {
        return idTurma;
    }

    public void setIdTurma(UUID idTurma) {
        this.idTurma = idTurma;
    }

    public TurmaEnum getTurma() {
        return turma;
    }

    public void setTurma(TurmaEnum turma) {
        this.turma = turma;
    }

    public SerieEnum getSerie() {
        return serie;
    }

    public void setSerie(SerieEnum serie) {
        this.serie = serie;
    }

    public PeriodoEnum getPeriodo() {
        return periodo;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    public void setPeriodo(PeriodoEnum periodo) {
        this.periodo = periodo;
    }

    public List<Diciplina> getDiciplinas() {
        return diciplinas;
    }

    public void setDiciplinas(List<Diciplina> diciplinas) {
        this.diciplinas = diciplinas;
    }

    //Metodos de vinculo de tabela

    public void addAluno(Aluno aluno) {
        alunos.add(aluno);
        aluno.setTurma(this);
    }

    public void removeAluno(Aluno aluno){
        alunos.remove(aluno);
        aluno.setTurma(null);
    }

    public void addDiciplina(Diciplina diciplina) {
        diciplinas.add(diciplina);
        diciplina.getTurmas().add(this);
    }

    public void removeDiciplina(Diciplina diciplina) {
        diciplinas.remove(diciplina);
        diciplina.getTurmas().remove(this);
    }

}
