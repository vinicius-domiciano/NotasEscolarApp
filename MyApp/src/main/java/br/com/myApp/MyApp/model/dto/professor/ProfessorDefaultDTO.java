package br.com.myApp.MyApp.model.dto.professor;

import br.com.myApp.MyApp.model.Professor;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class ProfessorDefaultDTO {

    private UUID idProfessor;
    private String nome;
    private String email;
    private String senha;

    /*Construtor*/

    public ProfessorDefaultDTO() {
    }

    public ProfessorDefaultDTO(Professor professor) {
        this.idProfessor = professor.getIdProfessor();
        this.nome = professor.getNome();
        this.email = professor.getEmail();
        this.senha = professor.getSenha();
    }

    /** GETTER E SETTER **/

    public UUID getIdProfessor() {
        return idProfessor;
    }

    public void setIdProfessor(UUID idProfessor) {
        this.idProfessor = idProfessor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    /*Metodos*/

    public static List<ProfessorDefaultDTO> convertProfessorToDTO(List<Professor> professores) {
        return professores.stream().map(ProfessorDefaultDTO::new).collect(Collectors.toList());
    }

}
