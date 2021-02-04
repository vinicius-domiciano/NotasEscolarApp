package br.com.myApp.MyApp.model.dto.professor;

import br.com.myApp.MyApp.model.Professor;

import java.util.UUID;

public class ProfessorIdentifyDTO {

    public ProfessorIdentifyDTO() {
    }

    public ProfessorIdentifyDTO(Professor professor) {
        this.idProfessor = professor.getIdProfessor();
        this.nome = professor.getNome();
    }

    private UUID idProfessor;
    private String nome;

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
}
