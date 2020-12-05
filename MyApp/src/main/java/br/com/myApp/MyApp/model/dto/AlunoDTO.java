package br.com.myApp.MyApp.model.dto;

import br.com.myApp.MyApp.model.Aluno;
import br.com.myApp.MyApp.model.enumerations.SerieEnum;
import br.com.myApp.MyApp.model.enumerations.TurmaEnum;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class AlunoDTO {

    public AlunoDTO() {
    }

    public AlunoDTO(Aluno aluno) {
        this.idAluno = aluno.getIdAluno();
        this.nome = aluno.getNome();
        this.ra = aluno.getRa();
        this.senha = aluno.getSenha();
        this.serie = aluno.getSerie();
        this.turma = aluno.getTurma();
        this.notas = NotasDTO.convertNotasToDTO(aluno.getNotas());
    }

    private UUID idAluno;
    private String nome;
    private String ra;
    private String senha;
    private SerieEnum serie;
    private TurmaEnum turma;
    private List<NotasDTO> notas;

    public static List<AlunoDTO> convertAlunoToDTO(List<Aluno> alunos) {
        return alunos.stream().map(AlunoDTO::new).collect(Collectors.toList());
    }

    public UUID getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(UUID idAluno) {
        this.idAluno = idAluno;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public SerieEnum getSerie() {
        return serie;
    }

    public void setSerie(SerieEnum serie) {
        this.serie = serie;
    }

    public TurmaEnum getTurma() {
        return turma;
    }

    public void setTurma(TurmaEnum turma) {
        this.turma = turma;
    }

    public List<NotasDTO> getNotas() {
        return notas;
    }

    public void setNotas(List<NotasDTO> notas) {
        this.notas = notas;
    }
}
