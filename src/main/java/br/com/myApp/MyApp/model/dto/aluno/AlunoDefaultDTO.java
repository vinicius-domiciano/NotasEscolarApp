package br.com.myApp.MyApp.model.dto.aluno;

import br.com.myApp.MyApp.model.Aluno;
import br.com.myApp.MyApp.model.enumerations.SerieEnum;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class AlunoDefaultDTO {

    public AlunoDefaultDTO() {
    }

    public AlunoDefaultDTO(Aluno aluno) {
        this.idAluno = aluno.getIdAluno();
        this.nome = aluno.getNome();
        this.ra = aluno.getRa();
        this.senha = aluno.getSenha();
        this.serie = aluno.getSerie();
    }

    private UUID idAluno;
    private String nome;
    private String ra;
    private String senha;
    private SerieEnum serie;

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

    public static List<AlunoDefaultDTO> convertAlunoToDTO(List<Aluno> alunos) {
        return alunos.stream().map(AlunoDefaultDTO::new).collect(Collectors.toList());
    }
}
